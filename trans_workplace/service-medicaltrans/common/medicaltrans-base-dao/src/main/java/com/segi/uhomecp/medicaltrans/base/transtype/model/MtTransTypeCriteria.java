package com.segi.uhomecp.medicaltrans.base.transtype.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.segi.uhomecp.common.model.AbstractCriteria;

public class MtTransTypeCriteria extends AbstractCriteria {
    /**
	 * 2018年9月29日下午4:59:52
	 */
	private static final long serialVersionUID = 1L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MtTransTypeCriteria() {
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

        public Criteria andTransTypeCodeIsNull() {
            addCriterion("TRANS_TYPE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTransTypeCodeIsNotNull() {
            addCriterion("TRANS_TYPE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTransTypeCodeEqualTo(String value) {
            addCriterion("TRANS_TYPE_CODE =", value, "transTypeCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeCodeNotEqualTo(String value) {
            addCriterion("TRANS_TYPE_CODE <>", value, "transTypeCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeCodeGreaterThan(String value) {
            addCriterion("TRANS_TYPE_CODE >", value, "transTypeCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_TYPE_CODE >=", value, "transTypeCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeCodeLessThan(String value) {
            addCriterion("TRANS_TYPE_CODE <", value, "transTypeCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("TRANS_TYPE_CODE <=", value, "transTypeCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeCodeLike(String value) {
            addCriterion("TRANS_TYPE_CODE like", value, "transTypeCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeCodeNotLike(String value) {
            addCriterion("TRANS_TYPE_CODE not like", value, "transTypeCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeCodeIn(List<String> values) {
            addCriterion("TRANS_TYPE_CODE in", values, "transTypeCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeCodeNotIn(List<String> values) {
            addCriterion("TRANS_TYPE_CODE not in", values, "transTypeCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeCodeBetween(String value1, String value2) {
            addCriterion("TRANS_TYPE_CODE between", value1, value2, "transTypeCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeCodeNotBetween(String value1, String value2) {
            addCriterion("TRANS_TYPE_CODE not between", value1, value2, "transTypeCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeNameIsNull() {
            addCriterion("TRANS_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTransTypeNameIsNotNull() {
            addCriterion("TRANS_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTransTypeNameEqualTo(String value) {
            addCriterion("TRANS_TYPE_NAME =", value, "transTypeName");
            return (Criteria) this;
        }

        public Criteria andTransTypeNameNotEqualTo(String value) {
            addCriterion("TRANS_TYPE_NAME <>", value, "transTypeName");
            return (Criteria) this;
        }

        public Criteria andTransTypeNameGreaterThan(String value) {
            addCriterion("TRANS_TYPE_NAME >", value, "transTypeName");
            return (Criteria) this;
        }

        public Criteria andTransTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_TYPE_NAME >=", value, "transTypeName");
            return (Criteria) this;
        }

        public Criteria andTransTypeNameLessThan(String value) {
            addCriterion("TRANS_TYPE_NAME <", value, "transTypeName");
            return (Criteria) this;
        }

        public Criteria andTransTypeNameLessThanOrEqualTo(String value) {
            addCriterion("TRANS_TYPE_NAME <=", value, "transTypeName");
            return (Criteria) this;
        }

        public Criteria andTransTypeNameLike(String value) {
            addCriterion("TRANS_TYPE_NAME like", value, "transTypeName");
            return (Criteria) this;
        }

        public Criteria andTransTypeNameNotLike(String value) {
            addCriterion("TRANS_TYPE_NAME not like", value, "transTypeName");
            return (Criteria) this;
        }

        public Criteria andTransTypeNameIn(List<String> values) {
            addCriterion("TRANS_TYPE_NAME in", values, "transTypeName");
            return (Criteria) this;
        }

        public Criteria andTransTypeNameNotIn(List<String> values) {
            addCriterion("TRANS_TYPE_NAME not in", values, "transTypeName");
            return (Criteria) this;
        }

        public Criteria andTransTypeNameBetween(String value1, String value2) {
            addCriterion("TRANS_TYPE_NAME between", value1, value2, "transTypeName");
            return (Criteria) this;
        }

        public Criteria andTransTypeNameNotBetween(String value1, String value2) {
            addCriterion("TRANS_TYPE_NAME not between", value1, value2, "transTypeName");
            return (Criteria) this;
        }

        public Criteria andStandardMiniteIsNull() {
            addCriterion("STANDARD_MINITE is null");
            return (Criteria) this;
        }

        public Criteria andStandardMiniteIsNotNull() {
            addCriterion("STANDARD_MINITE is not null");
            return (Criteria) this;
        }

        public Criteria andStandardMiniteEqualTo(Integer value) {
            addCriterion("STANDARD_MINITE =", value, "standardMinite");
            return (Criteria) this;
        }

        public Criteria andStandardMiniteNotEqualTo(Integer value) {
            addCriterion("STANDARD_MINITE <>", value, "standardMinite");
            return (Criteria) this;
        }

        public Criteria andStandardMiniteGreaterThan(Integer value) {
            addCriterion("STANDARD_MINITE >", value, "standardMinite");
            return (Criteria) this;
        }

        public Criteria andStandardMiniteGreaterThanOrEqualTo(Integer value) {
            addCriterion("STANDARD_MINITE >=", value, "standardMinite");
            return (Criteria) this;
        }

        public Criteria andStandardMiniteLessThan(Integer value) {
            addCriterion("STANDARD_MINITE <", value, "standardMinite");
            return (Criteria) this;
        }

        public Criteria andStandardMiniteLessThanOrEqualTo(Integer value) {
            addCriterion("STANDARD_MINITE <=", value, "standardMinite");
            return (Criteria) this;
        }

        public Criteria andStandardMiniteIn(List<Integer> values) {
            addCriterion("STANDARD_MINITE in", values, "standardMinite");
            return (Criteria) this;
        }

        public Criteria andStandardMiniteNotIn(List<Integer> values) {
            addCriterion("STANDARD_MINITE not in", values, "standardMinite");
            return (Criteria) this;
        }

        public Criteria andStandardMiniteBetween(Integer value1, Integer value2) {
            addCriterion("STANDARD_MINITE between", value1, value2, "standardMinite");
            return (Criteria) this;
        }

        public Criteria andStandardMiniteNotBetween(Integer value1, Integer value2) {
            addCriterion("STANDARD_MINITE not between", value1, value2, "standardMinite");
            return (Criteria) this;
        }

        public Criteria andWarnMiniteIsNull() {
            addCriterion("WARN_MINITE is null");
            return (Criteria) this;
        }

        public Criteria andWarnMiniteIsNotNull() {
            addCriterion("WARN_MINITE is not null");
            return (Criteria) this;
        }

        public Criteria andWarnMiniteEqualTo(Integer value) {
            addCriterion("WARN_MINITE =", value, "warnMinite");
            return (Criteria) this;
        }

        public Criteria andWarnMiniteNotEqualTo(Integer value) {
            addCriterion("WARN_MINITE <>", value, "warnMinite");
            return (Criteria) this;
        }

        public Criteria andWarnMiniteGreaterThan(Integer value) {
            addCriterion("WARN_MINITE >", value, "warnMinite");
            return (Criteria) this;
        }

        public Criteria andWarnMiniteGreaterThanOrEqualTo(Integer value) {
            addCriterion("WARN_MINITE >=", value, "warnMinite");
            return (Criteria) this;
        }

        public Criteria andWarnMiniteLessThan(Integer value) {
            addCriterion("WARN_MINITE <", value, "warnMinite");
            return (Criteria) this;
        }

        public Criteria andWarnMiniteLessThanOrEqualTo(Integer value) {
            addCriterion("WARN_MINITE <=", value, "warnMinite");
            return (Criteria) this;
        }

        public Criteria andWarnMiniteIn(List<Integer> values) {
            addCriterion("WARN_MINITE in", values, "warnMinite");
            return (Criteria) this;
        }

        public Criteria andWarnMiniteNotIn(List<Integer> values) {
            addCriterion("WARN_MINITE not in", values, "warnMinite");
            return (Criteria) this;
        }

        public Criteria andWarnMiniteBetween(Integer value1, Integer value2) {
            addCriterion("WARN_MINITE between", value1, value2, "warnMinite");
            return (Criteria) this;
        }

        public Criteria andWarnMiniteNotBetween(Integer value1, Integer value2) {
            addCriterion("WARN_MINITE not between", value1, value2, "warnMinite");
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

        public Criteria andTimeOutIsNull() {
            addCriterion("TIME_OUT is null");
            return (Criteria) this;
        }

        public Criteria andTimeOutIsNotNull() {
            addCriterion("TIME_OUT is not null");
            return (Criteria) this;
        }

        public Criteria andTimeOutEqualTo(Integer value) {
            addCriterion("TIME_OUT =", value, "timeOut");
            return (Criteria) this;
        }

        public Criteria andTimeOutNotEqualTo(Integer value) {
            addCriterion("TIME_OUT <>", value, "timeOut");
            return (Criteria) this;
        }

        public Criteria andTimeOutGreaterThan(Integer value) {
            addCriterion("TIME_OUT >", value, "timeOut");
            return (Criteria) this;
        }

        public Criteria andTimeOutGreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_OUT >=", value, "timeOut");
            return (Criteria) this;
        }

        public Criteria andTimeOutLessThan(Integer value) {
            addCriterion("TIME_OUT <", value, "timeOut");
            return (Criteria) this;
        }

        public Criteria andTimeOutLessThanOrEqualTo(Integer value) {
            addCriterion("TIME_OUT <=", value, "timeOut");
            return (Criteria) this;
        }

        public Criteria andTimeOutIn(List<Integer> values) {
            addCriterion("TIME_OUT in", values, "timeOut");
            return (Criteria) this;
        }

        public Criteria andTimeOutNotIn(List<Integer> values) {
            addCriterion("TIME_OUT not in", values, "timeOut");
            return (Criteria) this;
        }

        public Criteria andTimeOutBetween(Integer value1, Integer value2) {
            addCriterion("TIME_OUT between", value1, value2, "timeOut");
            return (Criteria) this;
        }

        public Criteria andTimeOutNotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_OUT not between", value1, value2, "timeOut");
            return (Criteria) this;
        }

        public Criteria andOprGuideIsNull() {
            addCriterion("OPR_GUIDE is null");
            return (Criteria) this;
        }

        public Criteria andOprGuideIsNotNull() {
            addCriterion("OPR_GUIDE is not null");
            return (Criteria) this;
        }

        public Criteria andOprGuideEqualTo(String value) {
            addCriterion("OPR_GUIDE =", value, "oprGuide");
            return (Criteria) this;
        }

        public Criteria andOprGuideNotEqualTo(String value) {
            addCriterion("OPR_GUIDE <>", value, "oprGuide");
            return (Criteria) this;
        }

        public Criteria andOprGuideGreaterThan(String value) {
            addCriterion("OPR_GUIDE >", value, "oprGuide");
            return (Criteria) this;
        }

        public Criteria andOprGuideGreaterThanOrEqualTo(String value) {
            addCriterion("OPR_GUIDE >=", value, "oprGuide");
            return (Criteria) this;
        }

        public Criteria andOprGuideLessThan(String value) {
            addCriterion("OPR_GUIDE <", value, "oprGuide");
            return (Criteria) this;
        }

        public Criteria andOprGuideLessThanOrEqualTo(String value) {
            addCriterion("OPR_GUIDE <=", value, "oprGuide");
            return (Criteria) this;
        }

        public Criteria andOprGuideLike(String value) {
            addCriterion("OPR_GUIDE like", value, "oprGuide");
            return (Criteria) this;
        }

        public Criteria andOprGuideNotLike(String value) {
            addCriterion("OPR_GUIDE not like", value, "oprGuide");
            return (Criteria) this;
        }

        public Criteria andOprGuideIn(List<String> values) {
            addCriterion("OPR_GUIDE in", values, "oprGuide");
            return (Criteria) this;
        }

        public Criteria andOprGuideNotIn(List<String> values) {
            addCriterion("OPR_GUIDE not in", values, "oprGuide");
            return (Criteria) this;
        }

        public Criteria andOprGuideBetween(String value1, String value2) {
            addCriterion("OPR_GUIDE between", value1, value2, "oprGuide");
            return (Criteria) this;
        }

        public Criteria andOprGuideNotBetween(String value1, String value2) {
            addCriterion("OPR_GUIDE not between", value1, value2, "oprGuide");
            return (Criteria) this;
        }

        public Criteria andTransTypeParentCodeLikeInsensitive(String value) {
            addCriterion("upper(TRANS_TYPE_PARENT_CODE) like", value.toUpperCase(), "transTypeParentCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeCodeLikeInsensitive(String value) {
            addCriterion("upper(TRANS_TYPE_CODE) like", value.toUpperCase(), "transTypeCode");
            return (Criteria) this;
        }

        public Criteria andTransTypeNameLikeInsensitive(String value) {
            addCriterion("upper(TRANS_TYPE_NAME) like", value.toUpperCase(), "transTypeName");
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

        public Criteria andOprGuideLikeInsensitive(String value) {
            addCriterion("upper(OPR_GUIDE) like", value.toUpperCase(), "oprGuide");
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