package com.segi.uhomecp.medicaltrans.report.monthrank.personal.model;

import java.util.ArrayList;
import java.util.List;

import com.segi.uhomecp.common.model.AbstractCriteria;

public class PersonalVolumeMonthCriteria extends AbstractCriteria {
	
	private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PersonalVolumeMonthCriteria() {
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

        public Criteria andSergroupIdIsNull() {
            addCriterion("SERGROUP_ID is null");
            return (Criteria) this;
        }

        public Criteria andSergroupIdIsNotNull() {
            addCriterion("SERGROUP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSergroupIdEqualTo(Integer value) {
            addCriterion("SERGROUP_ID =", value, "sergroupId");
            return (Criteria) this;
        }

        public Criteria andSergroupIdNotEqualTo(Integer value) {
            addCriterion("SERGROUP_ID <>", value, "sergroupId");
            return (Criteria) this;
        }

        public Criteria andSergroupIdGreaterThan(Integer value) {
            addCriterion("SERGROUP_ID >", value, "sergroupId");
            return (Criteria) this;
        }

        public Criteria andSergroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SERGROUP_ID >=", value, "sergroupId");
            return (Criteria) this;
        }

        public Criteria andSergroupIdLessThan(Integer value) {
            addCriterion("SERGROUP_ID <", value, "sergroupId");
            return (Criteria) this;
        }

        public Criteria andSergroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("SERGROUP_ID <=", value, "sergroupId");
            return (Criteria) this;
        }

        public Criteria andSergroupIdIn(List<Integer> values) {
            addCriterion("SERGROUP_ID in", values, "sergroupId");
            return (Criteria) this;
        }

        public Criteria andSergroupIdNotIn(List<Integer> values) {
            addCriterion("SERGROUP_ID not in", values, "sergroupId");
            return (Criteria) this;
        }

        public Criteria andSergroupIdBetween(Integer value1, Integer value2) {
            addCriterion("SERGROUP_ID between", value1, value2, "sergroupId");
            return (Criteria) this;
        }

        public Criteria andSergroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SERGROUP_ID not between", value1, value2, "sergroupId");
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

