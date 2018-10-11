package com.segi.uhomecp.medicaltrans.base.taskloop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.segi.uhomecp.common.model.AbstractCriteria;

public class MtTaskLoopCriteria extends AbstractCriteria {
    
	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = -571233403516289388L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MtTaskLoopCriteria() {
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

        public Criteria andTaskLoopIdIsNull() {
            addCriterion("TASK_LOOP_ID is null");
            return (Criteria) this;
        }

        public Criteria andTaskLoopIdIsNotNull() {
            addCriterion("TASK_LOOP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTaskLoopIdEqualTo(Integer value) {
            addCriterion("TASK_LOOP_ID =", value, "taskLoopId");
            return (Criteria) this;
        }

        public Criteria andTaskLoopIdNotEqualTo(Integer value) {
            addCriterion("TASK_LOOP_ID <>", value, "taskLoopId");
            return (Criteria) this;
        }

        public Criteria andTaskLoopIdGreaterThan(Integer value) {
            addCriterion("TASK_LOOP_ID >", value, "taskLoopId");
            return (Criteria) this;
        }

        public Criteria andTaskLoopIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TASK_LOOP_ID >=", value, "taskLoopId");
            return (Criteria) this;
        }

        public Criteria andTaskLoopIdLessThan(Integer value) {
            addCriterion("TASK_LOOP_ID <", value, "taskLoopId");
            return (Criteria) this;
        }

        public Criteria andTaskLoopIdLessThanOrEqualTo(Integer value) {
            addCriterion("TASK_LOOP_ID <=", value, "taskLoopId");
            return (Criteria) this;
        }

        public Criteria andTaskLoopIdIn(List<Integer> values) {
            addCriterion("TASK_LOOP_ID in", values, "taskLoopId");
            return (Criteria) this;
        }

        public Criteria andTaskLoopIdNotIn(List<Integer> values) {
            addCriterion("TASK_LOOP_ID not in", values, "taskLoopId");
            return (Criteria) this;
        }

        public Criteria andTaskLoopIdBetween(Integer value1, Integer value2) {
            addCriterion("TASK_LOOP_ID between", value1, value2, "taskLoopId");
            return (Criteria) this;
        }

        public Criteria andTaskLoopIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TASK_LOOP_ID not between", value1, value2, "taskLoopId");
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

        public Criteria andTransTypeIdIsNull() {
            addCriterion("TRANS_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTransTypeIdIsNotNull() {
            addCriterion("TRANS_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTransTypeIdEqualTo(Integer value) {
            addCriterion("TRANS_TYPE_ID =", value, "transTypeId");
            return (Criteria) this;
        }

        public Criteria andTransTypeIdNotEqualTo(Integer value) {
            addCriterion("TRANS_TYPE_ID <>", value, "transTypeId");
            return (Criteria) this;
        }

        public Criteria andTransTypeIdGreaterThan(Integer value) {
            addCriterion("TRANS_TYPE_ID >", value, "transTypeId");
            return (Criteria) this;
        }

        public Criteria andTransTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TRANS_TYPE_ID >=", value, "transTypeId");
            return (Criteria) this;
        }

        public Criteria andTransTypeIdLessThan(Integer value) {
            addCriterion("TRANS_TYPE_ID <", value, "transTypeId");
            return (Criteria) this;
        }

        public Criteria andTransTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("TRANS_TYPE_ID <=", value, "transTypeId");
            return (Criteria) this;
        }

        public Criteria andTransTypeIdIn(List<Integer> values) {
            addCriterion("TRANS_TYPE_ID in", values, "transTypeId");
            return (Criteria) this;
        }

        public Criteria andTransTypeIdNotIn(List<Integer> values) {
            addCriterion("TRANS_TYPE_ID not in", values, "transTypeId");
            return (Criteria) this;
        }

        public Criteria andTransTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("TRANS_TYPE_ID between", value1, value2, "transTypeId");
            return (Criteria) this;
        }

