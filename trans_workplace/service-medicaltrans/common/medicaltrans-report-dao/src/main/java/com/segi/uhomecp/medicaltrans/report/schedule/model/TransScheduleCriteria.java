package com.segi.uhomecp.medicaltrans.report.schedule.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.segi.uhomecp.common.model.AbstractCriteria;

public class TransScheduleCriteria extends AbstractCriteria{
    
	/**  描述   (@author: wangxiong@segimail.com) */      
	private static final long serialVersionUID = 5819300891718600196L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TransScheduleCriteria() {
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

        public Criteria andStartDateIsNull() {
            addCriterion("START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterion("START_DATE =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterion("START_DATE <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterion("START_DATE >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("START_DATE >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterion("START_DATE <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterion("START_DATE <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterion("START_DATE in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterion("START_DATE not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterion("START_DATE between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterion("START_DATE not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andParamDateIsNull() {
            addCriterion("PARAM_DATE is null");
            return (Criteria) this;
        }

        public Criteria andParamDateIsNotNull() {
            addCriterion("PARAM_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andParamDateEqualTo(Date value) {
            addCriterion("PARAM_DATE =", value, "paramDate");
            return (Criteria) this;
        }

        public Criteria andParamDateNotEqualTo(Date value) {
            addCriterion("PARAM_DATE <>", value, "paramDate");
            return (Criteria) this;
        }

        public Criteria andParamDateGreaterThan(Date value) {
            addCriterion("PARAM_DATE >", value, "paramDate");
            return (Criteria) this;
        }

        public Criteria andParamDateGreaterThanOrEqualTo(Date value) {
            addCriterion("PARAM_DATE >=", value, "paramDate");
            return (Criteria) this;
        }

        public Criteria andParamDateLessThan(Date value) {
            addCriterion("PARAM_DATE <", value, "paramDate");
            return (Criteria) this;
        }

        public Criteria andParamDateLessThanOrEqualTo(Date value) {
            addCriterion("PARAM_DATE <=", value, "paramDate");
            return (Criteria) this;
        }

        public Criteria andParamDateIn(List<Date> values) {
            addCriterion("PARAM_DATE in", values, "paramDate");
            return (Criteria) this;
        }

        public Criteria andParamDateNotIn(List<Date> values) {
            addCriterion("PARAM_DATE not in", values, "paramDate");
            return (Criteria) this;
        }

        public Criteria andParamDateBetween(Date value1, Date value2) {
            addCriterion("PARAM_DATE between", value1, value2, "paramDate");
            return (Criteria) this;
        }

        public Criteria andParamDateNotBetween(Date value1, Date value2) {
            addCriterion("PARAM_DATE not between", value1, value2, "paramDate");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRunningStatusIsNull() {
            addCriterion("RUNNING_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andRunningStatusIsNotNull() {
            addCriterion("RUNNING_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andRunningStatusEqualTo(String value) {
            addCriterion("RUNNING_STATUS =", value, "runningStatus");
            return (Criteria) this;
        }

        public Criteria andRunningStatusNotEqualTo(String value) {
            addCriterion("RUNNING_STATUS <>", value, "runningStatus");
            return (Criteria) this;
        }

        public Criteria andRunningStatusGreaterThan(String value) {
            addCriterion("RUNNING_STATUS >", value, "runningStatus");
            return (Criteria) this;
        }

        public Criteria andRunningStatusGreaterThanOrEqualTo(String value) {
            addCriterion("RUNNING_STATUS >=", value, "runningStatus");
            return (Criteria) this;
        }

        public Criteria andRunningStatusLessThan(String value) {
            addCriterion("RUNNING_STATUS <", value, "runningStatus");
            return (Criteria) this;
        }

        public Criteria andRunningStatusLessThanOrEqualTo(String value) {
            addCriterion("RUNNING_STATUS <=", value, "runningStatus");
            return (Criteria) this;
        }

        public Criteria andRunningStatusLike(String value) {
            addCriterion("RUNNING_STATUS like", value, "runningStatus");
            return (Criteria) this;
        }

        public Criteria andRunningStatusNotLike(String value) {
            addCriterion("RUNNING_STATUS not like", value, "runningStatus");
            return (Criteria) this;
        }

        public Criteria andRunningStatusIn(List<String> values) {
            addCriterion("RUNNING_STATUS in", values, "runningStatus");
            return (Criteria) this;
        }

        public Criteria andRunningStatusNotIn(List<String> values) {
            addCriterion("RUNNING_STATUS not in", values, "runningStatus");
            return (Criteria) this;
        }

        public Criteria andRunningStatusBetween(String value1, String value2) {
            addCriterion("RUNNING_STATUS between", value1, value2, "runningStatus");
            return (Criteria) this;
        }

        public Criteria andRunningStatusNotBetween(String value1, String value2) {
            addCriterion("RUNNING_STATUS not between", value1, value2, "runningStatus");
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

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
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

        public Criteria andUpdateDateIsNull() {
            addCriterion("UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("UPDATE_DATE =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("UPDATE_DATE <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("UPDATE_DATE >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("UPDATE_DATE <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("UPDATE_DATE in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("UPDATE_DATE not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andExcDateIsNull() {
            addCriterion("EXC_DATE is null");
            return (Criteria) this;
        }

        public Criteria andExcDateIsNotNull() {
            addCriterion("EXC_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andExcDateEqualTo(Date value) {
            addCriterion("EXC_DATE =", value, "excDate");
            return (Criteria) this;
        }

        public Criteria andExcDateNotEqualTo(Date value) {
            addCriterion("EXC_DATE <>", value, "excDate");
            return (Criteria) this;
        }

        public Criteria andExcDateGreaterThan(Date value) {
            addCriterion("EXC_DATE >", value, "excDate");
            return (Criteria) this;
        }

        public Criteria andExcDateGreaterThanOrEqualTo(Date value) {
            addCriterion("EXC_DATE >=", value, "excDate");
            return (Criteria) this;
        }

        public Criteria andExcDateLessThan(Date value) {
            addCriterion("EXC_DATE <", value, "excDate");
            return (Criteria) this;
        }

        public Criteria andExcDateLessThanOrEqualTo(Date value) {
            addCriterion("EXC_DATE <=", value, "excDate");
            return (Criteria) this;
        }

        public Criteria andExcDateIn(List<Date> values) {
            addCriterion("EXC_DATE in", values, "excDate");
            return (Criteria) this;
        }

        public Criteria andExcDateNotIn(List<Date> values) {
            addCriterion("EXC_DATE not in", values, "excDate");
            return (Criteria) this;
        }

        public Criteria andExcDateBetween(Date value1, Date value2) {
            addCriterion("EXC_DATE between", value1, value2, "excDate");
            return (Criteria) this;
        }

        public Criteria andExcDateNotBetween(Date value1, Date value2) {
            addCriterion("EXC_DATE not between", value1, value2, "excDate");
            return (Criteria) this;
        }

        public Criteria andLastExcDateIsNull() {
            addCriterion("LAST_EXC_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLastExcDateIsNotNull() {
            addCriterion("LAST_EXC_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLastExcDateEqualTo(Date value) {
            addCriterion("LAST_EXC_DATE =", value, "lastExcDate");
            return (Criteria) this;
        }

        public Criteria andLastExcDateNotEqualTo(Date value) {
            addCriterion("LAST_EXC_DATE <>", value, "lastExcDate");
            return (Criteria) this;
        }

        public Criteria andLastExcDateGreaterThan(Date value) {
            addCriterion("LAST_EXC_DATE >", value, "lastExcDate");
            return (Criteria) this;
        }

        public Criteria andLastExcDateGreaterThanOrEqualTo(Date value) {
            addCriterion("LAST_EXC_DATE >=", value, "lastExcDate");
            return (Criteria) this;
        }

        public Criteria andLastExcDateLessThan(Date value) {
            addCriterion("LAST_EXC_DATE <", value, "lastExcDate");
            return (Criteria) this;
        }

        public Criteria andLastExcDateLessThanOrEqualTo(Date value) {
            addCriterion("LAST_EXC_DATE <=", value, "lastExcDate");
            return (Criteria) this;
        }

        public Criteria andLastExcDateIn(List<Date> values) {
            addCriterion("LAST_EXC_DATE in", values, "lastExcDate");
            return (Criteria) this;
        }

        public Criteria andLastExcDateNotIn(List<Date> values) {
            addCriterion("LAST_EXC_DATE not in", values, "lastExcDate");
            return (Criteria) this;
        }

        public Criteria andLastExcDateBetween(Date value1, Date value2) {
            addCriterion("LAST_EXC_DATE between", value1, value2, "lastExcDate");
            return (Criteria) this;
        }

        public Criteria andLastExcDateNotBetween(Date value1, Date value2) {
            addCriterion("LAST_EXC_DATE not between", value1, value2, "lastExcDate");
            return (Criteria) this;
        }

        public Criteria andExcEndDateIsNull() {
            addCriterion("EXC_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andExcEndDateIsNotNull() {
            addCriterion("EXC_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andExcEndDateEqualTo(Date value) {
            addCriterion("EXC_END_DATE =", value, "excEndDate");
            return (Criteria) this;
        }

        public Criteria andExcEndDateNotEqualTo(Date value) {
            addCriterion("EXC_END_DATE <>", value, "excEndDate");
            return (Criteria) this;
        }

        public Criteria andExcEndDateGreaterThan(Date value) {
            addCriterion("EXC_END_DATE >", value, "excEndDate");
            return (Criteria) this;
        }

        public Criteria andExcEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("EXC_END_DATE >=", value, "excEndDate");
            return (Criteria) this;
        }

        public Criteria andExcEndDateLessThan(Date value) {
            addCriterion("EXC_END_DATE <", value, "excEndDate");
            return (Criteria) this;
        }

        public Criteria andExcEndDateLessThanOrEqualTo(Date value) {
            addCriterion("EXC_END_DATE <=", value, "excEndDate");
            return (Criteria) this;
        }

        public Criteria andExcEndDateIn(List<Date> values) {
            addCriterion("EXC_END_DATE in", values, "excEndDate");
            return (Criteria) this;
        }

        public Criteria andExcEndDateNotIn(List<Date> values) {
            addCriterion("EXC_END_DATE not in", values, "excEndDate");
            return (Criteria) this;
        }

        public Criteria andExcEndDateBetween(Date value1, Date value2) {
            addCriterion("EXC_END_DATE between", value1, value2, "excEndDate");
            return (Criteria) this;
        }

        public Criteria andExcEndDateNotBetween(Date value1, Date value2) {
            addCriterion("EXC_END_DATE not between", value1, value2, "excEndDate");
            return (Criteria) this;
        }

        public Criteria andStatusLikeInsensitive(String value) {
            addCriterion("upper(STATUS) like", value.toUpperCase(), "status");
            return (Criteria) this;
        }

        public Criteria andRunningStatusLikeInsensitive(String value) {
            addCriterion("upper(RUNNING_STATUS) like", value.toUpperCase(), "runningStatus");
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