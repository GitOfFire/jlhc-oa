package com.jlhc.sell.dto.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class NoteExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public NoteExample() {
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

        public Criteria andNoteIdIsNull() {
            addCriterion("note_id is null");
            return (Criteria) this;
        }

        public Criteria andNoteIdIsNotNull() {
            addCriterion("note_id is not null");
            return (Criteria) this;
        }

        public Criteria andNoteIdEqualTo(String value) {
            addCriterion("note_id =", value, "noteId");
            return (Criteria) this;
        }

        public Criteria andNoteIdNotEqualTo(String value) {
            addCriterion("note_id <>", value, "noteId");
            return (Criteria) this;
        }

        public Criteria andNoteIdGreaterThan(String value) {
            addCriterion("note_id >", value, "noteId");
            return (Criteria) this;
        }

        public Criteria andNoteIdGreaterThanOrEqualTo(String value) {
            addCriterion("note_id >=", value, "noteId");
            return (Criteria) this;
        }

        public Criteria andNoteIdLessThan(String value) {
            addCriterion("note_id <", value, "noteId");
            return (Criteria) this;
        }

        public Criteria andNoteIdLessThanOrEqualTo(String value) {
            addCriterion("note_id <=", value, "noteId");
            return (Criteria) this;
        }

        public Criteria andNoteIdLike(String value) {
            addCriterion("note_id like", value, "noteId");
            return (Criteria) this;
        }

        public Criteria andNoteIdNotLike(String value) {
            addCriterion("note_id not like", value, "noteId");
            return (Criteria) this;
        }

        public Criteria andNoteIdIn(List<String> values) {
            addCriterion("note_id in", values, "noteId");
            return (Criteria) this;
        }

        public Criteria andNoteIdNotIn(List<String> values) {
            addCriterion("note_id not in", values, "noteId");
            return (Criteria) this;
        }

        public Criteria andNoteIdBetween(String value1, String value2) {
            addCriterion("note_id between", value1, value2, "noteId");
            return (Criteria) this;
        }

        public Criteria andNoteIdNotBetween(String value1, String value2) {
            addCriterion("note_id not between", value1, value2, "noteId");
            return (Criteria) this;
        }

        public Criteria andNoteContentIsNull() {
            addCriterion("note_content is null");
            return (Criteria) this;
        }

        public Criteria andNoteContentIsNotNull() {
            addCriterion("note_content is not null");
            return (Criteria) this;
        }

        public Criteria andNoteContentEqualTo(String value) {
            addCriterion("note_content =", value, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentNotEqualTo(String value) {
            addCriterion("note_content <>", value, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentGreaterThan(String value) {
            addCriterion("note_content >", value, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentGreaterThanOrEqualTo(String value) {
            addCriterion("note_content >=", value, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentLessThan(String value) {
            addCriterion("note_content <", value, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentLessThanOrEqualTo(String value) {
            addCriterion("note_content <=", value, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentLike(String value) {
            addCriterion("note_content like", value, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentNotLike(String value) {
            addCriterion("note_content not like", value, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentIn(List<String> values) {
            addCriterion("note_content in", values, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentNotIn(List<String> values) {
            addCriterion("note_content not in", values, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentBetween(String value1, String value2) {
            addCriterion("note_content between", value1, value2, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentNotBetween(String value1, String value2) {
            addCriterion("note_content not between", value1, value2, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteCreatedTimeIsNull() {
            addCriterion("note_created_time is null");
            return (Criteria) this;
        }

        public Criteria andNoteCreatedTimeIsNotNull() {
            addCriterion("note_created_time is not null");
            return (Criteria) this;
        }

        public Criteria andNoteCreatedTimeEqualTo(Date value) {
            addCriterionForJDBCDate("note_created_time =", value, "noteCreatedTime");
            return (Criteria) this;
        }

        public Criteria andNoteCreatedTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("note_created_time <>", value, "noteCreatedTime");
            return (Criteria) this;
        }

        public Criteria andNoteCreatedTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("note_created_time >", value, "noteCreatedTime");
            return (Criteria) this;
        }

        public Criteria andNoteCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("note_created_time >=", value, "noteCreatedTime");
            return (Criteria) this;
        }

        public Criteria andNoteCreatedTimeLessThan(Date value) {
            addCriterionForJDBCDate("note_created_time <", value, "noteCreatedTime");
            return (Criteria) this;
        }

        public Criteria andNoteCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("note_created_time <=", value, "noteCreatedTime");
            return (Criteria) this;
        }

        public Criteria andNoteCreatedTimeIn(List<Date> values) {
            addCriterionForJDBCDate("note_created_time in", values, "noteCreatedTime");
            return (Criteria) this;
        }

        public Criteria andNoteCreatedTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("note_created_time not in", values, "noteCreatedTime");
            return (Criteria) this;
        }

        public Criteria andNoteCreatedTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("note_created_time between", value1, value2, "noteCreatedTime");
            return (Criteria) this;
        }

        public Criteria andNoteCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("note_created_time not between", value1, value2, "noteCreatedTime");
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
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