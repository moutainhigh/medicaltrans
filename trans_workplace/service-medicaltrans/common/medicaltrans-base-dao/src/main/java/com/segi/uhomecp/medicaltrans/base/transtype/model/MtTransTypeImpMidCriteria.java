package com.segi.uhomecp.medicaltrans.base.transtype.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.segi.uhomecp.common.model.AbstractCriteria;

public class MtTransTypeImpMidCriteria extends AbstractCriteria {
    /**
	 * 2018年10月8日上午11:09:16
	 */
	private static final long serialVersionUID = 5875181080437718288L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MtTransTypeImpMidCriteria() {
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

        public Criteria andItemIdIsNull() {
            addCriterion("ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(Integer value) {
            addCriterion("ITEM_ID =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(Integer value) {
            addCriterion("ITEM_ID <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(Integer value) {
            addCriterion("ITEM_ID >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ITEM_ID >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(Integer value) {
            addCriterion("ITEM_ID <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("ITEM_ID <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<Integer> values) {
            addCriterion("ITEM_ID in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<Integer> values) {
            addCriterion("ITEM_ID not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_ID between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_ID not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNull() {
            addCriterion("BATCH_ID is null");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNotNull() {
            addCriterion("BATCH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBatchIdEqualTo(Integer value) {
            addCriterion("BATCH_ID =", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotEqualTo(Integer value) {
            addCriterion("BATCH_ID <>", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThan(Integer value) {
            addCriterion("BATCH_ID >", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("BATCH_ID >=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThan(Integer value) {
            addCriterion("BATCH_ID <", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThanOrEqualTo(Integer value) {
            addCriterion("BATCH_ID <=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdIn(List<Integer> values) {
            addCriterion("BATCH_ID in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotIn(List<Integer> values) {
            addCriterion("BATCH_ID not in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdBetween(Integer value1, Integer value2) {
            addCriterion("BATCH_ID between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("BATCH_ID not between", value1, value2, "batchId");
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

        public Criteria andStatusCdIsNull() {
            addCriterion("STATUS_CD is null");
            return (Criteria) this;
        }

        public Criteria andStatusCdIsNotNull() {
            addCriterion("STATUS_CD is not null");
            return (Criteria) this;
        }

        public Criteria andStatusCdEqualTo(String value) {
            addCriterion("STATUS_CD =", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdNotEqualTo(String value) {
            addCriterion("STATUS_CD <>", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdGreaterThan(String value) {
            addCriterion("STATUS_CD >", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_CD >=", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdLessThan(String value) {
            addCriterion("STATUS_CD <", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdLessThanOrEqualTo(String value) {
            addCriterion("STATUS_CD <=", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdLike(String value) {
            addCriterion("STATUS_CD like", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdNotLike(String value) {
            addCriterion("STATUS_CD not like", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdIn(List<String> values) {
            addCriterion("STATUS_CD in", values, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdNotIn(List<String> values) {
            addCriterion("STATUS_CD not in", values, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdBetween(String value1, String value2) {
            addCriterion("STATUS_CD between", value1, value2, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdNotBetween(String value1, String value2) {
            addCriterion("STATUS_CD not between", value1, value2, "statusCd");
            return (Criteria) this;
        }

        public Criteria andExeDateIsNull() {
            addCriterion("EXE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andExeDateIsNotNull() {
            addCriterion("EXE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andExeDateEqualTo(Date value) {
            addCriterion("EXE_DATE =", value, "exeDate");
            return (Criteria) this;
        }

        public Criteria andExeDateNotEqualTo(Date value) {
            addCriterion("EXE_DATE <>", value, "exeDate");
            return (Criteria) this;
        }

        public Criteria andExeDateGreaterThan(Date value) {
            addCriterion("EXE_DATE >", value, "exeDate");
            return (Criteria) this;
        }

        public Criteria andExeDateGreaterThanOrEqualTo(Date value) {
            addCriterion("EXE_DATE >=", value, "exeDate");
            return (Criteria) this;
        }

        public Criteria andExeDateLessThan(Date value) {
            addCriterion("EXE_DATE <", value, "exeDate");
            return (Criteria) this;
        }

        public Criteria andExeDateLessThanOrEqualTo(Date value) {
            addCriterion("EXE_DATE <=", value, "exeDate");
            return (Criteria) this;
        }

        public Criteria andExeDateIn(List<Date> values) {
            addCriterion("EXE_DATE in", values, "exeDate");
            return (Criteria) this;
        }

        public Criteria andExeDateNotIn(List<Date> values) {
            addCriterion("EXE_DATE not in", values, "exeDate");
            return (Criteria) this;
        }

        public Criteria andExeDateBetween(Date value1, Date value2) {
            addCriterion("EXE_DATE between", value1, value2, "exeDate");
            return (Criteria) this;
        }

        public Criteria andExeDateNotBetween(Date value1, Date value2) {
            addCriterion("EXE_DATE not between", value1, value2, "exeDate");
            return (Criteria) this;
        }

        public Criteria andExcelRowIsNull() {
            addCriterion("EXCEL_ROW is null");
            return (Criteria) this;
        }

        public Criteria andExcelRowIsNotNull() {
            addCriterion("EXCEL_ROW is not null");
            return (Criteria) this;
        }

        public Criteria andExcelRowEqualTo(Integer value) {
            addCriterion("EXCEL_ROW =", value, "excelRow");
            return (Criteria) this;
        }

        public Criteria andExcelRowNotEqualTo(Integer value) {
            addCriterion("EXCEL_ROW <>", value, "excelRow");
            return (Criteria) this;
        }

        public Criteria andExcelRowGreaterThan(Integer value) {
            addCriterion("EXCEL_ROW >", value, "excelRow");
            return (Criteria) this;
        }

        public Criteria andExcelRowGreaterThanOrEqualTo(Integer value) {
            addCriterion("EXCEL_ROW >=", value, "excelRow");
            return (Criteria) this;
        }

        public Criteria andExcelRowLessThan(Integer value) {
            addCriterion("EXCEL_ROW <", value, "excelRow");
            return (Criteria) this;
        }

        public Criteria andExcelRowLessThanOrEqualTo(Integer value) {
            addCriterion("EXCEL_ROW <=", value, "excelRow");
            return (Criteria) this;
        }

        public Criteria andExcelRowIn(List<Integer> values) {
            addCriterion("EXCEL_ROW in", values, "excelRow");
            return (Criteria) this;
        }

        public Criteria andExcelRowNotIn(List<Integer> values) {
            addCriterion("EXCEL_ROW not in", values, "excelRow");
            return (Criteria) this;
        }

        public Criteria andExcelRowBetween(Integer value1, Integer value2) {
            addCriterion("EXCEL_ROW between", value1, value2, "excelRow");
            return (Criteria) this;
        }

        public Criteria andExcelRowNotBetween(Integer value1, Integer value2) {
            addCriterion("EXCEL_ROW not between", value1, value2, "excelRow");
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

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(REMARK) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }

        public Criteria andOprGuideLikeInsensitive(String value) {
            addCriterion("upper(OPR_GUIDE) like", value.toUpperCase(), "oprGuide");
            return (Criteria) this;
        }

        public Criteria andStatusCdLikeInsensitive(String value) {
            addCriterion("upper(STATUS_CD) like", value.toUpperCase(), "statusCd");
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