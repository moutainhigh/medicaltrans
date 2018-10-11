package com.segi.uhomecp.medicaltrans.trans.mttask.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.segi.uhomecp.common.model.AbstractCriteria;

public class MtTaskExecutorsCriteria extends AbstractCriteria {
    
	/**  描述   (@author: zhangyang@segimail.com) */      
	    
	private static final long serialVersionUID = 1L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MtTaskExecutorsCriteria() {
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

        public Criteria andTaskExeIdIsNull() {
            addCriterion("TASK_EXE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTaskExeIdIsNotNull() {
            addCriterion("TASK_EXE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTaskExeIdEqualTo(Integer value) {
            addCriterion("TASK_EXE_ID =", value, "taskExeId");
            return (Criteria) this;
        }

        public Criteria andTaskExeIdNotEqualTo(Integer value) {
            addCriterion("TASK_EXE_ID <>", value, "taskExeId");
            return (Criteria) this;
        }

        public Criteria andTaskExeIdGreaterThan(Integer value) {
            addCriterion("TASK_EXE_ID >", value, "taskExeId");
            return (Criteria) this;
        }

        public Criteria andTaskExeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TASK_EXE_ID >=", value, "taskExeId");
            return (Criteria) this;
        }

        public Criteria andTaskExeIdLessThan(Integer value) {
            addCriterion("TASK_EXE_ID <", value, "taskExeId");
            return (Criteria) this;
        }

        public Criteria andTaskExeIdLessThanOrEqualTo(Integer value) {
            addCriterion("TASK_EXE_ID <=", value, "taskExeId");
            return (Criteria) this;
        }

        public Criteria andTaskExeIdIn(List<Integer> values) {
            addCriterion("TASK_EXE_ID in", values, "taskExeId");
            return (Criteria) this;
        }

        public Criteria andTaskExeIdNotIn(List<Integer> values) {
            addCriterion("TASK_EXE_ID not in", values, "taskExeId");
            return (Criteria) this;
        }

        public Criteria andTaskExeIdBetween(Integer value1, Integer value2) {
            addCriterion("TASK_EXE_ID between", value1, value2, "taskExeId");
            return (Criteria) this;
        }

        public Criteria andTaskExeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TASK_EXE_ID not between", value1, value2, "taskExeId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("TASK_ID is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("TASK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(Integer value) {
            addCriterion("TASK_ID =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(Integer value) {
            addCriterion("TASK_ID <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(Integer value) {
            addCriterion("TASK_ID >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TASK_ID >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(Integer value) {
            addCriterion("TASK_ID <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(Integer value) {
            addCriterion("TASK_ID <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<Integer> values) {
            addCriterion("TASK_ID in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<Integer> values) {
            addCriterion("TASK_ID not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(Integer value1, Integer value2) {
            addCriterion("TASK_ID between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TASK_ID not between", value1, value2, "taskId");
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

        public Criteria andExeUserIdIsNull() {
            addCriterion("EXE_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andExeUserIdIsNotNull() {
            addCriterion("EXE_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExeUserIdEqualTo(Integer value) {
            addCriterion("EXE_USER_ID =", value, "exeUserId");
            return (Criteria) this;
        }

        public Criteria andExeUserIdNotEqualTo(Integer value) {
            addCriterion("EXE_USER_ID <>", value, "exeUserId");
            return (Criteria) this;
        }

        public Criteria andExeUserIdGreaterThan(Integer value) {
            addCriterion("EXE_USER_ID >", value, "exeUserId");
            return (Criteria) this;
        }

        public Criteria andExeUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("EXE_USER_ID >=", value, "exeUserId");
            return (Criteria) this;
        }

        public Criteria andExeUserIdLessThan(Integer value) {
            addCriterion("EXE_USER_ID <", value, "exeUserId");
            return (Criteria) this;
        }

        public Criteria andExeUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("EXE_USER_ID <=", value, "exeUserId");
            return (Criteria) this;
        }

        public Criteria andExeUserIdIn(List<Integer> values) {
            addCriterion("EXE_USER_ID in", values, "exeUserId");
            return (Criteria) this;
        }

        public Criteria andExeUserIdNotIn(List<Integer> values) {
            addCriterion("EXE_USER_ID not in", values, "exeUserId");
            return (Criteria) this;
        }

        public Criteria andExeUserIdBetween(Integer value1, Integer value2) {
            addCriterion("EXE_USER_ID between", value1, value2, "exeUserId");
            return (Criteria) this;
        }

        public Criteria andExeUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("EXE_USER_ID not between", value1, value2, "exeUserId");
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

        public Criteria andIsExeEndUserIsNull() {
            addCriterion("IS_EXE_END_USER is null");
            return (Criteria) this;
        }

        public Criteria andIsExeEndUserIsNotNull() {
            addCriterion("IS_EXE_END_USER is not null");
            return (Criteria) this;
        }

        public Criteria andIsExeEndUserEqualTo(String value) {
            addCriterion("IS_EXE_END_USER =", value, "isExeEndUser");
            return (Criteria) this;
        }

        public Criteria andIsExeEndUserNotEqualTo(String value) {
            addCriterion("IS_EXE_END_USER <>", value, "isExeEndUser");
            return (Criteria) this;
        }

        public Criteria andIsExeEndUserGreaterThan(String value) {
            addCriterion("IS_EXE_END_USER >", value, "isExeEndUser");
            return (Criteria) this;
        }

        public Criteria andIsExeEndUserGreaterThanOrEqualTo(String value) {
            addCriterion("IS_EXE_END_USER >=", value, "isExeEndUser");
            return (Criteria) this;
        }

        public Criteria andIsExeEndUserLessThan(String value) {
            addCriterion("IS_EXE_END_USER <", value, "isExeEndUser");
            return (Criteria) this;
        }

        public Criteria andIsExeEndUserLessThanOrEqualTo(String value) {
            addCriterion("IS_EXE_END_USER <=", value, "isExeEndUser");
            return (Criteria) this;
        }

        public Criteria andIsExeEndUserLike(String value) {
            addCriterion("IS_EXE_END_USER like", value, "isExeEndUser");
            return (Criteria) this;
        }

        public Criteria andIsExeEndUserNotLike(String value) {
            addCriterion("IS_EXE_END_USER not like", value, "isExeEndUser");
            return (Criteria) this;
        }

        public Criteria andIsExeEndUserIn(List<String> values) {
            addCriterion("IS_EXE_END_USER in", values, "isExeEndUser");
            return (Criteria) this;
        }

        public Criteria andIsExeEndUserNotIn(List<String> values) {
            addCriterion("IS_EXE_END_USER not in", values, "isExeEndUser");
            return (Criteria) this;
        }

        public Criteria andIsExeEndUserBetween(String value1, String value2) {
            addCriterion("IS_EXE_END_USER between", value1, value2, "isExeEndUser");
            return (Criteria) this;
        }

        public Criteria andIsExeEndUserNotBetween(String value1, String value2) {
            addCriterion("IS_EXE_END_USER not between", value1, value2, "isExeEndUser");
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

        public Criteria andTaskStatusIsNull() {
            addCriterion("TASK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIsNotNull() {
            addCriterion("TASK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusEqualTo(String value) {
            addCriterion("TASK_STATUS =", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotEqualTo(String value) {
            addCriterion("TASK_STATUS <>", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThan(String value) {
            addCriterion("TASK_STATUS >", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_STATUS >=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThan(String value) {
            addCriterion("TASK_STATUS <", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThanOrEqualTo(String value) {
            addCriterion("TASK_STATUS <=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLike(String value) {
            addCriterion("TASK_STATUS like", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotLike(String value) {
            addCriterion("TASK_STATUS not like", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIn(List<String> values) {
            addCriterion("TASK_STATUS in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotIn(List<String> values) {
            addCriterion("TASK_STATUS not in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusBetween(String value1, String value2) {
            addCriterion("TASK_STATUS between", value1, value2, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotBetween(String value1, String value2) {
            addCriterion("TASK_STATUS not between", value1, value2, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andEvaluateIsNull() {
            addCriterion("EVALUATE is null");
            return (Criteria) this;
        }

        public Criteria andEvaluateIsNotNull() {
            addCriterion("EVALUATE is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluateEqualTo(Short value) {
            addCriterion("EVALUATE =", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateNotEqualTo(Short value) {
            addCriterion("EVALUATE <>", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateGreaterThan(Short value) {
            addCriterion("EVALUATE >", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateGreaterThanOrEqualTo(Short value) {
            addCriterion("EVALUATE >=", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateLessThan(Short value) {
            addCriterion("EVALUATE <", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateLessThanOrEqualTo(Short value) {
            addCriterion("EVALUATE <=", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateIn(List<Short> values) {
            addCriterion("EVALUATE in", values, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateNotIn(List<Short> values) {
            addCriterion("EVALUATE not in", values, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateBetween(Short value1, Short value2) {
            addCriterion("EVALUATE between", value1, value2, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateNotBetween(Short value1, Short value2) {
            addCriterion("EVALUATE not between", value1, value2, "evaluate");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNull() {
            addCriterion("TASK_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNotNull() {
            addCriterion("TASK_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeEqualTo(String value) {
            addCriterion("TASK_TYPE =", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotEqualTo(String value) {
            addCriterion("TASK_TYPE <>", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThan(String value) {
            addCriterion("TASK_TYPE >", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_TYPE >=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThan(String value) {
            addCriterion("TASK_TYPE <", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThanOrEqualTo(String value) {
            addCriterion("TASK_TYPE <=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLike(String value) {
            addCriterion("TASK_TYPE like", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotLike(String value) {
            addCriterion("TASK_TYPE not like", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIn(List<String> values) {
            addCriterion("TASK_TYPE in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotIn(List<String> values) {
            addCriterion("TASK_TYPE not in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeBetween(String value1, String value2) {
            addCriterion("TASK_TYPE between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotBetween(String value1, String value2) {
            addCriterion("TASK_TYPE not between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andIsTimeOutIsNull() {
            addCriterion("IS_TIME_OUT is null");
            return (Criteria) this;
        }

        public Criteria andIsTimeOutIsNotNull() {
            addCriterion("IS_TIME_OUT is not null");
            return (Criteria) this;
        }

        public Criteria andIsTimeOutEqualTo(String value) {
            addCriterion("IS_TIME_OUT =", value, "isTimeOut");
            return (Criteria) this;
        }

        public Criteria andIsTimeOutNotEqualTo(String value) {
            addCriterion("IS_TIME_OUT <>", value, "isTimeOut");
            return (Criteria) this;
        }

        public Criteria andIsTimeOutGreaterThan(String value) {
            addCriterion("IS_TIME_OUT >", value, "isTimeOut");
            return (Criteria) this;
        }

        public Criteria andIsTimeOutGreaterThanOrEqualTo(String value) {
            addCriterion("IS_TIME_OUT >=", value, "isTimeOut");
            return (Criteria) this;
        }

        public Criteria andIsTimeOutLessThan(String value) {
            addCriterion("IS_TIME_OUT <", value, "isTimeOut");
            return (Criteria) this;
        }

        public Criteria andIsTimeOutLessThanOrEqualTo(String value) {
            addCriterion("IS_TIME_OUT <=", value, "isTimeOut");
            return (Criteria) this;
        }

        public Criteria andIsTimeOutLike(String value) {
            addCriterion("IS_TIME_OUT like", value, "isTimeOut");
            return (Criteria) this;
        }

        public Criteria andIsTimeOutNotLike(String value) {
            addCriterion("IS_TIME_OUT not like", value, "isTimeOut");
            return (Criteria) this;
        }

        public Criteria andIsTimeOutIn(List<String> values) {
            addCriterion("IS_TIME_OUT in", values, "isTimeOut");
            return (Criteria) this;
        }

        public Criteria andIsTimeOutNotIn(List<String> values) {
            addCriterion("IS_TIME_OUT not in", values, "isTimeOut");
            return (Criteria) this;
        }

        public Criteria andIsTimeOutBetween(String value1, String value2) {
            addCriterion("IS_TIME_OUT between", value1, value2, "isTimeOut");
            return (Criteria) this;
        }

        public Criteria andIsTimeOutNotBetween(String value1, String value2) {
            addCriterion("IS_TIME_OUT not between", value1, value2, "isTimeOut");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("BEGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("BEGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Long value) {
            addCriterion("BEGIN_TIME =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Long value) {
            addCriterion("BEGIN_TIME <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Long value) {
            addCriterion("BEGIN_TIME >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("BEGIN_TIME >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Long value) {
            addCriterion("BEGIN_TIME <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Long value) {
            addCriterion("BEGIN_TIME <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Long> values) {
            addCriterion("BEGIN_TIME in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Long> values) {
            addCriterion("BEGIN_TIME not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Long value1, Long value2) {
            addCriterion("BEGIN_TIME between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Long value1, Long value2) {
            addCriterion("BEGIN_TIME not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andExeBeginTimeIsNull() {
            addCriterion("EXE_BEGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExeBeginTimeIsNotNull() {
            addCriterion("EXE_BEGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExeBeginTimeEqualTo(Date value) {
            addCriterion("EXE_BEGIN_TIME =", value, "exeBeginTime");
            return (Criteria) this;
        }

        public Criteria andExeBeginTimeNotEqualTo(Date value) {
            addCriterion("EXE_BEGIN_TIME <>", value, "exeBeginTime");
            return (Criteria) this;
        }

        public Criteria andExeBeginTimeGreaterThan(Date value) {
            addCriterion("EXE_BEGIN_TIME >", value, "exeBeginTime");
            return (Criteria) this;
        }

        public Criteria andExeBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("EXE_BEGIN_TIME >=", value, "exeBeginTime");
            return (Criteria) this;
        }

        public Criteria andExeBeginTimeLessThan(Date value) {
            addCriterion("EXE_BEGIN_TIME <", value, "exeBeginTime");
            return (Criteria) this;
        }

        public Criteria andExeBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("EXE_BEGIN_TIME <=", value, "exeBeginTime");
            return (Criteria) this;
        }

        public Criteria andExeBeginTimeIn(List<Date> values) {
            addCriterion("EXE_BEGIN_TIME in", values, "exeBeginTime");
            return (Criteria) this;
        }

        public Criteria andExeBeginTimeNotIn(List<Date> values) {
            addCriterion("EXE_BEGIN_TIME not in", values, "exeBeginTime");
            return (Criteria) this;
        }

        public Criteria andExeBeginTimeBetween(Date value1, Date value2) {
            addCriterion("EXE_BEGIN_TIME between", value1, value2, "exeBeginTime");
            return (Criteria) this;
        }

        public Criteria andExeBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("EXE_BEGIN_TIME not between", value1, value2, "exeBeginTime");
            return (Criteria) this;
        }

        public Criteria andExeEndTimeIsNull() {
            addCriterion("EXE_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExeEndTimeIsNotNull() {
            addCriterion("EXE_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExeEndTimeEqualTo(Date value) {
            addCriterion("EXE_END_TIME =", value, "exeEndTime");
            return (Criteria) this;
        }

        public Criteria andExeEndTimeNotEqualTo(Date value) {
            addCriterion("EXE_END_TIME <>", value, "exeEndTime");
            return (Criteria) this;
        }

        public Criteria andExeEndTimeGreaterThan(Date value) {
            addCriterion("EXE_END_TIME >", value, "exeEndTime");
            return (Criteria) this;
        }

        public Criteria andExeEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("EXE_END_TIME >=", value, "exeEndTime");
            return (Criteria) this;
        }

        public Criteria andExeEndTimeLessThan(Date value) {
            addCriterion("EXE_END_TIME <", value, "exeEndTime");
            return (Criteria) this;
        }

        public Criteria andExeEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("EXE_END_TIME <=", value, "exeEndTime");
            return (Criteria) this;
        }

        public Criteria andExeEndTimeIn(List<Date> values) {
            addCriterion("EXE_END_TIME in", values, "exeEndTime");
            return (Criteria) this;
        }

        public Criteria andExeEndTimeNotIn(List<Date> values) {
            addCriterion("EXE_END_TIME not in", values, "exeEndTime");
            return (Criteria) this;
        }

        public Criteria andExeEndTimeBetween(Date value1, Date value2) {
            addCriterion("EXE_END_TIME between", value1, value2, "exeEndTime");
            return (Criteria) this;
        }

        public Criteria andExeEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("EXE_END_TIME not between", value1, value2, "exeEndTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("SEND_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("SEND_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("SEND_TIME =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("SEND_TIME <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("SEND_TIME >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SEND_TIME >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("SEND_TIME <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("SEND_TIME <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("SEND_TIME in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("SEND_TIME not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("SEND_TIME between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("SEND_TIME not between", value1, value2, "sendTime");
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

        public Criteria andUrgencyIsNull() {
            addCriterion("URGENCY is null");
            return (Criteria) this;
        }

        public Criteria andUrgencyIsNotNull() {
            addCriterion("URGENCY is not null");
            return (Criteria) this;
        }

        public Criteria andUrgencyEqualTo(String value) {
            addCriterion("URGENCY =", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyNotEqualTo(String value) {
            addCriterion("URGENCY <>", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyGreaterThan(String value) {
            addCriterion("URGENCY >", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyGreaterThanOrEqualTo(String value) {
            addCriterion("URGENCY >=", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyLessThan(String value) {
            addCriterion("URGENCY <", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyLessThanOrEqualTo(String value) {
            addCriterion("URGENCY <=", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyLike(String value) {
            addCriterion("URGENCY like", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyNotLike(String value) {
            addCriterion("URGENCY not like", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyIn(List<String> values) {
            addCriterion("URGENCY in", values, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyNotIn(List<String> values) {
            addCriterion("URGENCY not in", values, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyBetween(String value1, String value2) {
            addCriterion("URGENCY between", value1, value2, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyNotBetween(String value1, String value2) {
            addCriterion("URGENCY not between", value1, value2, "urgency");
            return (Criteria) this;
        }

        public Criteria andTransTypeParentCodeIsNull() {
            addCriterion("TRANS_TYPE_PARENT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTransTypeParentCodeIsNotNull() {
            addCriterion("TRANS_TYPE_PARENT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTransTypeParentCodeEqualTo(String value) {
            addCriterion("TRANS_TYPE_PARENT_CODE =", value, "transTypeParentCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeParentCodeNotEqualTo(String value) {
            addCriterion("TRANS_TYPE_PARENT_CODE <>", value, "transTypeParentCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeParentCodeGreaterThan(String value) {
            addCriterion("TRANS_TYPE_PARENT_CODE >", value, "transTypeParentCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeParentCodeGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_TYPE_PARENT_CODE >=", value, "transTypeParentCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeParentCodeLessThan(String value) {
            addCriterion("TRANS_TYPE_PARENT_CODE <", value, "transTypeParentCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeParentCodeLessThanOrEqualTo(String value) {
            addCriterion("TRANS_TYPE_PARENT_CODE <=", value, "transTypeParentCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeParentCodeLike(String value) {
            addCriterion("TRANS_TYPE_PARENT_CODE like", value, "transTypeParentCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeParentCodeNotLike(String value) {
            addCriterion("TRANS_TYPE_PARENT_CODE not like", value, "transTypeParentCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeParentCodeIn(List<String> values) {
            addCriterion("TRANS_TYPE_PARENT_CODE in", values, "transTypeParentCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeParentCodeNotIn(List<String> values) {
            addCriterion("TRANS_TYPE_PARENT_CODE not in", values, "transTypeParentCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeParentCodeBetween(String value1, String value2) {
            addCriterion("TRANS_TYPE_PARENT_CODE between", value1, value2, "transTypeParentCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeParentCodeNotBetween(String value1, String value2) {
            addCriterion("TRANS_TYPE_PARENT_CODE not between", value1, value2, "transTypeParentCode");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Long value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Long value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Long value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Long value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Long value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Long> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Long> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Long value1, Long value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Long value1, Long value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andIsExeEndUserLikeInsensitive(String value) {
            addCriterion("upper(IS_EXE_END_USER) like", value.toUpperCase(), "isExeEndUser");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLikeInsensitive(String value) {
            addCriterion("upper(TASK_STATUS) like", value.toUpperCase(), "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLikeInsensitive(String value) {
            addCriterion("upper(TASK_TYPE) like", value.toUpperCase(), "taskType");
            return (Criteria) this;
        }

        public Criteria andIsTimeOutLikeInsensitive(String value) {
            addCriterion("upper(IS_TIME_OUT) like", value.toUpperCase(), "isTimeOut");
            return (Criteria) this;
        }

        public Criteria andUrgencyLikeInsensitive(String value) {
            addCriterion("upper(URGENCY) like", value.toUpperCase(), "urgency");
            return (Criteria) this;
        }

        public Criteria andTransTypeParentCodeLikeInsensitive(String value) {
            addCriterion("upper(TRANS_TYPE_PARENT_CODE) like", value.toUpperCase(), "transTypeParentCode");
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