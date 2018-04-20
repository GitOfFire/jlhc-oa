package com.jlhc.sell.dto.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TaskExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public TaskExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andTaskContentIsNull() {
            addCriterion("task_content is null");
            return (Criteria) this;
        }

        public Criteria andTaskContentIsNotNull() {
            addCriterion("task_content is not null");
            return (Criteria) this;
        }

        public Criteria andTaskContentEqualTo(String value) {
            addCriterion("task_content =", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentNotEqualTo(String value) {
            addCriterion("task_content <>", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentGreaterThan(String value) {
            addCriterion("task_content >", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentGreaterThanOrEqualTo(String value) {
            addCriterion("task_content >=", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentLessThan(String value) {
            addCriterion("task_content <", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentLessThanOrEqualTo(String value) {
            addCriterion("task_content <=", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentLike(String value) {
            addCriterion("task_content like", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentNotLike(String value) {
            addCriterion("task_content not like", value, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentIn(List<String> values) {
            addCriterion("task_content in", values, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentNotIn(List<String> values) {
            addCriterion("task_content not in", values, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentBetween(String value1, String value2) {
            addCriterion("task_content between", value1, value2, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskContentNotBetween(String value1, String value2) {
            addCriterion("task_content not between", value1, value2, "taskContent");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedUserIsNull() {
            addCriterion("task_created_user is null");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedUserIsNotNull() {
            addCriterion("task_created_user is not null");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedUserEqualTo(Integer value) {
            addCriterion("task_created_user =", value, "taskCreatedUser");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedUserNotEqualTo(Integer value) {
            addCriterion("task_created_user <>", value, "taskCreatedUser");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedUserGreaterThan(Integer value) {
            addCriterion("task_created_user >", value, "taskCreatedUser");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_created_user >=", value, "taskCreatedUser");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedUserLessThan(Integer value) {
            addCriterion("task_created_user <", value, "taskCreatedUser");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedUserLessThanOrEqualTo(Integer value) {
            addCriterion("task_created_user <=", value, "taskCreatedUser");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedUserIn(List<Integer> values) {
            addCriterion("task_created_user in", values, "taskCreatedUser");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedUserNotIn(List<Integer> values) {
            addCriterion("task_created_user not in", values, "taskCreatedUser");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedUserBetween(Integer value1, Integer value2) {
            addCriterion("task_created_user between", value1, value2, "taskCreatedUser");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedUserNotBetween(Integer value1, Integer value2) {
            addCriterion("task_created_user not between", value1, value2, "taskCreatedUser");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedTimeIsNull() {
            addCriterion("task_created_time is null");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedTimeIsNotNull() {
            addCriterion("task_created_time is not null");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedTimeEqualTo(Date value) {
            addCriterionForJDBCDate("task_created_time =", value, "taskCreatedTime");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("task_created_time <>", value, "taskCreatedTime");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("task_created_time >", value, "taskCreatedTime");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("task_created_time >=", value, "taskCreatedTime");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedTimeLessThan(Date value) {
            addCriterionForJDBCDate("task_created_time <", value, "taskCreatedTime");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("task_created_time <=", value, "taskCreatedTime");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedTimeIn(List<Date> values) {
            addCriterionForJDBCDate("task_created_time in", values, "taskCreatedTime");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("task_created_time not in", values, "taskCreatedTime");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("task_created_time between", value1, value2, "taskCreatedTime");
            return (Criteria) this;
        }

        public Criteria andTaskCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("task_created_time not between", value1, value2, "taskCreatedTime");
            return (Criteria) this;
        }

        public Criteria andHoldUserIdIsNull() {
            addCriterion("hold_user_id is null");
            return (Criteria) this;
        }

        public Criteria andHoldUserIdIsNotNull() {
            addCriterion("hold_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andHoldUserIdEqualTo(Integer value) {
            addCriterion("hold_user_id =", value, "holdUserId");
            return (Criteria) this;
        }

        public Criteria andHoldUserIdNotEqualTo(Integer value) {
            addCriterion("hold_user_id <>", value, "holdUserId");
            return (Criteria) this;
        }

        public Criteria andHoldUserIdGreaterThan(Integer value) {
            addCriterion("hold_user_id >", value, "holdUserId");
            return (Criteria) this;
        }

        public Criteria andHoldUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("hold_user_id >=", value, "holdUserId");
            return (Criteria) this;
        }

        public Criteria andHoldUserIdLessThan(Integer value) {
            addCriterion("hold_user_id <", value, "holdUserId");
            return (Criteria) this;
        }

        public Criteria andHoldUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("hold_user_id <=", value, "holdUserId");
            return (Criteria) this;
        }

        public Criteria andHoldUserIdIn(List<Integer> values) {
            addCriterion("hold_user_id in", values, "holdUserId");
            return (Criteria) this;
        }

        public Criteria andHoldUserIdNotIn(List<Integer> values) {
            addCriterion("hold_user_id not in", values, "holdUserId");
            return (Criteria) this;
        }

        public Criteria andHoldUserIdBetween(Integer value1, Integer value2) {
            addCriterion("hold_user_id between", value1, value2, "holdUserId");
            return (Criteria) this;
        }

        public Criteria andHoldUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("hold_user_id not between", value1, value2, "holdUserId");
            return (Criteria) this;
        }

        public Criteria andTaskStateIsNull() {
            addCriterion("task_state is null");
            return (Criteria) this;
        }

        public Criteria andTaskStateIsNotNull() {
            addCriterion("task_state is not null");
            return (Criteria) this;
        }

        public Criteria andTaskStateEqualTo(Integer value) {
            addCriterion("task_state =", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateNotEqualTo(Integer value) {
            addCriterion("task_state <>", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateGreaterThan(Integer value) {
            addCriterion("task_state >", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_state >=", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateLessThan(Integer value) {
            addCriterion("task_state <", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateLessThanOrEqualTo(Integer value) {
            addCriterion("task_state <=", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateIn(List<Integer> values) {
            addCriterion("task_state in", values, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateNotIn(List<Integer> values) {
            addCriterion("task_state not in", values, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateBetween(Integer value1, Integer value2) {
            addCriterion("task_state between", value1, value2, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateNotBetween(Integer value1, Integer value2) {
            addCriterion("task_state not between", value1, value2, "taskState");
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