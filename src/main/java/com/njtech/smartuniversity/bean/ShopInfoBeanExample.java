package com.njtech.smartuniversity.bean;

import java.util.ArrayList;
import java.util.List;

public class ShopInfoBeanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopInfoBeanExample() {
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

        public Criteria andUuidIsNull() {
            addCriterion("uuid is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(String value) {
            addCriterion("uuid =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(String value) {
            addCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(String value) {
            addCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(String value) {
            addCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(String value) {
            addCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(String value) {
            addCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(String value) {
            addCriterion("uuid like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotLike(String value) {
            addCriterion("uuid not like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<String> values) {
            addCriterion("uuid in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<String> values) {
            addCriterion("uuid not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(String value1, String value2) {
            addCriterion("uuid between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(String value1, String value2) {
            addCriterion("uuid not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("shop_name is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("shop_name <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("shop_name >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("shop_name <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("shop_name <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("shop_name not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("shop_name in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("shop_name not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("shop_name between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("shop_name not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopAddrIsNull() {
            addCriterion("shop_addr is null");
            return (Criteria) this;
        }

        public Criteria andShopAddrIsNotNull() {
            addCriterion("shop_addr is not null");
            return (Criteria) this;
        }

        public Criteria andShopAddrEqualTo(String value) {
            addCriterion("shop_addr =", value, "shopAddr");
            return (Criteria) this;
        }

        public Criteria andShopAddrNotEqualTo(String value) {
            addCriterion("shop_addr <>", value, "shopAddr");
            return (Criteria) this;
        }

        public Criteria andShopAddrGreaterThan(String value) {
            addCriterion("shop_addr >", value, "shopAddr");
            return (Criteria) this;
        }

        public Criteria andShopAddrGreaterThanOrEqualTo(String value) {
            addCriterion("shop_addr >=", value, "shopAddr");
            return (Criteria) this;
        }

        public Criteria andShopAddrLessThan(String value) {
            addCriterion("shop_addr <", value, "shopAddr");
            return (Criteria) this;
        }

        public Criteria andShopAddrLessThanOrEqualTo(String value) {
            addCriterion("shop_addr <=", value, "shopAddr");
            return (Criteria) this;
        }

        public Criteria andShopAddrLike(String value) {
            addCriterion("shop_addr like", value, "shopAddr");
            return (Criteria) this;
        }

        public Criteria andShopAddrNotLike(String value) {
            addCriterion("shop_addr not like", value, "shopAddr");
            return (Criteria) this;
        }

        public Criteria andShopAddrIn(List<String> values) {
            addCriterion("shop_addr in", values, "shopAddr");
            return (Criteria) this;
        }

        public Criteria andShopAddrNotIn(List<String> values) {
            addCriterion("shop_addr not in", values, "shopAddr");
            return (Criteria) this;
        }

        public Criteria andShopAddrBetween(String value1, String value2) {
            addCriterion("shop_addr between", value1, value2, "shopAddr");
            return (Criteria) this;
        }

        public Criteria andShopAddrNotBetween(String value1, String value2) {
            addCriterion("shop_addr not between", value1, value2, "shopAddr");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andUserUuidIsNull() {
            addCriterion("user_uuid is null");
            return (Criteria) this;
        }

        public Criteria andUserUuidIsNotNull() {
            addCriterion("user_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUserUuidEqualTo(String value) {
            addCriterion("user_uuid =", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidNotEqualTo(String value) {
            addCriterion("user_uuid <>", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidGreaterThan(String value) {
            addCriterion("user_uuid >", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidGreaterThanOrEqualTo(String value) {
            addCriterion("user_uuid >=", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidLessThan(String value) {
            addCriterion("user_uuid <", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidLessThanOrEqualTo(String value) {
            addCriterion("user_uuid <=", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidLike(String value) {
            addCriterion("user_uuid like", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidNotLike(String value) {
            addCriterion("user_uuid not like", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidIn(List<String> values) {
            addCriterion("user_uuid in", values, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidNotIn(List<String> values) {
            addCriterion("user_uuid not in", values, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidBetween(String value1, String value2) {
            addCriterion("user_uuid between", value1, value2, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidNotBetween(String value1, String value2) {
            addCriterion("user_uuid not between", value1, value2, "userUuid");
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