package com.segi.uhomecp.medicaltrans.base.userposit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.segi.uhomecp.common.model.AbstractCriteria;

public class MtUserPositCriteria extends AbstractCriteria {

	private static final long serialVersionUID = 2977443774204709407L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MtUserPositCriteria() {
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

        public Criteria andUserPositIdIsNull() {
            addCriterion("USER_POSIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserPositIdIsNotNull() {
            addCriterion("USER_POSIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserPositIdEqualTo(Integer value) {
            addCriterion("USER_POSIT_ID =", value, "userPositId");
            return (Criteria) this;
        }

        public Criteria andUserPositIdNotEqualTo(Integer value) {
            addCriterion("USER_POSIT_ID <>", value, "userPositId");
            return (Criteria) this;
        }

        public Criteria andUserPositIdGreaterThan(Integer value) {
            addCriterion("USER_POSIT_ID >", value, "userPositId");
            return (Criteria) this;
        }

        public Criteria andUserPositIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("USER_POSIT_ID >=", value, "userPositId");
            return (Criteria) this;
        }

        public Criteria andUserPositIdLessThan(Integer value) {
            addCriterion("USER_POSIT_ID <", value, "userPositId");
            return (Criteria) this;
        }

        public Criteria andUserPositIdLessThanOrEqualTo(Integer value) {
            addCriterion("USER_POSIT_ID <=", value, "userPositId");
            return (Criteria) this;
        }

        public Criteria andUserPositIdIn(List<Integer> values) {
            addCriterion("USER_POSIT_ID in", values, "userPositId");
            return (Criteria) this;
        }

        public Criteria andUserPositIdNotIn(List<Integer> values) {
            addCriterion("USER_POSIT_ID not in", values, "userPositId");
            return (Criteria) this;
        }

        public Criteria andUserPositIdBetween(Integer value1, Integer value2) {
            addCriterion("USER_POSIT_ID between", value1, value2, "userPositId");
            return (Criteria) this;
        }

        public Criteria andUserPositIdNotBetween(Integer value1, Integer value2) {
            addCriterion("USER_POSIT_ID not between", value1, value2, "userPositId");
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

        public Criteria andBuildIdIsNull() {
            addCriterion("BUILD_ID is null");
            return (Criteria) this;
        }

        public Criteria andBuildIdIsNotNull() {
            addCriterion("BUILD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBuildIdEqualTo(Integer value) {
            addCriterion("BUILD_ID =", value, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdNotEqualTo(Integer value) {
            addCriterion("BUILD_ID <>", value, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdGreaterThan(Integer value) {
            addCriterion("BUILD_ID >", value, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("BUILD_ID >=", value, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdLessThan(Integer value) {
            addCriterion("BUILD_ID <", value, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdLessThanOrEqualTo(Integer value) {
            addCriterion("BUILD_ID <=", value, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdIn(List<Integer> values) {
            addCriterion("BUILD_ID in", values, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdNotIn(List<Integer> values) {
            addCriterion("BUILD_ID not in", values, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdBetween(Integer value1, Integer value2) {
            addCriterion("BUILD_ID between", value1, value2, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdNotBetween(Integer value1, Integer value2) {
            addCriterion("BUILD_ID not between", value1, value2, "buildId");
            return (Criteria) this;
        }

        public Criteria andFloorIdIsNull() {
            addCriterion("FLOOR_ID is null");
            return (Criteria) this;
        }

        public Criteria andFloorIdIsNotNull() {
            addCriterion("FLOOR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFloorIdEqualTo(Integer value) {
            addCriterion("FLOOR_ID =", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdNotEqualTo(Integer value) {
            addCriterion("FLOOR_ID <>", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdGreaterThan(Integer value) {
            addCriterion("FLOOR_ID >", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("FLOOR_ID >=", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdLessThan(Integer value) {
            addCriterion("FLOOR_ID <", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdLessThanOrEqualTo(Integer value) {
            addCriterion("FLOOR_ID <=", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdIn(List<Integer> values) {
            addCriterion("FLOOR_ID in", values, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdNotIn(List<Integer> values) {
            addCriterion("FLOOR_ID not in", values, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdBetween(Integer value1, Integer value2) {
            addCriterion("FLOOR_ID between", value1, value2, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("FLOOR_ID not between", value1, value2, "floorId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andHouseIdIsNull() {
            addCriterion("HOUSE_ID is null");
            return (Criteria) this;
        }

        public Criteria andHouseIdIsNotNull() {
            addCriterion("HOUSE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHouseIdEqualTo(Integer value) {
            addCriterion("HOUSE_ID =", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotEqualTo(Integer value) {
            addCriterion("HOUSE_ID <>", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThan(Integer value) {
            addCriterion("HOUSE_ID >", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("HOUSE_ID >=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThan(Integer value) {
            addCriterion("HOUSE_ID <", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("HOUSE_ID <=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdIn(List<Integer> values) {
            addCriterion("HOUSE_ID in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotIn(List<Integer> values) {
            addCriterion("HOUSE_ID not in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdBetween(Integer value1, Integer value2) {
            addCriterion("HOUSE_ID between", value1, value2, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("HOUSE_ID not between", value1, value2, "houseId");
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

        public Criteria andUnTaskNumIsNull() {
            addCriterion("UN_TASK_NUM is null");
            return (Criteria) this;
        }

        public Criteria andUnTaskNumIsNotNull() {
            addCriterion("UN_TASK_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andUnTaskNumEqualTo(Short value) {
            addCriterion("UN_TASK_NUM =", value, "unTaskNum");
            return (Criteria) this;
        }

        public Criteria andUnTaskNumNotEqualTo(Short value) {
            addCriterion("UN_TASK_NUM <>", value, "unTaskNum");
            return (Criteria) this;
        }

        public Criteria andUnTaskNumGreaterThan(Short value) {
            addCriterion("UN_TASK_NUM >", value, "unTaskNum");
            return (Criteria) this;
        }

        public Criteria andUnTaskNumGreaterThanOrEqualTo(Short value) {
            addCriterion("UN_TASK_NUM >=", value, "unTaskNum");
            return (Criteria) this;
        }

        public Criteria andUnTaskNumLessThan(Short value) {
            addCriterion("UN_TASK_NUM <", value, "unTaskNum");
            return (Criteria) this;
        }

        public Criteria andUnTaskNumLessThanOrEqualTo(Short value) {
            addCriterion("UN_TASK_NUM <=", value, "unTaskNum");
            return (Criteria) this;
        }

        public Criteria andUnTaskNumIn(List<Short> values) {
            addCriterion("UN_TASK_NUM in", values, "unTaskNum");
            return (Criteria) this;
        }

        public Criteria andUnTaskNumNotIn(List<Short> values) {
            addCriterion("UN_TASK_NUM not in", values, "unTaskNum");
            return (Criteria) this;
        }

        public Criteria andUnTaskNumBetween(Short value1, Short value2) {
            addCriterion("UN_TASK_NUM between", value1, value2, "unTaskNum");
            return (Criteria) this;
        }

        public Criteria andUnTaskNumNotBetween(Short value1, Short value2) {
            addCriterion("UN_TASK_NUM not between", value1, value2, "unTaskNum");
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

        public Criteria andRunTaskNumIsNull() {
            addCriterion("RUN_TASK_NUM is null");
            return (Criteria) this;
        }

        public Criteria andRunTaskNumIsNotNull() {
            addCriterion("RUN_TASK_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andRunTaskNumEqualTo(Short value) {
            addCriterion("RUN_TASK_NUM =", value, "runTaskNum");
            return (Criteria) this;
        }

        public Criteria andRunTaskNumNotEqualTo(Short value) {
            addCriterion("RUN_TASK_NUM <>", value, "runTaskNum");
            return (Criteria) this;
        }

        public Criteria andRunTaskNumGreaterThan(Short value) {
            addCriterion("RUN_TASK_NUM >", value, "runTaskNum");
            return (Criteria) this;
        }

        public Criteria andRunTaskNumGreaterThanOrEqualTo(Short value) {
            addCriterion("RUN_TASK_NUM >=", value, "runTaskNum");
            return (Criteria) this;
        }

        public Criteria andRunTaskNumLessThan(Short value) {
            addCriterion("RUN_TASK_NUM <", value, "runTaskNum");
            return (Criteria) this;
        }

        public Criteria andRunTaskNumLessThanOrEqualTo(Short value) {
            addCriterion("RUN_TASK_NUM <=", value, "runTaskNum");
            return (Criteria) this;
        }

        public Criteria andRunTaskNumIn(List<Short> values) {
            addCriterion("RUN_TASK_NUM in", values, "runTaskNum");
            return (Criteria) this;
        }

        public Criteria andRunTaskNumNotIn(List<Short> values) {
            addCriterion("RUN_TASK_NUM not in", values, "runTaskNum");
            return (Criteria) this;
        }

        public Criteria andRunTaskNumBetween(Short value1, Short value2) {
            addCriterion("RUN_TASK_NUM between", value1, value2, "runTaskNum");
            return (Criteria) this;
        }

        public Criteria andRunTaskNumNotBetween(Short value1, Short value2) {
            addCriterion("RUN_TASK_NUM not between", value1, value2, "runTaskNum");
            return (Criteria) this;
        }

        public Criteria andStatusLikeInsensitive(String value) {
            addCriterion("upper(STATUS) like", value.toUpperCase(), "status");
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