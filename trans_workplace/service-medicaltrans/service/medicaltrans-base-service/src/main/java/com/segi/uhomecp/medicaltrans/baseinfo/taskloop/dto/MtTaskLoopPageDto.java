package com.segi.uhomecp.medicaltrans.baseinfo.taskloop.dto;

import java.io.Serializable;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.Paginator;

/**
 * Title: MtTaskLoopPageDto.java
 * @Description: 描述
 * @author yangyh@segimail.com
 * @created 2018年5月7日 上午9:58:20
 */
public class MtTaskLoopPageDto implements Serializable {

	private static final long serialVersionUID = -6923253527319820943L;

	private Paginator paginator;

	private List<MtTaskLoopDto> resultList;

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	public List<MtTaskLoopDto> getResultList() {
		return resultList;
	}

	public void setResultList(List<MtTaskLoopDto> resultList) {
		this.resultList = resultList;
	}
}
