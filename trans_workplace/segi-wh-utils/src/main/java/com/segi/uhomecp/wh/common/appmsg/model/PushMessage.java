package com.segi.uhomecp.wh.common.appmsg.model;


public class PushMessage implements java.io.Serializable {
	private static final long serialVersionUID = 4097955413434292144L;

	//1：仅通知 2：仅透传 4：通知并透传
    private  String sendType;
    
    //1：对用户 2：对标签 3：对平台
    private String notice;
    
    //1：需要 0：不需要
    private String stationMessage;
    
    //关键词，用于后期搜索消息用，可以为空
    private String keys;
    
    private Extra extra;
    
    private StationMessage station;

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getStationMessage() {
		return stationMessage;
	}

	public void setStationMessage(String stationMessage) {
		this.stationMessage = stationMessage;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public Extra getExtra() {
		return extra;
	}

	public void setExtra(Extra extra) {
		this.extra = extra;
	}

	public StationMessage getStation() {
		return station;
	}

	public void setStation(StationMessage station) {
		this.station = station;
	}
}
