package com.jlhc.sell.dto.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlowExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public FlowExample() {
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

        public Criteria andFlowIdIsNull() {
            addCriterion("flow_id is null");
            return (Criteria) this;
        }

        public Criteria andFlowIdIsNotNull() {
            addCriterion("flow_id is not null");
            return (Criteria) this;
        }

        public Criteria andFlowIdEqualTo(String value) {
            addCriterion("flow_id =", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdNotEqualTo(String value) {
            addCriterion("flow_id <>", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdGreaterThan(String value) {
            addCriterion("flow_id >", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdGreaterThanOrEqualTo(String value) {
            addCriterion("flow_id >=", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdLessThan(String value) {
            addCriterion("flow_id <", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdLessThanOrEqualTo(String value) {
            addCriterion("flow_id <=", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdLike(String value) {
            addCriterion("flow_id like", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdNotLike(String value) {
            addCriterion("flow_id not like", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdIn(List<String> values) {
            addCriterion("flow_id in", values, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdNotIn(List<String> values) {
            addCriterion("flow_id not in", values, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdBetween(String value1, String value2) {
            addCriterion("flow_id between", value1, value2, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdNotBetween(String value1, String value2) {
            addCriterion("flow_id not between", value1, value2, "flowId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(String value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(String value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(String value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(String value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(String value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(String value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLike(String value) {
            addCriterion("task_id like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotLike(String value) {
            addCriterion("task_id not like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<String> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<String> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(String value1, String value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(String value1, String value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andflowTypeIsNull() {
            addCriterion("flow_type is null");
            return (Criteria) this;
        }

        public Criteria andflowTypeIsNotNull() {
            addCriterion("flow_type is not null");
            return (Criteria) this;
        }

        public Criteria andflowTypeEqualTo(Integer value) {
            addCriterion("flow_type =", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andflowTypeNotEqualTo(Integer value) {
            addCriterion("flow_type <>", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andflowTypeGreaterThan(Integer value) {
            addCriterion("flow_type >", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andflowTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("flow_type >=", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andflowTypeLessThan(Integer value) {
            addCriterion("flow_type <", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andflowTypeLessThanOrEqualTo(Integer value) {
            addCriterion("flow_type <=", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andflowTypeIn(List<Integer> values) {
            addCriterion("flow_type in", values, "flowType");
            return (Criteria) this;
        }

        public Criteria andflowTypeNotIn(List<Integer> values) {
            addCriterion("flow_type not in", values, "flowType");
            return (Criteria) this;
        }

        public Criteria andflowTypeBetween(Integer value1, Integer value2) {
            addCriterion("flow_type between", value1, value2, "flowType");
            return (Criteria) this;
        }

        public Criteria andflowTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("flow_type not between", value1, value2, "flowType");
            return (Criteria) this;
        }

        public Criteria andflowDescriptionIsNull() {
            addCriterion("flow_description is null");
            return (Criteria) this;
        }

        public Criteria andflowDescriptionIsNotNull() {
            addCriterion("flow_description is not null");
            return (Criteria) this;
        }

        public Criteria andflowDescriptionEqualTo(String value) {
            addCriterion("flow_description =", value, "flowDescription");
            return (Criteria) this;
        }

        public Criteria andflowDescriptionNotEqualTo(String value) {
            addCriterion("flow_description <>", value, "flowDescription");
            return (Criteria) this;
        }

        public Criteria andflowDescriptionGreaterThan(String value) {
            addCriterion("flow_description >", value, "flowDescription");
            return (Criteria) this;
        }

        public Criteria andflowDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("flow_description >=", value, "flowDescription");
            return (Criteria) this;
        }

        public Criteria andflowDescriptionLessThan(String value) {
            addCriterion("flow_description <", value, "flowDescription");
            return (Criteria) this;
        }

        public Criteria andflowDescriptionLessThanOrEqualTo(String value) {
            addCriterion("flow_description <=", value, "flowDescription");
            return (Criteria) this;
        }

        public Criteria andflowDescriptionLike(String value) {
            addCriterion("flow_description like", value, "flowDescription");
            return (Criteria) this;
        }

        public Criteria andflowDescriptionNotLike(String value) {
            addCriterion("flow_description not like", value, "flowDescription");
            return (Criteria) this;
        }

        public Criteria andflowDescriptionIn(List<String> values) {
            addCriterion("flow_description in", values, "flowDescription");
            return (Criteria) this;
        }

        public Criteria andflowDescriptionNotIn(List<String> values) {
            addCriterion("flow_description not in", values, "flowDescription");
            return (Criteria) this;
        }

        public Criteria andflowDescriptionBetween(String value1, String value2) {
            addCriterion("flow_description between", value1, value2, "flowDescription");
            return (Criteria) this;
        }

        public Criteria andflowDescriptionNotBetween(String value1, String value2) {
            addCriterion("flow_description not between", value1, value2, "flowDescription");
            return (Criteria) this;
        }

        public Criteria andFlowTimeIsNull() {
            addCriterion("flow_time is null");
            return (Criteria) this;
        }

        public Criteria andFlowTimeIsNotNull() {
            addCriterion("flow_time is not null");
            return (Criteria) this;
        }

        public Criteria andFlowTimeEqualTo(Date value) {
            addCriterion("flow_time =", value, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeNotEqualTo(Date value) {
            addCriterion("flow_time <>", value, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeGreaterThan(Date value) {
            addCriterion("flow_time >", value, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("flow_time >=", value, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeLessThan(Date value) {
            addCriterion("flow_time <", value, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeLessThanOrEqualTo(Date value) {
            addCriterion("flow_time <=", value, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeIn(List<Date> values) {
            addCriterion("flow_time in", values, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeNotIn(List<Date> values) {
            addCriterion("flow_time not in", values, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeBetween(Date value1, Date value2) {
            addCriterion("flow_time between", value1, value2, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowTimeNotBetween(Date value1, Date value2) {
            addCriterion("flow_time not between", value1, value2, "flowTime");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdIsNull() {
            addCriterion("flow_user_id is null");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdIsNotNull() {
            addCriterion("flow_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdEqualTo(Integer value) {
            addCriterion("flow_user_id =", value, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdNotEqualTo(Integer value) {
            addCriterion("flow_user_id <>", value, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdGreaterThan(Integer value) {
            addCriterion("flow_user_id >", value, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("flow_user_id >=", value, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdLessThan(Integer value) {
            addCriterion("flow_user_id <", value, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("flow_user_id <=", value, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdIn(List<Integer> values) {
            addCriterion("flow_user_id in", values, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdNotIn(List<Integer> values) {
            addCriterion("flow_user_id not in", values, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdBetween(Integer value1, Integer value2) {
            addCriterion("flow_user_id between", value1, value2, "flowUserId");
            return (Criteria) this;
        }

        public Criteria andFlowUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("flow_user_id not between", value1, value2, "flowUserId");
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