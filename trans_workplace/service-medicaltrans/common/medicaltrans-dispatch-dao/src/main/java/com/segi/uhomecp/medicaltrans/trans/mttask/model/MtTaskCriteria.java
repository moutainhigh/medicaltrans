package com.segi.uhomecp.medicaltrans.trans.mttask.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.segi.uhomecp.common.model.AbstractCriteria;

public class MtTaskCriteria extends AbstractCriteria {
    
	/**  描述   (@author: zhangyang@segimail.com) */      
	    
	private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MtTaskCriteria() {
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

        public Criteria andSourceHouseIdIsNull() {
            addCriterion("SOURCE_HOUSE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSourceHouseIdIsNotNull() {
            addCriterion("SOURCE_HOUSE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSourceHouseIdEqualTo(Integer value) {
            addCriterion("SOURCE_HOUSE_ID =", value, "sourceHouseId");
            return (Criteria) this;
        }

        public Criteria andSourceHouseIdNotEqualTo(Integer value) {
            addCriterion("SOURCE_HOUSE_ID <>", value, "sourceHouseId");
            return (Criteria) this;
        }

        public Criteria andSourceHouseIdGreaterThan(Integer value) {
            addCriterion("SOURCE_HOUSE_ID >", value, "sourceHouseId");
            return (Criteria) this;
        }

        public Criteria andSourceHouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SOURCE_HOUSE_ID >=", value, "sourceHouseId");
            return (Criteria) this;
        }

        public Criteria andSourceHouseIdLessThan(Integer value) {
            addCriterion("SOURCE_HOUSE_ID <", value, "sourceHouseId");
            return (Criteria) this;
        }

        public Criteria andSourceHouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("SOURCE_HOUSE_ID <=", value, "sourceHouseId");
            return (Criteria) this;
        }

        public Criteria andSourceHouseIdIn(List<Integer> values) {
            addCriterion("SOURCE_HOUSE_ID in", values, "sourceHouseId");
            return (Criteria) this;
        }

        public Criteria andSourceHouseIdNotIn(List<Integer> values) {
            addCriterion("SOURCE_HOUSE_ID not in", values, "sourceHouseId");
            return (Criteria) this;
        }

        public Criteria andSourceHouseIdBetween(Integer value1, Integer value2) {
            addCriterion("SOURCE_HOUSE_ID between", value1, value2, "sourceHouseId");
            return (Criteria) this;
        }

        public Criteria andSourceHouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SOURCE_HOUSE_ID not between", value1, value2, "sourceHouseId");
            return (Criteria) this;
        }

        public Criteria andFromHouseIdIsNull() {
            addCriterion("FROM_HOUSE_ID is null");
            return (Criteria) this;
        }

        public Criteria andFromHouseIdIsNotNull() {
            addCriterion("FROM_HOUSE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFromHouseIdEqualTo(Integer value) {
            addCriterion("FROM_HOUSE_ID =", value, "fromHouseId");
            return (Criteria) this;
        }

        public Criteria andFromHouseIdNotEqualTo(Integer value) {
            addCriterion("FROM_HOUSE_ID <>", value, "fromHouseId");
            return (Criteria) this;
        }

        public Criteria andFromHouseIdGreaterThan(Integer value) {
            addCriterion("FROM_HOUSE_ID >", value, "fromHouseId");
            return (Criteria) this;
        }

        public Criteria andFromHouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("FROM_HOUSE_ID >=", value, "fromHouseId");
            return (Criteria) this;
        }

        public Criteria andFromHouseIdLessThan(Integer value) {
            addCriterion("FROM_HOUSE_ID <", value, "fromHouseId");
            return (Criteria) this;
        }

