package com.segi.uhomecp.medicaltrans.baseinfo.location.dto;
import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

/**
 * 
 * Title: MtBuildLocationDto.java    
 * @Description: 新建位置dto
 * @author dengdong@segimail.com       
 * @created 2018年5月9日 下午2:23:04
 */
public class MtBuildLocationDto extends AbstractModel{
	
	/**  描述   (@author: dengdong@segimail.com) */      
	    
	private static final long serialVersionUID = 1L;

	private String curUserId;
	
	private String houseSpaces;
	
    private Integer locationId;

    private Integer groupOrganId;

    private Integer organId;

    private Integer buildId;

    private Integer sid;

    private Integer floorId;

    private Short floorNum;

    private String locationName;

    private String locateType;

    private String qrcode;

    private String mac;

    private String status;

    private String remark;

    private Date createDate;

    private Integer createuserId;

    private Date updateDate;

    private Integer updateuserId;

    private String floorPositX;

    private String floorPositY;
    
    private String pageNo;
    
    private String pageLength;
    
    private String defaultLocationId;

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getGroupOrganId() {
        return groupOrganId;
    }

    public void setGroupOrganId(Integer groupOrganId) {
        this.groupOrganId = groupOrganId;
    }

    public Integer getOrganId() {
        return organId;
    }

    public void setOrganId(Integer organId) {
        this.organId = organId;
    }

    public Integer getBuildId() {
        return buildId;
    }

    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public Short getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Short floorNum) {
        this.floorNum = floorNum;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName == null ? null : locationName.trim();
    }

    public String getLocateType() {
        return locateType;
    }

    public void setLocateType(String locateType) {
        this.locateType = locateType == null ? null : locateType.trim();
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode == null ? null : qrcode.trim();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateuserId() {
        return createuserId;
    }

    public void setCreateuserId(Integer createuserId) {
        this.createuserId = createuserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateuserId() {
        return updateuserId;
    }

    public void setUpdateuserId(Integer updateuserId) {
        this.updateuserId = updateuserId;
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
    

    public String getCurUserId() {
		return curUserId;
	}

	public void setCurUserId(String curUserId) {
		this.curUserId = curUserId;
	}

	public String getHouseSpaces() {
		return houseSpaces;
	}

	public void setHouseSpaces(String houseSpaces) {
		this.houseSpaces = houseSpaces;
	}

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

	public String getDefaultLocationId() {
		return defaultLocationId;
	}

	public void setDefaultLocationId(String defaultLocationId) {
		this.defaultLocationId = defaultLocationId;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", locationId=").append(locationId);
        sb.append(", groupOrganId=").append(groupOrganId);
        sb.append(", organId=").append(organId);
        sb.append(", buildId=").append(buildId);
        sb.append(", sid=").append(sid);
        sb.append(", floorId=").append(floorId);
        sb.append(", floorNum=").append(floorNum);
        sb.append(", locationName=").append(locationName);
        sb.append(", locateType=").append(locateType);
        sb.append(", qrcode=").append(qrcode);
        sb.append(", mac=").append(mac);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createDate=").append(createDate);
        sb.append(", createuserId=").append(createuserId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateuserId=").append(updateuserId);
        sb.append(", floorPositX=").append(floorPositX);
        sb.append(", floorPositY=").append(floorPositY);
        sb.append("]");
        return sb.toString();
    }
}