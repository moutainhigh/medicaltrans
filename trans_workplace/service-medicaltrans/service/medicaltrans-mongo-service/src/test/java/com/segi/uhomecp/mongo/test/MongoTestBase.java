package com.segi.uhomecp.mongo.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.segi.uhomecp.medicaltrans.mongodb.track.dao.TrackOperations;
import com.segi.uhomecp.medicaltrans.mongodb.track.entity.TaskTrack;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MongoTestBase {
	
	@Resource
	private TrackOperations taskOperations;
	
	
	@Test
	public void test() {
//		TaskTrack task = new TaskTrack();
//		task.setId("10002");
//		task.setOrganId(1001);
//		task.setTaskId(10001);
//		taskOperations.save(task);
//		
		
//		Item item = new Item();
//		
//		item.setCreateDate(new Date());
//		item.setMessage("测试");
//		item.setUserId(123456l);
//		item.setUserName("张三");
//		
//		taskOperations.push(10002, item);
	}
	
	@Test
	public void find() {
		TaskTrack task = taskOperations.findById(10002);
		System.out.println(task);
	}
	
}
