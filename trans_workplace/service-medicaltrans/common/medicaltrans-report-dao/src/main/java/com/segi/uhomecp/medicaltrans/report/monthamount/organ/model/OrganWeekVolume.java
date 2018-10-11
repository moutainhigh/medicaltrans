package com.segi.uhomecp.medicaltrans.report.monthamount.organ.model;

import com.segi.uhomecp.common.model.AbstractModel;

public class OrganWeekVolume extends AbstractModel {
    private Integer id;

    private Integer groupOrganId;

    private Integer organId;

    private Integer cycleYm;

    private Integer weekDay;

    private Integer weekMonthday;

    private Integer weekTuesday;

    private Integer weekWednesday;

    private Integer weekThursday;

    private Integer weekFriday;

    private Integer weekSaturday;

    private Integer weekSunday;

    private Long createDate;

    private Long updateDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCycleYm() {
        return cycleYm;
    }

    public void setCycleYm(Integer cycleYm) {
        this.cycleYm = cycleYm;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    public Integer getWeekMonthday() {
        return weekMonthday;
    }

    public void setWeekMonthday(Integer weekMonthday) {
        this.weekMonthday = weekMonthday;
    }

    public Integer getWeekTuesday() {
        return weekTuesday;
    }

    public void setWeekTuesday(Integer weekTuesday) {
        this.weekTuesday = weekTuesday;
    }

    public Integer getWeekWednesday() {
        return weekWednesday;
    }

    public void setWeekWednesday(Integer weekWednesday) {
        this.weekWednesday = weekWednesday;
    }

    public Integer getWeekThursday() {
        return weekThursday;
    }

    public void setWeekThursday(Integer weekThursday) {
        this.weekThursday = weekThursday;
    }

    public Integer getWeekFriday() {
        return weekFriday;
    }

    public void setWeekFriday(Integer weekFriday) {
        this.weekFriday = weekFriday;
    }

    public Integer getWeekSaturday() {
        return weekSaturday;
    }

    public void setWeekSaturday(Integer weekSaturday) {
        this.weekSaturday = weekSaturday;
    }

    public Integer getWeekSunday() {
        return weekSunday;
    }

    public void setWeekSunday(Integer weekSunday) {
        this.weekSunday = weekSunday;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupOrganId=").append(groupOrganId);
        sb.append(", organId=").append(organId);
        sb.append(", cycleYm=").append(cycleYm);
        sb.append(", weekDay=").append(weekDay);
        sb.append(", weekMonthday=").append(weekMonthday);
        sb.append(", weekTuesday=").append(weekTuesday);
        sb.append(", weekWednesday=").append(weekWednesday);
        sb.append(", weekThursday=").append(weekThursday);
        sb.append(", weekFriday=").append(weekFriday);
        sb.append(", weekSaturday=").append(weekSaturday);
        sb.append(", weekSunday=").append(weekSunday);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}