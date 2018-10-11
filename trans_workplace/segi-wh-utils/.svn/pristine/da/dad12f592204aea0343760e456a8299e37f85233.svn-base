package com.segi.uhomecp.wh.common.utils;

import java.util.Comparator;

import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.interfaces.InvocationHandler;

public class ListComparator<K,O> implements Comparator<O> {
	/***
	 * 对哪个列进行排序
	 */
	private InvocationHandler<K,O> handler;
	
	private String sort;

	public ListComparator(InvocationHandler<K,O> handler, String sort) {
		super();
		this.handler = handler;
		this.sort = sort;
	}

	public int compare(O o1, O o2) {
		K obj1 = handler.invoke(o1);
		K obj2 = handler.invoke(o2);
		if (sort != null && Constant.SORT_DESC.equals(sort)) {
			// 倒序
			return compareBase(obj2,obj1);
		}else {
			return compareBase(obj1,obj2);
		}
	}

	private int compareBase(K obj1, K obj2) {
		if(Number.class.isAssignableFrom(obj1.getClass())) {
			Number num1 = (Number) obj1;
			Number num2 = (Number) obj2;
			if(num1.intValue() >  num2.intValue() ) {
				return 1;
			}else if(num1.intValue() < num2.intValue()) {
				return -1;
			}else {
				return 0;
			}
		}else {
			return obj1.toString().compareTo(obj2.toString());
		}
	}
}