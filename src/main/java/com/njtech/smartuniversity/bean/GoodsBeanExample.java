package com.njtech.smartuniversity.bean;

import java.util.ArrayList;
import java.util.List;

public class GoodsBeanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsBeanExample() {
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

        public Criteria andGNameIsNull() {
            addCriterion("g_name is null");
            return (Criteria) this;
        }

        public Criteria andGNameIsNotNull() {
            addCriterion("g_name is not null");
            return (Criteria) this;
        }

        public Criteria andGNameEqualTo(String value) {
            addCriterion("g_name =", value, "gName");
            return (Criteria) this;
        }

        public Criteria andGNameNotEqualTo(String value) {
            addCriterion("g_name <>", value, "gName");
            return (Criteria) this;
        }

        public Criteria andGNameGreaterThan(String value) {
            addCriterion("g_name >", value, "gName");
            return (Criteria) this;
        }

        public Criteria andGNameGreaterThanOrEqualTo(String value) {
            addCriterion("g_name >=", value, "gName");
            return (Criteria) this;
        }

        public Criteria andGNameLessThan(String value) {
            addCriterion("g_name <", value, "gName");
            return (Criteria) this;
        }

        public Criteria andGNameLessThanOrEqualTo(String value) {
            addCriterion("g_name <=", value, "gName");
            return (Criteria) this;
        }

        public Criteria andGNameLike(String value) {
            addCriterion("g_name like", value, "gName");
            return (Criteria) this;
        }

        public Criteria andGNameNotLike(String value) {
            addCriterion("g_name not like", value, "gName");
            return (Criteria) this;
        }

        public Criteria andGNameIn(List<String> values) {
            addCriterion("g_name in", values, "gName");
            return (Criteria) this;
        }

        public Criteria andGNameNotIn(List<String> values) {
            addCriterion("g_name not in", values, "gName");
            return (Criteria) this;
        }

        public Criteria andGNameBetween(String value1, String value2) {
            addCriterion("g_name between", value1, value2, "gName");
            return (Criteria) this;
        }

        public Criteria andGNameNotBetween(String value1, String value2) {
            addCriterion("g_name not between", value1, value2, "gName");
            return (Criteria) this;
        }

        public Criteria andGPriceIsNull() {
            addCriterion("g_price is null");
            return (Criteria) this;
        }

        public Criteria andGPriceIsNotNull() {
            addCriterion("g_price is not null");
            return (Criteria) this;
        }

        public Criteria andGPriceEqualTo(String value) {
            addCriterion("g_price =", value, "gPrice");
            return (Criteria) this;
        }

        public Criteria andGPriceNotEqualTo(String value) {
            addCriterion("g_price <>", value, "gPrice");
            return (Criteria) this;
        }

        public Criteria andGPriceGreaterThan(String value) {
            addCriterion("g_price >", value, "gPrice");
            return (Criteria) this;
        }

        public Criteria andGPriceGreaterThanOrEqualTo(String value) {
            addCriterion("g_price >=", value, "gPrice");
            return (Criteria) this;
        }

        public Criteria andGPriceLessThan(String value) {
            addCriterion("g_price <", value, "gPrice");
            return (Criteria) this;
        }

        public Criteria andGPriceLessThanOrEqualTo(String value) {
            addCriterion("g_price <=", value, "gPrice");
            return (Criteria) this;
        }

        public Criteria andGPriceLike(String value) {
            addCriterion("g_price like", value, "gPrice");
            return (Criteria) this;
        }

        public Criteria andGPriceNotLike(String value) {
            addCriterion("g_price not like", value, "gPrice");
            return (Criteria) this;
        }

        public Criteria andGPriceIn(List<String> values) {
            addCriterion("g_price in", values, "gPrice");
            return (Criteria) this;
        }

        public Criteria andGPriceNotIn(List<String> values) {
            addCriterion("g_price not in", values, "gPrice");
            return (Criteria) this;
        }

        public Criteria andGPriceBetween(String value1, String value2) {
            addCriterion("g_price between", value1, value2, "gPrice");
            return (Criteria) this;
        }

        public Criteria andGPriceNotBetween(String value1, String value2) {
            addCriterion("g_price not between", value1, value2, "gPrice");
            return (Criteria) this;
        }

        public Criteria andGQuantityIsNull() {
            addCriterion("g_quantity is null");
            return (Criteria) this;
        }

