package com.segi.uhomecp.medicaltrans.baseinfo.userposit.dto;

import com.segi.uhomecp.medicaltrans.base.posit.bean.MtCurUserPosit;

public class MtUserLocationDto extends MtCurUserPosit{

	/**
	 * 类描述: 
	 * 创建人: dengdong@sige.com   
	 * 创建时间: 2018年5月3日 下午8:47:40   
	 */
	private static final long serialVersionUID = 3385227636466953845L;
	
	private String buildName;
	
	private String floorName;
	
	private String sidName;
	
	private String loactionName;
	
	private String blendName;
	
	private String statusName;
	
	private String planUrl;
	
	private String floorPositX;
	
	private String floorPositY;
	
	private String pageNo;
    
    private String pageLength;

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageLength() {
		return pageLength;
	}

	public void setPageLength(String pageLength) {
		this.pageLength = pageLength;
	}
	
	

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getSidName() {
		return sidName;
	}

	public void setSidName(String sidName) {
		this.sidName = sidName;
	}

	public String getLoactionName() {
		return loactionName;
	}

	public void setLoactionName(String loactionName) {
		this.loactionName = loactionName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getPlanUrl() {
		return planUrl;
	}

	public void setPlanUrl(String planUrl) {
		this.planUrl = planUrl;
	}

	public String getFloorPositX() {
		return floorPositX;
	}

	public void setFloorPositX(String floorPositX) {
		this.floorPositX = floorPositX;
	}

	public String getFloorPositY() {
		return floorPositY;
	}

	public void setFloorPositY(String floorPositY) {
		this.floorPositY = floorPositY;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	
	public String getBlendName() {
		return blendName;
	}

	public void setBlendName(String blendName) {
		this.blendName = blendName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MtUserLocationDto [floorName=");
		builder.append(floorName);
		builder.append(", sidName=");
		builder.append(sidName);
		builder.append(", loactionName=");
		builder.append(loactionName);
		builder.append(", statusName=");
		builder.append(statusName);
		builder.append(", planUrl=");
		builder.append(planUrl);
		builder.append(", floorPositX=");
		builder.append(floorPositX);
		builder.append(", floorPositY=");
		builder.append(floorPositY);
		builder.append(", pageNo=");
		builder.append(pageNo);
		builder.append(", pageLength=");
		builder.append(pageLength);
		builder.append("]");
		return builder.toString();
	}

	

	
	
	
}
