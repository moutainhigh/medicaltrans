package com.segi.uhomecp.medicaltrans.report.schedule.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExecuteHandlerCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExecuteHandlerCriteria() {
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

        public Criteria andHandlerIdIsNull() {
            addCriterion("HANDLER_ID is null");
            return (Criteria) this;
        }

        public Criteria andHandlerIdIsNotNull() {
            addCriterion("HANDLER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHandlerIdEqualTo(Integer value) {
            addCriterion("HANDLER_ID =", value, "handlerId");
            return (Criteria) this;
        }

        public Criteria andHandlerIdNotEqualTo(Integer value) {
            addCriterion("HANDLER_ID <>", value, "handlerId");
            return (Criteria) this;
        }

        public Criteria andHandlerIdGreaterThan(Integer value) {
            addCriterion("HANDLER_ID >", value, "handlerId");
            return (Criteria) this;
        }

        public Criteria andHandlerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("HANDLER_ID >=", value, "handlerId");
            return (Criteria) this;
        }

        public Criteria andHandlerIdLessThan(Integer value) {
            addCriterion("HANDLER_ID <", value, "handlerId");
            return (Criteria) this;
        }

        public Criteria andHandlerIdLessThanOrEqualTo(Integer value) {
            addCriterion("HANDLER_ID <=", value, "handlerId");
            return (Criteria) this;
        }

        public Criteria andHandlerIdIn(List<Integer> values) {
            addCriterion("HANDLER_ID in", values, "handlerId");
            return (Criteria) this;
        }

        public Criteria andHandlerIdNotIn(List<Integer> values) {
            addCriterion("HANDLER_ID not in", values, "handlerId");
            return (Criteria) this;
        }

        public Criteria andHandlerIdBetween(Integer value1, Integer value2) {
            addCriterion("HANDLER_ID between", value1, value2, "handlerId");
            return (Criteria) this;
        }

        public Criteria andHandlerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("HANDLER_ID not between", value1, value2, "handlerId");
            return (Criteria) this;
        }

        public Criteria andHandlerNameIsNull() {
            addCriterion("HANDLER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHandlerNameIsNotNull() {
            addCriterion("HANDLER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHandlerNameEqualTo(String value) {
            addCriterion("HANDLER_NAME =", value, "handlerName");
            return (Criteria) this;
        }

        public Criteria andHandlerNameNotEqualTo(String value) {
            addCriterion("HANDLER_NAME <>", value, "handlerName");
            return (Criteria) this;
        }

        public Criteria andHandlerNameGreaterThan(String value) {
            addCriterion("HANDLER_NAME >", value, "handlerName");
            return (Criteria) this;
        }

        public Criteria andHandlerNameGreaterThanOrEqualTo(String value) {
            addCriterion("HANDLER_NAME >=", value, "handlerName");
            return (Criteria) this;
        }

        public Criteria andHandlerNameLessThan(String value) {
            addCriterion("HANDLER_NAME <", value, "handlerName");
            return (Criteria) this;
        }

        public Criteria andHandlerNameLessThanOrEqualTo(String value) {
            addCriterion("HANDLER_NAME <=", value, "handlerName");
            return (Criteria) this;
        }

        public Criteria andHandlerNameLike(String value) {
            addCriterion("HANDLER_NAME like", value, "handlerName");
            return (Criteria) this;
        }

        public Criteria andHandlerNameNotLike(String value) {
            addCriterion("HANDLER_NAME not like", value, "handlerName");
            return (Criteria) this;
        }

        public Criteria andHandlerNameIn(List<String> values) {
            addCriterion("HANDLER_NAME in", values, "handlerName");
            return (Criteria) this;
        }

        public Criteria andHandlerNameNotIn(List<String> values) {
            addCriterion("HANDLER_NAME not in", values, "handlerName");
            return (Criteria) this;
        }

        public Criteria andHandlerNameBetween(String value1, String value2) {
            addCriterion("HANDLER_NAME between", value1, value2, "handlerName");
            return (Criteria) this;
        }

        public Criteria andHandlerNameNotBetween(String value1, String value2) {
            addCriterion("HANDLER_NAME not between", value1, value2, "handlerName");
            return (Criteria) this;
        }

        public Criteria andSyncFlagIsNull() {
            addCriterion("SYNC_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSyncFlagIsNotNull() {
            addCriterion("SYNC_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSyncFlagEqualTo(String value) {
            addCriterion("SYNC_FLAG =", value, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagNotEqualTo(String value) {
            addCriterion("SYNC_FLAG <>", value, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagGreaterThan(String value) {
            addCriterion("SYNC_FLAG >", value, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SYNC_FLAG >=", value, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagLessThan(String value) {
            addCriterion("SYNC_FLAG <", value, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagLessThanOrEqualTo(String value) {
            addCriterion("SYNC_FLAG <=", value, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagLike(String value) {
            addCriterion("SYNC_FLAG like", value, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagNotLike(String value) {
            addCriterion("SYNC_FLAG not like", value, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagIn(List<String> values) {
            addCriterion("SYNC_FLAG in", values, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagNotIn(List<String> values) {
            addCriterion("SYNC_FLAG not in", values, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagBetween(String value1, String value2) {
            addCriterion("SYNC_FLAG between", value1, value2, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagNotBetween(String value1, String value2) {
            addCriterion("SYNC_FLAG not between", value1, value2, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andExeOrderIsNull() {
            addCriterion("EXE_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andExeOrderIsNotNull() {
            addCriterion("EXE_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andExeOrderEqualTo(Integer value) {
            addCriterion("EXE_ORDER =", value, "exeOrder");
            return (Criteria) this;
        }

        public Criteria andExeOrderNotEqualTo(Integer value) {
            addCriterion("EXE_ORDER <>", value, "exeOrder");
            return (Criteria) this;
        }

        public Criteria andExeOrderGreaterThan(Integer value) {
            addCriterion("EXE_ORDER >", value, "exeOrder");
            return (Criteria) this;
        }

        public Criteria andExeOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("EXE_ORDER >=", value, "exeOrder");
            return (Criteria) this;
        }

        public Criteria andExeOrderLessThan(Integer value) {
            addCriterion("EXE_ORDER <", value, "exeOrder");
            return (Criteria) this;
        }

        public Criteria andExeOrderLessThanOrEqualTo(Integer value) {
            addCriterion("EXE_ORDER <=", value, "exeOrder");
            return (Criteria) this;
        }

        public Criteria andExeOrderIn(List<Integer> values) {
            addCriterion("EXE_ORDER in", values, "exeOrder");
            return (Criteria) this;
        }

        public Criteria andExeOrderNotIn(List<Integer> values) {
            addCriterion("EXE_ORDER not in", values, "exeOrder");
            return (Criteria) this;
        }

        public Criteria andExeOrderBetween(Integer value1, Integer value2) {
            addCriterion("EXE_ORDER between", value1, value2, "exeOrder");
            return (Criteria) this;
        }

        public Criteria andExeOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("EXE_ORDER not between", value1, value2, "exeOrder");
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

        public Criteria andHandlerNameLikeInsensitive(String value) {
            addCriterion("upper(HANDLER_NAME) like", value.toUpperCase(), "handlerName");
            return (Criteria) this;
        }

        public Criteria andSyncFlagLikeInsensitive(String value) {
            addCriterion("upper(SYNC_FLAG) like", value.toUpperCase(), "syncFlag");
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