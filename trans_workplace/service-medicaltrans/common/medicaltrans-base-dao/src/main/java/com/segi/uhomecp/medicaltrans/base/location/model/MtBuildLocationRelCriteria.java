package com.segi.uhomecp.medicaltrans.base.location.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.segi.uhomecp.common.model.AbstractCriteria;

public class MtBuildLocationRelCriteria extends AbstractCriteria {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4076603563122015175L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MtBuildLocationRelCriteria() {
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

        public Criteria andLocationIdIsNull() {
            addCriterion("LOCATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andLocationIdIsNotNull() {
            addCriterion("LOCATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLocationIdEqualTo(Integer value) {
            addCriterion("LOCATION_ID =", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdNotEqualTo(Integer value) {
            addCriterion("LOCATION_ID <>", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdGreaterThan(Integer value) {
            addCriterion("LOCATION_ID >", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("LOCATION_ID >=", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdLessThan(Integer value) {
            addCriterion("LOCATION_ID <", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdLessThanOrEqualTo(Integer value) {
            addCriterion("LOCATION_ID <=", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdIn(List<Integer> values) {
            addCriterion("LOCATION_ID in", values, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdNotIn(List<Integer> values) {
            addCriterion("LOCATION_ID not in", values, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdBetween(Integer value1, Integer value2) {
            addCriterion("LOCATION_ID between", value1, value2, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("LOCATION_ID not between", value1, value2, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationRelIdIsNull() {
            addCriterion("LOCATION_REL_ID is null");
            return (Criteria) this;
        }

        public Criteria andLocationRelIdIsNotNull() {
            addCriterion("LOCATION_REL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLocationRelIdEqualTo(Integer value) {
            addCriterion("LOCATION_REL_ID =", value, "locationRelId");
            return (Criteria) this;
        }

        public Criteria andLocationRelIdNotEqualTo(Integer value) {
            addCriterion("LOCATION_REL_ID <>", value, "locationRelId");
            return (Criteria) this;
        }

        public Criteria andLocationRelIdGreaterThan(Integer value) {
            addCriterion("LOCATION_REL_ID >", value, "locationRelId");
            return (Criteria) this;
        }

        public Criteria andLocationRelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("LOCATION_REL_ID >=", value, "locationRelId");
            return (Criteria) this;
        }

        public Criteria andLocationRelIdLessThan(Integer value) {
            addCriterion("LOCATION_REL_ID <", value, "locationRelId");
            return (Criteria) this;
        }

        public Criteria andLocationRelIdLessThanOrEqualTo(Integer value) {
            addCriterion("LOCATION_REL_ID <=", value, "locationRelId");
            return (Criteria) this;
        }

        public Criteria andLocationRelIdIn(List<Integer> values) {
            addCriterion("LOCATION_REL_ID in", values, "locationRelId");
            return (Criteria) this;
        }

        public Criteria andLocationRelIdNotIn(List<Integer> values) {
            addCriterion("LOCATION_REL_ID not in", values, "locationRelId");
            return (Criteria) this;
        }

        public Criteria andLocationRelIdBetween(Integer value1, Integer value2) {
            addCriterion("LOCATION_REL_ID between", value1, value2, "locationRelId");
            return (Criteria) this;
        }

        public Criteria andLocationRelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("LOCATION_REL_ID not between", value1, value2, "locationRelId");
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

        public Criteria andUpdateuserIdIsNull() {
            addCriterion("UPDATEUSER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUpdateuserIdIsNotNull() {
            addCriterion("UPDATEUSER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateuserIdEqualTo(Integer value) {
            addCriterion("UPDATEUSER_ID =", value, "updateuserId");
            return (Criteria) this;
        }

        public Criteria andUpdateuserIdNotEqualTo(Integer value) {
            addCriterion("UPDATEUSER_ID <>", value, "updateuserId");
            return (Criteria) this;
        }

        public Criteria andUpdateuserIdGreaterThan(Integer value) {
            addCriterion("UPDATEUSER_ID >", value, "updateuserId");
            return (Criteria) this;
        }

        public Criteria andUpdateuserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("UPDATEUSER_ID >=", value, "updateuserId");
            return (Criteria) this;
        }

        public Criteria andUpdateuserIdLessThan(Integer value) {
            addCriterion("UPDATEUSER_ID <", value, "updateuserId");
            return (Criteria) this;
        }

        public Criteria andUpdateuserIdLessThanOrEqualTo(Integer value) {
            addCriterion("UPDATEUSER_ID <=", value, "updateuserId");
            return (Criteria) this;
        }

        public Criteria andUpdateuserIdIn(List<Integer> values) {
            addCriterion("UPDATEUSER_ID in", values, "updateuserId");
            return (Criteria) this;
        }

        public Criteria andUpdateuserIdNotIn(List<Integer> values) {
            addCriterion("UPDATEUSER_ID not in", values, "updateuserId");
            return (Criteria) this;
        }

        public Criteria andUpdateuserIdBetween(Integer value1, Integer value2) {
            addCriterion("UPDATEUSER_ID between", value1, value2, "updateuserId");
            return (Criteria) this;
        }

        public Criteria andUpdateuserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("UPDATEUSER_ID not between", value1, value2, "updateuserId");
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