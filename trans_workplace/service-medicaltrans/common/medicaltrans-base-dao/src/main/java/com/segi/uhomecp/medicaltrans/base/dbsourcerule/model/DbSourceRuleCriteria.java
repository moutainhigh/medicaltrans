package com.segi.uhomecp.medicaltrans.base.dbsourcerule.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.segi.uhomecp.common.model.AbstractCriteria;

public class DbSourceRuleCriteria extends AbstractCriteria{
    
	/**  描述   (@author: wangxiong@segimail.com) */      
	private static final long serialVersionUID = 7136339648916304375L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DbSourceRuleCriteria() {
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

        public Criteria andIdxIdIsNull() {
            addCriterion("IDX_ID is null");
            return (Criteria) this;
        }

        public Criteria andIdxIdIsNotNull() {
            addCriterion("IDX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdxIdEqualTo(Integer value) {
            addCriterion("IDX_ID =", value, "idxId");
            return (Criteria) this;
        }

        public Criteria andIdxIdNotEqualTo(Integer value) {
            addCriterion("IDX_ID <>", value, "idxId");
            return (Criteria) this;
        }

        public Criteria andIdxIdGreaterThan(Integer value) {
            addCriterion("IDX_ID >", value, "idxId");
            return (Criteria) this;
        }

        public Criteria andIdxIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("IDX_ID >=", value, "idxId");
            return (Criteria) this;
        }

        public Criteria andIdxIdLessThan(Integer value) {
            addCriterion("IDX_ID <", value, "idxId");
            return (Criteria) this;
        }

        public Criteria andIdxIdLessThanOrEqualTo(Integer value) {
            addCriterion("IDX_ID <=", value, "idxId");
            return (Criteria) this;
        }

        public Criteria andIdxIdIn(List<Integer> values) {
            addCriterion("IDX_ID in", values, "idxId");
            return (Criteria) this;
        }

        public Criteria andIdxIdNotIn(List<Integer> values) {
            addCriterion("IDX_ID not in", values, "idxId");
            return (Criteria) this;
        }

        public Criteria andIdxIdBetween(Integer value1, Integer value2) {
            addCriterion("IDX_ID between", value1, value2, "idxId");
            return (Criteria) this;
        }

        public Criteria andIdxIdNotBetween(Integer value1, Integer value2) {
            addCriterion("IDX_ID not between", value1, value2, "idxId");
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

        public Criteria andDbIdxIsNull() {
            addCriterion("DB_IDX is null");
            return (Criteria) this;
        }

        public Criteria andDbIdxIsNotNull() {
            addCriterion("DB_IDX is not null");
            return (Criteria) this;
        }

        public Criteria andDbIdxEqualTo(String value) {
            addCriterion("DB_IDX =", value, "dbIdx");
            return (Criteria) this;
        }

        public Criteria andDbIdxNotEqualTo(String value) {
            addCriterion("DB_IDX <>", value, "dbIdx");
            return (Criteria) this;
        }

        public Criteria andDbIdxGreaterThan(String value) {
            addCriterion("DB_IDX >", value, "dbIdx");
            return (Criteria) this;
        }

        public Criteria andDbIdxGreaterThanOrEqualTo(String value) {
            addCriterion("DB_IDX >=", value, "dbIdx");
            return (Criteria) this;
        }

        public Criteria andDbIdxLessThan(String value) {
            addCriterion("DB_IDX <", value, "dbIdx");
            return (Criteria) this;
        }

        public Criteria andDbIdxLessThanOrEqualTo(String value) {
            addCriterion("DB_IDX <=", value, "dbIdx");
            return (Criteria) this;
        }

        public Criteria andDbIdxLike(String value) {
            addCriterion("DB_IDX like", value, "dbIdx");
            return (Criteria) this;
        }

        public Criteria andDbIdxNotLike(String value) {
            addCriterion("DB_IDX not like", value, "dbIdx");
            return (Criteria) this;
        }

        public Criteria andDbIdxIn(List<String> values) {
            addCriterion("DB_IDX in", values, "dbIdx");
            return (Criteria) this;
        }

        public Criteria andDbIdxNotIn(List<String> values) {
            addCriterion("DB_IDX not in", values, "dbIdx");
            return (Criteria) this;
        }

        public Criteria andDbIdxBetween(String value1, String value2) {
            addCriterion("DB_IDX between", value1, value2, "dbIdx");
            return (Criteria) this;
        }

        public Criteria andDbIdxNotBetween(String value1, String value2) {
            addCriterion("DB_IDX not between", value1, value2, "dbIdx");
            return (Criteria) this;
        }

        public Criteria andTableIdxIsNull() {
            addCriterion("TABLE_IDX is null");
            return (Criteria) this;
        }

        public Criteria andTableIdxIsNotNull() {
            addCriterion("TABLE_IDX is not null");
            return (Criteria) this;
        }

        public Criteria andTableIdxEqualTo(String value) {
            addCriterion("TABLE_IDX =", value, "tableIdx");
            return (Criteria) this;
        }

        public Criteria andTableIdxNotEqualTo(String value) {
            addCriterion("TABLE_IDX <>", value, "tableIdx");
            return (Criteria) this;
        }

        public Criteria andTableIdxGreaterThan(String value) {
            addCriterion("TABLE_IDX >", value, "tableIdx");
            return (Criteria) this;
        }

        public Criteria andTableIdxGreaterThanOrEqualTo(String value) {
            addCriterion("TABLE_IDX >=", value, "tableIdx");
            return (Criteria) this;
        }

        public Criteria andTableIdxLessThan(String value) {
            addCriterion("TABLE_IDX <", value, "tableIdx");
            return (Criteria) this;
        }

        public Criteria andTableIdxLessThanOrEqualTo(String value) {
            addCriterion("TABLE_IDX <=", value, "tableIdx");
            return (Criteria) this;
        }

        public Criteria andTableIdxLike(String value) {
            addCriterion("TABLE_IDX like", value, "tableIdx");
            return (Criteria) this;
        }

        public Criteria andTableIdxNotLike(String value) {
            addCriterion("TABLE_IDX not like", value, "tableIdx");
            return (Criteria) this;
        }

        public Criteria andTableIdxIn(List<String> values) {
            addCriterion("TABLE_IDX in", values, "tableIdx");
            return (Criteria) this;
        }

        public Criteria andTableIdxNotIn(List<String> values) {
            addCriterion("TABLE_IDX not in", values, "tableIdx");
            return (Criteria) this;
        }

        public Criteria andTableIdxBetween(String value1, String value2) {
            addCriterion("TABLE_IDX between", value1, value2, "tableIdx");
            return (Criteria) this;
        }

        public Criteria andTableIdxNotBetween(String value1, String value2) {
            addCriterion("TABLE_IDX not between", value1, value2, "tableIdx");
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

        public Criteria andDbIdxLikeInsensitive(String value) {
            addCriterion("upper(DB_IDX) like", value.toUpperCase(), "dbIdx");
            return (Criteria) this;
        }

        public Criteria andTableIdxLikeInsensitive(String value) {
            addCriterion("upper(TABLE_IDX) like", value.toUpperCase(), "tableIdx");
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