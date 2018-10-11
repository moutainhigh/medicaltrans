package com.segi.uhomecp.medicaltrans.report.monthrank.personal.dto;

import java.io.Serializable;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.Paginator;

/** 
 * Title: MtPersonalVolumeMonthPageDto.java    
 * @Description: 描述
 * @author yangyh@segimail.com       
 * @created 2018年5月6日 下午3:10:32    
 */
public class PersonalVolumeMonthPageDto implements Serializable {

	private static final long serialVersionUID = -3918142955421225587L;

	private Paginator paginator;
	
	private List<PersonalVolumeMonthDto> resultList;
	
	private CurUserRankDto curUserRankDto;

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	public List<PersonalVolumeMonthDto> getResultList() {
		return resultList;
	}

	public void setResultList(List<PersonalVolumeMonthDto> resultList) {
		this.resultList = resultList;
	}

	public CurUserRankDto getCurUserRankDto() {
		return curUserRankDto;
	}

	public void setCurUserRankDto(CurUserRankDto curUserRankDto) {
		this.curUserRankDto = curUserRankDto;
	}
}
