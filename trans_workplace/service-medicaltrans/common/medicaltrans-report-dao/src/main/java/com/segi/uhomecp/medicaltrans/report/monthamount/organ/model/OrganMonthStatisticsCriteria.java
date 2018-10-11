package com.segi.uhomecp.medicaltrans.report.monthamount.organ.model;

import java.util.ArrayList;
import java.util.List;

import com.segi.uhomecp.common.model.AbstractCriteria;

public class OrganMonthStatisticsCriteria extends AbstractCriteria {
    
	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = 8241569901602892953L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrganMonthStatisticsCriteria() {
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

        public Criteria andTransAmountIsNull() {
            addCriterion("TRANS_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andTransAmountIsNotNull() {
            addCriterion("TRANS_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTransAmountEqualTo(Integer value) {
            addCriterion("TRANS_AMOUNT =", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountNotEqualTo(Integer value) {
            addCriterion("TRANS_AMOUNT <>", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountGreaterThan(Integer value) {
            addCriterion("TRANS_AMOUNT >", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("TRANS_AMOUNT >=", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountLessThan(Integer value) {
            addCriterion("TRANS_AMOUNT <", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountLessThanOrEqualTo(Integer value) {
            addCriterion("TRANS_AMOUNT <=", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountIn(List<Integer> values) {
            addCriterion("TRANS_AMOUNT in", values, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountNotIn(List<Integer> values) {
            addCriterion("TRANS_AMOUNT not in", values, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountBetween(Integer value1, Integer value2) {
            addCriterion("TRANS_AMOUNT between", value1, value2, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("TRANS_AMOUNT not between", value1, value2, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransUserAmountIsNull() {
            addCriterion("TRANS_USER_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andTransUserAmountIsNotNull() {
            addCriterion("TRANS_USER_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTransUserAmountEqualTo(Integer value) {
            addCriterion("TRANS_USER_AMOUNT =", value, "transUserAmount");
            return (Criteria) this;
        }

        public Criteria andTransUserAmountNotEqualTo(Integer value) {
            addCriterion("TRANS_USER_AMOUNT <>", value, "transUserAmount");
            return (Criteria) this;
        }

        public Criteria andTransUserAmountGreaterThan(Integer value) {
            addCriterion("TRANS_USER_AMOUNT >", value, "transUserAmount");
            return (Criteria) this;
        }

        public Criteria andTransUserAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("TRANS_USER_AMOUNT >=", value, "transUserAmount");
            return (Criteria) this;
        }

        public Criteria andTransUserAmountLessThan(Integer value) {
            addCriterion("TRANS_USER_AMOUNT <", value, "transUserAmount");
            return (Criteria) this;
        }

        public Criteria andTransUserAmountLessThanOrEqualTo(Integer value) {
            addCriterion("TRANS_USER_AMOUNT <=", value, "transUserAmount");
            return (Criteria) this;
        }

        public Criteria andTransUserAmountIn(List<Integer> values) {
            addCriterion("TRANS_USER_AMOUNT in", values, "transUserAmount");
            return (Criteria) this;
        }

        public Criteria andTransUserAmountNotIn(List<Integer> values) {
            addCriterion("TRANS_USER_AMOUNT not in", values, "transUserAmount");
            return (Criteria) this;
        }

        public Criteria andTransUserAmountBetween(Integer value1, Integer value2) {
            addCriterion("TRANS_USER_AMOUNT between", value1, value2, "transUserAmount");
            return (Criteria) this;
        }

        public Criteria andTransUserAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("TRANS_USER_AMOUNT not between", value1, value2, "transUserAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchAmountIsNull() {
            addCriterion("DISPATCH_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andDispatchAmountIsNotNull() {
            addCriterion("DISPATCH_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDispatchAmountEqualTo(Integer value) {
            addCriterion("DISPATCH_AMOUNT =", value, "dispatchAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchAmountNotEqualTo(Integer value) {
            addCriterion("DISPATCH_AMOUNT <>", value, "dispatchAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchAmountGreaterThan(Integer value) {
            addCriterion("DISPATCH_AMOUNT >", value, "dispatchAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("DISPATCH_AMOUNT >=", value, "dispatchAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchAmountLessThan(Integer value) {
            addCriterion("DISPATCH_AMOUNT <", value, "dispatchAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchAmountLessThanOrEqualTo(Integer value) {
            addCriterion("DISPATCH_AMOUNT <=", value, "dispatchAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchAmountIn(List<Integer> values) {
            addCriterion("DISPATCH_AMOUNT in", values, "dispatchAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchAmountNotIn(List<Integer> values) {
            addCriterion("DISPATCH_AMOUNT not in", values, "dispatchAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchAmountBetween(Integer value1, Integer value2) {
            addCriterion("DISPATCH_AMOUNT between", value1, value2, "dispatchAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("DISPATCH_AMOUNT not between", value1, value2, "dispatchAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousAmountIsNull() {
            addCriterion("AUTONOMOUS_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andAutonomousAmountIsNotNull() {
            addCriterion("AUTONOMOUS_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAutonomousAmountEqualTo(Integer value) {
            addCriterion("AUTONOMOUS_AMOUNT =", value, "autonomousAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousAmountNotEqualTo(Integer value) {
            addCriterion("AUTONOMOUS_AMOUNT <>", value, "autonomousAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousAmountGreaterThan(Integer value) {
            addCriterion("AUTONOMOUS_AMOUNT >", value, "autonomousAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("AUTONOMOUS_AMOUNT >=", value, "autonomousAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousAmountLessThan(Integer value) {
            addCriterion("AUTONOMOUS_AMOUNT <", value, "autonomousAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousAmountLessThanOrEqualTo(Integer value) {
            addCriterion("AUTONOMOUS_AMOUNT <=", value, "autonomousAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousAmountIn(List<Integer> values) {
            addCriterion("AUTONOMOUS_AMOUNT in", values, "autonomousAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousAmountNotIn(List<Integer> values) {
            addCriterion("AUTONOMOUS_AMOUNT not in", values, "autonomousAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousAmountBetween(Integer value1, Integer value2) {
            addCriterion("AUTONOMOUS_AMOUNT between", value1, value2, "autonomousAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("AUTONOMOUS_AMOUNT not between", value1, value2, "autonomousAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountIsNull() {
            addCriterion("FIXED_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andFixedAmountIsNotNull() {
            addCriterion("FIXED_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andFixedAmountEqualTo(Integer value) {
            addCriterion("FIXED_AMOUNT =", value, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountNotEqualTo(Integer value) {
            addCriterion("FIXED_AMOUNT <>", value, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountGreaterThan(Integer value) {
            addCriterion("FIXED_AMOUNT >", value, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("FIXED_AMOUNT >=", value, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountLessThan(Integer value) {
            addCriterion("FIXED_AMOUNT <", value, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountLessThanOrEqualTo(Integer value) {
            addCriterion("FIXED_AMOUNT <=", value, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountIn(List<Integer> values) {
            addCriterion("FIXED_AMOUNT in", values, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountNotIn(List<Integer> values) {
            addCriterion("FIXED_AMOUNT not in", values, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountBetween(Integer value1, Integer value2) {
            addCriterion("FIXED_AMOUNT between", value1, value2, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("FIXED_AMOUNT not between", value1, value2, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andTransInstantTimeIsNull() {
            addCriterion("TRANS_INSTANT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTransInstantTimeIsNotNull() {
            addCriterion("TRANS_INSTANT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTransInstantTimeEqualTo(Long value) {
            addCriterion("TRANS_INSTANT_TIME =", value, "transInstantTime");
            return (Criteria) this;
        }

        public Criteria andTransInstantTimeNotEqualTo(Long value) {
            addCriterion("TRANS_INSTANT_TIME <>", value, "transInstantTime");
            return (Criteria) this;
        }

        public Criteria andTransInstantTimeGreaterThan(Long value) {
            addCriterion("TRANS_INSTANT_TIME >", value, "transInstantTime");
            return (Criteria) this;
        }

        public Criteria andTransInstantTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("TRANS_INSTANT_TIME >=", value, "transInstantTime");
            return (Criteria) this;
        }

        public Criteria andTransInstantTimeLessThan(Long value) {
            addCriterion("TRANS_INSTANT_TIME <", value, "transInstantTime");
            return (Criteria) this;
        }

        public Criteria andTransInstantTimeLessThanOrEqualTo(Long value) {
            addCriterion("TRANS_INSTANT_TIME <=", value, "transInstantTime");
            return (Criteria) this;
        }

        public Criteria andTransInstantTimeIn(List<Long> values) {
            addCriterion("TRANS_INSTANT_TIME in", values, "transInstantTime");
            return (Criteria) this;
        }

        public Criteria andTransInstantTimeNotIn(List<Long> values) {
            addCriterion("TRANS_INSTANT_TIME not in", values, "transInstantTime");
            return (Criteria) this;
        }

        public Criteria andTransInstantTimeBetween(Long value1, Long value2) {
            addCriterion("TRANS_INSTANT_TIME between", value1, value2, "transInstantTime");
            return (Criteria) this;
        }

        public Criteria andTransInstantTimeNotBetween(Long value1, Long value2) {
            addCriterion("TRANS_INSTANT_TIME not between", value1, value2, "transInstantTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeIsNull() {
            addCriterion("TRANS_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTransTimeIsNotNull() {
            addCriterion("TRANS_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTransTimeEqualTo(Long value) {
            addCriterion("TRANS_TIME =", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeNotEqualTo(Long value) {
            addCriterion("TRANS_TIME <>", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeGreaterThan(Long value) {
            addCriterion("TRANS_TIME >", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("TRANS_TIME >=", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeLessThan(Long value) {
            addCriterion("TRANS_TIME <", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeLessThanOrEqualTo(Long value) {
            addCriterion("TRANS_TIME <=", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeIn(List<Long> values) {
            addCriterion("TRANS_TIME in", values, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeNotIn(List<Long> values) {
            addCriterion("TRANS_TIME not in", values, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeBetween(Long value1, Long value2) {
            addCriterion("TRANS_TIME between", value1, value2, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeNotBetween(Long value1, Long value2) {
            addCriterion("TRANS_TIME not between", value1, value2, "transTime");
            return (Criteria) this;
        }

        public Criteria andTimelyAmountIsNull() {
            addCriterion("TIMELY_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andTimelyAmountIsNotNull() {
            addCriterion("TIMELY_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTimelyAmountEqualTo(Integer value) {
            addCriterion("TIMELY_AMOUNT =", value, "timelyAmount");
            return (Criteria) this;
        }

        public Criteria andTimelyAmountNotEqualTo(Integer value) {
            addCriterion("TIMELY_AMOUNT <>", value, "timelyAmount");
            return (Criteria) this;
        }

        public Criteria andTimelyAmountGreaterThan(Integer value) {
            addCriterion("TIMELY_AMOUNT >", value, "timelyAmount");
            return (Criteria) this;
        }

        public Criteria andTimelyAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("TIMELY_AMOUNT >=", value, "timelyAmount");
            return (Criteria) this;
        }

        public Criteria andTimelyAmountLessThan(Integer value) {
            addCriterion("TIMELY_AMOUNT <", value, "timelyAmount");
            return (Criteria) this;
        }

        public Criteria andTimelyAmountLessThanOrEqualTo(Integer value) {
            addCriterion("TIMELY_AMOUNT <=", value, "timelyAmount");
            return (Criteria) this;
        }

        public Criteria andTimelyAmountIn(List<Integer> values) {
            addCriterion("TIMELY_AMOUNT in", values, "timelyAmount");
            return (Criteria) this;
        }

        public Criteria andTimelyAmountNotIn(List<Integer> values) {
            addCriterion("TIMELY_AMOUNT not in", values, "timelyAmount");
            return (Criteria) this;
        }

        public Criteria andTimelyAmountBetween(Integer value1, Integer value2) {
            addCriterion("TIMELY_AMOUNT between", value1, value2, "timelyAmount");
            return (Criteria) this;
        }

        public Criteria andTimelyAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("TIMELY_AMOUNT not between", value1, value2, "timelyAmount");
            return (Criteria) this;
        }

        public Criteria andSatisfactionAmountIsNull() {
            addCriterion("SATISFACTION_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andSatisfactionAmountIsNotNull() {
            addCriterion("SATISFACTION_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andSatisfactionAmountEqualTo(Integer value) {
            addCriterion("SATISFACTION_AMOUNT =", value, "satisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andSatisfactionAmountNotEqualTo(Integer value) {
            addCriterion("SATISFACTION_AMOUNT <>", value, "satisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andSatisfactionAmountGreaterThan(Integer value) {
            addCriterion("SATISFACTION_AMOUNT >", value, "satisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andSatisfactionAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("SATISFACTION_AMOUNT >=", value, "satisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andSatisfactionAmountLessThan(Integer value) {
            addCriterion("SATISFACTION_AMOUNT <", value, "satisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andSatisfactionAmountLessThanOrEqualTo(Integer value) {
            addCriterion("SATISFACTION_AMOUNT <=", value, "satisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andSatisfactionAmountIn(List<Integer> values) {
            addCriterion("SATISFACTION_AMOUNT in", values, "satisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andSatisfactionAmountNotIn(List<Integer> values) {
            addCriterion("SATISFACTION_AMOUNT not in", values, "satisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andSatisfactionAmountBetween(Integer value1, Integer value2) {
            addCriterion("SATISFACTION_AMOUNT between", value1, value2, "satisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andSatisfactionAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("SATISFACTION_AMOUNT not between", value1, value2, "satisfactionAmount");
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

        public Criteria andWebDatasourceIsNull() {
            addCriterion("WEB_DATASOURCE is null");
            return (Criteria) this;
        }

        public Criteria andWebDatasourceIsNotNull() {
            addCriterion("WEB_DATASOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andWebDatasourceEqualTo(Integer value) {
            addCriterion("WEB_DATASOURCE =", value, "webDatasource");
            return (Criteria) this;
        }

        public Criteria andWebDatasourceNotEqualTo(Integer value) {
            addCriterion("WEB_DATASOURCE <>", value, "webDatasource");
            return (Criteria) this;
        }

        public Criteria andWebDatasourceGreaterThan(Integer value) {
            addCriterion("WEB_DATASOURCE >", value, "webDatasource");
            return (Criteria) this;
        }

        public Criteria andWebDatasourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("WEB_DATASOURCE >=", value, "webDatasource");
            return (Criteria) this;
        }

        public Criteria andWebDatasourceLessThan(Integer value) {
            addCriterion("WEB_DATASOURCE <", value, "webDatasource");
            return (Criteria) this;
        }

        public Criteria andWebDatasourceLessThanOrEqualTo(Integer value) {
            addCriterion("WEB_DATASOURCE <=", value, "webDatasource");
            return (Criteria) this;
        }

        public Criteria andWebDatasourceIn(List<Integer> values) {
            addCriterion("WEB_DATASOURCE in", values, "webDatasource");
            return (Criteria) this;
        }

        public Criteria andWebDatasourceNotIn(List<Integer> values) {
            addCriterion("WEB_DATASOURCE not in", values, "webDatasource");
            return (Criteria) this;
        }

        public Criteria andWebDatasourceBetween(Integer value1, Integer value2) {
            addCriterion("WEB_DATASOURCE between", value1, value2, "webDatasource");
            return (Criteria) this;
        }

        public Criteria andWebDatasourceNotBetween(Integer value1, Integer value2) {
            addCriterion("WEB_DATASOURCE not between", value1, value2, "webDatasource");
            return (Criteria) this;
        }

        public Criteria andWechatDatasourceIsNull() {
            addCriterion("WECHAT_DATASOURCE is null");
            return (Criteria) this;
        }

        public Criteria andWechatDatasourceIsNotNull() {
            addCriterion("WECHAT_DATASOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andWechatDatasourceEqualTo(Integer value) {
            addCriterion("WECHAT_DATASOURCE =", value, "wechatDatasource");
            return (Criteria) this;
        }

        public Criteria andWechatDatasourceNotEqualTo(Integer value) {
            addCriterion("WECHAT_DATASOURCE <>", value, "wechatDatasource");
            return (Criteria) this;
        }

        public Criteria andWechatDatasourceGreaterThan(Integer value) {
            addCriterion("WECHAT_DATASOURCE >", value, "wechatDatasource");
            return (Criteria) this;
        }

        public Criteria andWechatDatasourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("WECHAT_DATASOURCE >=", value, "wechatDatasource");
            return (Criteria) this;
        }

        public Criteria andWechatDatasourceLessThan(Integer value) {
            addCriterion("WECHAT_DATASOURCE <", value, "wechatDatasource");
            return (Criteria) this;
        }

        public Criteria andWechatDatasourceLessThanOrEqualTo(Integer value) {
            addCriterion("WECHAT_DATASOURCE <=", value, "wechatDatasource");
            return (Criteria) this;
        }

        public Criteria andWechatDatasourceIn(List<Integer> values) {
            addCriterion("WECHAT_DATASOURCE in", values, "wechatDatasource");
            return (Criteria) this;
        }

        public Criteria andWechatDatasourceNotIn(List<Integer> values) {
            addCriterion("WECHAT_DATASOURCE not in", values, "wechatDatasource");
            return (Criteria) this;
        }

        public Criteria andWechatDatasourceBetween(Integer value1, Integer value2) {
            addCriterion("WECHAT_DATASOURCE between", value1, value2, "wechatDatasource");
            return (Criteria) this;
        }

        public Criteria andWechatDatasourceNotBetween(Integer value1, Integer value2) {
            addCriterion("WECHAT_DATASOURCE not between", value1, value2, "wechatDatasource");
            return (Criteria) this;
        }

        public Criteria andPadDatasourceIsNull() {
            addCriterion("PAD_DATASOURCE is null");
            return (Criteria) this;
        }

        public Criteria andPadDatasourceIsNotNull() {
            addCriterion("PAD_DATASOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andPadDatasourceEqualTo(Integer value) {
            addCriterion("PAD_DATASOURCE =", value, "padDatasource");
            return (Criteria) this;
        }

        public Criteria andPadDatasourceNotEqualTo(Integer value) {
            addCriterion("PAD_DATASOURCE <>", value, "padDatasource");
            return (Criteria) this;
        }

        public Criteria andPadDatasourceGreaterThan(Integer value) {
            addCriterion("PAD_DATASOURCE >", value, "padDatasource");
            return (Criteria) this;
        }

        public Criteria andPadDatasourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("PAD_DATASOURCE >=", value, "padDatasource");
            return (Criteria) this;
        }

        public Criteria andPadDatasourceLessThan(Integer value) {
            addCriterion("PAD_DATASOURCE <", value, "padDatasource");
            return (Criteria) this;
        }

        public Criteria andPadDatasourceLessThanOrEqualTo(Integer value) {
            addCriterion("PAD_DATASOURCE <=", value, "padDatasource");
            return (Criteria) this;
        }

        public Criteria andPadDatasourceIn(List<Integer> values) {
            addCriterion("PAD_DATASOURCE in", values, "padDatasource");
            return (Criteria) this;
        }

        public Criteria andPadDatasourceNotIn(List<Integer> values) {
            addCriterion("PAD_DATASOURCE not in", values, "padDatasource");
            return (Criteria) this;
        }

        public Criteria andPadDatasourceBetween(Integer value1, Integer value2) {
            addCriterion("PAD_DATASOURCE between", value1, value2, "padDatasource");
            return (Criteria) this;
        }

        public Criteria andPadDatasourceNotBetween(Integer value1, Integer value2) {
            addCriterion("PAD_DATASOURCE not between", value1, value2, "padDatasource");
            return (Criteria) this;
        }

        public Criteria andAppDatasourceIsNull() {
            addCriterion("APP_DATASOURCE is null");
            return (Criteria) this;
        }

        public Criteria andAppDatasourceIsNotNull() {
            addCriterion("APP_DATASOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andAppDatasourceEqualTo(Integer value) {
            addCriterion("APP_DATASOURCE =", value, "appDatasource");
            return (Criteria) this;
        }

        public Criteria andAppDatasourceNotEqualTo(Integer value) {
            addCriterion("APP_DATASOURCE <>", value, "appDatasource");
            return (Criteria) this;
        }

        public Criteria andAppDatasourceGreaterThan(Integer value) {
            addCriterion("APP_DATASOURCE >", value, "appDatasource");
            return (Criteria) this;
        }

        public Criteria andAppDatasourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("APP_DATASOURCE >=", value, "appDatasource");
            return (Criteria) this;
        }

        public Criteria andAppDatasourceLessThan(Integer value) {
            addCriterion("APP_DATASOURCE <", value, "appDatasource");
            return (Criteria) this;
        }

        public Criteria andAppDatasourceLessThanOrEqualTo(Integer value) {
            addCriterion("APP_DATASOURCE <=", value, "appDatasource");
            return (Criteria) this;
        }

        public Criteria andAppDatasourceIn(List<Integer> values) {
            addCriterion("APP_DATASOURCE in", values, "appDatasource");
            return (Criteria) this;
        }

        public Criteria andAppDatasourceNotIn(List<Integer> values) {
            addCriterion("APP_DATASOURCE not in", values, "appDatasource");
            return (Criteria) this;
        }

        public Criteria andAppDatasourceBetween(Integer value1, Integer value2) {
            addCriterion("APP_DATASOURCE between", value1, value2, "appDatasource");
            return (Criteria) this;
        }

        public Criteria andAppDatasourceNotBetween(Integer value1, Integer value2) {
            addCriterion("APP_DATASOURCE not between", value1, value2, "appDatasource");
            return (Criteria) this;
        }

        public Criteria andIstaskdayIsNull() {
            addCriterion("isTaskDay is null");
            return (Criteria) this;
        }

        public Criteria andIstaskdayIsNotNull() {
            addCriterion("isTaskDay is not null");
            return (Criteria) this;
        }

        public Criteria andIstaskdayEqualTo(String value) {
            addCriterion("isTaskDay =", value, "istaskday");
            return (Criteria) this;
        }

        public Criteria andIstaskdayNotEqualTo(String value) {
            addCriterion("isTaskDay <>", value, "istaskday");
            return (Criteria) this;
        }

        public Criteria andIstaskdayGreaterThan(String value) {
            addCriterion("isTaskDay >", value, "istaskday");
            return (Criteria) this;
        }

        public Criteria andIstaskdayGreaterThanOrEqualTo(String value) {
            addCriterion("isTaskDay >=", value, "istaskday");
            return (Criteria) this;
        }

        public Criteria andIstaskdayLessThan(String value) {
            addCriterion("isTaskDay <", value, "istaskday");
            return (Criteria) this;
        }

        public Criteria andIstaskdayLessThanOrEqualTo(String value) {
            addCriterion("isTaskDay <=", value, "istaskday");
            return (Criteria) this;
        }

        public Criteria andIstaskdayLike(String value) {
            addCriterion("isTaskDay like", value, "istaskday");
            return (Criteria) this;
        }

        public Criteria andIstaskdayNotLike(String value) {
            addCriterion("isTaskDay not like", value, "istaskday");
            return (Criteria) this;
        }

        public Criteria andIstaskdayIn(List<String> values) {
            addCriterion("isTaskDay in", values, "istaskday");
            return (Criteria) this;
        }

        public Criteria andIstaskdayNotIn(List<String> values) {
            addCriterion("isTaskDay not in", values, "istaskday");
            return (Criteria) this;
        }

        public Criteria andIstaskdayBetween(String value1, String value2) {
            addCriterion("isTaskDay between", value1, value2, "istaskday");
            return (Criteria) this;
        }

        public Criteria andIstaskdayNotBetween(String value1, String value2) {
            addCriterion("isTaskDay not between", value1, value2, "istaskday");
            return (Criteria) this;
        }

        public Criteria andIstaskdayLikeInsensitive(String value) {
            addCriterion("upper(isTaskDay) like", value.toUpperCase(), "istaskday");
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