package com.segi.uhomecp.medicaltrans.utils;

import java.util.Random;

import com.segi.uhomecp.redis.cluster.SegiRedisCluster;

public class RedisLock {

	/** 加锁标志 */
	public static final String LOCKED = "TRUE";
	/** 毫秒与毫微秒的换算单位 1毫秒 = 1000000毫微秒 */
	public static final long MILLI_NANO_CONVERSION = 1000 * 1000L;
	/** 默认超时时间（毫秒） */
	public static final long DEFAULT_TIME_OUT = 1000;
	public static final Random RANDOM = new Random();
	/** 锁的超时时间（秒），过期删除 */
	public static final int EXPIRE = 3 * 60;
	private SegiRedisCluster cluster;
	private String key;
	// 锁状态标志
	private boolean locked = false;

	/**
	 * This creates a RedisLock
	 * @param key key
	 * @param shardedJedisPool 数据源
	 */
	public RedisLock(String key, SegiRedisCluster cluster) {
		this.key = key + "_lock";
		this.cluster = cluster;
	}

	/**
	 * 加锁
	 * 应该以：
	 * lock();
	 * try {
	 * 		doSomething();
	 * } finally {
	 * 		unlock()；
	 * }
	 * 的方式调用 
	 * @param timeout 超时时间
	 * @return 成功或失败标志
	 */
	public boolean lock(long timeout) {
		long nano = System.nanoTime();
		timeout *= MILLI_NANO_CONVERSION;
		try {
			while ((System.nanoTime() - nano) < timeout) {
				if (this.cluster.setnx(this.key, LOCKED) == 1) {
					this.cluster.expire(this.key, EXPIRE);
					this.locked = true;
					return this.locked;
				}
				// 短暂休眠，避免出现活锁
				Thread.sleep(3, RANDOM.nextInt(500));
			}
		} catch (Exception e) {
			throw new RuntimeException("Locking error", e);
		}
		return false;
	}

	/**
	 * 加锁
	 * 应该以：
	 * lock();
	 * try {
	 * 		doSomething();
	 * } finally {
	 * 		unlock()；
	 * }
	 * 的方式调用
	 * @param timeout 超时时间
	 * @param expire 锁的超时时间（秒），过期删除
	 * @return 成功或失败标志
	 */
	public boolean lock(long timeout, int expire) {
		long nano = System.nanoTime();
		timeout *= MILLI_NANO_CONVERSION;
		try {
			while ((System.nanoTime() - nano) < timeout) {
				if (this.cluster.setnx(this.key, LOCKED) == 1) {
					this.cluster.expire(this.key, expire);
					this.locked = true;
					return this.locked;
				}
				// 短暂休眠，避免出现活锁
				Thread.sleep(3, RANDOM.nextInt(500));
			}
		} catch (Exception e) {
			throw new RuntimeException("Locking error", e);
		}
		return false;
	}

	/**
	 * 加锁
	 * 应该以：
	 * lock();
	 * try {
	 * 		doSomething();
	 * } finally {
	 * 		unlock()；
	 * }
	 * 的方式调用
	 * @return 成功或失败标志
	 */
	public boolean lock() {
		return lock(DEFAULT_TIME_OUT);
	}

	/**
	 * 解锁
	 * 无论是否加锁成功，都需要调用unlock
	 * 应该以：
	 * lock();
	 * try {
	 * 		doSomething();
	 * } finally {
	 * 		unlock()；
	 * }
	 * 的方式调用
	 */
	public void unlock() {
		if (this.locked) {
			System.out.println("==============to Unlock================"+this.key);
			this.cluster.del(this.key);
		}
	}
}
