package com.njtech.smartuniversity.bean;

import java.util.ArrayList;
import java.util.List;

public class AuthorityBeanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AuthorityBeanExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAuNameIsNull() {
            addCriterion("au_name is null");
            return (Criteria) this;
        }

        public Criteria andAuNameIsNotNull() {
            addCriterion("au_name is not null");
            return (Criteria) this;
        }

        public Criteria andAuNameEqualTo(String value) {
            addCriterion("au_name =", value, "auName");
            return (Criteria) this;
        }

        public Criteria andAuNameNotEqualTo(String value) {
            addCriterion("au_name <>", value, "auName");
            return (Criteria) this;
        }

        public Criteria andAuNameGreaterThan(String value) {
            addCriterion("au_name >", value, "auName");
            return (Criteria) this;
        }

        public Criteria andAuNameGreaterThanOrEqualTo(String value) {
            addCriterion("au_name >=", value, "auName");
            return (Criteria) this;
        }

        public Criteria andAuNameLessThan(String value) {
            addCriterion("au_name <", value, "auName");
            return (Criteria) this;
        }

        public Criteria andAuNameLessThanOrEqualTo(String value) {
            addCriterion("au_name <=", value, "auName");
            return (Criteria) this;
        }

        public Criteria andAuNameLike(String value) {
            addCriterion("au_name like", value, "auName");
            return (Criteria) this;
        }

        public Criteria andAuNameNotLike(String value) {
            addCriterion("au_name not like", value, "auName");
            return (Criteria) this;
        }

        public Criteria andAuNameIn(List<String> values) {
            addCriterion("au_name in", values, "auName");
            return (Criteria) this;
        }

        public Criteria andAuNameNotIn(List<String> values) {
            addCriterion("au_name not in", values, "auName");
            return (Criteria) this;
        }

        public Criteria andAuNameBetween(String value1, String value2) {
            addCriterion("au_name between", value1, value2, "auName");
            return (Criteria) this;
        }

        public Criteria andAuNameNotBetween(String value1, String value2) {
            addCriterion("au_name not between", value1, value2, "auName");
            return (Criteria) this;
        }

        public Criteria andAuDetailsIsNull() {
            addCriterion("au_details is null");
            return (Criteria) this;
        }

        public Criteria andAuDetailsIsNotNull() {
            addCriterion("au_details is not null");
            return (Criteria) this;
        }

        public Criteria andAuDetailsEqualTo(String value) {
            addCriterion("au_details =", value, "auDetails");
            return (Criteria) this;
        }

        public Criteria andAuDetailsNotEqualTo(String value) {
            addCriterion("au_details <>", value, "auDetails");
            return (Criteria) this;
        }

        public Criteria andAuDetailsGreaterThan(String value) {
            addCriterion("au_details >", value, "auDetails");
            return (Criteria) this;
        }

        public Criteria andAuDetailsGreaterThanOrEqualTo(String value) {
            addCriterion("au_details >=", value, "auDetails");
            return (Criteria) this;
        }

        public Criteria andAuDetailsLessThan(String value) {
            addCriterion("au_details <", value, "auDetails");
            return (Criteria) this;
        }

        public Criteria andAuDetailsLessThanOrEqualTo(String value) {
            addCriterion("au_details <=", value, "auDetails");
            return (Criteria) this;
        }

        public Criteria andAuDetailsLike(String value) {
            addCriterion("au_details like", value, "auDetails");
            return (Criteria) this;
        }

        public Criteria andAuDetailsNotLike(String value) {
            addCriterion("au_details not like", value, "auDetails");
            return (Criteria) this;
        }

        public Criteria andAuDetailsIn(List<String> values) {
            addCriterion("au_details in", values, "auDetails");
            return (Criteria) this;
        }

        public Criteria andAuDetailsNotIn(List<String> values) {
            addCriterion("au_details not in", values, "auDetails");
            return (Criteria) this;
        }

        public Criteria andAuDetailsBetween(String value1, String value2) {
            addCriterion("au_details between", value1, value2, "auDetails");
            return (Criteria) this;
        }

        public Criteria andAuDetailsNotBetween(String value1, String value2) {
            addCriterion("au_details not between", value1, value2, "auDetails");
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