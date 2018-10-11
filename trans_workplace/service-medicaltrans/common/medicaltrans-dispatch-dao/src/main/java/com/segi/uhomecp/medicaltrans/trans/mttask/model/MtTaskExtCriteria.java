package com.segi.uhomecp.medicaltrans.trans.mttask.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.segi.uhomecp.common.model.AbstractCriteria;

public class MtTaskExtCriteria extends AbstractCriteria {
    
	/**  描述   (@author: zhangyang@segimail.com) */      
	    
	private static final long serialVersionUID = 1L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MtTaskExtCriteria() {
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

        public Criteria andTaskExtIdIsNull() {
            addCriterion("TASK_EXT_ID is null");
            return (Criteria) this;
        }

        public Criteria andTaskExtIdIsNotNull() {
            addCriterion("TASK_EXT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTaskExtIdEqualTo(Integer value) {
            addCriterion("TASK_EXT_ID =", value, "taskExtId");
            return (Criteria) this;
        }

        public Criteria andTaskExtIdNotEqualTo(Integer value) {
            addCriterion("TASK_EXT_ID <>", value, "taskExtId");
            return (Criteria) this;
        }

        public Criteria andTaskExtIdGreaterThan(Integer value) {
            addCriterion("TASK_EXT_ID >", value, "taskExtId");
            return (Criteria) this;
        }

        public Criteria andTaskExtIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TASK_EXT_ID >=", value, "taskExtId");
            return (Criteria) this;
        }

        public Criteria andTaskExtIdLessThan(Integer value) {
            addCriterion("TASK_EXT_ID <", value, "taskExtId");
            return (Criteria) this;
        }

        public Criteria andTaskExtIdLessThanOrEqualTo(Integer value) {
            addCriterion("TASK_EXT_ID <=", value, "taskExtId");
            return (Criteria) this;
        }

        public Criteria andTaskExtIdIn(List<Integer> values) {
            addCriterion("TASK_EXT_ID in", values, "taskExtId");
            return (Criteria) this;
        }

        public Criteria andTaskExtIdNotIn(List<Integer> values) {
            addCriterion("TASK_EXT_ID not in", values, "taskExtId");
            return (Criteria) this;
        }

        public Criteria andTaskExtIdBetween(Integer value1, Integer value2) {
            addCriterion("TASK_EXT_ID between", value1, value2, "taskExtId");
            return (Criteria) this;
        }

        public Criteria andTaskExtIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TASK_EXT_ID not between", value1, value2, "taskExtId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("TASK_ID is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("TASK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(Integer value) {
            addCriterion("TASK_ID =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(Integer value) {
            addCriterion("TASK_ID <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(Integer value) {
            addCriterion("TASK_ID >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TASK_ID >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(Integer value) {
            addCriterion("TASK_ID <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(Integer value) {
            addCriterion("TASK_ID <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<Integer> values) {
            addCriterion("TASK_ID in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<Integer> values) {
            addCriterion("TASK_ID not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(Integer value1, Integer value2) {
            addCriterion("TASK_ID between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TASK_ID not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andPatientNameIsNull() {
            addCriterion("PATIENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPatientNameIsNotNull() {
            addCriterion("PATIENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPatientNameEqualTo(String value) {
            addCriterion("PATIENT_NAME =", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotEqualTo(String value) {
            addCriterion("PATIENT_NAME <>", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameGreaterThan(String value) {
            addCriterion("PATIENT_NAME >", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_NAME >=", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLessThan(String value) {
            addCriterion("PATIENT_NAME <", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_NAME <=", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLike(String value) {
            addCriterion("PATIENT_NAME like", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotLike(String value) {
            addCriterion("PATIENT_NAME not like", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameIn(List<String> values) {
            addCriterion("PATIENT_NAME in", values, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotIn(List<String> values) {
            addCriterion("PATIENT_NAME not in", values, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameBetween(String value1, String value2) {
            addCriterion("PATIENT_NAME between", value1, value2, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotBetween(String value1, String value2) {
            addCriterion("PATIENT_NAME not between", value1, value2, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientGenderIsNull() {
            addCriterion("PATIENT_GENDER is null");
            return (Criteria) this;
        }

        public Criteria andPatientGenderIsNotNull() {
            addCriterion("PATIENT_GENDER is not null");
            return (Criteria) this;
        }

        public Criteria andPatientGenderEqualTo(String value) {
            addCriterion("PATIENT_GENDER =", value, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderNotEqualTo(String value) {
            addCriterion("PATIENT_GENDER <>", value, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderGreaterThan(String value) {
            addCriterion("PATIENT_GENDER >", value, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_GENDER >=", value, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderLessThan(String value) {
            addCriterion("PATIENT_GENDER <", value, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_GENDER <=", value, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderLike(String value) {
            addCriterion("PATIENT_GENDER like", value, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderNotLike(String value) {
            addCriterion("PATIENT_GENDER not like", value, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderIn(List<String> values) {
            addCriterion("PATIENT_GENDER in", values, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderNotIn(List<String> values) {
            addCriterion("PATIENT_GENDER not in", values, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderBetween(String value1, String value2) {
            addCriterion("PATIENT_GENDER between", value1, value2, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderNotBetween(String value1, String value2) {
            addCriterion("PATIENT_GENDER not between", value1, value2, "patientGender");
            return (Criteria) this;
        }

        public Criteria andBedNoIsNull() {
            addCriterion("BED_NO is null");
            return (Criteria) this;
        }

        public Criteria andBedNoIsNotNull() {
            addCriterion("BED_NO is not null");
            return (Criteria) this;
        }

        public Criteria andBedNoEqualTo(String value) {
            addCriterion("BED_NO =", value, "bedNo");
            return (Criteria) this;
        }

        public Criteria andBedNoNotEqualTo(String value) {
            addCriterion("BED_NO <>", value, "bedNo");
            return (Criteria) this;
        }

        public Criteria andBedNoGreaterThan(String value) {
            addCriterion("BED_NO >", value, "bedNo");
            return (Criteria) this;
        }

        public Criteria andBedNoGreaterThanOrEqualTo(String value) {
            addCriterion("BED_NO >=", value, "bedNo");
            return (Criteria) this;
        }

        public Criteria andBedNoLessThan(String value) {
            addCriterion("BED_NO <", value, "bedNo");
            return (Criteria) this;
        }

        public Criteria andBedNoLessThanOrEqualTo(String value) {
            addCriterion("BED_NO <=", value, "bedNo");
            return (Criteria) this;
        }

        public Criteria andBedNoLike(String value) {
            addCriterion("BED_NO like", value, "bedNo");
            return (Criteria) this;
        }

        public Criteria andBedNoNotLike(String value) {
            addCriterion("BED_NO not like", value, "bedNo");
            return (Criteria) this;
        }

        public Criteria andBedNoIn(List<String> values) {
            addCriterion("BED_NO in", values, "bedNo");
            return (Criteria) this;
        }

        public Criteria andBedNoNotIn(List<String> values) {
            addCriterion("BED_NO not in", values, "bedNo");
            return (Criteria) this;
        }

        public Criteria andBedNoBetween(String value1, String value2) {
            addCriterion("BED_NO between", value1, value2, "bedNo");
            return (Criteria) this;
        }

        public Criteria andBedNoNotBetween(String value1, String value2) {
            addCriterion("BED_NO not between", value1, value2, "bedNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecNoIsNull() {
            addCriterion("MEDICAL_REC_NO is null");
            return (Criteria) this;
        }

        public Criteria andMedicalRecNoIsNotNull() {
            addCriterion("MEDICAL_REC_NO is not null");
            return (Criteria) this;
        }

        public Criteria andMedicalRecNoEqualTo(String value) {
            addCriterion("MEDICAL_REC_NO =", value, "medicalRecNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecNoNotEqualTo(String value) {
            addCriterion("MEDICAL_REC_NO <>", value, "medicalRecNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecNoGreaterThan(String value) {
            addCriterion("MEDICAL_REC_NO >", value, "medicalRecNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecNoGreaterThanOrEqualTo(String value) {
            addCriterion("MEDICAL_REC_NO >=", value, "medicalRecNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecNoLessThan(String value) {
            addCriterion("MEDICAL_REC_NO <", value, "medicalRecNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecNoLessThanOrEqualTo(String value) {
            addCriterion("MEDICAL_REC_NO <=", value, "medicalRecNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecNoLike(String value) {
            addCriterion("MEDICAL_REC_NO like", value, "medicalRecNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecNoNotLike(String value) {
            addCriterion("MEDICAL_REC_NO not like", value, "medicalRecNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecNoIn(List<String> values) {
            addCriterion("MEDICAL_REC_NO in", values, "medicalRecNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecNoNotIn(List<String> values) {
            addCriterion("MEDICAL_REC_NO not in", values, "medicalRecNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecNoBetween(String value1, String value2) {
            addCriterion("MEDICAL_REC_NO between", value1, value2, "medicalRecNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecNoNotBetween(String value1, String value2) {
            addCriterion("MEDICAL_REC_NO not between", value1, value2, "medicalRecNo");
            return (Criteria) this;
        }

        public Criteria andTaskContentIsNull() {
            addCriterion("TASK_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andTaskContentIsNotNull() {
            addCriterion("TASK_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andTaskContentEqualTo(String value) {
            addCriterion("TASK_CONTENT =", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentNotEqualTo(String value) {
            addCriterion("TASK_CONTENT <>", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentGreaterThan(String value) {
            addCriterion("TASK_CONTENT >", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_CONTENT >=", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentLessThan(String value) {
            addCriterion("TASK_CONTENT <", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentLessThanOrEqualTo(String value) {
            addCriterion("TASK_CONTENT <=", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentLike(String value) {
            addCriterion("TASK_CONTENT like", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentNotLike(String value) {
            addCriterion("TASK_CONTENT not like", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentIn(List<String> values) {
            addCriterion("TASK_CONTENT in", values, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentNotIn(List<String> values) {
            addCriterion("TASK_CONTENT not in", values, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentBetween(String value1, String value2) {
            addCriterion("TASK_CONTENT between", value1, value2, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentNotBetween(String value1, String value2) {
            addCriterion("TASK_CONTENT not between", value1, value2, "taskContent");
            return (Criteria) this;
        }

        public Criteria andSendContentIsNull() {
            addCriterion("SEND_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andSendContentIsNotNull() {
            addCriterion("SEND_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andSendContentEqualTo(String value) {
            addCriterion("SEND_CONTENT =", value, "sendContent");
            return (Criteria) this;
        }

        public Criteria andSendContentNotEqualTo(String value) {
            addCriterion("SEND_CONTENT <>", value, "sendContent");
            return (Criteria) this;
        }

        public Criteria andSendContentGreaterThan(String value) {
            addCriterion("SEND_CONTENT >", value, "sendContent");
            return (Criteria) this;
        }

        public Criteria andSendContentGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_CONTENT >=", value, "sendContent");
            return (Criteria) this;
        }

        public Criteria andSendContentLessThan(String value) {
            addCriterion("SEND_CONTENT <", value, "sendContent");
            return (Criteria) this;
        }

        public Criteria andSendContentLessThanOrEqualTo(String value) {
            addCriterion("SEND_CONTENT <=", value, "sendContent");
            return (Criteria) this;
        }

        public Criteria andSendContentLike(String value) {
            addCriterion("SEND_CONTENT like", value, "sendContent");
            return (Criteria) this;
        }

        public Criteria andSendContentNotLike(String value) {
            addCriterion("SEND_CONTENT not like", value, "sendContent");
            return (Criteria) this;
        }

        public Criteria andSendContentIn(List<String> values) {
            addCriterion("SEND_CONTENT in", values, "sendContent");
            return (Criteria) this;
        }

        public Criteria andSendContentNotIn(List<String> values) {
            addCriterion("SEND_CONTENT not in", values, "sendContent");
            return (Criteria) this;
        }

        public Criteria andSendContentBetween(String value1, String value2) {
            addCriterion("SEND_CONTENT between", value1, value2, "sendContent");
            return (Criteria) this;
        }

        public Criteria andSendContentNotBetween(String value1, String value2) {
            addCriterion("SEND_CONTENT not between", value1, value2, "sendContent");
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

        public Criteria andEvaluateContentIsNull() {
            addCriterion("EVALUATE_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentIsNotNull() {
            addCriterion("EVALUATE_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentEqualTo(String value) {
            addCriterion("EVALUATE_CONTENT =", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentNotEqualTo(String value) {
            addCriterion("EVALUATE_CONTENT <>", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentGreaterThan(String value) {
            addCriterion("EVALUATE_CONTENT >", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentGreaterThanOrEqualTo(String value) {
            addCriterion("EVALUATE_CONTENT >=", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentLessThan(String value) {
            addCriterion("EVALUATE_CONTENT <", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentLessThanOrEqualTo(String value) {
            addCriterion("EVALUATE_CONTENT <=", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentLike(String value) {
            addCriterion("EVALUATE_CONTENT like", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentNotLike(String value) {
            addCriterion("EVALUATE_CONTENT not like", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentIn(List<String> values) {
            addCriterion("EVALUATE_CONTENT in", values, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentNotIn(List<String> values) {
            addCriterion("EVALUATE_CONTENT not in", values, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentBetween(String value1, String value2) {
            addCriterion("EVALUATE_CONTENT between", value1, value2, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentNotBetween(String value1, String value2) {
            addCriterion("EVALUATE_CONTENT not between", value1, value2, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNull() {
            addCriterion("RECEIVER is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("RECEIVER is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(Integer value) {
            addCriterion("RECEIVER =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(Integer value) {
            addCriterion("RECEIVER <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(Integer value) {
            addCriterion("RECEIVER >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(Integer value) {
            addCriterion("RECEIVER >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(Integer value) {
            addCriterion("RECEIVER <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(Integer value) {
            addCriterion("RECEIVER <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<Integer> values) {
            addCriterion("RECEIVER in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<Integer> values) {
            addCriterion("RECEIVER not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(Integer value1, Integer value2) {
            addCriterion("RECEIVER between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(Integer value1, Integer value2) {
            addCriterion("RECEIVER not between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNull() {
            addCriterion("RECEIVE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNotNull() {
            addCriterion("RECEIVE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeEqualTo(Date value) {
            addCriterion("RECEIVE_TIME =", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotEqualTo(Date value) {
            addCriterion("RECEIVE_TIME <>", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThan(Date value) {
            addCriterion("RECEIVE_TIME >", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("RECEIVE_TIME >=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThan(Date value) {
            addCriterion("RECEIVE_TIME <", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThanOrEqualTo(Date value) {
            addCriterion("RECEIVE_TIME <=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIn(List<Date> values) {
            addCriterion("RECEIVE_TIME in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotIn(List<Date> values) {
            addCriterion("RECEIVE_TIME not in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeBetween(Date value1, Date value2) {
            addCriterion("RECEIVE_TIME between", value1, value2, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotBetween(Date value1, Date value2) {
            addCriterion("RECEIVE_TIME not between", value1, value2, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andPatientNameLikeInsensitive(String value) {
            addCriterion("upper(PATIENT_NAME) like", value.toUpperCase(), "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientGenderLikeInsensitive(String value) {
            addCriterion("upper(PATIENT_GENDER) like", value.toUpperCase(), "patientGender");
            return (Criteria) this;
        }

        public Criteria andBedNoLikeInsensitive(String value) {
            addCriterion("upper(BED_NO) like", value.toUpperCase(), "bedNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecNoLikeInsensitive(String value) {
            addCriterion("upper(MEDICAL_REC_NO) like", value.toUpperCase(), "medicalRecNo");
            return (Criteria) this;
        }

        public Criteria andTaskContentLikeInsensitive(String value) {
            addCriterion("upper(TASK_CONTENT) like", value.toUpperCase(), "taskContent");
            return (Criteria) this;
        }

        public Criteria andSendContentLikeInsensitive(String value) {
            addCriterion("upper(SEND_CONTENT) like", value.toUpperCase(), "sendContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentLikeInsensitive(String value) {
            addCriterion("upper(EVALUATE_CONTENT) like", value.toUpperCase(), "evaluateContent");
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