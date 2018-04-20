package com.jlhc.oa.dto.function.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FunctionExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public FunctionExample() {
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

        public Criteria andFuncIdIsNull() {
            addCriterion("func_id is null");
            return (Criteria) this;
        }

        public Criteria andFuncIdIsNotNull() {
            addCriterion("func_id is not null");
            return (Criteria) this;
        }

        public Criteria andFuncIdEqualTo(Integer value) {
            addCriterion("func_id =", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdNotEqualTo(Integer value) {
            addCriterion("func_id <>", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdGreaterThan(Integer value) {
            addCriterion("func_id >", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("func_id >=", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdLessThan(Integer value) {
            addCriterion("func_id <", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdLessThanOrEqualTo(Integer value) {
            addCriterion("func_id <=", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdIn(List<Integer> values) {
            addCriterion("func_id in", values, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdNotIn(List<Integer> values) {
            addCriterion("func_id not in", values, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdBetween(Integer value1, Integer value2) {
            addCriterion("func_id between", value1, value2, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdNotBetween(Integer value1, Integer value2) {
            addCriterion("func_id not between", value1, value2, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncNameIsNull() {
            addCriterion("func_name is null");
            return (Criteria) this;
        }

        public Criteria andFuncNameIsNotNull() {
            addCriterion("func_name is not null");
            return (Criteria) this;
        }

        public Criteria andFuncNameEqualTo(String value) {
            addCriterion("func_name =", value, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameNotEqualTo(String value) {
            addCriterion("func_name <>", value, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameGreaterThan(String value) {
            addCriterion("func_name >", value, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameGreaterThanOrEqualTo(String value) {
            addCriterion("func_name >=", value, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameLessThan(String value) {
            addCriterion("func_name <", value, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameLessThanOrEqualTo(String value) {
            addCriterion("func_name <=", value, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameLike(String value) {
            addCriterion("func_name like", value, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameNotLike(String value) {
            addCriterion("func_name not like", value, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameIn(List<String> values) {
            addCriterion("func_name in", values, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameNotIn(List<String> values) {
            addCriterion("func_name not in", values, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameBetween(String value1, String value2) {
            addCriterion("func_name between", value1, value2, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameNotBetween(String value1, String value2) {
            addCriterion("func_name not between", value1, value2, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncDescriptionIsNull() {
            addCriterion("func_description is null");
            return (Criteria) this;
        }

        public Criteria andFuncDescriptionIsNotNull() {
            addCriterion("func_description is not null");
            return (Criteria) this;
        }

        public Criteria andFuncDescriptionEqualTo(String value) {
            addCriterion("func_description =", value, "funcDescription");
            return (Criteria) this;
        }

        public Criteria andFuncDescriptionNotEqualTo(String value) {
            addCriterion("func_description <>", value, "funcDescription");
            return (Criteria) this;
        }

        public Criteria andFuncDescriptionGreaterThan(String value) {
            addCriterion("func_description >", value, "funcDescription");
            return (Criteria) this;
        }

        public Criteria andFuncDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("func_description >=", value, "funcDescription");
            return (Criteria) this;
        }

        public Criteria andFuncDescriptionLessThan(String value) {
            addCriterion("func_description <", value, "funcDescription");
            return (Criteria) this;
        }

        public Criteria andFuncDescriptionLessThanOrEqualTo(String value) {
            addCriterion("func_description <=", value, "funcDescription");
            return (Criteria) this;
        }

        public Criteria andFuncDescriptionLike(String value) {
            addCriterion("func_description like", value, "funcDescription");
            return (Criteria) this;
        }

        public Criteria andFuncDescriptionNotLike(String value) {
            addCriterion("func_description not like", value, "funcDescription");
            return (Criteria) this;
        }

        public Criteria andFuncDescriptionIn(List<String> values) {
            addCriterion("func_description in", values, "funcDescription");
            return (Criteria) this;
        }

        public Criteria andFuncDescriptionNotIn(List<String> values) {
            addCriterion("func_description not in", values, "funcDescription");
            return (Criteria) this;
        }

        public Criteria andFuncDescriptionBetween(String value1, String value2) {
            addCriterion("func_description between", value1, value2, "funcDescription");
            return (Criteria) this;
        }

        public Criteria andFuncDescriptionNotBetween(String value1, String value2) {
            addCriterion("func_description not between", value1, value2, "funcDescription");
            return (Criteria) this;
        }

        public Criteria andModuleIdIsNull() {
            addCriterion("module_id is null");
            return (Criteria) this;
        }

        public Criteria andModuleIdIsNotNull() {
            addCriterion("module_id is not null");
            return (Criteria) this;
        }

        public Criteria andModuleIdEqualTo(Integer value) {
            addCriterion("module_id =", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotEqualTo(Integer value) {
            addCriterion("module_id <>", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThan(Integer value) {
            addCriterion("module_id >", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("module_id >=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThan(Integer value) {
            addCriterion("module_id <", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("module_id <=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdIn(List<Integer> values) {
            addCriterion("module_id in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotIn(List<Integer> values) {
            addCriterion("module_id not in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdBetween(Integer value1, Integer value2) {
            addCriterion("module_id between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("module_id not between", value1, value2, "moduleId");
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