package com.segi.uhomecp.wh.common.rocketMQ;

import com.google.common.collect.Maps;
import com.segi.uhomecp.common.model.AbstractModel;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Map;

/**
 * Created by zouzhenze on 2017/6/12.
 */
public class NoticeMQ extends AbstractModel {

    @NotBlank(message = "发送类型不能为空->sendType")
    @Pattern(regexp = "^[1-9][0-9]*|[0]$", message = "发送类型格式不正确")
    @Min(value = 1, message = "发送类型不能小于1")
    @Max(value = 2147483647, message = "发送类型超出最大范围")
    private String sendType;

    @NotBlank(message = "目标类型不能为空->notice")
    @Pattern(regexp = "^[1-9][0-9]*|[0]$", message = "目标类型格式不正确")
    @Min(value = 1, message = "目标类型不能小于1")
    @Max(value = 2147483647, message = "目标类型超出最大范围")
    private String notice;

    @NotBlank(message = "是否发送站内信标识不能为空->stationMessage")
    @Pattern(regexp = "^[1-9][0-9]*|[0]$", message = "是否发送站内信标识格式不正确")
    @Min(value = 1, message = "是否发送站内信标识不能小于1")
    @Max(value = 2147483647, message = "是否发送站内信标识超出最大范围")
    private String stationMessage;

    private String keys;

    private Map<String, String> station;

    private Map<String, String> extra;

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

    public Map<String, String> getStation() {
        if (null == station) {
            return Maps.newHashMap();
        }
        return station;
    }

    public void setStation(Map<String, String> station) {
        this.station = station;
    }

    public Map<String, String> getExtra() {
        if (null == extra) {
            return Maps.newHashMap();
        }
        return extra;
    }

    public void setExtra(Map<String, String> extra) {
        this.extra = extra;
    }
}
