package com.segi.uhomecp.wh.common.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.miemiedev.mybatis.paginator.domain.PageList;

public class ResultDto<K, V, O> implements Serializable {
	private static final long serialVersionUID = 4002295632703388951L;
	private Boolean isSucc;
	private String message;
	private O obj;
	private V val;
	private List<O> list;
	private Map<K, V> map;
	private Set<O> noRepeatList;
	private PageList<O> pageList;

	public ResultDto(Boolean isSucc, String message) {
		super();
		this.isSucc = isSucc;
		this.message = message;
	}
	
	public ResultDto() {
		super();
	}

	public Boolean getIsSucc() {
		return isSucc;
	}

	public void setIsSucc(Boolean isSucc) {
		this.isSucc = isSucc;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<O> getList() {
		return list;
	}

	public void setList(List<O> list) {
		this.list = list;
	}

	public Map<K, V> getMap() {
		return map;
	}

	public void setMap(Map<K, V> map) {
		this.map = map;
	}

	public O getObj() {
		return obj;
	}

	public void setObj(O obj) {
		this.obj = obj;
	}

	public Set<O> getNoRepeatList() {
		return noRepeatList;
	}

	public void setNoRepeatList(Set<O> noRepeatList) {
		this.noRepeatList = noRepeatList;
	}

	public PageList<O> getPageList() {
		return pageList;
	}

	public void setPageList(PageList<O> pageList) {
		this.pageList = pageList;
	}

	public V getVal() {
		return val;
	}

	public void setVal(V val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "ResultInfo [isSucc=" + isSucc + ", message=" + message
				+ ", obj=" + obj + ", list=" + list + ", map=" + map
				+ ", noRepeatList=" + noRepeatList + "]";
	}
	
}