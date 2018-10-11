package com.segi.uhomecp.medicaltrans.report.monthamount.organ.model;

import java.util.ArrayList;
import java.util.List;

import com.segi.uhomecp.common.model.AbstractCriteria;

public class OrganWeekVolumeCriteria extends AbstractCriteria {
	
	private static final long serialVersionUID = 3312275929160900511L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrganWeekVolumeCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGroupOrganIdIsNull() {
            addCriterion("GROUP_ORGAN_ID is null");
            return (Criteria) this;
        }

        public Criteria andGroupOrganIdIsNotNull() {
            addCriterion("GROUP_ORGAN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGroupOrganIdEqualTo(Integer value) {
            addCriterion("GROUP_ORGAN_ID =", value, "groupOrganId");
            return (Criteria) this;
        }

        public Criteria andGroupOrganIdNotEqualTo(Integer value) {
            addCriterion("GROUP_ORGAN_ID <>", value, "groupOrganId");
            return (Criteria) this;
        }

        public Criteria andGroupOrganIdGreaterThan(Integer value) {
            addCriterion("GROUP_ORGAN_ID >", value, "groupOrganId");
            return (Criteria) this;
        }

        public Criteria andGroupOrganIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("GROUP_ORGAN_ID >=", value, "groupOrganId");
            return (Criteria) this;
        }

        public Criteria andGroupOrganIdLessThan(Integer value) {
            addCriterion("GROUP_ORGAN_ID <", value, "groupOrganId");
            return (Criteria) this;
        }

        public Criteria andGroupOrganIdLessThanOrEqualTo(Integer value) {
            addCriterion("GROUP_ORGAN_ID <=", value, "groupOrganId");
            return (Criteria) this;
        }

        public Criteria andGroupOrganIdIn(List<Integer> values) {
            addCriterion("GROUP_ORGAN_ID in", values, "groupOrganId");
            return (Criteria) this;
        }

        public Criteria andGroupOrganIdNotIn(List<Integer> values) {
            addCriterion("GROUP_ORGAN_ID not in", values, "groupOrganId");
            return (Criteria) this;
        }

        public Criteria andGroupOrganIdBetween(Integer value1, Integer value2) {
            addCriterion("GROUP_ORGAN_ID between", value1, value2, "groupOrganId");
            return (Criteria) this;
        }

        public Criteria andGroupOrganIdNotBetween(Integer value1, Integer value2) {
            addCriterion("GROUP_ORGAN_ID not between", value1, value2, "groupOrganId");
            return (Criteria) this;
        }

