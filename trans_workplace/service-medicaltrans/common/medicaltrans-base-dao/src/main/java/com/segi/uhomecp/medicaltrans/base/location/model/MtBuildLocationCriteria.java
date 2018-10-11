package com.segi.uhomecp.medicaltrans.base.location.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.segi.uhomecp.common.model.AbstractCriteria;

public class MtBuildLocationCriteria extends AbstractCriteria{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7592792191986791401L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MtBuildLocationCriteria() {
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

        public Criteria andFloorNumIsNull() {
            addCriterion("FLOOR_NUM is null");
            return (Criteria) this;
        }

        public Criteria andFloorNumIsNotNull() {
            addCriterion("FLOOR_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andFloorNumEqualTo(Short value) {
            addCriterion("FLOOR_NUM =", value, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumNotEqualTo(Short value) {
            addCriterion("FLOOR_NUM <>", value, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumGreaterThan(Short value) {
            addCriterion("FLOOR_NUM >", value, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumGreaterThanOrEqualTo(Short value) {
            addCriterion("FLOOR_NUM >=", value, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumLessThan(Short value) {
            addCriterion("FLOOR_NUM <", value, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumLessThanOrEqualTo(Short value) {
            addCriterion("FLOOR_NUM <=", value, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumIn(List<Short> values) {
            addCriterion("FLOOR_NUM in", values, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumNotIn(List<Short> values) {
            addCriterion("FLOOR_NUM not in", values, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumBetween(Short value1, Short value2) {
            addCriterion("FLOOR_NUM between", value1, value2, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumNotBetween(Short value1, Short value2) {
            addCriterion("FLOOR_NUM not between", value1, value2, "floorNum");
            return (Criteria) this;
        }

        public Criteria andLocationNameIsNull() {
            addCriterion("LOCATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLocationNameIsNotNull() {
            addCriterion("LOCATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLocationNameEqualTo(String value) {
            addCriterion("LOCATION_NAME =", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameNotEqualTo(String value) {
            addCriterion("LOCATION_NAME <>", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameGreaterThan(String value) {
            addCriterion("LOCATION_NAME >", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameGreaterThanOrEqualTo(String value) {
            addCriterion("LOCATION_NAME >=", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameLessThan(String value) {
            addCriterion("LOCATION_NAME <", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameLessThanOrEqualTo(String value) {
            addCriterion("LOCATION_NAME <=", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameLike(String value) {
            addCriterion("LOCATION_NAME like", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameNotLike(String value) {
            addCriterion("LOCATION_NAME not like", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameIn(List<String> values) {
            addCriterion("LOCATION_NAME in", values, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameNotIn(List<String> values) {
            addCriterion("LOCATION_NAME not in", values, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameBetween(String value1, String value2) {
            addCriterion("LOCATION_NAME between", value1, value2, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameNotBetween(String value1, String value2) {
            addCriterion("LOCATION_NAME not between", value1, value2, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocateTypeIsNull() {
            addCriterion("LOCATE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andLocateTypeIsNotNull() {
            addCriterion("LOCATE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andLocateTypeEqualTo(String value) {
            addCriterion("LOCATE_TYPE =", value, "locateType");
            return (Criteria) this;
        }

        public Criteria andLocateTypeNotEqualTo(String value) {
            addCriterion("LOCATE_TYPE <>", value, "locateType");
            return (Criteria) this;
        }

        public Criteria andLocateTypeGreaterThan(String value) {
            addCriterion("LOCATE_TYPE >", value, "locateType");
            return (Criteria) this;
        }

        public Criteria andLocateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("LOCATE_TYPE >=", value, "locateType");
            return (Criteria) this;
        }

        public Criteria andLocateTypeLessThan(String value) {
            addCriterion("LOCATE_TYPE <", value, "locateType");
            return (Criteria) this;
        }

        public Criteria andLocateTypeLessThanOrEqualTo(String value) {
            addCriterion("LOCATE_TYPE <=", value, "locateType");
            return (Criteria) this;
        }

        public Criteria andLocateTypeLike(String value) {
            addCriterion("LOCATE_TYPE like", value, "locateType");
            return (Criteria) this;
        }

        public Criteria andLocateTypeNotLike(String value) {
            addCriterion("LOCATE_TYPE not like", value, "locateType");
            return (Criteria) this;
        }

        public Criteria andLocateTypeIn(List<String> values) {
            addCriterion("LOCATE_TYPE in", values, "locateType");
            return (Criteria) this;
        }

        public Criteria andLocateTypeNotIn(List<String> values) {
            addCriterion("LOCATE_TYPE not in", values, "locateType");
            return (Criteria) this;
        }

        public Criteria andLocateTypeBetween(String value1, String value2) {
            addCriterion("LOCATE_TYPE between", value1, value2, "locateType");
            return (Criteria) this;
        }

        public Criteria andLocateTypeNotBetween(String value1, String value2) {
            addCriterion("LOCATE_TYPE not between", value1, value2, "locateType");
            return (Criteria) this;
        }

        public Criteria andQrcodeIsNull() {
            addCriterion("QRCODE is null");
            return (Criteria) this;
        }

        public Criteria andQrcodeIsNotNull() {
            addCriterion("QRCODE is not null");
            return (Criteria) this;
        }

        public Criteria andQrcodeEqualTo(String value) {
            addCriterion("QRCODE =", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeNotEqualTo(String value) {
            addCriterion("QRCODE <>", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeGreaterThan(String value) {
            addCriterion("QRCODE >", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeGreaterThanOrEqualTo(String value) {
            addCriterion("QRCODE >=", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeLessThan(String value) {
            addCriterion("QRCODE <", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeLessThanOrEqualTo(String value) {
            addCriterion("QRCODE <=", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeLike(String value) {
            addCriterion("QRCODE like", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeNotLike(String value) {
            addCriterion("QRCODE not like", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeIn(List<String> values) {
            addCriterion("QRCODE in", values, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeNotIn(List<String> values) {
            addCriterion("QRCODE not in", values, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeBetween(String value1, String value2) {
            addCriterion("QRCODE between", value1, value2, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeNotBetween(String value1, String value2) {
            addCriterion("QRCODE not between", value1, value2, "qrcode");
            return (Criteria) this;
        }

        public Criteria andMacIsNull() {
            addCriterion("MAC is null");
            return (Criteria) this;
        }

        public Criteria andMacIsNotNull() {
            addCriterion("MAC is not null");
            return (Criteria) this;
        }

        public Criteria andMacEqualTo(String value) {
            addCriterion("MAC =", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotEqualTo(String value) {
            addCriterion("MAC <>", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacGreaterThan(String value) {
            addCriterion("MAC >", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacGreaterThanOrEqualTo(String value) {
            addCriterion("MAC >=", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacLessThan(String value) {
            addCriterion("MAC <", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacLessThanOrEqualTo(String value) {
            addCriterion("MAC <=", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacLike(String value) {
            addCriterion("MAC like", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotLike(String value) {
            addCriterion("MAC not like", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacIn(List<String> values) {
            addCriterion("MAC in", values, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotIn(List<String> values) {
            addCriterion("MAC not in", values, "mac");
            return (Criteria) this;
        }

        public Criteria andMacBetween(String value1, String value2) {
            addCriterion("MAC between", value1, value2, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotBetween(String value1, String value2) {
            addCriterion("MAC not between", value1, value2, "mac");
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

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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

        public Criteria andCreateuserIdIsNull() {
            addCriterion("CREATEUSER_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreateuserIdIsNotNull() {
            addCriterion("CREATEUSER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreateuserIdEqualTo(Integer value) {
            addCriterion("CREATEUSER_ID =", value, "createuserId");
            return (Criteria) this;
        }

        public Criteria andCreateuserIdNotEqualTo(Integer value) {
            addCriterion("CREATEUSER_ID <>", value, "createuserId");
            return (Criteria) this;
        }

        public Criteria andCreateuserIdGreaterThan(Integer value) {
            addCriterion("CREATEUSER_ID >", value, "createuserId");
            return (Criteria) this;
        }

        public Criteria andCreateuserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CREATEUSER_ID >=", value, "createuserId");
            return (Criteria) this;
        }

        public Criteria andCreateuserIdLessThan(Integer value) {
            addCriterion("CREATEUSER_ID <", value, "createuserId");
            return (Criteria) this;
        }

        public Criteria andCreateuserIdLessThanOrEqualTo(Integer value) {
            addCriterion("CREATEUSER_ID <=", value, "createuserId");
            return (Criteria) this;
        }

        public Criteria andCreateuserIdIn(List<Integer> values) {
            addCriterion("CREATEUSER_ID in", values, "createuserId");
            return (Criteria) this;
        }

        public Criteria andCreateuserIdNotIn(List<Integer> values) {
            addCriterion("CREATEUSER_ID not in", values, "createuserId");
            return (Criteria) this;
        }

        public Criteria andCreateuserIdBetween(Integer value1, Integer value2) {
            addCriterion("CREATEUSER_ID between", value1, value2, "createuserId");
            return (Criteria) this;
        }

        public Criteria andCreateuserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CREATEUSER_ID not between", value1, value2, "createuserId");
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

        public Criteria andFloorPositXIsNull() {
            addCriterion("FLOOR_POSIT_X is null");
            return (Criteria) this;
        }

        public Criteria andFloorPositXIsNotNull() {
            addCriterion("FLOOR_POSIT_X is not null");
            return (Criteria) this;
        }

        public Criteria andFloorPositXEqualTo(Float value) {
            addCriterion("FLOOR_POSIT_X =", value, "floorPositX");
            return (Criteria) this;
        }

        public Criteria andFloorPositXNotEqualTo(Float value) {
            addCriterion("FLOOR_POSIT_X <>", value, "floorPositX");
            return (Criteria) this;
        }

        public Criteria andFloorPositXGreaterThan(Float value) {
            addCriterion("FLOOR_POSIT_X >", value, "floorPositX");
            return (Criteria) this;
        }

        public Criteria andFloorPositXGreaterThanOrEqualTo(Float value) {
            addCriterion("FLOOR_POSIT_X >=", value, "floorPositX");
            return (Criteria) this;
        }

        public Criteria andFloorPositXLessThan(Float value) {
            addCriterion("FLOOR_POSIT_X <", value, "floorPositX");
            return (Criteria) this;
        }

        public Criteria andFloorPositXLessThanOrEqualTo(Float value) {
            addCriterion("FLOOR_POSIT_X <=", value, "floorPositX");
            return (Criteria) this;
        }

        public Criteria andFloorPositXIn(List<Float> values) {
            addCriterion("FLOOR_POSIT_X in", values, "floorPositX");
            return (Criteria) this;
        }

        public Criteria andFloorPositXNotIn(List<Float> values) {
            addCriterion("FLOOR_POSIT_X not in", values, "floorPositX");
            return (Criteria) this;
        }

        public Criteria andFloorPositXBetween(Float value1, Float value2) {
            addCriterion("FLOOR_POSIT_X between", value1, value2, "floorPositX");
            return (Criteria) this;
        }

        public Criteria andFloorPositXNotBetween(Float value1, Float value2) {
            addCriterion("FLOOR_POSIT_X not between", value1, value2, "floorPositX");
            return (Criteria) this;
        }

        public Criteria andFloorPositYIsNull() {
            addCriterion("FLOOR_POSIT_Y is null");
            return (Criteria) this;
        }

        public Criteria andFloorPositYIsNotNull() {
            addCriterion("FLOOR_POSIT_Y is not null");
            return (Criteria) this;
        }

        public Criteria andFloorPositYEqualTo(Float value) {
            addCriterion("FLOOR_POSIT_Y =", value, "floorPositY");
            return (Criteria) this;
        }

        public Criteria andFloorPositYNotEqualTo(Float value) {
            addCriterion("FLOOR_POSIT_Y <>", value, "floorPositY");
            return (Criteria) this;
        }

        public Criteria andFloorPositYGreaterThan(Float value) {
            addCriterion("FLOOR_POSIT_Y >", value, "floorPositY");
            return (Criteria) this;
        }

        public Criteria andFloorPositYGreaterThanOrEqualTo(Float value) {
            addCriterion("FLOOR_POSIT_Y >=", value, "floorPositY");
            return (Criteria) this;
        }

        public Criteria andFloorPositYLessThan(Float value) {
            addCriterion("FLOOR_POSIT_Y <", value, "floorPositY");
            return (Criteria) this;
        }

        public Criteria andFloorPositYLessThanOrEqualTo(Float value) {
            addCriterion("FLOOR_POSIT_Y <=", value, "floorPositY");
            return (Criteria) this;
        }

        public Criteria andFloorPositYIn(List<Float> values) {
            addCriterion("FLOOR_POSIT_Y in", values, "floorPositY");
            return (Criteria) this;
        }

        public Criteria andFloorPositYNotIn(List<Float> values) {
            addCriterion("FLOOR_POSIT_Y not in", values, "floorPositY");
            return (Criteria) this;
        }

        public Criteria andFloorPositYBetween(Float value1, Float value2) {
            addCriterion("FLOOR_POSIT_Y between", value1, value2, "floorPositY");
            return (Criteria) this;
        }

        public Criteria andFloorPositYNotBetween(Float value1, Float value2) {
            addCriterion("FLOOR_POSIT_Y not between", value1, value2, "floorPositY");
            return (Criteria) this;
        }

        public Criteria andLocationNameLikeInsensitive(String value) {
            addCriterion("upper(LOCATION_NAME) like", value.toUpperCase(), "locationName");
            return (Criteria) this;
        }

        public Criteria andLocateTypeLikeInsensitive(String value) {
            addCriterion("upper(LOCATE_TYPE) like", value.toUpperCase(), "locateType");
            return (Criteria) this;
        }

        public Criteria andQrcodeLikeInsensitive(String value) {
            addCriterion("upper(QRCODE) like", value.toUpperCase(), "qrcode");
            return (Criteria) this;
        }

        public Criteria andMacLikeInsensitive(String value) {
            addCriterion("upper(MAC) like", value.toUpperCase(), "mac");
            return (Criteria) this;
        }

        public Criteria andStatusLikeInsensitive(String value) {
            addCriterion("upper(STATUS) like", value.toUpperCase(), "status");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(REMARK) like", value.toUpperCase(), "remark");
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