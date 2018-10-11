package com.segi.uhomecp.medicaltrans.report.monthrank.dept.model;

import java.util.ArrayList;
import java.util.List;

import com.segi.uhomecp.common.model.AbstractCriteria;

public class DeptVolumeMonthCriteria extends AbstractCriteria{
    /**
	 * 2018年8月2日下午2:55:20
	 */
	private static final long serialVersionUID = 1L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeptVolumeMonthCriteria() {
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

        public Criteria andSidIsNull() {
            addCriterion("SID is null");
            return (Criteria) this;
        }

        public Criteria andSidIsNotNull() {
            addCriterion("SID is not null");
            return (Criteria) this;
        }

        public Criteria andSidEqualTo(Integer value) {
            addCriterion("SID =", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotEqualTo(Integer value) {
            addCriterion("SID <>", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThan(Integer value) {
            addCriterion("SID >", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThanOrEqualTo(Integer value) {
            addCriterion("SID >=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThan(Integer value) {
            addCriterion("SID <", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThanOrEqualTo(Integer value) {
            addCriterion("SID <=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidIn(List<Integer> values) {
            addCriterion("SID in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotIn(List<Integer> values) {
            addCriterion("SID not in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidBetween(Integer value1, Integer value2) {
            addCriterion("SID between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotBetween(Integer value1, Integer value2) {
            addCriterion("SID not between", value1, value2, "sid");
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

        public Criteria andSpecialAmountIsNull() {
            addCriterion("SPECIAL_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andSpecialAmountIsNotNull() {
            addCriterion("SPECIAL_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialAmountEqualTo(Integer value) {
            addCriterion("SPECIAL_AMOUNT =", value, "specialAmount");
            return (Criteria) this;
        }

        public Criteria andSpecialAmountNotEqualTo(Integer value) {
            addCriterion("SPECIAL_AMOUNT <>", value, "specialAmount");
            return (Criteria) this;
        }

        public Criteria andSpecialAmountGreaterThan(Integer value) {
            addCriterion("SPECIAL_AMOUNT >", value, "specialAmount");
            return (Criteria) this;
        }

        public Criteria andSpecialAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("SPECIAL_AMOUNT >=", value, "specialAmount");
            return (Criteria) this;
        }

        public Criteria andSpecialAmountLessThan(Integer value) {
            addCriterion("SPECIAL_AMOUNT <", value, "specialAmount");
            return (Criteria) this;
        }

        public Criteria andSpecialAmountLessThanOrEqualTo(Integer value) {
            addCriterion("SPECIAL_AMOUNT <=", value, "specialAmount");
            return (Criteria) this;
        }

        public Criteria andSpecialAmountIn(List<Integer> values) {
            addCriterion("SPECIAL_AMOUNT in", values, "specialAmount");
            return (Criteria) this;
        }

        public Criteria andSpecialAmountNotIn(List<Integer> values) {
            addCriterion("SPECIAL_AMOUNT not in", values, "specialAmount");
            return (Criteria) this;
        }

        public Criteria andSpecialAmountBetween(Integer value1, Integer value2) {
            addCriterion("SPECIAL_AMOUNT between", value1, value2, "specialAmount");
            return (Criteria) this;
        }

        public Criteria andSpecialAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("SPECIAL_AMOUNT not between", value1, value2, "specialAmount");
            return (Criteria) this;
        }

        public Criteria andUrgentAmountIsNull() {
            addCriterion("URGENT_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andUrgentAmountIsNotNull() {
            addCriterion("URGENT_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andUrgentAmountEqualTo(Integer value) {
            addCriterion("URGENT_AMOUNT =", value, "urgentAmount");
            return (Criteria) this;
        }

        public Criteria andUrgentAmountNotEqualTo(Integer value) {
            addCriterion("URGENT_AMOUNT <>", value, "urgentAmount");
            return (Criteria) this;
        }

        public Criteria andUrgentAmountGreaterThan(Integer value) {
            addCriterion("URGENT_AMOUNT >", value, "urgentAmount");
            return (Criteria) this;
        }

        public Criteria andUrgentAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("URGENT_AMOUNT >=", value, "urgentAmount");
            return (Criteria) this;
        }

        public Criteria andUrgentAmountLessThan(Integer value) {
            addCriterion("URGENT_AMOUNT <", value, "urgentAmount");
            return (Criteria) this;
        }

        public Criteria andUrgentAmountLessThanOrEqualTo(Integer value) {
            addCriterion("URGENT_AMOUNT <=", value, "urgentAmount");
            return (Criteria) this;
        }

        public Criteria andUrgentAmountIn(List<Integer> values) {
            addCriterion("URGENT_AMOUNT in", values, "urgentAmount");
            return (Criteria) this;
        }

        public Criteria andUrgentAmountNotIn(List<Integer> values) {
            addCriterion("URGENT_AMOUNT not in", values, "urgentAmount");
            return (Criteria) this;
        }

        public Criteria andUrgentAmountBetween(Integer value1, Integer value2) {
            addCriterion("URGENT_AMOUNT between", value1, value2, "urgentAmount");
            return (Criteria) this;
        }

        public Criteria andUrgentAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("URGENT_AMOUNT not between", value1, value2, "urgentAmount");
            return (Criteria) this;
        }

        public Criteria andCommonAmountIsNull() {
            addCriterion("COMMON_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andCommonAmountIsNotNull() {
            addCriterion("COMMON_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCommonAmountEqualTo(Integer value) {
            addCriterion("COMMON_AMOUNT =", value, "commonAmount");
            return (Criteria) this;
        }

        public Criteria andCommonAmountNotEqualTo(Integer value) {
            addCriterion("COMMON_AMOUNT <>", value, "commonAmount");
            return (Criteria) this;
        }

        public Criteria andCommonAmountGreaterThan(Integer value) {
            addCriterion("COMMON_AMOUNT >", value, "commonAmount");
            return (Criteria) this;
        }

        public Criteria andCommonAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("COMMON_AMOUNT >=", value, "commonAmount");
            return (Criteria) this;
        }

        public Criteria andCommonAmountLessThan(Integer value) {
            addCriterion("COMMON_AMOUNT <", value, "commonAmount");
            return (Criteria) this;
        }

        public Criteria andCommonAmountLessThanOrEqualTo(Integer value) {
            addCriterion("COMMON_AMOUNT <=", value, "commonAmount");
            return (Criteria) this;
        }

        public Criteria andCommonAmountIn(List<Integer> values) {
            addCriterion("COMMON_AMOUNT in", values, "commonAmount");
            return (Criteria) this;
        }

        public Criteria andCommonAmountNotIn(List<Integer> values) {
            addCriterion("COMMON_AMOUNT not in", values, "commonAmount");
            return (Criteria) this;
        }

        public Criteria andCommonAmountBetween(Integer value1, Integer value2) {
            addCriterion("COMMON_AMOUNT between", value1, value2, "commonAmount");
            return (Criteria) this;
        }

        public Criteria andCommonAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("COMMON_AMOUNT not between", value1, value2, "commonAmount");
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