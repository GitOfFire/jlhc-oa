package com.jlhc.oa.dto.user.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrganizationExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public OrganizationExample() {
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

        public Criteria andOrgIdIsNull() {
            addCriterion("org_id is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("org_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(Integer value) {
            addCriterion("org_id =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(Integer value) {
            addCriterion("org_id <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(Integer value) {
            addCriterion("org_id >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("org_id >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(Integer value) {
            addCriterion("org_id <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(Integer value) {
            addCriterion("org_id <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<Integer> values) {
            addCriterion("org_id in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<Integer> values) {
            addCriterion("org_id not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(Integer value1, Integer value2) {
            addCriterion("org_id between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("org_id not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdIsNull() {
            addCriterion("org_parent_id is null");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdIsNotNull() {
            addCriterion("org_parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdEqualTo(Integer value) {
            addCriterion("org_parent_id =", value, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdNotEqualTo(Integer value) {
            addCriterion("org_parent_id <>", value, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdGreaterThan(Integer value) {
            addCriterion("org_parent_id >", value, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("org_parent_id >=", value, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdLessThan(Integer value) {
            addCriterion("org_parent_id <", value, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("org_parent_id <=", value, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdIn(List<Integer> values) {
            addCriterion("org_parent_id in", values, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdNotIn(List<Integer> values) {
            addCriterion("org_parent_id not in", values, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdBetween(Integer value1, Integer value2) {
            addCriterion("org_parent_id between", value1, value2, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("org_parent_id not between", value1, value2, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("org_name is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("org_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("org_name =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("org_name <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("org_name >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("org_name >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("org_name <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("org_name <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("org_name like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("org_name not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("org_name in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("org_name not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("org_name between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("org_name not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedtimeIsNull() {
            addCriterion("org_createdtime is null");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedtimeIsNotNull() {
            addCriterion("org_createdtime is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedtimeEqualTo(Date value) {
            addCriterion("org_createdtime =", value, "orgCreatedtime");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedtimeNotEqualTo(Date value) {
            addCriterion("org_createdtime <>", value, "orgCreatedtime");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedtimeGreaterThan(Date value) {
            addCriterion("org_createdtime >", value, "orgCreatedtime");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("org_createdtime >=", value, "orgCreatedtime");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedtimeLessThan(Date value) {
            addCriterion("org_createdtime <", value, "orgCreatedtime");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedtimeLessThanOrEqualTo(Date value) {
            addCriterion("org_createdtime <=", value, "orgCreatedtime");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedtimeIn(List<Date> values) {
            addCriterion("org_createdtime in", values, "orgCreatedtime");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedtimeNotIn(List<Date> values) {
            addCriterion("org_createdtime not in", values, "orgCreatedtime");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedtimeBetween(Date value1, Date value2) {
            addCriterion("org_createdtime between", value1, value2, "orgCreatedtime");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedtimeNotBetween(Date value1, Date value2) {
            addCriterion("org_createdtime not between", value1, value2, "orgCreatedtime");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionIsNull() {
            addCriterion("org_description is null");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionIsNotNull() {
            addCriterion("org_description is not null");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionEqualTo(String value) {
            addCriterion("org_description =", value, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionNotEqualTo(String value) {
            addCriterion("org_description <>", value, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionGreaterThan(String value) {
            addCriterion("org_description >", value, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("org_description >=", value, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionLessThan(String value) {
            addCriterion("org_description <", value, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionLessThanOrEqualTo(String value) {
            addCriterion("org_description <=", value, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionLike(String value) {
            addCriterion("org_description like", value, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionNotLike(String value) {
            addCriterion("org_description not like", value, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionIn(List<String> values) {
            addCriterion("org_description in", values, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionNotIn(List<String> values) {
            addCriterion("org_description not in", values, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionBetween(String value1, String value2) {
            addCriterion("org_description between", value1, value2, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionNotBetween(String value1, String value2) {
            addCriterion("org_description not between", value1, value2, "orgDescription");
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