        public Criteria andFromHouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("FROM_HOUSE_ID <=", value, "fromHouseId");
            return (Criteria) this;
        }

        public Criteria andFromHouseIdIn(List<Integer> values) {
            addCriterion("FROM_HOUSE_ID in", values, "fromHouseId");
            return (Criteria) this;
        }

        public Criteria andFromHouseIdNotIn(List<Integer> values) {
            addCriterion("FROM_HOUSE_ID not in", values, "fromHouseId");
            return (Criteria) this;
        }

        public Criteria andFromHouseIdBetween(Integer value1, Integer value2) {
            addCriterion("FROM_HOUSE_ID between", value1, value2, "fromHouseId");
            return (Criteria) this;
        }

        public Criteria andFromHouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("FROM_HOUSE_ID not between", value1, value2, "fromHouseId");
            return (Criteria) this;
        }

        public Criteria andToHouseIdIsNull() {
            addCriterion("TO_HOUSE_ID is null");
            return (Criteria) this;
        }

        public Criteria andToHouseIdIsNotNull() {
            addCriterion("TO_HOUSE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andToHouseIdEqualTo(Integer value) {
            addCriterion("TO_HOUSE_ID =", value, "toHouseId");
            return (Criteria) this;
        }

        public Criteria andToHouseIdNotEqualTo(Integer value) {
            addCriterion("TO_HOUSE_ID <>", value, "toHouseId");
            return (Criteria) this;
        }

        public Criteria andToHouseIdGreaterThan(Integer value) {
            addCriterion("TO_HOUSE_ID >", value, "toHouseId");
            return (Criteria) this;
        }

        public Criteria andToHouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TO_HOUSE_ID >=", value, "toHouseId");
            return (Criteria) this;
        }

        public Criteria andToHouseIdLessThan(Integer value) {
            addCriterion("TO_HOUSE_ID <", value, "toHouseId");
            return (Criteria) this;
        }

        public Criteria andToHouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("TO_HOUSE_ID <=", value, "toHouseId");
            return (Criteria) this;
        }

        public Criteria andToHouseIdIn(List<Integer> values) {
            addCriterion("TO_HOUSE_ID in", values, "toHouseId");
            return (Criteria) this;
        }

        public Criteria andToHouseIdNotIn(List<Integer> values) {
            addCriterion("TO_HOUSE_ID not in", values, "toHouseId");
            return (Criteria) this;
        }

        public Criteria andToHouseIdBetween(Integer value1, Integer value2) {
            addCriterion("TO_HOUSE_ID between", value1, value2, "toHouseId");
            return (Criteria) this;
        }

        public Criteria andToHouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TO_HOUSE_ID not between", value1, value2, "toHouseId");
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

        public Criteria andTransPersonCountIsNull() {
            addCriterion("TRANS_PERSON_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andTransPersonCountIsNotNull() {
            addCriterion("TRANS_PERSON_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTransPersonCountEqualTo(Short value) {
            addCriterion("TRANS_PERSON_COUNT =", value, "transPersonCount");
            return (Criteria) this;
        }

        public Criteria andTransPersonCountNotEqualTo(Short value) {
            addCriterion("TRANS_PERSON_COUNT <>", value, "transPersonCount");
            return (Criteria) this;
        }

        public Criteria andTransPersonCountGreaterThan(Short value) {
            addCriterion("TRANS_PERSON_COUNT >", value, "transPersonCount");
            return (Criteria) this;
        }

        public Criteria andTransPersonCountGreaterThanOrEqualTo(Short value) {
            addCriterion("TRANS_PERSON_COUNT >=", value, "transPersonCount");
            return (Criteria) this;
        }

        public Criteria andTransPersonCountLessThan(Short value) {
            addCriterion("TRANS_PERSON_COUNT <", value, "transPersonCount");
            return (Criteria) this;
        }

        public Criteria andTransPersonCountLessThanOrEqualTo(Short value) {
            addCriterion("TRANS_PERSON_COUNT <=", value, "transPersonCount");
            return (Criteria) this;
        }

        public Criteria andTransPersonCountIn(List<Short> values) {
            addCriterion("TRANS_PERSON_COUNT in", values, "transPersonCount");
            return (Criteria) this;
        }

        public Criteria andTransPersonCountNotIn(List<Short> values) {
            addCriterion("TRANS_PERSON_COUNT not in", values, "transPersonCount");
            return (Criteria) this;
        }

        public Criteria andTransPersonCountBetween(Short value1, Short value2) {
            addCriterion("TRANS_PERSON_COUNT between", value1, value2, "transPersonCount");
            return (Criteria) this;
        }

        public Criteria andTransPersonCountNotBetween(Short value1, Short value2) {
            addCriterion("TRANS_PERSON_COUNT not between", value1, value2, "transPersonCount");
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

        public Criteria andResTypeIsNull() {
            addCriterion("RES_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andResTypeIsNotNull() {
            addCriterion("RES_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andResTypeEqualTo(String value) {
            addCriterion("RES_TYPE =", value, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeNotEqualTo(String value) {
            addCriterion("RES_TYPE <>", value, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeGreaterThan(String value) {
            addCriterion("RES_TYPE >", value, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RES_TYPE >=", value, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeLessThan(String value) {
            addCriterion("RES_TYPE <", value, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeLessThanOrEqualTo(String value) {
            addCriterion("RES_TYPE <=", value, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeLike(String value) {
            addCriterion("RES_TYPE like", value, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeNotLike(String value) {
            addCriterion("RES_TYPE not like", value, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeIn(List<String> values) {
            addCriterion("RES_TYPE in", values, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeNotIn(List<String> values) {
            addCriterion("RES_TYPE not in", values, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeBetween(String value1, String value2) {
            addCriterion("RES_TYPE between", value1, value2, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeNotBetween(String value1, String value2) {
            addCriterion("RES_TYPE not between", value1, value2, "resType");
            return (Criteria) this;
        }

        public Criteria andDataSourceIsNull() {
            addCriterion("DATA_SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andDataSourceIsNotNull() {
            addCriterion("DATA_SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andDataSourceEqualTo(String value) {
            addCriterion("DATA_SOURCE =", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotEqualTo(String value) {
            addCriterion("DATA_SOURCE <>", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceGreaterThan(String value) {
            addCriterion("DATA_SOURCE >", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_SOURCE >=", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceLessThan(String value) {
            addCriterion("DATA_SOURCE <", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceLessThanOrEqualTo(String value) {
            addCriterion("DATA_SOURCE <=", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceLike(String value) {
            addCriterion("DATA_SOURCE like", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotLike(String value) {
            addCriterion("DATA_SOURCE not like", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceIn(List<String> values) {
            addCriterion("DATA_SOURCE in", values, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotIn(List<String> values) {
            addCriterion("DATA_SOURCE not in", values, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceBetween(String value1, String value2) {
            addCriterion("DATA_SOURCE between", value1, value2, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotBetween(String value1, String value2) {
            addCriterion("DATA_SOURCE not between", value1, value2, "dataSource");
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

        public Criteria andExeEndUserIdIsNull() {
            addCriterion("EXE_END_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andExeEndUserIdIsNotNull() {
            addCriterion("EXE_END_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExeEndUserIdEqualTo(Integer value) {
            addCriterion("EXE_END_USER_ID =", value, "exeEndUserId");
            return (Criteria) this;
        }

        public Criteria andExeEndUserIdNotEqualTo(Integer value) {
            addCriterion("EXE_END_USER_ID <>", value, "exeEndUserId");
            return (Criteria) this;
        }

        public Criteria andExeEndUserIdGreaterThan(Integer value) {
            addCriterion("EXE_END_USER_ID >", value, "exeEndUserId");
            return (Criteria) this;
        }

        public Criteria andExeEndUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("EXE_END_USER_ID >=", value, "exeEndUserId");
            return (Criteria) this;
        }

        public Criteria andExeEndUserIdLessThan(Integer value) {
            addCriterion("EXE_END_USER_ID <", value, "exeEndUserId");
            return (Criteria) this;
        }

        public Criteria andExeEndUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("EXE_END_USER_ID <=", value, "exeEndUserId");
            return (Criteria) this;
        }

        public Criteria andExeEndUserIdIn(List<Integer> values) {
            addCriterion("EXE_END_USER_ID in", values, "exeEndUserId");
            return (Criteria) this;
        }

        public Criteria andExeEndUserIdNotIn(List<Integer> values) {
            addCriterion("EXE_END_USER_ID not in", values, "exeEndUserId");
            return (Criteria) this;
        }

        public Criteria andExeEndUserIdBetween(Integer value1, Integer value2) {
            addCriterion("EXE_END_USER_ID between", value1, value2, "exeEndUserId");
            return (Criteria) this;
        }

        public Criteria andExeEndUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("EXE_END_USER_ID not between", value1, value2, "exeEndUserId");
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

        public Criteria andVersionIsNull() {
            addCriterion("VERSION is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("VERSION =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("VERSION <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("VERSION >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("VERSION >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("VERSION <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("VERSION <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("VERSION in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("VERSION not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("VERSION between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("VERSION not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andDispatchUserIdIsNull() {
            addCriterion("DISPATCH_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andDispatchUserIdIsNotNull() {
            addCriterion("DISPATCH_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDispatchUserIdEqualTo(Integer value) {
            addCriterion("DISPATCH_USER_ID =", value, "dispatchUserId");
            return (Criteria) this;
        }

        public Criteria andDispatchUserIdNotEqualTo(Integer value) {
            addCriterion("DISPATCH_USER_ID <>", value, "dispatchUserId");
            return (Criteria) this;
        }

        public Criteria andDispatchUserIdGreaterThan(Integer value) {
            addCriterion("DISPATCH_USER_ID >", value, "dispatchUserId");
            return (Criteria) this;
        }

        public Criteria andDispatchUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("DISPATCH_USER_ID >=", value, "dispatchUserId");
            return (Criteria) this;
        }

        public Criteria andDispatchUserIdLessThan(Integer value) {
            addCriterion("DISPATCH_USER_ID <", value, "dispatchUserId");
            return (Criteria) this;
        }

        public Criteria andDispatchUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("DISPATCH_USER_ID <=", value, "dispatchUserId");
            return (Criteria) this;
        }

        public Criteria andDispatchUserIdIn(List<Integer> values) {
            addCriterion("DISPATCH_USER_ID in", values, "dispatchUserId");
            return (Criteria) this;
        }

        public Criteria andDispatchUserIdNotIn(List<Integer> values) {
            addCriterion("DISPATCH_USER_ID not in", values, "dispatchUserId");
            return (Criteria) this;
        }

        public Criteria andDispatchUserIdBetween(Integer value1, Integer value2) {
            addCriterion("DISPATCH_USER_ID between", value1, value2, "dispatchUserId");
            return (Criteria) this;
        }

        public Criteria andDispatchUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("DISPATCH_USER_ID not between", value1, value2, "dispatchUserId");
            return (Criteria) this;
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

        public Criteria andTaskHourIsNull() {
            addCriterion("TASK_HOUR is null");
            return (Criteria) this;
        }

        public Criteria andTaskHourIsNotNull() {
            addCriterion("TASK_HOUR is not null");
            return (Criteria) this;
        }

        public Criteria andTaskHourEqualTo(Byte value) {
            addCriterion("TASK_HOUR =", value, "taskHour");
            return (Criteria) this;
        }

        public Criteria andTaskHourNotEqualTo(Byte value) {
            addCriterion("TASK_HOUR <>", value, "taskHour");
            return (Criteria) this;
        }

        public Criteria andTaskHourGreaterThan(Byte value) {
            addCriterion("TASK_HOUR >", value, "taskHour");
            return (Criteria) this;
        }

        public Criteria andTaskHourGreaterThanOrEqualTo(Byte value) {
            addCriterion("TASK_HOUR >=", value, "taskHour");
            return (Criteria) this;
        }

        public Criteria andTaskHourLessThan(Byte value) {
            addCriterion("TASK_HOUR <", value, "taskHour");
            return (Criteria) this;
        }

        public Criteria andTaskHourLessThanOrEqualTo(Byte value) {
            addCriterion("TASK_HOUR <=", value, "taskHour");
            return (Criteria) this;
        }

        public Criteria andTaskHourIn(List<Byte> values) {
            addCriterion("TASK_HOUR in", values, "taskHour");
            return (Criteria) this;
        }

        public Criteria andTaskHourNotIn(List<Byte> values) {
            addCriterion("TASK_HOUR not in", values, "taskHour");
            return (Criteria) this;
        }

        public Criteria andTaskHourBetween(Byte value1, Byte value2) {
            addCriterion("TASK_HOUR between", value1, value2, "taskHour");
            return (Criteria) this;
        }

        public Criteria andTaskHourNotBetween(Byte value1, Byte value2) {
            addCriterion("TASK_HOUR not between", value1, value2, "taskHour");
            return (Criteria) this;
        }

        public Criteria andTimeConsumingIsNull() {
            addCriterion("TIME_CONSUMING is null");
            return (Criteria) this;
        }

        public Criteria andTimeConsumingIsNotNull() {
            addCriterion("TIME_CONSUMING is not null");
            return (Criteria) this;
        }

        public Criteria andTimeConsumingEqualTo(Integer value) {
            addCriterion("TIME_CONSUMING =", value, "timeConsuming");
            return (Criteria) this;
        }

        public Criteria andTimeConsumingNotEqualTo(Integer value) {
            addCriterion("TIME_CONSUMING <>", value, "timeConsuming");
            return (Criteria) this;
        }

        public Criteria andTimeConsumingGreaterThan(Integer value) {
            addCriterion("TIME_CONSUMING >", value, "timeConsuming");
            return (Criteria) this;
        }

        public Criteria andTimeConsumingGreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_CONSUMING >=", value, "timeConsuming");
            return (Criteria) this;
        }

        public Criteria andTimeConsumingLessThan(Integer value) {
            addCriterion("TIME_CONSUMING <", value, "timeConsuming");
            return (Criteria) this;
        }

        public Criteria andTimeConsumingLessThanOrEqualTo(Integer value) {
            addCriterion("TIME_CONSUMING <=", value, "timeConsuming");
            return (Criteria) this;
        }

        public Criteria andTimeConsumingIn(List<Integer> values) {
            addCriterion("TIME_CONSUMING in", values, "timeConsuming");
            return (Criteria) this;
        }

        public Criteria andTimeConsumingNotIn(List<Integer> values) {
            addCriterion("TIME_CONSUMING not in", values, "timeConsuming");
            return (Criteria) this;
        }

        public Criteria andTimeConsumingBetween(Integer value1, Integer value2) {
            addCriterion("TIME_CONSUMING between", value1, value2, "timeConsuming");
            return (Criteria) this;
        }

        public Criteria andTimeConsumingNotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_CONSUMING not between", value1, value2, "timeConsuming");
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

        public Criteria andRespTimeIsNull() {
            addCriterion("RESP_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRespTimeIsNotNull() {
            addCriterion("RESP_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRespTimeEqualTo(Integer value) {
            addCriterion("RESP_TIME =", value, "respTime");
            return (Criteria) this;
        }

        public Criteria andRespTimeNotEqualTo(Integer value) {
            addCriterion("RESP_TIME <>", value, "respTime");
            return (Criteria) this;
        }

        public Criteria andRespTimeGreaterThan(Integer value) {
            addCriterion("RESP_TIME >", value, "respTime");
            return (Criteria) this;
        }

        public Criteria andRespTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("RESP_TIME >=", value, "respTime");
            return (Criteria) this;
        }

        public Criteria andRespTimeLessThan(Integer value) {
            addCriterion("RESP_TIME <", value, "respTime");
            return (Criteria) this;
        }

        public Criteria andRespTimeLessThanOrEqualTo(Integer value) {
            addCriterion("RESP_TIME <=", value, "respTime");
            return (Criteria) this;
        }

        public Criteria andRespTimeIn(List<Integer> values) {
            addCriterion("RESP_TIME in", values, "respTime");
            return (Criteria) this;
        }

        public Criteria andRespTimeNotIn(List<Integer> values) {
            addCriterion("RESP_TIME not in", values, "respTime");
            return (Criteria) this;
        }

        public Criteria andRespTimeBetween(Integer value1, Integer value2) {
            addCriterion("RESP_TIME between", value1, value2, "respTime");
            return (Criteria) this;
        }

        public Criteria andRespTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("RESP_TIME not between", value1, value2, "respTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeIsNull() {
            addCriterion("TASK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTaskTimeIsNotNull() {
            addCriterion("TASK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTimeEqualTo(Integer value) {
            addCriterion("TASK_TIME =", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeNotEqualTo(Integer value) {
            addCriterion("TASK_TIME <>", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeGreaterThan(Integer value) {
            addCriterion("TASK_TIME >", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("TASK_TIME >=", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeLessThan(Integer value) {
            addCriterion("TASK_TIME <", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeLessThanOrEqualTo(Integer value) {
            addCriterion("TASK_TIME <=", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeIn(List<Integer> values) {
            addCriterion("TASK_TIME in", values, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeNotIn(List<Integer> values) {
            addCriterion("TASK_TIME not in", values, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeBetween(Integer value1, Integer value2) {
            addCriterion("TASK_TIME between", value1, value2, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("TASK_TIME not between", value1, value2, "taskTime");
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

        public Criteria andTransTypeParentCodeLikeInsensitive(String value) {
            addCriterion("upper(TRANS_TYPE_PARENT_CODE) like", value.toUpperCase(), "transTypeParentCode");
            return (Criteria) this;
        }

        public Criteria andUrgencyLikeInsensitive(String value) {
            addCriterion("upper(URGENCY) like", value.toUpperCase(), "urgency");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLikeInsensitive(String value) {
            addCriterion("upper(TASK_TYPE) like", value.toUpperCase(), "taskType");
            return (Criteria) this;
        }

        public Criteria andTransToolsLikeInsensitive(String value) {
            addCriterion("upper(TRANS_TOOLS) like", value.toUpperCase(), "transTools");
            return (Criteria) this;
        }

        public Criteria andResTypeLikeInsensitive(String value) {
            addCriterion("upper(RES_TYPE) like", value.toUpperCase(), "resType");
            return (Criteria) this;
        }

        public Criteria andDataSourceLikeInsensitive(String value) {
            addCriterion("upper(DATA_SOURCE) like", value.toUpperCase(), "dataSource");
            return (Criteria) this;
        }

        public Criteria andStatusLikeInsensitive(String value) {
            addCriterion("upper(STATUS) like", value.toUpperCase(), "status");
            return (Criteria) this;
        }

        public Criteria andIsTimeOutLikeInsensitive(String value) {
            addCriterion("upper(IS_TIME_OUT) like", value.toUpperCase(), "isTimeOut");
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