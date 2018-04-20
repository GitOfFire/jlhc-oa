package com.jlhc.base_com.dto.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public CustomerExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
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

        public Criteria andCusIdIsNull() {
            addCriterion("cus_id is null");
            return (Criteria) this;
        }

        public Criteria andCusIdIsNotNull() {
            addCriterion("cus_id is not null");
            return (Criteria) this;
        }

        public Criteria andCusIdEqualTo(String value) {
            addCriterion("cus_id =", value, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdNotEqualTo(String value) {
            addCriterion("cus_id <>", value, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdGreaterThan(String value) {
            addCriterion("cus_id >", value, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdGreaterThanOrEqualTo(String value) {
            addCriterion("cus_id >=", value, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdLessThan(String value) {
            addCriterion("cus_id <", value, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdLessThanOrEqualTo(String value) {
            addCriterion("cus_id <=", value, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdLike(String value) {
            addCriterion("cus_id like", value, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdNotLike(String value) {
            addCriterion("cus_id not like", value, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdIn(List<String> values) {
            addCriterion("cus_id in", values, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdNotIn(List<String> values) {
            addCriterion("cus_id not in", values, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdBetween(String value1, String value2) {
            addCriterion("cus_id between", value1, value2, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdNotBetween(String value1, String value2) {
            addCriterion("cus_id not between", value1, value2, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusNameIsNull() {
            addCriterion("cus_name is null");
            return (Criteria) this;
        }

        public Criteria andCusNameIsNotNull() {
            addCriterion("cus_name is not null");
            return (Criteria) this;
        }

        public Criteria andCusNameEqualTo(String value) {
            addCriterion("cus_name =", value, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameNotEqualTo(String value) {
            addCriterion("cus_name <>", value, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameGreaterThan(String value) {
            addCriterion("cus_name >", value, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameGreaterThanOrEqualTo(String value) {
            addCriterion("cus_name >=", value, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameLessThan(String value) {
            addCriterion("cus_name <", value, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameLessThanOrEqualTo(String value) {
            addCriterion("cus_name <=", value, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameLike(String value) {
            addCriterion("cus_name like", value, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameNotLike(String value) {
            addCriterion("cus_name not like", value, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameIn(List<String> values) {
            addCriterion("cus_name in", values, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameNotIn(List<String> values) {
            addCriterion("cus_name not in", values, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameBetween(String value1, String value2) {
            addCriterion("cus_name between", value1, value2, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameNotBetween(String value1, String value2) {
            addCriterion("cus_name not between", value1, value2, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusGenderTypeIsNull() {
            addCriterion("cus_gender_type is null");
            return (Criteria) this;
        }

        public Criteria andCusGenderTypeIsNotNull() {
            addCriterion("cus_gender_type is not null");
            return (Criteria) this;
        }

        public Criteria andCusGenderTypeEqualTo(Integer value) {
            addCriterion("cus_gender_type =", value, "cusGenderType");
            return (Criteria) this;
        }

        public Criteria andCusGenderTypeNotEqualTo(Integer value) {
            addCriterion("cus_gender_type <>", value, "cusGenderType");
            return (Criteria) this;
        }

        public Criteria andCusGenderTypeGreaterThan(Integer value) {
            addCriterion("cus_gender_type >", value, "cusGenderType");
            return (Criteria) this;
        }

        public Criteria andCusGenderTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("cus_gender_type >=", value, "cusGenderType");
            return (Criteria) this;
        }

        public Criteria andCusGenderTypeLessThan(Integer value) {
            addCriterion("cus_gender_type <", value, "cusGenderType");
            return (Criteria) this;
        }

        public Criteria andCusGenderTypeLessThanOrEqualTo(Integer value) {
            addCriterion("cus_gender_type <=", value, "cusGenderType");
            return (Criteria) this;
        }

        public Criteria andCusGenderTypeIn(List<Integer> values) {
            addCriterion("cus_gender_type in", values, "cusGenderType");
            return (Criteria) this;
        }

        public Criteria andCusGenderTypeNotIn(List<Integer> values) {
            addCriterion("cus_gender_type not in", values, "cusGenderType");
            return (Criteria) this;
        }

        public Criteria andCusGenderTypeBetween(Integer value1, Integer value2) {
            addCriterion("cus_gender_type between", value1, value2, "cusGenderType");
            return (Criteria) this;
        }

        public Criteria andCusGenderTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("cus_gender_type not between", value1, value2, "cusGenderType");
            return (Criteria) this;
        }

        public Criteria andCusIdCardIsNull() {
            addCriterion("cus_id_card is null");
            return (Criteria) this;
        }

        public Criteria andCusIdCardIsNotNull() {
            addCriterion("cus_id_card is not null");
            return (Criteria) this;
        }

        public Criteria andCusIdCardEqualTo(String value) {
            addCriterion("cus_id_card =", value, "cusIdCard");
            return (Criteria) this;
        }

        public Criteria andCusIdCardNotEqualTo(String value) {
            addCriterion("cus_id_card <>", value, "cusIdCard");
            return (Criteria) this;
        }

        public Criteria andCusIdCardGreaterThan(String value) {
            addCriterion("cus_id_card >", value, "cusIdCard");
            return (Criteria) this;
        }

        public Criteria andCusIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("cus_id_card >=", value, "cusIdCard");
            return (Criteria) this;
        }

        public Criteria andCusIdCardLessThan(String value) {
            addCriterion("cus_id_card <", value, "cusIdCard");
            return (Criteria) this;
        }

        public Criteria andCusIdCardLessThanOrEqualTo(String value) {
            addCriterion("cus_id_card <=", value, "cusIdCard");
            return (Criteria) this;
        }

        public Criteria andCusIdCardLike(String value) {
            addCriterion("cus_id_card like", value, "cusIdCard");
            return (Criteria) this;
        }

        public Criteria andCusIdCardNotLike(String value) {
            addCriterion("cus_id_card not like", value, "cusIdCard");
            return (Criteria) this;
        }

        public Criteria andCusIdCardIn(List<String> values) {
            addCriterion("cus_id_card in", values, "cusIdCard");
            return (Criteria) this;
        }

        public Criteria andCusIdCardNotIn(List<String> values) {
            addCriterion("cus_id_card not in", values, "cusIdCard");
            return (Criteria) this;
        }

        public Criteria andCusIdCardBetween(String value1, String value2) {
            addCriterion("cus_id_card between", value1, value2, "cusIdCard");
            return (Criteria) this;
        }

        public Criteria andCusIdCardNotBetween(String value1, String value2) {
            addCriterion("cus_id_card not between", value1, value2, "cusIdCard");
            return (Criteria) this;
        }

        public Criteria andCusRemarksIsNull() {
            addCriterion("cus_remarks is null");
            return (Criteria) this;
        }

        public Criteria andCusRemarksIsNotNull() {
            addCriterion("cus_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andCusRemarksEqualTo(String value) {
            addCriterion("cus_remarks =", value, "cusRemarks");
            return (Criteria) this;
        }

        public Criteria andCusRemarksNotEqualTo(String value) {
            addCriterion("cus_remarks <>", value, "cusRemarks");
            return (Criteria) this;
        }

        public Criteria andCusRemarksGreaterThan(String value) {
            addCriterion("cus_remarks >", value, "cusRemarks");
            return (Criteria) this;
        }

        public Criteria andCusRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("cus_remarks >=", value, "cusRemarks");
            return (Criteria) this;
        }

        public Criteria andCusRemarksLessThan(String value) {
            addCriterion("cus_remarks <", value, "cusRemarks");
            return (Criteria) this;
        }

        public Criteria andCusRemarksLessThanOrEqualTo(String value) {
            addCriterion("cus_remarks <=", value, "cusRemarks");
            return (Criteria) this;
        }

        public Criteria andCusRemarksLike(String value) {
            addCriterion("cus_remarks like", value, "cusRemarks");
            return (Criteria) this;
        }

        public Criteria andCusRemarksNotLike(String value) {
            addCriterion("cus_remarks not like", value, "cusRemarks");
            return (Criteria) this;
        }

        public Criteria andCusRemarksIn(List<String> values) {
            addCriterion("cus_remarks in", values, "cusRemarks");
            return (Criteria) this;
        }

        public Criteria andCusRemarksNotIn(List<String> values) {
            addCriterion("cus_remarks not in", values, "cusRemarks");
            return (Criteria) this;
        }

        public Criteria andCusRemarksBetween(String value1, String value2) {
            addCriterion("cus_remarks between", value1, value2, "cusRemarks");
            return (Criteria) this;
        }

        public Criteria andCusRemarksNotBetween(String value1, String value2) {
            addCriterion("cus_remarks not between", value1, value2, "cusRemarks");
            return (Criteria) this;
        }

        public Criteria andComIdIsNull() {
            addCriterion("com_id is null");
            return (Criteria) this;
        }

        public Criteria andComIdIsNotNull() {
            addCriterion("com_id is not null");
            return (Criteria) this;
        }

        public Criteria andComIdEqualTo(String value) {
            addCriterion("com_id =", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdNotEqualTo(String value) {
            addCriterion("com_id <>", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdGreaterThan(String value) {
            addCriterion("com_id >", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdGreaterThanOrEqualTo(String value) {
            addCriterion("com_id >=", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdLessThan(String value) {
            addCriterion("com_id <", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdLessThanOrEqualTo(String value) {
            addCriterion("com_id <=", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdLike(String value) {
            addCriterion("com_id like", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdNotLike(String value) {
            addCriterion("com_id not like", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdIn(List<String> values) {
            addCriterion("com_id in", values, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdNotIn(List<String> values) {
            addCriterion("com_id not in", values, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdBetween(String value1, String value2) {
            addCriterion("com_id between", value1, value2, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdNotBetween(String value1, String value2) {
            addCriterion("com_id not between", value1, value2, "comId");
            return (Criteria) this;
        }

        public Criteria andCallMainIsNull() {
            addCriterion("call_main is null");
            return (Criteria) this;
        }

        public Criteria andCallMainIsNotNull() {
            addCriterion("call_main is not null");
            return (Criteria) this;
        }

        public Criteria andCallMainEqualTo(String value) {
            addCriterion("call_main =", value, "callMain");
            return (Criteria) this;
        }

        public Criteria andCallMainNotEqualTo(String value) {
            addCriterion("call_main <>", value, "callMain");
            return (Criteria) this;
        }

        public Criteria andCallMainGreaterThan(String value) {
            addCriterion("call_main >", value, "callMain");
            return (Criteria) this;
        }

        public Criteria andCallMainGreaterThanOrEqualTo(String value) {
            addCriterion("call_main >=", value, "callMain");
            return (Criteria) this;
        }

        public Criteria andCallMainLessThan(String value) {
            addCriterion("call_main <", value, "callMain");
            return (Criteria) this;
        }

        public Criteria andCallMainLessThanOrEqualTo(String value) {
            addCriterion("call_main <=", value, "callMain");
            return (Criteria) this;
        }

        public Criteria andCallMainLike(String value) {
            addCriterion("call_main like", value, "callMain");
            return (Criteria) this;
        }

        public Criteria andCallMainNotLike(String value) {
            addCriterion("call_main not like", value, "callMain");
            return (Criteria) this;
        }

        public Criteria andCallMainIn(List<String> values) {
            addCriterion("call_main in", values, "callMain");
            return (Criteria) this;
        }

        public Criteria andCallMainNotIn(List<String> values) {
            addCriterion("call_main not in", values, "callMain");
            return (Criteria) this;
        }

        public Criteria andCallMainBetween(String value1, String value2) {
            addCriterion("call_main between", value1, value2, "callMain");
            return (Criteria) this;
        }

        public Criteria andCallMainNotBetween(String value1, String value2) {
            addCriterion("call_main not between", value1, value2, "callMain");
            return (Criteria) this;
        }

        public Criteria andCallSecondaryIsNull() {
            addCriterion("call_secondary is null");
            return (Criteria) this;
        }

        public Criteria andCallSecondaryIsNotNull() {
            addCriterion("call_secondary is not null");
            return (Criteria) this;
        }

        public Criteria andCallSecondaryEqualTo(String value) {
            addCriterion("call_secondary =", value, "callSecondary");
            return (Criteria) this;
        }

        public Criteria andCallSecondaryNotEqualTo(String value) {
            addCriterion("call_secondary <>", value, "callSecondary");
            return (Criteria) this;
        }

        public Criteria andCallSecondaryGreaterThan(String value) {
            addCriterion("call_secondary >", value, "callSecondary");
            return (Criteria) this;
        }

        public Criteria andCallSecondaryGreaterThanOrEqualTo(String value) {
            addCriterion("call_secondary >=", value, "callSecondary");
            return (Criteria) this;
        }

        public Criteria andCallSecondaryLessThan(String value) {
            addCriterion("call_secondary <", value, "callSecondary");
            return (Criteria) this;
        }

        public Criteria andCallSecondaryLessThanOrEqualTo(String value) {
            addCriterion("call_secondary <=", value, "callSecondary");
            return (Criteria) this;
        }

        public Criteria andCallSecondaryLike(String value) {
            addCriterion("call_secondary like", value, "callSecondary");
            return (Criteria) this;
        }

        public Criteria andCallSecondaryNotLike(String value) {
            addCriterion("call_secondary not like", value, "callSecondary");
            return (Criteria) this;
        }

        public Criteria andCallSecondaryIn(List<String> values) {
            addCriterion("call_secondary in", values, "callSecondary");
            return (Criteria) this;
        }

        public Criteria andCallSecondaryNotIn(List<String> values) {
            addCriterion("call_secondary not in", values, "callSecondary");
            return (Criteria) this;
        }

        public Criteria andCallSecondaryBetween(String value1, String value2) {
            addCriterion("call_secondary between", value1, value2, "callSecondary");
            return (Criteria) this;
        }

        public Criteria andCallSecondaryNotBetween(String value1, String value2) {
            addCriterion("call_secondary not between", value1, value2, "callSecondary");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
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