        public Criteria andOrganIdIsNull() {
            addCriterion("ORGAN_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrganIdIsNotNull() {
            addCriterion("ORGAN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrganIdEqualTo(Integer value) {
            addCriterion("ORGAN_ID =", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdNotEqualTo(Integer value) {
            addCriterion("ORGAN_ID <>", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdGreaterThan(Integer value) {
            addCriterion("ORGAN_ID >", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORGAN_ID >=", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdLessThan(Integer value) {
            addCriterion("ORGAN_ID <", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdLessThanOrEqualTo(Integer value) {
            addCriterion("ORGAN_ID <=", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdIn(List<Integer> values) {
            addCriterion("ORGAN_ID in", values, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdNotIn(List<Integer> values) {
            addCriterion("ORGAN_ID not in", values, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdBetween(Integer value1, Integer value2) {
            addCriterion("ORGAN_ID between", value1, value2, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ORGAN_ID not between", value1, value2, "organId");
            return (Criteria) this;
        }

        public Criteria andCycleYmIsNull() {
            addCriterion("CYCLE_YM is null");
            return (Criteria) this;
        }

        public Criteria andCycleYmIsNotNull() {
            addCriterion("CYCLE_YM is not null");
            return (Criteria) this;
        }

        public Criteria andCycleYmEqualTo(Integer value) {
            addCriterion("CYCLE_YM =", value, "cycleYm");
            return (Criteria) this;
        }

        public Criteria andCycleYmNotEqualTo(Integer value) {
            addCriterion("CYCLE_YM <>", value, "cycleYm");
            return (Criteria) this;
        }

        public Criteria andCycleYmGreaterThan(Integer value) {
            addCriterion("CYCLE_YM >", value, "cycleYm");
            return (Criteria) this;
        }

        public Criteria andCycleYmGreaterThanOrEqualTo(Integer value) {
            addCriterion("CYCLE_YM >=", value, "cycleYm");
            return (Criteria) this;
        }

        public Criteria andCycleYmLessThan(Integer value) {
            addCriterion("CYCLE_YM <", value, "cycleYm");
            return (Criteria) this;
        }

        public Criteria andCycleYmLessThanOrEqualTo(Integer value) {
            addCriterion("CYCLE_YM <=", value, "cycleYm");
            return (Criteria) this;
        }

        public Criteria andCycleYmIn(List<Integer> values) {
            addCriterion("CYCLE_YM in", values, "cycleYm");
            return (Criteria) this;
        }

        public Criteria andCycleYmNotIn(List<Integer> values) {
            addCriterion("CYCLE_YM not in", values, "cycleYm");
            return (Criteria) this;
        }

        public Criteria andCycleYmBetween(Integer value1, Integer value2) {
            addCriterion("CYCLE_YM between", value1, value2, "cycleYm");
            return (Criteria) this;
        }

        public Criteria andCycleYmNotBetween(Integer value1, Integer value2) {
            addCriterion("CYCLE_YM not between", value1, value2, "cycleYm");
            return (Criteria) this;
        }

        public Criteria andWeekDayIsNull() {
            addCriterion("WEEK_DAY is null");
            return (Criteria) this;
        }

        public Criteria andWeekDayIsNotNull() {
            addCriterion("WEEK_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andWeekDayEqualTo(Integer value) {
            addCriterion("WEEK_DAY =", value, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayNotEqualTo(Integer value) {
            addCriterion("WEEK_DAY <>", value, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayGreaterThan(Integer value) {
            addCriterion("WEEK_DAY >", value, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("WEEK_DAY >=", value, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayLessThan(Integer value) {
            addCriterion("WEEK_DAY <", value, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayLessThanOrEqualTo(Integer value) {
            addCriterion("WEEK_DAY <=", value, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayIn(List<Integer> values) {
            addCriterion("WEEK_DAY in", values, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayNotIn(List<Integer> values) {
            addCriterion("WEEK_DAY not in", values, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayBetween(Integer value1, Integer value2) {
            addCriterion("WEEK_DAY between", value1, value2, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayNotBetween(Integer value1, Integer value2) {
            addCriterion("WEEK_DAY not between", value1, value2, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekMonthdayIsNull() {
            addCriterion("WEEK_MONTHDAY is null");
            return (Criteria) this;
        }

        public Criteria andWeekMonthdayIsNotNull() {
            addCriterion("WEEK_MONTHDAY is not null");
            return (Criteria) this;
        }

        public Criteria andWeekMonthdayEqualTo(Integer value) {
            addCriterion("WEEK_MONTHDAY =", value, "weekMonthday");
            return (Criteria) this;
        }

        public Criteria andWeekMonthdayNotEqualTo(Integer value) {
            addCriterion("WEEK_MONTHDAY <>", value, "weekMonthday");
            return (Criteria) this;
        }

        public Criteria andWeekMonthdayGreaterThan(Integer value) {
            addCriterion("WEEK_MONTHDAY >", value, "weekMonthday");
            return (Criteria) this;
        }

        public Criteria andWeekMonthdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("WEEK_MONTHDAY >=", value, "weekMonthday");
            return (Criteria) this;
        }

        public Criteria andWeekMonthdayLessThan(Integer value) {
            addCriterion("WEEK_MONTHDAY <", value, "weekMonthday");
            return (Criteria) this;
        }

        public Criteria andWeekMonthdayLessThanOrEqualTo(Integer value) {
            addCriterion("WEEK_MONTHDAY <=", value, "weekMonthday");
            return (Criteria) this;
        }

        public Criteria andWeekMonthdayIn(List<Integer> values) {
            addCriterion("WEEK_MONTHDAY in", values, "weekMonthday");
            return (Criteria) this;
        }

        public Criteria andWeekMonthdayNotIn(List<Integer> values) {
            addCriterion("WEEK_MONTHDAY not in", values, "weekMonthday");
            return (Criteria) this;
        }

        public Criteria andWeekMonthdayBetween(Integer value1, Integer value2) {
            addCriterion("WEEK_MONTHDAY between", value1, value2, "weekMonthday");
            return (Criteria) this;
        }

        public Criteria andWeekMonthdayNotBetween(Integer value1, Integer value2) {
            addCriterion("WEEK_MONTHDAY not between", value1, value2, "weekMonthday");
            return (Criteria) this;
        }

        public Criteria andWeekTuesdayIsNull() {
            addCriterion("WEEK_TUESDAY is null");
            return (Criteria) this;
        }

        public Criteria andWeekTuesdayIsNotNull() {
            addCriterion("WEEK_TUESDAY is not null");
            return (Criteria) this;
        }

        public Criteria andWeekTuesdayEqualTo(Integer value) {
            addCriterion("WEEK_TUESDAY =", value, "weekTuesday");
            return (Criteria) this;
        }

        public Criteria andWeekTuesdayNotEqualTo(Integer value) {
            addCriterion("WEEK_TUESDAY <>", value, "weekTuesday");
            return (Criteria) this;
        }

        public Criteria andWeekTuesdayGreaterThan(Integer value) {
            addCriterion("WEEK_TUESDAY >", value, "weekTuesday");
            return (Criteria) this;
        }

        public Criteria andWeekTuesdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("WEEK_TUESDAY >=", value, "weekTuesday");
            return (Criteria) this;
        }

        public Criteria andWeekTuesdayLessThan(Integer value) {
            addCriterion("WEEK_TUESDAY <", value, "weekTuesday");
            return (Criteria) this;
        }

        public Criteria andWeekTuesdayLessThanOrEqualTo(Integer value) {
            addCriterion("WEEK_TUESDAY <=", value, "weekTuesday");
            return (Criteria) this;
        }

        public Criteria andWeekTuesdayIn(List<Integer> values) {
            addCriterion("WEEK_TUESDAY in", values, "weekTuesday");
            return (Criteria) this;
        }

        public Criteria andWeekTuesdayNotIn(List<Integer> values) {
            addCriterion("WEEK_TUESDAY not in", values, "weekTuesday");
            return (Criteria) this;
        }

        public Criteria andWeekTuesdayBetween(Integer value1, Integer value2) {
            addCriterion("WEEK_TUESDAY between", value1, value2, "weekTuesday");
            return (Criteria) this;
        }

        public Criteria andWeekTuesdayNotBetween(Integer value1, Integer value2) {
            addCriterion("WEEK_TUESDAY not between", value1, value2, "weekTuesday");
            return (Criteria) this;
        }

        public Criteria andWeekWednesdayIsNull() {
            addCriterion("WEEK_WEDNESDAY is null");
            return (Criteria) this;
        }

        public Criteria andWeekWednesdayIsNotNull() {
            addCriterion("WEEK_WEDNESDAY is not null");
            return (Criteria) this;
        }

        public Criteria andWeekWednesdayEqualTo(Integer value) {
            addCriterion("WEEK_WEDNESDAY =", value, "weekWednesday");
            return (Criteria) this;
        }

        public Criteria andWeekWednesdayNotEqualTo(Integer value) {
            addCriterion("WEEK_WEDNESDAY <>", value, "weekWednesday");
            return (Criteria) this;
        }

        public Criteria andWeekWednesdayGreaterThan(Integer value) {
            addCriterion("WEEK_WEDNESDAY >", value, "weekWednesday");
            return (Criteria) this;
        }

        public Criteria andWeekWednesdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("WEEK_WEDNESDAY >=", value, "weekWednesday");
            return (Criteria) this;
        }

        public Criteria andWeekWednesdayLessThan(Integer value) {
            addCriterion("WEEK_WEDNESDAY <", value, "weekWednesday");
            return (Criteria) this;
        }

        public Criteria andWeekWednesdayLessThanOrEqualTo(Integer value) {
            addCriterion("WEEK_WEDNESDAY <=", value, "weekWednesday");
            return (Criteria) this;
        }

        public Criteria andWeekWednesdayIn(List<Integer> values) {
            addCriterion("WEEK_WEDNESDAY in", values, "weekWednesday");
            return (Criteria) this;
        }

        public Criteria andWeekWednesdayNotIn(List<Integer> values) {
            addCriterion("WEEK_WEDNESDAY not in", values, "weekWednesday");
            return (Criteria) this;
        }

        public Criteria andWeekWednesdayBetween(Integer value1, Integer value2) {
            addCriterion("WEEK_WEDNESDAY between", value1, value2, "weekWednesday");
            return (Criteria) this;
        }

        public Criteria andWeekWednesdayNotBetween(Integer value1, Integer value2) {
            addCriterion("WEEK_WEDNESDAY not between", value1, value2, "weekWednesday");
            return (Criteria) this;
        }

        public Criteria andWeekThursdayIsNull() {
            addCriterion("WEEK_THURSDAY is null");
            return (Criteria) this;
        }

        public Criteria andWeekThursdayIsNotNull() {
            addCriterion("WEEK_THURSDAY is not null");
            return (Criteria) this;
        }

        public Criteria andWeekThursdayEqualTo(Integer value) {
            addCriterion("WEEK_THURSDAY =", value, "weekThursday");
            return (Criteria) this;
        }

        public Criteria andWeekThursdayNotEqualTo(Integer value) {
            addCriterion("WEEK_THURSDAY <>", value, "weekThursday");
            return (Criteria) this;
        }

        public Criteria andWeekThursdayGreaterThan(Integer value) {
            addCriterion("WEEK_THURSDAY >", value, "weekThursday");
            return (Criteria) this;
        }

        public Criteria andWeekThursdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("WEEK_THURSDAY >=", value, "weekThursday");
            return (Criteria) this;
        }

        public Criteria andWeekThursdayLessThan(Integer value) {
            addCriterion("WEEK_THURSDAY <", value, "weekThursday");
            return (Criteria) this;
        }

        public Criteria andWeekThursdayLessThanOrEqualTo(Integer value) {
            addCriterion("WEEK_THURSDAY <=", value, "weekThursday");
            return (Criteria) this;
        }

        public Criteria andWeekThursdayIn(List<Integer> values) {
            addCriterion("WEEK_THURSDAY in", values, "weekThursday");
            return (Criteria) this;
        }

        public Criteria andWeekThursdayNotIn(List<Integer> values) {
            addCriterion("WEEK_THURSDAY not in", values, "weekThursday");
            return (Criteria) this;
        }

        public Criteria andWeekThursdayBetween(Integer value1, Integer value2) {
            addCriterion("WEEK_THURSDAY between", value1, value2, "weekThursday");
            return (Criteria) this;
        }

        public Criteria andWeekThursdayNotBetween(Integer value1, Integer value2) {
            addCriterion("WEEK_THURSDAY not between", value1, value2, "weekThursday");
            return (Criteria) this;
        }

        public Criteria andWeekFridayIsNull() {
            addCriterion("WEEK_FRIDAY is null");
            return (Criteria) this;
        }

        public Criteria andWeekFridayIsNotNull() {
            addCriterion("WEEK_FRIDAY is not null");
            return (Criteria) this;
        }

        public Criteria andWeekFridayEqualTo(Integer value) {
            addCriterion("WEEK_FRIDAY =", value, "weekFriday");
            return (Criteria) this;
        }

        public Criteria andWeekFridayNotEqualTo(Integer value) {
            addCriterion("WEEK_FRIDAY <>", value, "weekFriday");
            return (Criteria) this;
        }

        public Criteria andWeekFridayGreaterThan(Integer value) {
            addCriterion("WEEK_FRIDAY >", value, "weekFriday");
            return (Criteria) this;
        }

        public Criteria andWeekFridayGreaterThanOrEqualTo(Integer value) {
            addCriterion("WEEK_FRIDAY >=", value, "weekFriday");
            return (Criteria) this;
        }

        public Criteria andWeekFridayLessThan(Integer value) {
            addCriterion("WEEK_FRIDAY <", value, "weekFriday");
            return (Criteria) this;
        }

        public Criteria andWeekFridayLessThanOrEqualTo(Integer value) {
            addCriterion("WEEK_FRIDAY <=", value, "weekFriday");
            return (Criteria) this;
        }

        public Criteria andWeekFridayIn(List<Integer> values) {
            addCriterion("WEEK_FRIDAY in", values, "weekFriday");
            return (Criteria) this;
        }

        public Criteria andWeekFridayNotIn(List<Integer> values) {
            addCriterion("WEEK_FRIDAY not in", values, "weekFriday");
            return (Criteria) this;
        }

        public Criteria andWeekFridayBetween(Integer value1, Integer value2) {
            addCriterion("WEEK_FRIDAY between", value1, value2, "weekFriday");
            return (Criteria) this;
        }

        public Criteria andWeekFridayNotBetween(Integer value1, Integer value2) {
            addCriterion("WEEK_FRIDAY not between", value1, value2, "weekFriday");
            return (Criteria) this;
        }

        public Criteria andWeekSaturdayIsNull() {
            addCriterion("WEEK_SATURDAY is null");
            return (Criteria) this;
        }

        public Criteria andWeekSaturdayIsNotNull() {
            addCriterion("WEEK_SATURDAY is not null");
            return (Criteria) this;
        }

        public Criteria andWeekSaturdayEqualTo(Integer value) {
            addCriterion("WEEK_SATURDAY =", value, "weekSaturday");
            return (Criteria) this;
        }

        public Criteria andWeekSaturdayNotEqualTo(Integer value) {
            addCriterion("WEEK_SATURDAY <>", value, "weekSaturday");
            return (Criteria) this;
        }

        public Criteria andWeekSaturdayGreaterThan(Integer value) {
            addCriterion("WEEK_SATURDAY >", value, "weekSaturday");
            return (Criteria) this;
        }

        public Criteria andWeekSaturdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("WEEK_SATURDAY >=", value, "weekSaturday");
            return (Criteria) this;
        }

        public Criteria andWeekSaturdayLessThan(Integer value) {
            addCriterion("WEEK_SATURDAY <", value, "weekSaturday");
            return (Criteria) this;
        }

        public Criteria andWeekSaturdayLessThanOrEqualTo(Integer value) {
            addCriterion("WEEK_SATURDAY <=", value, "weekSaturday");
            return (Criteria) this;
        }

        public Criteria andWeekSaturdayIn(List<Integer> values) {
            addCriterion("WEEK_SATURDAY in", values, "weekSaturday");
            return (Criteria) this;
        }

        public Criteria andWeekSaturdayNotIn(List<Integer> values) {
            addCriterion("WEEK_SATURDAY not in", values, "weekSaturday");
            return (Criteria) this;
        }

        public Criteria andWeekSaturdayBetween(Integer value1, Integer value2) {
            addCriterion("WEEK_SATURDAY between", value1, value2, "weekSaturday");
            return (Criteria) this;
        }

        public Criteria andWeekSaturdayNotBetween(Integer value1, Integer value2) {
            addCriterion("WEEK_SATURDAY not between", value1, value2, "weekSaturday");
            return (Criteria) this;
        }

        public Criteria andWeekSundayIsNull() {
            addCriterion("WEEK_SUNDAY is null");
            return (Criteria) this;
        }

        public Criteria andWeekSundayIsNotNull() {
            addCriterion("WEEK_SUNDAY is not null");
            return (Criteria) this;
        }

        public Criteria andWeekSundayEqualTo(Integer value) {
            addCriterion("WEEK_SUNDAY =", value, "weekSunday");
            return (Criteria) this;
        }

        public Criteria andWeekSundayNotEqualTo(Integer value) {
            addCriterion("WEEK_SUNDAY <>", value, "weekSunday");
            return (Criteria) this;
        }

        public Criteria andWeekSundayGreaterThan(Integer value) {
            addCriterion("WEEK_SUNDAY >", value, "weekSunday");
            return (Criteria) this;
        }

        public Criteria andWeekSundayGreaterThanOrEqualTo(Integer value) {
            addCriterion("WEEK_SUNDAY >=", value, "weekSunday");
            return (Criteria) this;
        }

        public Criteria andWeekSundayLessThan(Integer value) {
            addCriterion("WEEK_SUNDAY <", value, "weekSunday");
            return (Criteria) this;
        }

        public Criteria andWeekSundayLessThanOrEqualTo(Integer value) {
            addCriterion("WEEK_SUNDAY <=", value, "weekSunday");
            return (Criteria) this;
        }

        public Criteria andWeekSundayIn(List<Integer> values) {
            addCriterion("WEEK_SUNDAY in", values, "weekSunday");
            return (Criteria) this;
        }

        public Criteria andWeekSundayNotIn(List<Integer> values) {
            addCriterion("WEEK_SUNDAY not in", values, "weekSunday");
            return (Criteria) this;
        }

        public Criteria andWeekSundayBetween(Integer value1, Integer value2) {
            addCriterion("WEEK_SUNDAY between", value1, value2, "weekSunday");
            return (Criteria) this;
        }

        public Criteria andWeekSundayNotBetween(Integer value1, Integer value2) {
            addCriterion("WEEK_SUNDAY not between", value1, value2, "weekSunday");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Long value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Long value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Long value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Long value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Long value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Long value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Long> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Long> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Long value1, Long value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Long value1, Long value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Long value) {
            addCriterion("UPDATE_DATE =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Long value) {
            addCriterion("UPDATE_DATE <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Long value) {
            addCriterion("UPDATE_DATE >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Long value) {
            addCriterion("UPDATE_DATE >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Long value) {
            addCriterion("UPDATE_DATE <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Long value) {
            addCriterion("UPDATE_DATE <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Long> values) {
            addCriterion("UPDATE_DATE in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Long> values) {
            addCriterion("UPDATE_DATE not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Long value1, Long value2) {
            addCriterion("UPDATE_DATE between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Long value1, Long value2) {
            addCriterion("UPDATE_DATE not between", value1, value2, "updateDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}