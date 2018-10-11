package com.segi.uhomecp.medicaltrans.report.monthamount.time.model;

import java.util.ArrayList;
import java.util.List;

import com.segi.uhomecp.common.model.AbstractCriteria;

public class OrganTimeStatisticsCriteria extends AbstractCriteria {
    
	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = 3952787546270917786L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrganTimeStatisticsCriteria() {
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

        public Criteria andTimeAmount0IsNull() {
            addCriterion("TIME_AMOUNT_0 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount0IsNotNull() {
            addCriterion("TIME_AMOUNT_0 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount0EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_0 =", value, "timeAmount0");
            return (Criteria) this;
        }

        public Criteria andTimeAmount0NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_0 <>", value, "timeAmount0");
            return (Criteria) this;
        }

        public Criteria andTimeAmount0GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_0 >", value, "timeAmount0");
            return (Criteria) this;
        }

        public Criteria andTimeAmount0GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_0 >=", value, "timeAmount0");
            return (Criteria) this;
        }

        public Criteria andTimeAmount0LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_0 <", value, "timeAmount0");
            return (Criteria) this;
        }

        public Criteria andTimeAmount0LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_0 <=", value, "timeAmount0");
            return (Criteria) this;
        }

        public Criteria andTimeAmount0In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_0 in", values, "timeAmount0");
            return (Criteria) this;
        }

        public Criteria andTimeAmount0NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_0 not in", values, "timeAmount0");
            return (Criteria) this;
        }

        public Criteria andTimeAmount0Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_0 between", value1, value2, "timeAmount0");
            return (Criteria) this;
        }

        public Criteria andTimeAmount0NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_0 not between", value1, value2, "timeAmount0");
            return (Criteria) this;
        }

        public Criteria andTimeAmount1IsNull() {
            addCriterion("TIME_AMOUNT_1 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount1IsNotNull() {
            addCriterion("TIME_AMOUNT_1 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount1EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_1 =", value, "timeAmount1");
            return (Criteria) this;
        }

        public Criteria andTimeAmount1NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_1 <>", value, "timeAmount1");
            return (Criteria) this;
        }

        public Criteria andTimeAmount1GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_1 >", value, "timeAmount1");
            return (Criteria) this;
        }

        public Criteria andTimeAmount1GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_1 >=", value, "timeAmount1");
            return (Criteria) this;
        }

        public Criteria andTimeAmount1LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_1 <", value, "timeAmount1");
            return (Criteria) this;
        }

        public Criteria andTimeAmount1LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_1 <=", value, "timeAmount1");
            return (Criteria) this;
        }

        public Criteria andTimeAmount1In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_1 in", values, "timeAmount1");
            return (Criteria) this;
        }

        public Criteria andTimeAmount1NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_1 not in", values, "timeAmount1");
            return (Criteria) this;
        }

        public Criteria andTimeAmount1Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_1 between", value1, value2, "timeAmount1");
            return (Criteria) this;
        }

        public Criteria andTimeAmount1NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_1 not between", value1, value2, "timeAmount1");
            return (Criteria) this;
        }

        public Criteria andTimeAmount2IsNull() {
            addCriterion("TIME_AMOUNT_2 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount2IsNotNull() {
            addCriterion("TIME_AMOUNT_2 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount2EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_2 =", value, "timeAmount2");
            return (Criteria) this;
        }

        public Criteria andTimeAmount2NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_2 <>", value, "timeAmount2");
            return (Criteria) this;
        }

        public Criteria andTimeAmount2GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_2 >", value, "timeAmount2");
            return (Criteria) this;
        }

        public Criteria andTimeAmount2GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_2 >=", value, "timeAmount2");
            return (Criteria) this;
        }

        public Criteria andTimeAmount2LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_2 <", value, "timeAmount2");
            return (Criteria) this;
        }

        public Criteria andTimeAmount2LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_2 <=", value, "timeAmount2");
            return (Criteria) this;
        }

        public Criteria andTimeAmount2In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_2 in", values, "timeAmount2");
            return (Criteria) this;
        }

        public Criteria andTimeAmount2NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_2 not in", values, "timeAmount2");
            return (Criteria) this;
        }

        public Criteria andTimeAmount2Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_2 between", value1, value2, "timeAmount2");
            return (Criteria) this;
        }

        public Criteria andTimeAmount2NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_2 not between", value1, value2, "timeAmount2");
            return (Criteria) this;
        }

        public Criteria andTimeAmount3IsNull() {
            addCriterion("TIME_AMOUNT_3 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount3IsNotNull() {
            addCriterion("TIME_AMOUNT_3 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount3EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_3 =", value, "timeAmount3");
            return (Criteria) this;
        }

        public Criteria andTimeAmount3NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_3 <>", value, "timeAmount3");
            return (Criteria) this;
        }

        public Criteria andTimeAmount3GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_3 >", value, "timeAmount3");
            return (Criteria) this;
        }

        public Criteria andTimeAmount3GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_3 >=", value, "timeAmount3");
            return (Criteria) this;
        }

        public Criteria andTimeAmount3LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_3 <", value, "timeAmount3");
            return (Criteria) this;
        }

        public Criteria andTimeAmount3LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_3 <=", value, "timeAmount3");
            return (Criteria) this;
        }

        public Criteria andTimeAmount3In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_3 in", values, "timeAmount3");
            return (Criteria) this;
        }

        public Criteria andTimeAmount3NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_3 not in", values, "timeAmount3");
            return (Criteria) this;
        }

        public Criteria andTimeAmount3Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_3 between", value1, value2, "timeAmount3");
            return (Criteria) this;
        }

        public Criteria andTimeAmount3NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_3 not between", value1, value2, "timeAmount3");
            return (Criteria) this;
        }

        public Criteria andTimeAmount4IsNull() {
            addCriterion("TIME_AMOUNT_4 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount4IsNotNull() {
            addCriterion("TIME_AMOUNT_4 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount4EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_4 =", value, "timeAmount4");
            return (Criteria) this;
        }

        public Criteria andTimeAmount4NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_4 <>", value, "timeAmount4");
            return (Criteria) this;
        }

        public Criteria andTimeAmount4GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_4 >", value, "timeAmount4");
            return (Criteria) this;
        }

        public Criteria andTimeAmount4GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_4 >=", value, "timeAmount4");
            return (Criteria) this;
        }

        public Criteria andTimeAmount4LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_4 <", value, "timeAmount4");
            return (Criteria) this;
        }

        public Criteria andTimeAmount4LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_4 <=", value, "timeAmount4");
            return (Criteria) this;
        }

        public Criteria andTimeAmount4In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_4 in", values, "timeAmount4");
            return (Criteria) this;
        }

        public Criteria andTimeAmount4NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_4 not in", values, "timeAmount4");
            return (Criteria) this;
        }

        public Criteria andTimeAmount4Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_4 between", value1, value2, "timeAmount4");
            return (Criteria) this;
        }

        public Criteria andTimeAmount4NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_4 not between", value1, value2, "timeAmount4");
            return (Criteria) this;
        }

        public Criteria andTimeAmount5IsNull() {
            addCriterion("TIME_AMOUNT_5 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount5IsNotNull() {
            addCriterion("TIME_AMOUNT_5 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount5EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_5 =", value, "timeAmount5");
            return (Criteria) this;
        }

        public Criteria andTimeAmount5NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_5 <>", value, "timeAmount5");
            return (Criteria) this;
        }

        public Criteria andTimeAmount5GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_5 >", value, "timeAmount5");
            return (Criteria) this;
        }

        public Criteria andTimeAmount5GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_5 >=", value, "timeAmount5");
            return (Criteria) this;
        }

        public Criteria andTimeAmount5LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_5 <", value, "timeAmount5");
            return (Criteria) this;
        }

        public Criteria andTimeAmount5LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_5 <=", value, "timeAmount5");
            return (Criteria) this;
        }

        public Criteria andTimeAmount5In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_5 in", values, "timeAmount5");
            return (Criteria) this;
        }

        public Criteria andTimeAmount5NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_5 not in", values, "timeAmount5");
            return (Criteria) this;
        }

        public Criteria andTimeAmount5Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_5 between", value1, value2, "timeAmount5");
            return (Criteria) this;
        }

        public Criteria andTimeAmount5NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_5 not between", value1, value2, "timeAmount5");
            return (Criteria) this;
        }

        public Criteria andTimeAmount6IsNull() {
            addCriterion("TIME_AMOUNT_6 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount6IsNotNull() {
            addCriterion("TIME_AMOUNT_6 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount6EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_6 =", value, "timeAmount6");
            return (Criteria) this;
        }

        public Criteria andTimeAmount6NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_6 <>", value, "timeAmount6");
            return (Criteria) this;
        }

        public Criteria andTimeAmount6GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_6 >", value, "timeAmount6");
            return (Criteria) this;
        }

        public Criteria andTimeAmount6GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_6 >=", value, "timeAmount6");
            return (Criteria) this;
        }

        public Criteria andTimeAmount6LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_6 <", value, "timeAmount6");
            return (Criteria) this;
        }

        public Criteria andTimeAmount6LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_6 <=", value, "timeAmount6");
            return (Criteria) this;
        }

        public Criteria andTimeAmount6In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_6 in", values, "timeAmount6");
            return (Criteria) this;
        }

        public Criteria andTimeAmount6NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_6 not in", values, "timeAmount6");
            return (Criteria) this;
        }

        public Criteria andTimeAmount6Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_6 between", value1, value2, "timeAmount6");
            return (Criteria) this;
        }

        public Criteria andTimeAmount6NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_6 not between", value1, value2, "timeAmount6");
            return (Criteria) this;
        }

        public Criteria andTimeAmount7IsNull() {
            addCriterion("TIME_AMOUNT_7 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount7IsNotNull() {
            addCriterion("TIME_AMOUNT_7 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount7EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_7 =", value, "timeAmount7");
            return (Criteria) this;
        }

        public Criteria andTimeAmount7NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_7 <>", value, "timeAmount7");
            return (Criteria) this;
        }

        public Criteria andTimeAmount7GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_7 >", value, "timeAmount7");
            return (Criteria) this;
        }

        public Criteria andTimeAmount7GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_7 >=", value, "timeAmount7");
            return (Criteria) this;
        }

        public Criteria andTimeAmount7LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_7 <", value, "timeAmount7");
            return (Criteria) this;
        }

        public Criteria andTimeAmount7LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_7 <=", value, "timeAmount7");
            return (Criteria) this;
        }

        public Criteria andTimeAmount7In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_7 in", values, "timeAmount7");
            return (Criteria) this;
        }

        public Criteria andTimeAmount7NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_7 not in", values, "timeAmount7");
            return (Criteria) this;
        }

        public Criteria andTimeAmount7Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_7 between", value1, value2, "timeAmount7");
            return (Criteria) this;
        }

        public Criteria andTimeAmount7NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_7 not between", value1, value2, "timeAmount7");
            return (Criteria) this;
        }

        public Criteria andTimeAmount8IsNull() {
            addCriterion("TIME_AMOUNT_8 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount8IsNotNull() {
            addCriterion("TIME_AMOUNT_8 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount8EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_8 =", value, "timeAmount8");
            return (Criteria) this;
        }

        public Criteria andTimeAmount8NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_8 <>", value, "timeAmount8");
            return (Criteria) this;
        }

        public Criteria andTimeAmount8GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_8 >", value, "timeAmount8");
            return (Criteria) this;
        }

        public Criteria andTimeAmount8GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_8 >=", value, "timeAmount8");
            return (Criteria) this;
        }

        public Criteria andTimeAmount8LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_8 <", value, "timeAmount8");
            return (Criteria) this;
        }

        public Criteria andTimeAmount8LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_8 <=", value, "timeAmount8");
            return (Criteria) this;
        }

        public Criteria andTimeAmount8In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_8 in", values, "timeAmount8");
            return (Criteria) this;
        }

        public Criteria andTimeAmount8NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_8 not in", values, "timeAmount8");
            return (Criteria) this;
        }

        public Criteria andTimeAmount8Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_8 between", value1, value2, "timeAmount8");
            return (Criteria) this;
        }

        public Criteria andTimeAmount8NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_8 not between", value1, value2, "timeAmount8");
            return (Criteria) this;
        }

        public Criteria andTimeAmount9IsNull() {
            addCriterion("TIME_AMOUNT_9 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount9IsNotNull() {
            addCriterion("TIME_AMOUNT_9 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount9EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_9 =", value, "timeAmount9");
            return (Criteria) this;
        }

        public Criteria andTimeAmount9NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_9 <>", value, "timeAmount9");
            return (Criteria) this;
        }

        public Criteria andTimeAmount9GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_9 >", value, "timeAmount9");
            return (Criteria) this;
        }

        public Criteria andTimeAmount9GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_9 >=", value, "timeAmount9");
            return (Criteria) this;
        }

        public Criteria andTimeAmount9LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_9 <", value, "timeAmount9");
            return (Criteria) this;
        }

        public Criteria andTimeAmount9LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_9 <=", value, "timeAmount9");
            return (Criteria) this;
        }

        public Criteria andTimeAmount9In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_9 in", values, "timeAmount9");
            return (Criteria) this;
        }

        public Criteria andTimeAmount9NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_9 not in", values, "timeAmount9");
            return (Criteria) this;
        }

        public Criteria andTimeAmount9Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_9 between", value1, value2, "timeAmount9");
            return (Criteria) this;
        }

        public Criteria andTimeAmount9NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_9 not between", value1, value2, "timeAmount9");
            return (Criteria) this;
        }

        public Criteria andTimeAmount10IsNull() {
            addCriterion("TIME_AMOUNT_10 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount10IsNotNull() {
            addCriterion("TIME_AMOUNT_10 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount10EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_10 =", value, "timeAmount10");
            return (Criteria) this;
        }

        public Criteria andTimeAmount10NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_10 <>", value, "timeAmount10");
            return (Criteria) this;
        }

        public Criteria andTimeAmount10GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_10 >", value, "timeAmount10");
            return (Criteria) this;
        }

        public Criteria andTimeAmount10GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_10 >=", value, "timeAmount10");
            return (Criteria) this;
        }

        public Criteria andTimeAmount10LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_10 <", value, "timeAmount10");
            return (Criteria) this;
        }

        public Criteria andTimeAmount10LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_10 <=", value, "timeAmount10");
            return (Criteria) this;
        }

        public Criteria andTimeAmount10In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_10 in", values, "timeAmount10");
            return (Criteria) this;
        }

        public Criteria andTimeAmount10NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_10 not in", values, "timeAmount10");
            return (Criteria) this;
        }

        public Criteria andTimeAmount10Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_10 between", value1, value2, "timeAmount10");
            return (Criteria) this;
        }

        public Criteria andTimeAmount10NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_10 not between", value1, value2, "timeAmount10");
            return (Criteria) this;
        }

        public Criteria andTimeAmount11IsNull() {
            addCriterion("TIME_AMOUNT_11 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount11IsNotNull() {
            addCriterion("TIME_AMOUNT_11 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount11EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_11 =", value, "timeAmount11");
            return (Criteria) this;
        }

        public Criteria andTimeAmount11NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_11 <>", value, "timeAmount11");
            return (Criteria) this;
        }

        public Criteria andTimeAmount11GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_11 >", value, "timeAmount11");
            return (Criteria) this;
        }

        public Criteria andTimeAmount11GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_11 >=", value, "timeAmount11");
            return (Criteria) this;
        }

        public Criteria andTimeAmount11LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_11 <", value, "timeAmount11");
            return (Criteria) this;
        }

        public Criteria andTimeAmount11LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_11 <=", value, "timeAmount11");
            return (Criteria) this;
        }

        public Criteria andTimeAmount11In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_11 in", values, "timeAmount11");
            return (Criteria) this;
        }

        public Criteria andTimeAmount11NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_11 not in", values, "timeAmount11");
            return (Criteria) this;
        }

        public Criteria andTimeAmount11Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_11 between", value1, value2, "timeAmount11");
            return (Criteria) this;
        }

        public Criteria andTimeAmount11NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_11 not between", value1, value2, "timeAmount11");
            return (Criteria) this;
        }

        public Criteria andTimeAmount12IsNull() {
            addCriterion("TIME_AMOUNT_12 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount12IsNotNull() {
            addCriterion("TIME_AMOUNT_12 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount12EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_12 =", value, "timeAmount12");
            return (Criteria) this;
        }

        public Criteria andTimeAmount12NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_12 <>", value, "timeAmount12");
            return (Criteria) this;
        }

        public Criteria andTimeAmount12GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_12 >", value, "timeAmount12");
            return (Criteria) this;
        }

        public Criteria andTimeAmount12GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_12 >=", value, "timeAmount12");
            return (Criteria) this;
        }

        public Criteria andTimeAmount12LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_12 <", value, "timeAmount12");
            return (Criteria) this;
        }

        public Criteria andTimeAmount12LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_12 <=", value, "timeAmount12");
            return (Criteria) this;
        }

        public Criteria andTimeAmount12In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_12 in", values, "timeAmount12");
            return (Criteria) this;
        }

        public Criteria andTimeAmount12NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_12 not in", values, "timeAmount12");
            return (Criteria) this;
        }

        public Criteria andTimeAmount12Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_12 between", value1, value2, "timeAmount12");
            return (Criteria) this;
        }

        public Criteria andTimeAmount12NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_12 not between", value1, value2, "timeAmount12");
            return (Criteria) this;
        }

        public Criteria andTimeAmount13IsNull() {
            addCriterion("TIME_AMOUNT_13 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount13IsNotNull() {
            addCriterion("TIME_AMOUNT_13 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount13EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_13 =", value, "timeAmount13");
            return (Criteria) this;
        }

        public Criteria andTimeAmount13NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_13 <>", value, "timeAmount13");
            return (Criteria) this;
        }

        public Criteria andTimeAmount13GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_13 >", value, "timeAmount13");
            return (Criteria) this;
        }

        public Criteria andTimeAmount13GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_13 >=", value, "timeAmount13");
            return (Criteria) this;
        }

        public Criteria andTimeAmount13LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_13 <", value, "timeAmount13");
            return (Criteria) this;
        }

        public Criteria andTimeAmount13LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_13 <=", value, "timeAmount13");
            return (Criteria) this;
        }

        public Criteria andTimeAmount13In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_13 in", values, "timeAmount13");
            return (Criteria) this;
        }

        public Criteria andTimeAmount13NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_13 not in", values, "timeAmount13");
            return (Criteria) this;
        }

        public Criteria andTimeAmount13Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_13 between", value1, value2, "timeAmount13");
            return (Criteria) this;
        }

        public Criteria andTimeAmount13NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_13 not between", value1, value2, "timeAmount13");
            return (Criteria) this;
        }

        public Criteria andTimeAmount14IsNull() {
            addCriterion("TIME_AMOUNT_14 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount14IsNotNull() {
            addCriterion("TIME_AMOUNT_14 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount14EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_14 =", value, "timeAmount14");
            return (Criteria) this;
        }

        public Criteria andTimeAmount14NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_14 <>", value, "timeAmount14");
            return (Criteria) this;
        }

        public Criteria andTimeAmount14GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_14 >", value, "timeAmount14");
            return (Criteria) this;
        }

        public Criteria andTimeAmount14GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_14 >=", value, "timeAmount14");
            return (Criteria) this;
        }

        public Criteria andTimeAmount14LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_14 <", value, "timeAmount14");
            return (Criteria) this;
        }

        public Criteria andTimeAmount14LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_14 <=", value, "timeAmount14");
            return (Criteria) this;
        }

        public Criteria andTimeAmount14In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_14 in", values, "timeAmount14");
            return (Criteria) this;
        }

        public Criteria andTimeAmount14NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_14 not in", values, "timeAmount14");
            return (Criteria) this;
        }

        public Criteria andTimeAmount14Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_14 between", value1, value2, "timeAmount14");
            return (Criteria) this;
        }

        public Criteria andTimeAmount14NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_14 not between", value1, value2, "timeAmount14");
            return (Criteria) this;
        }

        public Criteria andTimeAmount15IsNull() {
            addCriterion("TIME_AMOUNT_15 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount15IsNotNull() {
            addCriterion("TIME_AMOUNT_15 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount15EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_15 =", value, "timeAmount15");
            return (Criteria) this;
        }

        public Criteria andTimeAmount15NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_15 <>", value, "timeAmount15");
            return (Criteria) this;
        }

        public Criteria andTimeAmount15GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_15 >", value, "timeAmount15");
            return (Criteria) this;
        }

        public Criteria andTimeAmount15GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_15 >=", value, "timeAmount15");
            return (Criteria) this;
        }

        public Criteria andTimeAmount15LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_15 <", value, "timeAmount15");
            return (Criteria) this;
        }

        public Criteria andTimeAmount15LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_15 <=", value, "timeAmount15");
            return (Criteria) this;
        }

        public Criteria andTimeAmount15In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_15 in", values, "timeAmount15");
            return (Criteria) this;
        }

        public Criteria andTimeAmount15NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_15 not in", values, "timeAmount15");
            return (Criteria) this;
        }

        public Criteria andTimeAmount15Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_15 between", value1, value2, "timeAmount15");
            return (Criteria) this;
        }

        public Criteria andTimeAmount15NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_15 not between", value1, value2, "timeAmount15");
            return (Criteria) this;
        }

        public Criteria andTimeAmount16IsNull() {
            addCriterion("TIME_AMOUNT_16 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount16IsNotNull() {
            addCriterion("TIME_AMOUNT_16 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount16EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_16 =", value, "timeAmount16");
            return (Criteria) this;
        }

        public Criteria andTimeAmount16NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_16 <>", value, "timeAmount16");
            return (Criteria) this;
        }

        public Criteria andTimeAmount16GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_16 >", value, "timeAmount16");
            return (Criteria) this;
        }

        public Criteria andTimeAmount16GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_16 >=", value, "timeAmount16");
            return (Criteria) this;
        }

        public Criteria andTimeAmount16LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_16 <", value, "timeAmount16");
            return (Criteria) this;
        }

        public Criteria andTimeAmount16LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_16 <=", value, "timeAmount16");
            return (Criteria) this;
        }

        public Criteria andTimeAmount16In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_16 in", values, "timeAmount16");
            return (Criteria) this;
        }

        public Criteria andTimeAmount16NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_16 not in", values, "timeAmount16");
            return (Criteria) this;
        }

        public Criteria andTimeAmount16Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_16 between", value1, value2, "timeAmount16");
            return (Criteria) this;
        }

        public Criteria andTimeAmount16NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_16 not between", value1, value2, "timeAmount16");
            return (Criteria) this;
        }

        public Criteria andTimeAmount17IsNull() {
            addCriterion("TIME_AMOUNT_17 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount17IsNotNull() {
            addCriterion("TIME_AMOUNT_17 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount17EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_17 =", value, "timeAmount17");
            return (Criteria) this;
        }

        public Criteria andTimeAmount17NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_17 <>", value, "timeAmount17");
            return (Criteria) this;
        }

        public Criteria andTimeAmount17GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_17 >", value, "timeAmount17");
            return (Criteria) this;
        }

        public Criteria andTimeAmount17GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_17 >=", value, "timeAmount17");
            return (Criteria) this;
        }

        public Criteria andTimeAmount17LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_17 <", value, "timeAmount17");
            return (Criteria) this;
        }

        public Criteria andTimeAmount17LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_17 <=", value, "timeAmount17");
            return (Criteria) this;
        }

        public Criteria andTimeAmount17In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_17 in", values, "timeAmount17");
            return (Criteria) this;
        }

        public Criteria andTimeAmount17NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_17 not in", values, "timeAmount17");
            return (Criteria) this;
        }

        public Criteria andTimeAmount17Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_17 between", value1, value2, "timeAmount17");
            return (Criteria) this;
        }

        public Criteria andTimeAmount17NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_17 not between", value1, value2, "timeAmount17");
            return (Criteria) this;
        }

        public Criteria andTimeAmount18IsNull() {
            addCriterion("TIME_AMOUNT_18 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount18IsNotNull() {
            addCriterion("TIME_AMOUNT_18 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount18EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_18 =", value, "timeAmount18");
            return (Criteria) this;
        }

        public Criteria andTimeAmount18NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_18 <>", value, "timeAmount18");
            return (Criteria) this;
        }

        public Criteria andTimeAmount18GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_18 >", value, "timeAmount18");
            return (Criteria) this;
        }

        public Criteria andTimeAmount18GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_18 >=", value, "timeAmount18");
            return (Criteria) this;
        }

        public Criteria andTimeAmount18LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_18 <", value, "timeAmount18");
            return (Criteria) this;
        }

        public Criteria andTimeAmount18LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_18 <=", value, "timeAmount18");
            return (Criteria) this;
        }

        public Criteria andTimeAmount18In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_18 in", values, "timeAmount18");
            return (Criteria) this;
        }

        public Criteria andTimeAmount18NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_18 not in", values, "timeAmount18");
            return (Criteria) this;
        }

        public Criteria andTimeAmount18Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_18 between", value1, value2, "timeAmount18");
            return (Criteria) this;
        }

        public Criteria andTimeAmount18NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_18 not between", value1, value2, "timeAmount18");
            return (Criteria) this;
        }

        public Criteria andTimeAmount19IsNull() {
            addCriterion("TIME_AMOUNT_19 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount19IsNotNull() {
            addCriterion("TIME_AMOUNT_19 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount19EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_19 =", value, "timeAmount19");
            return (Criteria) this;
        }

        public Criteria andTimeAmount19NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_19 <>", value, "timeAmount19");
            return (Criteria) this;
        }

        public Criteria andTimeAmount19GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_19 >", value, "timeAmount19");
            return (Criteria) this;
        }

        public Criteria andTimeAmount19GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_19 >=", value, "timeAmount19");
            return (Criteria) this;
        }

        public Criteria andTimeAmount19LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_19 <", value, "timeAmount19");
            return (Criteria) this;
        }

        public Criteria andTimeAmount19LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_19 <=", value, "timeAmount19");
            return (Criteria) this;
        }

        public Criteria andTimeAmount19In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_19 in", values, "timeAmount19");
            return (Criteria) this;
        }

        public Criteria andTimeAmount19NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_19 not in", values, "timeAmount19");
            return (Criteria) this;
        }

        public Criteria andTimeAmount19Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_19 between", value1, value2, "timeAmount19");
            return (Criteria) this;
        }

        public Criteria andTimeAmount19NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_19 not between", value1, value2, "timeAmount19");
            return (Criteria) this;
        }

        public Criteria andTimeAmount20IsNull() {
            addCriterion("TIME_AMOUNT_20 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount20IsNotNull() {
            addCriterion("TIME_AMOUNT_20 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount20EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_20 =", value, "timeAmount20");
            return (Criteria) this;
        }

        public Criteria andTimeAmount20NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_20 <>", value, "timeAmount20");
            return (Criteria) this;
        }

        public Criteria andTimeAmount20GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_20 >", value, "timeAmount20");
            return (Criteria) this;
        }

        public Criteria andTimeAmount20GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_20 >=", value, "timeAmount20");
            return (Criteria) this;
        }

        public Criteria andTimeAmount20LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_20 <", value, "timeAmount20");
            return (Criteria) this;
        }

        public Criteria andTimeAmount20LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_20 <=", value, "timeAmount20");
            return (Criteria) this;
        }

        public Criteria andTimeAmount20In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_20 in", values, "timeAmount20");
            return (Criteria) this;
        }

        public Criteria andTimeAmount20NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_20 not in", values, "timeAmount20");
            return (Criteria) this;
        }

        public Criteria andTimeAmount20Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_20 between", value1, value2, "timeAmount20");
            return (Criteria) this;
        }

        public Criteria andTimeAmount20NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_20 not between", value1, value2, "timeAmount20");
            return (Criteria) this;
        }

        public Criteria andTimeAmount21IsNull() {
            addCriterion("TIME_AMOUNT_21 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount21IsNotNull() {
            addCriterion("TIME_AMOUNT_21 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount21EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_21 =", value, "timeAmount21");
            return (Criteria) this;
        }

        public Criteria andTimeAmount21NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_21 <>", value, "timeAmount21");
            return (Criteria) this;
        }

        public Criteria andTimeAmount21GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_21 >", value, "timeAmount21");
            return (Criteria) this;
        }

        public Criteria andTimeAmount21GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_21 >=", value, "timeAmount21");
            return (Criteria) this;
        }

        public Criteria andTimeAmount21LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_21 <", value, "timeAmount21");
            return (Criteria) this;
        }

        public Criteria andTimeAmount21LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_21 <=", value, "timeAmount21");
            return (Criteria) this;
        }

        public Criteria andTimeAmount21In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_21 in", values, "timeAmount21");
            return (Criteria) this;
        }

        public Criteria andTimeAmount21NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_21 not in", values, "timeAmount21");
            return (Criteria) this;
        }

        public Criteria andTimeAmount21Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_21 between", value1, value2, "timeAmount21");
            return (Criteria) this;
        }

        public Criteria andTimeAmount21NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_21 not between", value1, value2, "timeAmount21");
            return (Criteria) this;
        }

        public Criteria andTimeAmount22IsNull() {
            addCriterion("TIME_AMOUNT_22 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount22IsNotNull() {
            addCriterion("TIME_AMOUNT_22 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount22EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_22 =", value, "timeAmount22");
            return (Criteria) this;
        }

        public Criteria andTimeAmount22NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_22 <>", value, "timeAmount22");
            return (Criteria) this;
        }

        public Criteria andTimeAmount22GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_22 >", value, "timeAmount22");
            return (Criteria) this;
        }

        public Criteria andTimeAmount22GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_22 >=", value, "timeAmount22");
            return (Criteria) this;
        }

        public Criteria andTimeAmount22LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_22 <", value, "timeAmount22");
            return (Criteria) this;
        }

        public Criteria andTimeAmount22LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_22 <=", value, "timeAmount22");
            return (Criteria) this;
        }

        public Criteria andTimeAmount22In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_22 in", values, "timeAmount22");
            return (Criteria) this;
        }

        public Criteria andTimeAmount22NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_22 not in", values, "timeAmount22");
            return (Criteria) this;
        }

        public Criteria andTimeAmount22Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_22 between", value1, value2, "timeAmount22");
            return (Criteria) this;
        }

        public Criteria andTimeAmount22NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_22 not between", value1, value2, "timeAmount22");
            return (Criteria) this;
        }

        public Criteria andTimeAmount23IsNull() {
            addCriterion("TIME_AMOUNT_23 is null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount23IsNotNull() {
            addCriterion("TIME_AMOUNT_23 is not null");
            return (Criteria) this;
        }

        public Criteria andTimeAmount23EqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_23 =", value, "timeAmount23");
            return (Criteria) this;
        }

        public Criteria andTimeAmount23NotEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_23 <>", value, "timeAmount23");
            return (Criteria) this;
        }

        public Criteria andTimeAmount23GreaterThan(Integer value) {
            addCriterion("TIME_AMOUNT_23 >", value, "timeAmount23");
            return (Criteria) this;
        }

        public Criteria andTimeAmount23GreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_23 >=", value, "timeAmount23");
            return (Criteria) this;
        }

        public Criteria andTimeAmount23LessThan(Integer value) {
            addCriterion("TIME_AMOUNT_23 <", value, "timeAmount23");
            return (Criteria) this;
        }

        public Criteria andTimeAmount23LessThanOrEqualTo(Integer value) {
            addCriterion("TIME_AMOUNT_23 <=", value, "timeAmount23");
            return (Criteria) this;
        }

        public Criteria andTimeAmount23In(List<Integer> values) {
            addCriterion("TIME_AMOUNT_23 in", values, "timeAmount23");
            return (Criteria) this;
        }

        public Criteria andTimeAmount23NotIn(List<Integer> values) {
            addCriterion("TIME_AMOUNT_23 not in", values, "timeAmount23");
            return (Criteria) this;
        }

        public Criteria andTimeAmount23Between(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_23 between", value1, value2, "timeAmount23");
            return (Criteria) this;
        }

        public Criteria andTimeAmount23NotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_AMOUNT_23 not between", value1, value2, "timeAmount23");
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