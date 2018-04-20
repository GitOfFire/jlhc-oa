package com.jlhc.oa.dto.role.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author renzhong
 * @version 1.0
 * @Description
 * @Date: Created in 15:55 2018/1/22 0022
 */
public class RoleExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public RoleExample() {
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

        public Criteria andRoleIdIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(Integer value) {
            addCriterion("role_id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(Integer value) {
            addCriterion("role_id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(Integer value) {
            addCriterion("role_id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(Integer value) {
            addCriterion("role_id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(Integer value) {
            addCriterion("role_id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<Integer> values) {
            addCriterion("role_id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<Integer> values) {
            addCriterion("role_id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(Integer value1, Integer value2) {
            addCriterion("role_id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("role_id not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleGroupIdIsNull() {
            addCriterion("role_group_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleGroupIdIsNotNull() {
            addCriterion("role_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleGroupIdEqualTo(Integer value) {
            addCriterion("role_group_id =", value, "roleGroupId");
            return (Criteria) this;
        }

        public Criteria andRoleGroupIdNotEqualTo(Integer value) {
            addCriterion("role_group_id <>", value, "roleGroupId");
            return (Criteria) this;
        }

        public Criteria andRoleGroupIdGreaterThan(Integer value) {
            addCriterion("role_group_id >", value, "roleGroupId");
            return (Criteria) this;
        }

        public Criteria andRoleGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_group_id >=", value, "roleGroupId");
            return (Criteria) this;
        }

        public Criteria andRoleGroupIdLessThan(Integer value) {
            addCriterion("role_group_id <", value, "roleGroupId");
            return (Criteria) this;
        }

        public Criteria andRoleGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("role_group_id <=", value, "roleGroupId");
            return (Criteria) this;
        }

        public Criteria andRoleGroupIdIn(List<Integer> values) {
            addCriterion("role_group_id in", values, "roleGroupId");
            return (Criteria) this;
        }

        public Criteria andRoleGroupIdNotIn(List<Integer> values) {
            addCriterion("role_group_id not in", values, "roleGroupId");
            return (Criteria) this;
        }

        public Criteria andRoleGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("role_group_id between", value1, value2, "roleGroupId");
            return (Criteria) this;
        }

        public Criteria andRoleGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("role_group_id not between", value1, value2, "roleGroupId");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNull() {
            addCriterion("role_name is null");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNotNull() {
            addCriterion("role_name is not null");
            return (Criteria) this;
        }

        public Criteria andRoleNameEqualTo(String value) {
            addCriterion("role_name =", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotEqualTo(String value) {
            addCriterion("role_name <>", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThan(String value) {
            addCriterion("role_name >", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("role_name >=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThan(String value) {
            addCriterion("role_name <", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThanOrEqualTo(String value) {
            addCriterion("role_name <=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLike(String value) {
            addCriterion("role_name like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotLike(String value) {
            addCriterion("role_name not like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameIn(List<String> values) {
            addCriterion("role_name in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotIn(List<String> values) {
            addCriterion("role_name not in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameBetween(String value1, String value2) {
            addCriterion("role_name between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotBetween(String value1, String value2) {
            addCriterion("role_name not between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleCreatedtimeIsNull() {
            addCriterion("role_createdtime is null");
            return (Criteria) this;
        }

        public Criteria andRoleCreatedtimeIsNotNull() {
            addCriterion("role_createdtime is not null");
            return (Criteria) this;
        }

        public Criteria andRoleCreatedtimeEqualTo(Date value) {
            addCriterion("role_createdtime =", value, "roleCreatedtime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatedtimeNotEqualTo(Date value) {
            addCriterion("role_createdtime <>", value, "roleCreatedtime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatedtimeGreaterThan(Date value) {
            addCriterion("role_createdtime >", value, "roleCreatedtime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatedtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("role_createdtime >=", value, "roleCreatedtime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatedtimeLessThan(Date value) {
            addCriterion("role_createdtime <", value, "roleCreatedtime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatedtimeLessThanOrEqualTo(Date value) {
            addCriterion("role_createdtime <=", value, "roleCreatedtime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatedtimeIn(List<Date> values) {
            addCriterion("role_createdtime in", values, "roleCreatedtime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatedtimeNotIn(List<Date> values) {
            addCriterion("role_createdtime not in", values, "roleCreatedtime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatedtimeBetween(Date value1, Date value2) {
            addCriterion("role_createdtime between", value1, value2, "roleCreatedtime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatedtimeNotBetween(Date value1, Date value2) {
            addCriterion("role_createdtime not between", value1, value2, "roleCreatedtime");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionIsNull() {
            addCriterion("role_description is null");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionIsNotNull() {
            addCriterion("role_description is not null");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionEqualTo(String value) {
            addCriterion("role_description =", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionNotEqualTo(String value) {
            addCriterion("role_description <>", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionGreaterThan(String value) {
            addCriterion("role_description >", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("role_description >=", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionLessThan(String value) {
            addCriterion("role_description <", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionLessThanOrEqualTo(String value) {
            addCriterion("role_description <=", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionLike(String value) {
            addCriterion("role_description like", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionNotLike(String value) {
            addCriterion("role_description not like", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionIn(List<String> values) {
            addCriterion("role_description in", values, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionNotIn(List<String> values) {
            addCriterion("role_description not in", values, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionBetween(String value1, String value2) {
            addCriterion("role_description between", value1, value2, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionNotBetween(String value1, String value2) {
            addCriterion("role_description not between", value1, value2, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDatarangeIsNull() {
            addCriterion("role_datarange is null");
            return (Criteria) this;
        }

        public Criteria andRoleDatarangeIsNotNull() {
            addCriterion("role_datarange is not null");
            return (Criteria) this;
        }

        public Criteria andRoleDatarangeEqualTo(Integer value) {
            addCriterion("role_datarange =", value, "roleDatarange");
            return (Criteria) this;
        }

        public Criteria andRoleDatarangeNotEqualTo(Integer value) {
            addCriterion("role_datarange <>", value, "roleDatarange");
            return (Criteria) this;
        }

        public Criteria andRoleDatarangeGreaterThan(Integer value) {
            addCriterion("role_datarange >", value, "roleDatarange");
            return (Criteria) this;
        }

        public Criteria andRoleDatarangeGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_datarange >=", value, "roleDatarange");
            return (Criteria) this;
        }

        public Criteria andRoleDatarangeLessThan(Integer value) {
            addCriterion("role_datarange <", value, "roleDatarange");
            return (Criteria) this;
        }

        public Criteria andRoleDatarangeLessThanOrEqualTo(Integer value) {
            addCriterion("role_datarange <=", value, "roleDatarange");
            return (Criteria) this;
        }

        public Criteria andRoleDatarangeIn(List<Integer> values) {
            addCriterion("role_datarange in", values, "roleDatarange");
            return (Criteria) this;
        }

        public Criteria andRoleDatarangeNotIn(List<Integer> values) {
            addCriterion("role_datarange not in", values, "roleDatarange");
            return (Criteria) this;
        }

        public Criteria andRoleDatarangeBetween(Integer value1, Integer value2) {
            addCriterion("role_datarange between", value1, value2, "roleDatarange");
            return (Criteria) this;
        }

        public Criteria andRoleDatarangeNotBetween(Integer value1, Integer value2) {
            addCriterion("role_datarange not between", value1, value2, "roleDatarange");
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