        public Criteria andGQuantityIsNotNull() {
            addCriterion("g_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andGQuantityEqualTo(Integer value) {
            addCriterion("g_quantity =", value, "gQuantity");
            return (Criteria) this;
        }

        public Criteria andGQuantityNotEqualTo(Integer value) {
            addCriterion("g_quantity <>", value, "gQuantity");
            return (Criteria) this;
        }

        public Criteria andGQuantityGreaterThan(Integer value) {
            addCriterion("g_quantity >", value, "gQuantity");
            return (Criteria) this;
        }

        public Criteria andGQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("g_quantity >=", value, "gQuantity");
            return (Criteria) this;
        }

        public Criteria andGQuantityLessThan(Integer value) {
            addCriterion("g_quantity <", value, "gQuantity");
            return (Criteria) this;
        }

        public Criteria andGQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("g_quantity <=", value, "gQuantity");
            return (Criteria) this;
        }

        public Criteria andGQuantityIn(List<Integer> values) {
            addCriterion("g_quantity in", values, "gQuantity");
            return (Criteria) this;
        }

        public Criteria andGQuantityNotIn(List<Integer> values) {
            addCriterion("g_quantity not in", values, "gQuantity");
            return (Criteria) this;
        }

        public Criteria andGQuantityBetween(Integer value1, Integer value2) {
            addCriterion("g_quantity between", value1, value2, "gQuantity");
            return (Criteria) this;
        }

        public Criteria andGQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("g_quantity not between", value1, value2, "gQuantity");
            return (Criteria) this;
        }

        public Criteria andGLyIsNull() {
            addCriterion("g_ly is null");
            return (Criteria) this;
        }

        public Criteria andGLyIsNotNull() {
            addCriterion("g_ly is not null");
            return (Criteria) this;
        }

        public Criteria andGLyEqualTo(String value) {
            addCriterion("g_ly =", value, "gLy");
            return (Criteria) this;
        }

        public Criteria andGLyNotEqualTo(String value) {
            addCriterion("g_ly <>", value, "gLy");
            return (Criteria) this;
        }

        public Criteria andGLyGreaterThan(String value) {
            addCriterion("g_ly >", value, "gLy");
            return (Criteria) this;
        }

        public Criteria andGLyGreaterThanOrEqualTo(String value) {
            addCriterion("g_ly >=", value, "gLy");
            return (Criteria) this;
        }

        public Criteria andGLyLessThan(String value) {
            addCriterion("g_ly <", value, "gLy");
            return (Criteria) this;
        }

        public Criteria andGLyLessThanOrEqualTo(String value) {
            addCriterion("g_ly <=", value, "gLy");
            return (Criteria) this;
        }

        public Criteria andGLyLike(String value) {
            addCriterion("g_ly like", value, "gLy");
            return (Criteria) this;
        }

        public Criteria andGLyNotLike(String value) {
            addCriterion("g_ly not like", value, "gLy");
            return (Criteria) this;
        }

        public Criteria andGLyIn(List<String> values) {
            addCriterion("g_ly in", values, "gLy");
            return (Criteria) this;
        }

        public Criteria andGLyNotIn(List<String> values) {
            addCriterion("g_ly not in", values, "gLy");
            return (Criteria) this;
        }

        public Criteria andGLyBetween(String value1, String value2) {
            addCriterion("g_ly between", value1, value2, "gLy");
            return (Criteria) this;
        }

        public Criteria andGLyNotBetween(String value1, String value2) {
            addCriterion("g_ly not between", value1, value2, "gLy");
            return (Criteria) this;
        }

        public Criteria andGVidIsNull() {
            addCriterion("g_vid is null");
            return (Criteria) this;
        }

        public Criteria andGVidIsNotNull() {
            addCriterion("g_vid is not null");
            return (Criteria) this;
        }

        public Criteria andGVidEqualTo(String value) {
            addCriterion("g_vid =", value, "gVid");
            return (Criteria) this;
        }

        public Criteria andGVidNotEqualTo(String value) {
            addCriterion("g_vid <>", value, "gVid");
            return (Criteria) this;
        }

        public Criteria andGVidGreaterThan(String value) {
            addCriterion("g_vid >", value, "gVid");
            return (Criteria) this;
        }

        public Criteria andGVidGreaterThanOrEqualTo(String value) {
            addCriterion("g_vid >=", value, "gVid");
            return (Criteria) this;
        }

        public Criteria andGVidLessThan(String value) {
            addCriterion("g_vid <", value, "gVid");
            return (Criteria) this;
        }

        public Criteria andGVidLessThanOrEqualTo(String value) {
            addCriterion("g_vid <=", value, "gVid");
            return (Criteria) this;
        }

        public Criteria andGVidLike(String value) {
            addCriterion("g_vid like", value, "gVid");
            return (Criteria) this;
        }

        public Criteria andGVidNotLike(String value) {
            addCriterion("g_vid not like", value, "gVid");
            return (Criteria) this;
        }

        public Criteria andGVidIn(List<String> values) {
            addCriterion("g_vid in", values, "gVid");
            return (Criteria) this;
        }

        public Criteria andGVidNotIn(List<String> values) {
            addCriterion("g_vid not in", values, "gVid");
            return (Criteria) this;
        }

        public Criteria andGVidBetween(String value1, String value2) {
            addCriterion("g_vid between", value1, value2, "gVid");
            return (Criteria) this;
        }

        public Criteria andGVidNotBetween(String value1, String value2) {
            addCriterion("g_vid not between", value1, value2, "gVid");
            return (Criteria) this;
        }

        public Criteria andGImgIsNull() {
            addCriterion("g_img is null");
            return (Criteria) this;
        }

        public Criteria andGImgIsNotNull() {
            addCriterion("g_img is not null");
            return (Criteria) this;
        }

        public Criteria andGImgEqualTo(String value) {
            addCriterion("g_img =", value, "gImg");
            return (Criteria) this;
        }

        public Criteria andGImgNotEqualTo(String value) {
            addCriterion("g_img <>", value, "gImg");
            return (Criteria) this;
        }

        public Criteria andGImgGreaterThan(String value) {
            addCriterion("g_img >", value, "gImg");
            return (Criteria) this;
        }

        public Criteria andGImgGreaterThanOrEqualTo(String value) {
            addCriterion("g_img >=", value, "gImg");
            return (Criteria) this;
        }

        public Criteria andGImgLessThan(String value) {
            addCriterion("g_img <", value, "gImg");
            return (Criteria) this;
        }

        public Criteria andGImgLessThanOrEqualTo(String value) {
            addCriterion("g_img <=", value, "gImg");
            return (Criteria) this;
        }

        public Criteria andGImgLike(String value) {
            addCriterion("g_img like", value, "gImg");
            return (Criteria) this;
        }

        public Criteria andGImgNotLike(String value) {
            addCriterion("g_img not like", value, "gImg");
            return (Criteria) this;
        }

        public Criteria andGImgIn(List<String> values) {
            addCriterion("g_img in", values, "gImg");
            return (Criteria) this;
        }

        public Criteria andGImgNotIn(List<String> values) {
            addCriterion("g_img not in", values, "gImg");
            return (Criteria) this;
        }

        public Criteria andGImgBetween(String value1, String value2) {
            addCriterion("g_img between", value1, value2, "gImg");
            return (Criteria) this;
        }

        public Criteria andGImgNotBetween(String value1, String value2) {
            addCriterion("g_img not between", value1, value2, "gImg");
            return (Criteria) this;
        }

        public Criteria andGSimgIsNull() {
            addCriterion("g_simg is null");
            return (Criteria) this;
        }

        public Criteria andGSimgIsNotNull() {
            addCriterion("g_simg is not null");
            return (Criteria) this;
        }

        public Criteria andGSimgEqualTo(String value) {
            addCriterion("g_simg =", value, "gSimg");
            return (Criteria) this;
        }

        public Criteria andGSimgNotEqualTo(String value) {
            addCriterion("g_simg <>", value, "gSimg");
            return (Criteria) this;
        }

        public Criteria andGSimgGreaterThan(String value) {
            addCriterion("g_simg >", value, "gSimg");
            return (Criteria) this;
        }

        public Criteria andGSimgGreaterThanOrEqualTo(String value) {
            addCriterion("g_simg >=", value, "gSimg");
            return (Criteria) this;
        }

        public Criteria andGSimgLessThan(String value) {
            addCriterion("g_simg <", value, "gSimg");
            return (Criteria) this;
        }

        public Criteria andGSimgLessThanOrEqualTo(String value) {
            addCriterion("g_simg <=", value, "gSimg");
            return (Criteria) this;
        }

        public Criteria andGSimgLike(String value) {
            addCriterion("g_simg like", value, "gSimg");
            return (Criteria) this;
        }

        public Criteria andGSimgNotLike(String value) {
            addCriterion("g_simg not like", value, "gSimg");
            return (Criteria) this;
        }

        public Criteria andGSimgIn(List<String> values) {
            addCriterion("g_simg in", values, "gSimg");
            return (Criteria) this;
        }

        public Criteria andGSimgNotIn(List<String> values) {
            addCriterion("g_simg not in", values, "gSimg");
            return (Criteria) this;
        }

        public Criteria andGSimgBetween(String value1, String value2) {
            addCriterion("g_simg between", value1, value2, "gSimg");
            return (Criteria) this;
        }

        public Criteria andGSimgNotBetween(String value1, String value2) {
            addCriterion("g_simg not between", value1, value2, "gSimg");
            return (Criteria) this;
        }

        public Criteria andGLabelIsNull() {
            addCriterion("g_label is null");
            return (Criteria) this;
        }

        public Criteria andGLabelIsNotNull() {
            addCriterion("g_label is not null");
            return (Criteria) this;
        }

        public Criteria andGLabelEqualTo(String value) {
            addCriterion("g_label =", value, "gLabel");
            return (Criteria) this;
        }

        public Criteria andGLabelNotEqualTo(String value) {
            addCriterion("g_label <>", value, "gLabel");
            return (Criteria) this;
        }

        public Criteria andGLabelGreaterThan(String value) {
            addCriterion("g_label >", value, "gLabel");
            return (Criteria) this;
        }

        public Criteria andGLabelGreaterThanOrEqualTo(String value) {
            addCriterion("g_label >=", value, "gLabel");
            return (Criteria) this;
        }

        public Criteria andGLabelLessThan(String value) {
            addCriterion("g_label <", value, "gLabel");
            return (Criteria) this;
        }

        public Criteria andGLabelLessThanOrEqualTo(String value) {
            addCriterion("g_label <=", value, "gLabel");
            return (Criteria) this;
        }

        public Criteria andGLabelLike(String value) {
            addCriterion("g_label like", value, "gLabel");
            return (Criteria) this;
        }

        public Criteria andGLabelNotLike(String value) {
            addCriterion("g_label not like", value, "gLabel");
            return (Criteria) this;
        }

        public Criteria andGLabelIn(List<String> values) {
            addCriterion("g_label in", values, "gLabel");
            return (Criteria) this;
        }

        public Criteria andGLabelNotIn(List<String> values) {
            addCriterion("g_label not in", values, "gLabel");
            return (Criteria) this;
        }

        public Criteria andGLabelBetween(String value1, String value2) {
            addCriterion("g_label between", value1, value2, "gLabel");
            return (Criteria) this;
        }

        public Criteria andGLabelNotBetween(String value1, String value2) {
            addCriterion("g_label not between", value1, value2, "gLabel");
            return (Criteria) this;
        }

        public Criteria andGDetailsIsNull() {
            addCriterion("g_details is null");
            return (Criteria) this;
        }

        public Criteria andGDetailsIsNotNull() {
            addCriterion("g_details is not null");
            return (Criteria) this;
        }

        public Criteria andGDetailsEqualTo(String value) {
            addCriterion("g_details =", value, "gDetails");
            return (Criteria) this;
        }

        public Criteria andGDetailsNotEqualTo(String value) {
            addCriterion("g_details <>", value, "gDetails");
            return (Criteria) this;
        }

        public Criteria andGDetailsGreaterThan(String value) {
            addCriterion("g_details >", value, "gDetails");
            return (Criteria) this;
        }

        public Criteria andGDetailsGreaterThanOrEqualTo(String value) {
            addCriterion("g_details >=", value, "gDetails");
            return (Criteria) this;
        }

        public Criteria andGDetailsLessThan(String value) {
            addCriterion("g_details <", value, "gDetails");
            return (Criteria) this;
        }

        public Criteria andGDetailsLessThanOrEqualTo(String value) {
            addCriterion("g_details <=", value, "gDetails");
            return (Criteria) this;
        }

        public Criteria andGDetailsLike(String value) {
            addCriterion("g_details like", value, "gDetails");
            return (Criteria) this;
        }

        public Criteria andGDetailsNotLike(String value) {
            addCriterion("g_details not like", value, "gDetails");
            return (Criteria) this;
        }

        public Criteria andGDetailsIn(List<String> values) {
            addCriterion("g_details in", values, "gDetails");
            return (Criteria) this;
        }

        public Criteria andGDetailsNotIn(List<String> values) {
            addCriterion("g_details not in", values, "gDetails");
            return (Criteria) this;
        }

        public Criteria andGDetailsBetween(String value1, String value2) {
            addCriterion("g_details between", value1, value2, "gDetails");
            return (Criteria) this;
        }

        public Criteria andGDetailsNotBetween(String value1, String value2) {
            addCriterion("g_details not between", value1, value2, "gDetails");
            return (Criteria) this;
        }

        public Criteria andGStateIsNull() {
            addCriterion("g_state is null");
            return (Criteria) this;
        }

        public Criteria andGStateIsNotNull() {
            addCriterion("g_state is not null");
            return (Criteria) this;
        }

        public Criteria andGStateEqualTo(String value) {
            addCriterion("g_state =", value, "gState");
            return (Criteria) this;
        }

        public Criteria andGStateNotEqualTo(String value) {
            addCriterion("g_state <>", value, "gState");
            return (Criteria) this;
        }

        public Criteria andGStateGreaterThan(String value) {
            addCriterion("g_state >", value, "gState");
            return (Criteria) this;
        }

        public Criteria andGStateGreaterThanOrEqualTo(String value) {
            addCriterion("g_state >=", value, "gState");
            return (Criteria) this;
        }

        public Criteria andGStateLessThan(String value) {
            addCriterion("g_state <", value, "gState");
            return (Criteria) this;
        }

        public Criteria andGStateLessThanOrEqualTo(String value) {
            addCriterion("g_state <=", value, "gState");
            return (Criteria) this;
        }

        public Criteria andGStateLike(String value) {
            addCriterion("g_state like", value, "gState");
            return (Criteria) this;
        }

        public Criteria andGStateNotLike(String value) {
            addCriterion("g_state not like", value, "gState");
            return (Criteria) this;
        }

        public Criteria andGStateIn(List<String> values) {
            addCriterion("g_state in", values, "gState");
            return (Criteria) this;
        }

        public Criteria andGStateNotIn(List<String> values) {
            addCriterion("g_state not in", values, "gState");
            return (Criteria) this;
        }

        public Criteria andGStateBetween(String value1, String value2) {
            addCriterion("g_state between", value1, value2, "gState");
            return (Criteria) this;
        }

        public Criteria andGStateNotBetween(String value1, String value2) {
            addCriterion("g_state not between", value1, value2, "gState");
            return (Criteria) this;
        }

        public Criteria andSIUuidIsNull() {
            addCriterion("s_i_uuid is null");
            return (Criteria) this;
        }

        public Criteria andSIUuidIsNotNull() {
            addCriterion("s_i_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andSIUuidEqualTo(String value) {
            addCriterion("s_i_uuid =", value, "sIUuid");
            return (Criteria) this;
        }

        public Criteria andSIUuidNotEqualTo(String value) {
            addCriterion("s_i_uuid <>", value, "sIUuid");
            return (Criteria) this;
        }

        public Criteria andSIUuidGreaterThan(String value) {
            addCriterion("s_i_uuid >", value, "sIUuid");
            return (Criteria) this;
        }

        public Criteria andSIUuidGreaterThanOrEqualTo(String value) {
            addCriterion("s_i_uuid >=", value, "sIUuid");
            return (Criteria) this;
        }

        public Criteria andSIUuidLessThan(String value) {
            addCriterion("s_i_uuid <", value, "sIUuid");
            return (Criteria) this;
        }

        public Criteria andSIUuidLessThanOrEqualTo(String value) {
            addCriterion("s_i_uuid <=", value, "sIUuid");
            return (Criteria) this;
        }

        public Criteria andSIUuidLike(String value) {
            addCriterion("s_i_uuid like", value, "sIUuid");
            return (Criteria) this;
        }

        public Criteria andSIUuidNotLike(String value) {
            addCriterion("s_i_uuid not like", value, "sIUuid");
            return (Criteria) this;
        }

        public Criteria andSIUuidIn(List<String> values) {
            addCriterion("s_i_uuid in", values, "sIUuid");
            return (Criteria) this;
        }

        public Criteria andSIUuidNotIn(List<String> values) {
            addCriterion("s_i_uuid not in", values, "sIUuid");
            return (Criteria) this;
        }

        public Criteria andSIUuidBetween(String value1, String value2) {
            addCriterion("s_i_uuid between", value1, value2, "sIUuid");
            return (Criteria) this;
        }

        public Criteria andSIUuidNotBetween(String value1, String value2) {
            addCriterion("s_i_uuid not between", value1, value2, "sIUuid");
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