        public Criteria andDispatchSatisfactionAmountIsNull() {
            addCriterion("DISPATCH_SATISFACTION_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andDispatchSatisfactionAmountIsNotNull() {
            addCriterion("DISPATCH_SATISFACTION_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDispatchSatisfactionAmountEqualTo(Integer value) {
            addCriterion("DISPATCH_SATISFACTION_AMOUNT =", value, "dispatchSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchSatisfactionAmountNotEqualTo(Integer value) {
            addCriterion("DISPATCH_SATISFACTION_AMOUNT <>", value, "dispatchSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchSatisfactionAmountGreaterThan(Integer value) {
            addCriterion("DISPATCH_SATISFACTION_AMOUNT >", value, "dispatchSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchSatisfactionAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("DISPATCH_SATISFACTION_AMOUNT >=", value, "dispatchSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchSatisfactionAmountLessThan(Integer value) {
            addCriterion("DISPATCH_SATISFACTION_AMOUNT <", value, "dispatchSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchSatisfactionAmountLessThanOrEqualTo(Integer value) {
            addCriterion("DISPATCH_SATISFACTION_AMOUNT <=", value, "dispatchSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchSatisfactionAmountIn(List<Integer> values) {
            addCriterion("DISPATCH_SATISFACTION_AMOUNT in", values, "dispatchSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchSatisfactionAmountNotIn(List<Integer> values) {
            addCriterion("DISPATCH_SATISFACTION_AMOUNT not in", values, "dispatchSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchSatisfactionAmountBetween(Integer value1, Integer value2) {
            addCriterion("DISPATCH_SATISFACTION_AMOUNT between", value1, value2, "dispatchSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchSatisfactionAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("DISPATCH_SATISFACTION_AMOUNT not between", value1, value2, "dispatchSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchTimelyAmountIsNull() {
            addCriterion("DISPATCH_TIMELY_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andDispatchTimelyAmountIsNotNull() {
            addCriterion("DISPATCH_TIMELY_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDispatchTimelyAmountEqualTo(Integer value) {
            addCriterion("DISPATCH_TIMELY_AMOUNT =", value, "dispatchTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchTimelyAmountNotEqualTo(Integer value) {
            addCriterion("DISPATCH_TIMELY_AMOUNT <>", value, "dispatchTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchTimelyAmountGreaterThan(Integer value) {
            addCriterion("DISPATCH_TIMELY_AMOUNT >", value, "dispatchTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchTimelyAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("DISPATCH_TIMELY_AMOUNT >=", value, "dispatchTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchTimelyAmountLessThan(Integer value) {
            addCriterion("DISPATCH_TIMELY_AMOUNT <", value, "dispatchTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchTimelyAmountLessThanOrEqualTo(Integer value) {
            addCriterion("DISPATCH_TIMELY_AMOUNT <=", value, "dispatchTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchTimelyAmountIn(List<Integer> values) {
            addCriterion("DISPATCH_TIMELY_AMOUNT in", values, "dispatchTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchTimelyAmountNotIn(List<Integer> values) {
            addCriterion("DISPATCH_TIMELY_AMOUNT not in", values, "dispatchTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchTimelyAmountBetween(Integer value1, Integer value2) {
            addCriterion("DISPATCH_TIMELY_AMOUNT between", value1, value2, "dispatchTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andDispatchTimelyAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("DISPATCH_TIMELY_AMOUNT not between", value1, value2, "dispatchTimelyAmount");
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

        public Criteria andAutonomousSatisfactionAmountIsNull() {
            addCriterion("AUTONOMOUS_SATISFACTION_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andAutonomousSatisfactionAmountIsNotNull() {
            addCriterion("AUTONOMOUS_SATISFACTION_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAutonomousSatisfactionAmountEqualTo(Integer value) {
            addCriterion("AUTONOMOUS_SATISFACTION_AMOUNT =", value, "autonomousSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousSatisfactionAmountNotEqualTo(Integer value) {
            addCriterion("AUTONOMOUS_SATISFACTION_AMOUNT <>", value, "autonomousSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousSatisfactionAmountGreaterThan(Integer value) {
            addCriterion("AUTONOMOUS_SATISFACTION_AMOUNT >", value, "autonomousSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousSatisfactionAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("AUTONOMOUS_SATISFACTION_AMOUNT >=", value, "autonomousSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousSatisfactionAmountLessThan(Integer value) {
            addCriterion("AUTONOMOUS_SATISFACTION_AMOUNT <", value, "autonomousSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousSatisfactionAmountLessThanOrEqualTo(Integer value) {
            addCriterion("AUTONOMOUS_SATISFACTION_AMOUNT <=", value, "autonomousSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousSatisfactionAmountIn(List<Integer> values) {
            addCriterion("AUTONOMOUS_SATISFACTION_AMOUNT in", values, "autonomousSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousSatisfactionAmountNotIn(List<Integer> values) {
            addCriterion("AUTONOMOUS_SATISFACTION_AMOUNT not in", values, "autonomousSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousSatisfactionAmountBetween(Integer value1, Integer value2) {
            addCriterion("AUTONOMOUS_SATISFACTION_AMOUNT between", value1, value2, "autonomousSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousSatisfactionAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("AUTONOMOUS_SATISFACTION_AMOUNT not between", value1, value2, "autonomousSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousTimelyAmountIsNull() {
            addCriterion("AUTONOMOUS_TIMELY_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andAutonomousTimelyAmountIsNotNull() {
            addCriterion("AUTONOMOUS_TIMELY_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAutonomousTimelyAmountEqualTo(Integer value) {
            addCriterion("AUTONOMOUS_TIMELY_AMOUNT =", value, "autonomousTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousTimelyAmountNotEqualTo(Integer value) {
            addCriterion("AUTONOMOUS_TIMELY_AMOUNT <>", value, "autonomousTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousTimelyAmountGreaterThan(Integer value) {
            addCriterion("AUTONOMOUS_TIMELY_AMOUNT >", value, "autonomousTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousTimelyAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("AUTONOMOUS_TIMELY_AMOUNT >=", value, "autonomousTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousTimelyAmountLessThan(Integer value) {
            addCriterion("AUTONOMOUS_TIMELY_AMOUNT <", value, "autonomousTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousTimelyAmountLessThanOrEqualTo(Integer value) {
            addCriterion("AUTONOMOUS_TIMELY_AMOUNT <=", value, "autonomousTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousTimelyAmountIn(List<Integer> values) {
            addCriterion("AUTONOMOUS_TIMELY_AMOUNT in", values, "autonomousTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousTimelyAmountNotIn(List<Integer> values) {
            addCriterion("AUTONOMOUS_TIMELY_AMOUNT not in", values, "autonomousTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousTimelyAmountBetween(Integer value1, Integer value2) {
            addCriterion("AUTONOMOUS_TIMELY_AMOUNT between", value1, value2, "autonomousTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andAutonomousTimelyAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("AUTONOMOUS_TIMELY_AMOUNT not between", value1, value2, "autonomousTimelyAmount");
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

        public Criteria andFixedSatisfactionAmountIsNull() {
            addCriterion("FIXED_SATISFACTION_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andFixedSatisfactionAmountIsNotNull() {
            addCriterion("FIXED_SATISFACTION_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andFixedSatisfactionAmountEqualTo(Integer value) {
            addCriterion("FIXED_SATISFACTION_AMOUNT =", value, "fixedSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andFixedSatisfactionAmountNotEqualTo(Integer value) {
            addCriterion("FIXED_SATISFACTION_AMOUNT <>", value, "fixedSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andFixedSatisfactionAmountGreaterThan(Integer value) {
            addCriterion("FIXED_SATISFACTION_AMOUNT >", value, "fixedSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andFixedSatisfactionAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("FIXED_SATISFACTION_AMOUNT >=", value, "fixedSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andFixedSatisfactionAmountLessThan(Integer value) {
            addCriterion("FIXED_SATISFACTION_AMOUNT <", value, "fixedSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andFixedSatisfactionAmountLessThanOrEqualTo(Integer value) {
            addCriterion("FIXED_SATISFACTION_AMOUNT <=", value, "fixedSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andFixedSatisfactionAmountIn(List<Integer> values) {
            addCriterion("FIXED_SATISFACTION_AMOUNT in", values, "fixedSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andFixedSatisfactionAmountNotIn(List<Integer> values) {
            addCriterion("FIXED_SATISFACTION_AMOUNT not in", values, "fixedSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andFixedSatisfactionAmountBetween(Integer value1, Integer value2) {
            addCriterion("FIXED_SATISFACTION_AMOUNT between", value1, value2, "fixedSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andFixedSatisfactionAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("FIXED_SATISFACTION_AMOUNT not between", value1, value2, "fixedSatisfactionAmount");
            return (Criteria) this;
        }

        public Criteria andFixedTimelyAmountIsNull() {
            addCriterion("FIXED_TIMELY_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andFixedTimelyAmountIsNotNull() {
            addCriterion("FIXED_TIMELY_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andFixedTimelyAmountEqualTo(Integer value) {
            addCriterion("FIXED_TIMELY_AMOUNT =", value, "fixedTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andFixedTimelyAmountNotEqualTo(Integer value) {
            addCriterion("FIXED_TIMELY_AMOUNT <>", value, "fixedTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andFixedTimelyAmountGreaterThan(Integer value) {
            addCriterion("FIXED_TIMELY_AMOUNT >", value, "fixedTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andFixedTimelyAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("FIXED_TIMELY_AMOUNT >=", value, "fixedTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andFixedTimelyAmountLessThan(Integer value) {
            addCriterion("FIXED_TIMELY_AMOUNT <", value, "fixedTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andFixedTimelyAmountLessThanOrEqualTo(Integer value) {
            addCriterion("FIXED_TIMELY_AMOUNT <=", value, "fixedTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andFixedTimelyAmountIn(List<Integer> values) {
            addCriterion("FIXED_TIMELY_AMOUNT in", values, "fixedTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andFixedTimelyAmountNotIn(List<Integer> values) {
            addCriterion("FIXED_TIMELY_AMOUNT not in", values, "fixedTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andFixedTimelyAmountBetween(Integer value1, Integer value2) {
            addCriterion("FIXED_TIMELY_AMOUNT between", value1, value2, "fixedTimelyAmount");
            return (Criteria) this;
        }

        public Criteria andFixedTimelyAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("FIXED_TIMELY_AMOUNT not between", value1, value2, "fixedTimelyAmount");
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