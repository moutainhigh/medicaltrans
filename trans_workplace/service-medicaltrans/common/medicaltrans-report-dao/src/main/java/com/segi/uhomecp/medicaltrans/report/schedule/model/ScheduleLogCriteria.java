package com.segi.uhomecp.medicaltrans.report.schedule.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduleLogCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScheduleLogCriteria() {
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

        public Criteria andEndDateIsNull() {
            addCriterion("END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterion("END_DATE =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterion("END_DATE <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterion("END_DATE >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("END_DATE >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterion("END_DATE <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterion("END_DATE <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterion("END_DATE in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterion("END_DATE not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterion("END_DATE between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterion("END_DATE not between", value1, value2, "endDate");
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

        public Criteria andTaskCountIsNull() {
            addCriterion("TASK_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andTaskCountIsNotNull() {
            addCriterion("TASK_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTaskCountEqualTo(Integer value) {
            addCriterion("TASK_COUNT =", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountNotEqualTo(Integer value) {
            addCriterion("TASK_COUNT <>", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountGreaterThan(Integer value) {
            addCriterion("TASK_COUNT >", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("TASK_COUNT >=", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountLessThan(Integer value) {
            addCriterion("TASK_COUNT <", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountLessThanOrEqualTo(Integer value) {
            addCriterion("TASK_COUNT <=", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountIn(List<Integer> values) {
            addCriterion("TASK_COUNT in", values, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountNotIn(List<Integer> values) {
            addCriterion("TASK_COUNT not in", values, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountBetween(Integer value1, Integer value2) {
            addCriterion("TASK_COUNT between", value1, value2, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountNotBetween(Integer value1, Integer value2) {
            addCriterion("TASK_COUNT not between", value1, value2, "taskCount");
            return (Criteria) this;
        }

        public Criteria andExeTimeIsNull() {
            addCriterion("EXE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExeTimeIsNotNull() {
            addCriterion("EXE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExeTimeEqualTo(Integer value) {
            addCriterion("EXE_TIME =", value, "exeTime");
            return (Criteria) this;
        }

        public Criteria andExeTimeNotEqualTo(Integer value) {
            addCriterion("EXE_TIME <>", value, "exeTime");
            return (Criteria) this;
        }

        public Criteria andExeTimeGreaterThan(Integer value) {
            addCriterion("EXE_TIME >", value, "exeTime");
            return (Criteria) this;
        }

        public Criteria andExeTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("EXE_TIME >=", value, "exeTime");
            return (Criteria) this;
        }

        public Criteria andExeTimeLessThan(Integer value) {
            addCriterion("EXE_TIME <", value, "exeTime");
            return (Criteria) this;
        }

        public Criteria andExeTimeLessThanOrEqualTo(Integer value) {
            addCriterion("EXE_TIME <=", value, "exeTime");
            return (Criteria) this;
        }

        public Criteria andExeTimeIn(List<Integer> values) {
            addCriterion("EXE_TIME in", values, "exeTime");
            return (Criteria) this;
        }

        public Criteria andExeTimeNotIn(List<Integer> values) {
            addCriterion("EXE_TIME not in", values, "exeTime");
            return (Criteria) this;
        }

        public Criteria andExeTimeBetween(Integer value1, Integer value2) {
            addCriterion("EXE_TIME between", value1, value2, "exeTime");
            return (Criteria) this;
        }

        public Criteria andExeTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("EXE_TIME not between", value1, value2, "exeTime");
            return (Criteria) this;
        }

        public Criteria andErrorMessageIsNull() {
            addCriterion("ERROR_MESSAGE is null");
            return (Criteria) this;
        }

        public Criteria andErrorMessageIsNotNull() {
            addCriterion("ERROR_MESSAGE is not null");
            return (Criteria) this;
        }

        public Criteria andErrorMessageEqualTo(String value) {
            addCriterion("ERROR_MESSAGE =", value, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageNotEqualTo(String value) {
            addCriterion("ERROR_MESSAGE <>", value, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageGreaterThan(String value) {
            addCriterion("ERROR_MESSAGE >", value, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageGreaterThanOrEqualTo(String value) {
            addCriterion("ERROR_MESSAGE >=", value, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageLessThan(String value) {
            addCriterion("ERROR_MESSAGE <", value, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageLessThanOrEqualTo(String value) {
            addCriterion("ERROR_MESSAGE <=", value, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageLike(String value) {
            addCriterion("ERROR_MESSAGE like", value, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageNotLike(String value) {
            addCriterion("ERROR_MESSAGE not like", value, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageIn(List<String> values) {
            addCriterion("ERROR_MESSAGE in", values, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageNotIn(List<String> values) {
            addCriterion("ERROR_MESSAGE not in", values, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageBetween(String value1, String value2) {
            addCriterion("ERROR_MESSAGE between", value1, value2, "errorMessage");
            return (Criteria) this;
        }

        public Criteria andErrorMessageNotBetween(String value1, String value2) {
            addCriterion("ERROR_MESSAGE not between", value1, value2, "errorMessage");
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

        public Criteria andExcTimeIsNull() {
            addCriterion("EXC_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExcTimeIsNotNull() {
            addCriterion("EXC_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExcTimeEqualTo(Date value) {
            addCriterion("EXC_TIME =", value, "excTime");
            return (Criteria) this;
        }

        public Criteria andExcTimeNotEqualTo(Date value) {
            addCriterion("EXC_TIME <>", value, "excTime");
            return (Criteria) this;
        }

        public Criteria andExcTimeGreaterThan(Date value) {
            addCriterion("EXC_TIME >", value, "excTime");
            return (Criteria) this;
        }

        public Criteria andExcTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("EXC_TIME >=", value, "excTime");
            return (Criteria) this;
        }

        public Criteria andExcTimeLessThan(Date value) {
            addCriterion("EXC_TIME <", value, "excTime");
            return (Criteria) this;
        }

        public Criteria andExcTimeLessThanOrEqualTo(Date value) {
            addCriterion("EXC_TIME <=", value, "excTime");
            return (Criteria) this;
        }

        public Criteria andExcTimeIn(List<Date> values) {
            addCriterion("EXC_TIME in", values, "excTime");
            return (Criteria) this;
        }

        public Criteria andExcTimeNotIn(List<Date> values) {
            addCriterion("EXC_TIME not in", values, "excTime");
            return (Criteria) this;
        }

        public Criteria andExcTimeBetween(Date value1, Date value2) {
            addCriterion("EXC_TIME between", value1, value2, "excTime");
            return (Criteria) this;
        }

        public Criteria andExcTimeNotBetween(Date value1, Date value2) {
            addCriterion("EXC_TIME not between", value1, value2, "excTime");
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

        public Criteria andRunningStatusLikeInsensitive(String value) {
            addCriterion("upper(RUNNING_STATUS) like", value.toUpperCase(), "runningStatus");
            return (Criteria) this;
        }

        public Criteria andErrorMessageLikeInsensitive(String value) {
            addCriterion("upper(ERROR_MESSAGE) like", value.toUpperCase(), "errorMessage");
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