        public Criteria andTransTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TRANS_TYPE_ID not between", value1, value2, "transTypeId");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNull() {
            addCriterion("TASK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNotNull() {
            addCriterion("TASK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTaskNameEqualTo(String value) {
            addCriterion("TASK_NAME =", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotEqualTo(String value) {
            addCriterion("TASK_NAME <>", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThan(String value) {
            addCriterion("TASK_NAME >", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_NAME >=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThan(String value) {
            addCriterion("TASK_NAME <", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThanOrEqualTo(String value) {
            addCriterion("TASK_NAME <=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLike(String value) {
            addCriterion("TASK_NAME like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotLike(String value) {
            addCriterion("TASK_NAME not like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameIn(List<String> values) {
            addCriterion("TASK_NAME in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotIn(List<String> values) {
            addCriterion("TASK_NAME not in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameBetween(String value1, String value2) {
            addCriterion("TASK_NAME between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotBetween(String value1, String value2) {
            addCriterion("TASK_NAME not between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTransToolsIsNull() {
            addCriterion("TRANS_TOOLS is null");
            return (Criteria) this;
        }

        public Criteria andTransToolsIsNotNull() {
            addCriterion("TRANS_TOOLS is not null");
            return (Criteria) this;
        }

        public Criteria andTransToolsEqualTo(String value) {
            addCriterion("TRANS_TOOLS =", value, "transTools");
            return (Criteria) this;
        }

        public Criteria andTransToolsNotEqualTo(String value) {
            addCriterion("TRANS_TOOLS <>", value, "transTools");
            return (Criteria) this;
        }

        public Criteria andTransToolsGreaterThan(String value) {
            addCriterion("TRANS_TOOLS >", value, "transTools");
            return (Criteria) this;
        }

        public Criteria andTransToolsGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_TOOLS >=", value, "transTools");
            return (Criteria) this;
        }

        public Criteria andTransToolsLessThan(String value) {
            addCriterion("TRANS_TOOLS <", value, "transTools");
            return (Criteria) this;
        }

        public Criteria andTransToolsLessThanOrEqualTo(String value) {
            addCriterion("TRANS_TOOLS <=", value, "transTools");
            return (Criteria) this;
        }

        public Criteria andTransToolsLike(String value) {
            addCriterion("TRANS_TOOLS like", value, "transTools");
            return (Criteria) this;
        }

        public Criteria andTransToolsNotLike(String value) {
            addCriterion("TRANS_TOOLS not like", value, "transTools");
            return (Criteria) this;
        }

        public Criteria andTransToolsIn(List<String> values) {
            addCriterion("TRANS_TOOLS in", values, "transTools");
            return (Criteria) this;
        }

        public Criteria andTransToolsNotIn(List<String> values) {
            addCriterion("TRANS_TOOLS not in", values, "transTools");
            return (Criteria) this;
        }

        public Criteria andTransToolsBetween(String value1, String value2) {
            addCriterion("TRANS_TOOLS between", value1, value2, "transTools");
            return (Criteria) this;
        }

        public Criteria andTransToolsNotBetween(String value1, String value2) {
            addCriterion("TRANS_TOOLS not between", value1, value2, "transTools");
            return (Criteria) this;
        }

        public Criteria andLoopTypeIsNull() {
            addCriterion("LOOP_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andLoopTypeIsNotNull() {
            addCriterion("LOOP_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andLoopTypeEqualTo(String value) {
            addCriterion("LOOP_TYPE =", value, "loopType");
            return (Criteria) this;
        }

        public Criteria andLoopTypeNotEqualTo(String value) {
            addCriterion("LOOP_TYPE <>", value, "loopType");
            return (Criteria) this;
        }

        public Criteria andLoopTypeGreaterThan(String value) {
            addCriterion("LOOP_TYPE >", value, "loopType");
            return (Criteria) this;
        }

        public Criteria andLoopTypeGreaterThanOrEqualTo(String value) {
            addCriterion("LOOP_TYPE >=", value, "loopType");
            return (Criteria) this;
        }

        public Criteria andLoopTypeLessThan(String value) {
            addCriterion("LOOP_TYPE <", value, "loopType");
            return (Criteria) this;
        }

        public Criteria andLoopTypeLessThanOrEqualTo(String value) {
            addCriterion("LOOP_TYPE <=", value, "loopType");
            return (Criteria) this;
        }

        public Criteria andLoopTypeLike(String value) {
            addCriterion("LOOP_TYPE like", value, "loopType");
            return (Criteria) this;
        }

        public Criteria andLoopTypeNotLike(String value) {
            addCriterion("LOOP_TYPE not like", value, "loopType");
            return (Criteria) this;
        }

        public Criteria andLoopTypeIn(List<String> values) {
            addCriterion("LOOP_TYPE in", values, "loopType");
            return (Criteria) this;
        }

        public Criteria andLoopTypeNotIn(List<String> values) {
            addCriterion("LOOP_TYPE not in", values, "loopType");
            return (Criteria) this;
        }

        public Criteria andLoopTypeBetween(String value1, String value2) {
            addCriterion("LOOP_TYPE between", value1, value2, "loopType");
            return (Criteria) this;
        }

        public Criteria andLoopTypeNotBetween(String value1, String value2) {
            addCriterion("LOOP_TYPE not between", value1, value2, "loopType");
            return (Criteria) this;
        }

        public Criteria andTaskBeginTimeIsNull() {
            addCriterion("TASK_BEGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTaskBeginTimeIsNotNull() {
            addCriterion("TASK_BEGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTaskBeginTimeEqualTo(Integer value) {
            addCriterion("TASK_BEGIN_TIME =", value, "taskBeginTime");
            return (Criteria) this;
        }

        public Criteria andTaskBeginTimeNotEqualTo(Integer value) {
            addCriterion("TASK_BEGIN_TIME <>", value, "taskBeginTime");
            return (Criteria) this;
        }

        public Criteria andTaskBeginTimeGreaterThan(Integer value) {
            addCriterion("TASK_BEGIN_TIME >", value, "taskBeginTime");
            return (Criteria) this;
        }

        public Criteria andTaskBeginTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("TASK_BEGIN_TIME >=", value, "taskBeginTime");
            return (Criteria) this;
        }

        public Criteria andTaskBeginTimeLessThan(Integer value) {
            addCriterion("TASK_BEGIN_TIME <", value, "taskBeginTime");
            return (Criteria) this;
        }

        public Criteria andTaskBeginTimeLessThanOrEqualTo(Integer value) {
            addCriterion("TASK_BEGIN_TIME <=", value, "taskBeginTime");
            return (Criteria) this;
        }

        public Criteria andTaskBeginTimeIn(List<Integer> values) {
            addCriterion("TASK_BEGIN_TIME in", values, "taskBeginTime");
            return (Criteria) this;
        }

        public Criteria andTaskBeginTimeNotIn(List<Integer> values) {
            addCriterion("TASK_BEGIN_TIME not in", values, "taskBeginTime");
            return (Criteria) this;
        }

        public Criteria andTaskBeginTimeBetween(Integer value1, Integer value2) {
            addCriterion("TASK_BEGIN_TIME between", value1, value2, "taskBeginTime");
            return (Criteria) this;
        }

        public Criteria andTaskBeginTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("TASK_BEGIN_TIME not between", value1, value2, "taskBeginTime");
            return (Criteria) this;
        }

        public Criteria andTaskEndTimeIsNull() {
            addCriterion("TASK_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTaskEndTimeIsNotNull() {
            addCriterion("TASK_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTaskEndTimeEqualTo(Integer value) {
            addCriterion("TASK_END_TIME =", value, "taskEndTime");
            return (Criteria) this;
        }

        public Criteria andTaskEndTimeNotEqualTo(Integer value) {
            addCriterion("TASK_END_TIME <>", value, "taskEndTime");
            return (Criteria) this;
        }

        public Criteria andTaskEndTimeGreaterThan(Integer value) {
            addCriterion("TASK_END_TIME >", value, "taskEndTime");
            return (Criteria) this;
        }

        public Criteria andTaskEndTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("TASK_END_TIME >=", value, "taskEndTime");
            return (Criteria) this;
        }

        public Criteria andTaskEndTimeLessThan(Integer value) {
            addCriterion("TASK_END_TIME <", value, "taskEndTime");
            return (Criteria) this;
        }

        public Criteria andTaskEndTimeLessThanOrEqualTo(Integer value) {
            addCriterion("TASK_END_TIME <=", value, "taskEndTime");
            return (Criteria) this;
        }

        public Criteria andTaskEndTimeIn(List<Integer> values) {
            addCriterion("TASK_END_TIME in", values, "taskEndTime");
            return (Criteria) this;
        }

        public Criteria andTaskEndTimeNotIn(List<Integer> values) {
            addCriterion("TASK_END_TIME not in", values, "taskEndTime");
            return (Criteria) this;
        }

        public Criteria andTaskEndTimeBetween(Integer value1, Integer value2) {
            addCriterion("TASK_END_TIME between", value1, value2, "taskEndTime");
            return (Criteria) this;
        }

        public Criteria andTaskEndTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("TASK_END_TIME not between", value1, value2, "taskEndTime");
            return (Criteria) this;
        }

        public Criteria andLoopDaysIsNull() {
            addCriterion("LOOP_DAYS is null");
            return (Criteria) this;
        }

        public Criteria andLoopDaysIsNotNull() {
            addCriterion("LOOP_DAYS is not null");
            return (Criteria) this;
        }

        public Criteria andLoopDaysEqualTo(String value) {
            addCriterion("LOOP_DAYS =", value, "loopDays");
            return (Criteria) this;
        }

        public Criteria andLoopDaysNotEqualTo(String value) {
            addCriterion("LOOP_DAYS <>", value, "loopDays");
            return (Criteria) this;
        }

        public Criteria andLoopDaysGreaterThan(String value) {
            addCriterion("LOOP_DAYS >", value, "loopDays");
            return (Criteria) this;
        }

        public Criteria andLoopDaysGreaterThanOrEqualTo(String value) {
            addCriterion("LOOP_DAYS >=", value, "loopDays");
            return (Criteria) this;
        }

        public Criteria andLoopDaysLessThan(String value) {
            addCriterion("LOOP_DAYS <", value, "loopDays");
            return (Criteria) this;
        }

        public Criteria andLoopDaysLessThanOrEqualTo(String value) {
            addCriterion("LOOP_DAYS <=", value, "loopDays");
            return (Criteria) this;
        }

        public Criteria andLoopDaysLike(String value) {
            addCriterion("LOOP_DAYS like", value, "loopDays");
            return (Criteria) this;
        }

        public Criteria andLoopDaysNotLike(String value) {
            addCriterion("LOOP_DAYS not like", value, "loopDays");
            return (Criteria) this;
        }

        public Criteria andLoopDaysIn(List<String> values) {
            addCriterion("LOOP_DAYS in", values, "loopDays");
            return (Criteria) this;
        }

        public Criteria andLoopDaysNotIn(List<String> values) {
            addCriterion("LOOP_DAYS not in", values, "loopDays");
            return (Criteria) this;
        }

        public Criteria andLoopDaysBetween(String value1, String value2) {
            addCriterion("LOOP_DAYS between", value1, value2, "loopDays");
            return (Criteria) this;
        }

        public Criteria andLoopDaysNotBetween(String value1, String value2) {
            addCriterion("LOOP_DAYS not between", value1, value2, "loopDays");
            return (Criteria) this;
        }

        public Criteria andLoopWeeksIsNull() {
            addCriterion("LOOP_WEEKS is null");
            return (Criteria) this;
        }

        public Criteria andLoopWeeksIsNotNull() {
            addCriterion("LOOP_WEEKS is not null");
            return (Criteria) this;
        }

        public Criteria andLoopWeeksEqualTo(String value) {
            addCriterion("LOOP_WEEKS =", value, "loopWeeks");
            return (Criteria) this;
        }

        public Criteria andLoopWeeksNotEqualTo(String value) {
            addCriterion("LOOP_WEEKS <>", value, "loopWeeks");
            return (Criteria) this;
        }

        public Criteria andLoopWeeksGreaterThan(String value) {
            addCriterion("LOOP_WEEKS >", value, "loopWeeks");
            return (Criteria) this;
        }

        public Criteria andLoopWeeksGreaterThanOrEqualTo(String value) {
            addCriterion("LOOP_WEEKS >=", value, "loopWeeks");
            return (Criteria) this;
        }

        public Criteria andLoopWeeksLessThan(String value) {
            addCriterion("LOOP_WEEKS <", value, "loopWeeks");
            return (Criteria) this;
        }

        public Criteria andLoopWeeksLessThanOrEqualTo(String value) {
            addCriterion("LOOP_WEEKS <=", value, "loopWeeks");
            return (Criteria) this;
        }

        public Criteria andLoopWeeksLike(String value) {
            addCriterion("LOOP_WEEKS like", value, "loopWeeks");
            return (Criteria) this;
        }

        public Criteria andLoopWeeksNotLike(String value) {
            addCriterion("LOOP_WEEKS not like", value, "loopWeeks");
            return (Criteria) this;
        }

        public Criteria andLoopWeeksIn(List<String> values) {
            addCriterion("LOOP_WEEKS in", values, "loopWeeks");
            return (Criteria) this;
        }

        public Criteria andLoopWeeksNotIn(List<String> values) {
            addCriterion("LOOP_WEEKS not in", values, "loopWeeks");
            return (Criteria) this;
        }

        public Criteria andLoopWeeksBetween(String value1, String value2) {
            addCriterion("LOOP_WEEKS between", value1, value2, "loopWeeks");
            return (Criteria) this;
        }

        public Criteria andLoopWeeksNotBetween(String value1, String value2) {
            addCriterion("LOOP_WEEKS not between", value1, value2, "loopWeeks");
            return (Criteria) this;
        }

        public Criteria andLoopMonthsIsNull() {
            addCriterion("LOOP_MONTHS is null");
            return (Criteria) this;
        }

        public Criteria andLoopMonthsIsNotNull() {
            addCriterion("LOOP_MONTHS is not null");
            return (Criteria) this;
        }

        public Criteria andLoopMonthsEqualTo(String value) {
            addCriterion("LOOP_MONTHS =", value, "loopMonths");
            return (Criteria) this;
        }

        public Criteria andLoopMonthsNotEqualTo(String value) {
            addCriterion("LOOP_MONTHS <>", value, "loopMonths");
            return (Criteria) this;
        }

        public Criteria andLoopMonthsGreaterThan(String value) {
            addCriterion("LOOP_MONTHS >", value, "loopMonths");
            return (Criteria) this;
        }

        public Criteria andLoopMonthsGreaterThanOrEqualTo(String value) {
            addCriterion("LOOP_MONTHS >=", value, "loopMonths");
            return (Criteria) this;
        }

        public Criteria andLoopMonthsLessThan(String value) {
            addCriterion("LOOP_MONTHS <", value, "loopMonths");
            return (Criteria) this;
        }

        public Criteria andLoopMonthsLessThanOrEqualTo(String value) {
            addCriterion("LOOP_MONTHS <=", value, "loopMonths");
            return (Criteria) this;
        }

        public Criteria andLoopMonthsLike(String value) {
            addCriterion("LOOP_MONTHS like", value, "loopMonths");
            return (Criteria) this;
        }

        public Criteria andLoopMonthsNotLike(String value) {
            addCriterion("LOOP_MONTHS not like", value, "loopMonths");
            return (Criteria) this;
        }

        public Criteria andLoopMonthsIn(List<String> values) {
            addCriterion("LOOP_MONTHS in", values, "loopMonths");
            return (Criteria) this;
        }

        public Criteria andLoopMonthsNotIn(List<String> values) {
            addCriterion("LOOP_MONTHS not in", values, "loopMonths");
            return (Criteria) this;
        }

        public Criteria andLoopMonthsBetween(String value1, String value2) {
            addCriterion("LOOP_MONTHS between", value1, value2, "loopMonths");
            return (Criteria) this;
        }

        public Criteria andLoopMonthsNotBetween(String value1, String value2) {
            addCriterion("LOOP_MONTHS not between", value1, value2, "loopMonths");
            return (Criteria) this;
        }

        public Criteria andPreGenerateMinuteIsNull() {
            addCriterion("PRE_GENERATE_MINUTE is null");
            return (Criteria) this;
        }

        public Criteria andPreGenerateMinuteIsNotNull() {
            addCriterion("PRE_GENERATE_MINUTE is not null");
            return (Criteria) this;
        }

        public Criteria andPreGenerateMinuteEqualTo(Integer value) {
            addCriterion("PRE_GENERATE_MINUTE =", value, "preGenerateMinute");
            return (Criteria) this;
        }

        public Criteria andPreGenerateMinuteNotEqualTo(Integer value) {
            addCriterion("PRE_GENERATE_MINUTE <>", value, "preGenerateMinute");
            return (Criteria) this;
        }

        public Criteria andPreGenerateMinuteGreaterThan(Integer value) {
            addCriterion("PRE_GENERATE_MINUTE >", value, "preGenerateMinute");
            return (Criteria) this;
        }

        public Criteria andPreGenerateMinuteGreaterThanOrEqualTo(Integer value) {
            addCriterion("PRE_GENERATE_MINUTE >=", value, "preGenerateMinute");
            return (Criteria) this;
        }

        public Criteria andPreGenerateMinuteLessThan(Integer value) {
            addCriterion("PRE_GENERATE_MINUTE <", value, "preGenerateMinute");
            return (Criteria) this;
        }

        public Criteria andPreGenerateMinuteLessThanOrEqualTo(Integer value) {
            addCriterion("PRE_GENERATE_MINUTE <=", value, "preGenerateMinute");
            return (Criteria) this;
        }

        public Criteria andPreGenerateMinuteIn(List<Integer> values) {
            addCriterion("PRE_GENERATE_MINUTE in", values, "preGenerateMinute");
            return (Criteria) this;
        }

        public Criteria andPreGenerateMinuteNotIn(List<Integer> values) {
            addCriterion("PRE_GENERATE_MINUTE not in", values, "preGenerateMinute");
            return (Criteria) this;
        }

        public Criteria andPreGenerateMinuteBetween(Integer value1, Integer value2) {
            addCriterion("PRE_GENERATE_MINUTE between", value1, value2, "preGenerateMinute");
            return (Criteria) this;
        }

        public Criteria andPreGenerateMinuteNotBetween(Integer value1, Integer value2) {
            addCriterion("PRE_GENERATE_MINUTE not between", value1, value2, "preGenerateMinute");
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

        public Criteria andCreateByIsNull() {
            addCriterion("CREATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("CREATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(Integer value) {
            addCriterion("CREATE_BY =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(Integer value) {
            addCriterion("CREATE_BY <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(Integer value) {
            addCriterion("CREATE_BY >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("CREATE_BY >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(Integer value) {
            addCriterion("CREATE_BY <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(Integer value) {
            addCriterion("CREATE_BY <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<Integer> values) {
            addCriterion("CREATE_BY in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<Integer> values) {
            addCriterion("CREATE_BY not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(Integer value1, Integer value2) {
            addCriterion("CREATE_BY between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(Integer value1, Integer value2) {
            addCriterion("CREATE_BY not between", value1, value2, "createBy");
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

        public Criteria andUpdateByIsNull() {
            addCriterion("UPDATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("UPDATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(Integer value) {
            addCriterion("UPDATE_BY =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(Integer value) {
            addCriterion("UPDATE_BY <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(Integer value) {
            addCriterion("UPDATE_BY >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("UPDATE_BY >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(Integer value) {
            addCriterion("UPDATE_BY <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(Integer value) {
            addCriterion("UPDATE_BY <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<Integer> values) {
            addCriterion("UPDATE_BY in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<Integer> values) {
            addCriterion("UPDATE_BY not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(Integer value1, Integer value2) {
            addCriterion("UPDATE_BY between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(Integer value1, Integer value2) {
            addCriterion("UPDATE_BY not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andTaskCronIsNull() {
            addCriterion("TASK_CRON is null");
            return (Criteria) this;
        }

        public Criteria andTaskCronIsNotNull() {
            addCriterion("TASK_CRON is not null");
            return (Criteria) this;
        }

        public Criteria andTaskCronEqualTo(String value) {
            addCriterion("TASK_CRON =", value, "taskCron");
            return (Criteria) this;
        }

        public Criteria andTaskCronNotEqualTo(String value) {
            addCriterion("TASK_CRON <>", value, "taskCron");
            return (Criteria) this;
        }

        public Criteria andTaskCronGreaterThan(String value) {
            addCriterion("TASK_CRON >", value, "taskCron");
            return (Criteria) this;
        }

        public Criteria andTaskCronGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_CRON >=", value, "taskCron");
            return (Criteria) this;
        }

        public Criteria andTaskCronLessThan(String value) {
            addCriterion("TASK_CRON <", value, "taskCron");
            return (Criteria) this;
        }

        public Criteria andTaskCronLessThanOrEqualTo(String value) {
            addCriterion("TASK_CRON <=", value, "taskCron");
            return (Criteria) this;
        }

        public Criteria andTaskCronLike(String value) {
            addCriterion("TASK_CRON like", value, "taskCron");
            return (Criteria) this;
        }

        public Criteria andTaskCronNotLike(String value) {
            addCriterion("TASK_CRON not like", value, "taskCron");
            return (Criteria) this;
        }

        public Criteria andTaskCronIn(List<String> values) {
            addCriterion("TASK_CRON in", values, "taskCron");
            return (Criteria) this;
        }

        public Criteria andTaskCronNotIn(List<String> values) {
            addCriterion("TASK_CRON not in", values, "taskCron");
            return (Criteria) this;
        }

        public Criteria andTaskCronBetween(String value1, String value2) {
            addCriterion("TASK_CRON between", value1, value2, "taskCron");
            return (Criteria) this;
        }

        public Criteria andTaskCronNotBetween(String value1, String value2) {
            addCriterion("TASK_CRON not between", value1, value2, "taskCron");
            return (Criteria) this;
        }

        public Criteria andLoseRemarkIsNull() {
            addCriterion("LOSE_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andLoseRemarkIsNotNull() {
            addCriterion("LOSE_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andLoseRemarkEqualTo(String value) {
            addCriterion("LOSE_REMARK =", value, "loseRemark");
            return (Criteria) this;
        }

        public Criteria andLoseRemarkNotEqualTo(String value) {
            addCriterion("LOSE_REMARK <>", value, "loseRemark");
            return (Criteria) this;
        }

        public Criteria andLoseRemarkGreaterThan(String value) {
            addCriterion("LOSE_REMARK >", value, "loseRemark");
            return (Criteria) this;
        }

        public Criteria andLoseRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("LOSE_REMARK >=", value, "loseRemark");
            return (Criteria) this;
        }

        public Criteria andLoseRemarkLessThan(String value) {
            addCriterion("LOSE_REMARK <", value, "loseRemark");
            return (Criteria) this;
        }

        public Criteria andLoseRemarkLessThanOrEqualTo(String value) {
            addCriterion("LOSE_REMARK <=", value, "loseRemark");
            return (Criteria) this;
        }

        public Criteria andLoseRemarkLike(String value) {
            addCriterion("LOSE_REMARK like", value, "loseRemark");
            return (Criteria) this;
        }

        public Criteria andLoseRemarkNotLike(String value) {
            addCriterion("LOSE_REMARK not like", value, "loseRemark");
            return (Criteria) this;
        }

        public Criteria andLoseRemarkIn(List<String> values) {
            addCriterion("LOSE_REMARK in", values, "loseRemark");
            return (Criteria) this;
        }

        public Criteria andLoseRemarkNotIn(List<String> values) {
            addCriterion("LOSE_REMARK not in", values, "loseRemark");
            return (Criteria) this;
        }

        public Criteria andLoseRemarkBetween(String value1, String value2) {
            addCriterion("LOSE_REMARK between", value1, value2, "loseRemark");
            return (Criteria) this;
        }

        public Criteria andLoseRemarkNotBetween(String value1, String value2) {
            addCriterion("LOSE_REMARK not between", value1, value2, "loseRemark");
            return (Criteria) this;
        }

        public Criteria andLimitMinuteIsNull() {
            addCriterion("LIMIT_MINUTE is null");
            return (Criteria) this;
        }

        public Criteria andLimitMinuteIsNotNull() {
            addCriterion("LIMIT_MINUTE is not null");
            return (Criteria) this;
        }

        public Criteria andLimitMinuteEqualTo(Integer value) {
            addCriterion("LIMIT_MINUTE =", value, "limitMinute");
            return (Criteria) this;
        }

        public Criteria andLimitMinuteNotEqualTo(Integer value) {
            addCriterion("LIMIT_MINUTE <>", value, "limitMinute");
            return (Criteria) this;
        }

        public Criteria andLimitMinuteGreaterThan(Integer value) {
            addCriterion("LIMIT_MINUTE >", value, "limitMinute");
            return (Criteria) this;
        }

        public Criteria andLimitMinuteGreaterThanOrEqualTo(Integer value) {
            addCriterion("LIMIT_MINUTE >=", value, "limitMinute");
            return (Criteria) this;
        }

        public Criteria andLimitMinuteLessThan(Integer value) {
            addCriterion("LIMIT_MINUTE <", value, "limitMinute");
            return (Criteria) this;
        }

        public Criteria andLimitMinuteLessThanOrEqualTo(Integer value) {
            addCriterion("LIMIT_MINUTE <=", value, "limitMinute");
            return (Criteria) this;
        }

        public Criteria andLimitMinuteIn(List<Integer> values) {
            addCriterion("LIMIT_MINUTE in", values, "limitMinute");
            return (Criteria) this;
        }

        public Criteria andLimitMinuteNotIn(List<Integer> values) {
            addCriterion("LIMIT_MINUTE not in", values, "limitMinute");
            return (Criteria) this;
        }

        public Criteria andLimitMinuteBetween(Integer value1, Integer value2) {
            addCriterion("LIMIT_MINUTE between", value1, value2, "limitMinute");
            return (Criteria) this;
        }

        public Criteria andLimitMinuteNotBetween(Integer value1, Integer value2) {
            addCriterion("LIMIT_MINUTE not between", value1, value2, "limitMinute");
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

        public Criteria andTransTypeParentCodeLikeInsensitive(String value) {
            addCriterion("upper(TRANS_TYPE_PARENT_CODE) like", value.toUpperCase(), "transTypeParentCode");
            return (Criteria) this;
        }

        public Criteria andTaskNameLikeInsensitive(String value) {
            addCriterion("upper(TASK_NAME) like", value.toUpperCase(), "taskName");
            return (Criteria) this;
        }

        public Criteria andTransToolsLikeInsensitive(String value) {
            addCriterion("upper(TRANS_TOOLS) like", value.toUpperCase(), "transTools");
            return (Criteria) this;
        }

        public Criteria andLoopTypeLikeInsensitive(String value) {
            addCriterion("upper(LOOP_TYPE) like", value.toUpperCase(), "loopType");
            return (Criteria) this;
        }

        public Criteria andLoopDaysLikeInsensitive(String value) {
            addCriterion("upper(LOOP_DAYS) like", value.toUpperCase(), "loopDays");
            return (Criteria) this;
        }

        public Criteria andLoopWeeksLikeInsensitive(String value) {
            addCriterion("upper(LOOP_WEEKS) like", value.toUpperCase(), "loopWeeks");
            return (Criteria) this;
        }

        public Criteria andLoopMonthsLikeInsensitive(String value) {
            addCriterion("upper(LOOP_MONTHS) like", value.toUpperCase(), "loopMonths");
            return (Criteria) this;
        }

        public Criteria andStatusLikeInsensitive(String value) {
            addCriterion("upper(STATUS) like", value.toUpperCase(), "status");
            return (Criteria) this;
        }

        public Criteria andTaskCronLikeInsensitive(String value) {
            addCriterion("upper(TASK_CRON) like", value.toUpperCase(), "taskCron");
            return (Criteria) this;
        }

        public Criteria andLoseRemarkLikeInsensitive(String value) {
            addCriterion("upper(LOSE_REMARK) like", value.toUpperCase(), "loseRemark");
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