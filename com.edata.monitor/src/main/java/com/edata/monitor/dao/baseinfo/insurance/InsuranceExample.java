package com.edata.monitor.dao.baseinfo.insurance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsuranceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InsuranceExample() {
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

        public Criteria andInsurancecompanyIsNull() {
            addCriterion("INSURANCECOMPANY is null");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanyIsNotNull() {
            addCriterion("INSURANCECOMPANY is not null");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanyEqualTo(String value) {
            addCriterion("INSURANCECOMPANY =", value, "insurancecompany");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanyNotEqualTo(String value) {
            addCriterion("INSURANCECOMPANY <>", value, "insurancecompany");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanyGreaterThan(String value) {
            addCriterion("INSURANCECOMPANY >", value, "insurancecompany");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanyGreaterThanOrEqualTo(String value) {
            addCriterion("INSURANCECOMPANY >=", value, "insurancecompany");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanyLessThan(String value) {
            addCriterion("INSURANCECOMPANY <", value, "insurancecompany");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanyLessThanOrEqualTo(String value) {
            addCriterion("INSURANCECOMPANY <=", value, "insurancecompany");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanyLike(String value) {
            addCriterion("INSURANCECOMPANY like", value, "insurancecompany");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanyNotLike(String value) {
            addCriterion("INSURANCECOMPANY not like", value, "insurancecompany");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanyIn(List<String> values) {
            addCriterion("INSURANCECOMPANY in", values, "insurancecompany");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanyNotIn(List<String> values) {
            addCriterion("INSURANCECOMPANY not in", values, "insurancecompany");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanyBetween(String value1, String value2) {
            addCriterion("INSURANCECOMPANY between", value1, value2, "insurancecompany");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanyNotBetween(String value1, String value2) {
            addCriterion("INSURANCECOMPANY not between", value1, value2, "insurancecompany");
            return (Criteria) this;
        }

        public Criteria andPolicynoIsNull() {
            addCriterion("POLICYNO is null");
            return (Criteria) this;
        }

        public Criteria andPolicynoIsNotNull() {
            addCriterion("POLICYNO is not null");
            return (Criteria) this;
        }

        public Criteria andPolicynoEqualTo(String value) {
            addCriterion("POLICYNO =", value, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoNotEqualTo(String value) {
            addCriterion("POLICYNO <>", value, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoGreaterThan(String value) {
            addCriterion("POLICYNO >", value, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoGreaterThanOrEqualTo(String value) {
            addCriterion("POLICYNO >=", value, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoLessThan(String value) {
            addCriterion("POLICYNO <", value, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoLessThanOrEqualTo(String value) {
            addCriterion("POLICYNO <=", value, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoLike(String value) {
            addCriterion("POLICYNO like", value, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoNotLike(String value) {
            addCriterion("POLICYNO not like", value, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoIn(List<String> values) {
            addCriterion("POLICYNO in", values, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoNotIn(List<String> values) {
            addCriterion("POLICYNO not in", values, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoBetween(String value1, String value2) {
            addCriterion("POLICYNO between", value1, value2, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoNotBetween(String value1, String value2) {
            addCriterion("POLICYNO not between", value1, value2, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicyexpirenotifydateIsNull() {
            addCriterion("POLICYEXPIRENOTIFYDATE is null");
            return (Criteria) this;
        }

        public Criteria andPolicyexpirenotifydateIsNotNull() {
            addCriterion("POLICYEXPIRENOTIFYDATE is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyexpirenotifydateEqualTo(Date value) {
            addCriterion("POLICYEXPIRENOTIFYDATE =", value, "policyexpirenotifydate");
            return (Criteria) this;
        }

        public Criteria andPolicyexpirenotifydateNotEqualTo(Date value) {
            addCriterion("POLICYEXPIRENOTIFYDATE <>", value, "policyexpirenotifydate");
            return (Criteria) this;
        }

        public Criteria andPolicyexpirenotifydateGreaterThan(Date value) {
            addCriterion("POLICYEXPIRENOTIFYDATE >", value, "policyexpirenotifydate");
            return (Criteria) this;
        }

        public Criteria andPolicyexpirenotifydateGreaterThanOrEqualTo(Date value) {
            addCriterion("POLICYEXPIRENOTIFYDATE >=", value, "policyexpirenotifydate");
            return (Criteria) this;
        }

        public Criteria andPolicyexpirenotifydateLessThan(Date value) {
            addCriterion("POLICYEXPIRENOTIFYDATE <", value, "policyexpirenotifydate");
            return (Criteria) this;
        }

        public Criteria andPolicyexpirenotifydateLessThanOrEqualTo(Date value) {
            addCriterion("POLICYEXPIRENOTIFYDATE <=", value, "policyexpirenotifydate");
            return (Criteria) this;
        }

        public Criteria andPolicyexpirenotifydateIn(List<Date> values) {
            addCriterion("POLICYEXPIRENOTIFYDATE in", values, "policyexpirenotifydate");
            return (Criteria) this;
        }

        public Criteria andPolicyexpirenotifydateNotIn(List<Date> values) {
            addCriterion("POLICYEXPIRENOTIFYDATE not in", values, "policyexpirenotifydate");
            return (Criteria) this;
        }

        public Criteria andPolicyexpirenotifydateBetween(Date value1, Date value2) {
            addCriterion("POLICYEXPIRENOTIFYDATE between", value1, value2, "policyexpirenotifydate");
            return (Criteria) this;
        }

        public Criteria andPolicyexpirenotifydateNotBetween(Date value1, Date value2) {
            addCriterion("POLICYEXPIRENOTIFYDATE not between", value1, value2, "policyexpirenotifydate");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanycontactIsNull() {
            addCriterion("INSURANCECOMPANYCONTACT is null");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanycontactIsNotNull() {
            addCriterion("INSURANCECOMPANYCONTACT is not null");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanycontactEqualTo(String value) {
            addCriterion("INSURANCECOMPANYCONTACT =", value, "insurancecompanycontact");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanycontactNotEqualTo(String value) {
            addCriterion("INSURANCECOMPANYCONTACT <>", value, "insurancecompanycontact");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanycontactGreaterThan(String value) {
            addCriterion("INSURANCECOMPANYCONTACT >", value, "insurancecompanycontact");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanycontactGreaterThanOrEqualTo(String value) {
            addCriterion("INSURANCECOMPANYCONTACT >=", value, "insurancecompanycontact");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanycontactLessThan(String value) {
            addCriterion("INSURANCECOMPANYCONTACT <", value, "insurancecompanycontact");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanycontactLessThanOrEqualTo(String value) {
            addCriterion("INSURANCECOMPANYCONTACT <=", value, "insurancecompanycontact");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanycontactLike(String value) {
            addCriterion("INSURANCECOMPANYCONTACT like", value, "insurancecompanycontact");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanycontactNotLike(String value) {
            addCriterion("INSURANCECOMPANYCONTACT not like", value, "insurancecompanycontact");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanycontactIn(List<String> values) {
            addCriterion("INSURANCECOMPANYCONTACT in", values, "insurancecompanycontact");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanycontactNotIn(List<String> values) {
            addCriterion("INSURANCECOMPANYCONTACT not in", values, "insurancecompanycontact");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanycontactBetween(String value1, String value2) {
            addCriterion("INSURANCECOMPANYCONTACT between", value1, value2, "insurancecompanycontact");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanycontactNotBetween(String value1, String value2) {
            addCriterion("INSURANCECOMPANYCONTACT not between", value1, value2, "insurancecompanycontact");
            return (Criteria) this;
        }

        public Criteria andPremiumIsNull() {
            addCriterion("PREMIUM is null");
            return (Criteria) this;
        }

        public Criteria andPremiumIsNotNull() {
            addCriterion("PREMIUM is not null");
            return (Criteria) this;
        }

        public Criteria andPremiumEqualTo(String value) {
            addCriterion("PREMIUM =", value, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumNotEqualTo(String value) {
            addCriterion("PREMIUM <>", value, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumGreaterThan(String value) {
            addCriterion("PREMIUM >", value, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumGreaterThanOrEqualTo(String value) {
            addCriterion("PREMIUM >=", value, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumLessThan(String value) {
            addCriterion("PREMIUM <", value, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumLessThanOrEqualTo(String value) {
            addCriterion("PREMIUM <=", value, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumLike(String value) {
            addCriterion("PREMIUM like", value, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumNotLike(String value) {
            addCriterion("PREMIUM not like", value, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumIn(List<String> values) {
            addCriterion("PREMIUM in", values, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumNotIn(List<String> values) {
            addCriterion("PREMIUM not in", values, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumBetween(String value1, String value2) {
            addCriterion("PREMIUM between", value1, value2, "premium");
            return (Criteria) this;
        }

        public Criteria andPremiumNotBetween(String value1, String value2) {
            addCriterion("PREMIUM not between", value1, value2, "premium");
            return (Criteria) this;
        }

        public Criteria andPolicypicIsNull() {
            addCriterion("POLICYPIC is null");
            return (Criteria) this;
        }

        public Criteria andPolicypicIsNotNull() {
            addCriterion("POLICYPIC is not null");
            return (Criteria) this;
        }

        public Criteria andPolicypicEqualTo(String value) {
            addCriterion("POLICYPIC =", value, "policypic");
            return (Criteria) this;
        }

        public Criteria andPolicypicNotEqualTo(String value) {
            addCriterion("POLICYPIC <>", value, "policypic");
            return (Criteria) this;
        }

        public Criteria andPolicypicGreaterThan(String value) {
            addCriterion("POLICYPIC >", value, "policypic");
            return (Criteria) this;
        }

        public Criteria andPolicypicGreaterThanOrEqualTo(String value) {
            addCriterion("POLICYPIC >=", value, "policypic");
            return (Criteria) this;
        }

        public Criteria andPolicypicLessThan(String value) {
            addCriterion("POLICYPIC <", value, "policypic");
            return (Criteria) this;
        }

        public Criteria andPolicypicLessThanOrEqualTo(String value) {
            addCriterion("POLICYPIC <=", value, "policypic");
            return (Criteria) this;
        }

        public Criteria andPolicypicLike(String value) {
            addCriterion("POLICYPIC like", value, "policypic");
            return (Criteria) this;
        }

        public Criteria andPolicypicNotLike(String value) {
            addCriterion("POLICYPIC not like", value, "policypic");
            return (Criteria) this;
        }

        public Criteria andPolicypicIn(List<String> values) {
            addCriterion("POLICYPIC in", values, "policypic");
            return (Criteria) this;
        }

        public Criteria andPolicypicNotIn(List<String> values) {
            addCriterion("POLICYPIC not in", values, "policypic");
            return (Criteria) this;
        }

        public Criteria andPolicypicBetween(String value1, String value2) {
            addCriterion("POLICYPIC between", value1, value2, "policypic");
            return (Criteria) this;
        }

        public Criteria andPolicypicNotBetween(String value1, String value2) {
            addCriterion("POLICYPIC not between", value1, value2, "policypic");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanytelIsNull() {
            addCriterion("INSURANCECOMPANYTEL is null");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanytelIsNotNull() {
            addCriterion("INSURANCECOMPANYTEL is not null");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanytelEqualTo(String value) {
            addCriterion("INSURANCECOMPANYTEL =", value, "insurancecompanytel");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanytelNotEqualTo(String value) {
            addCriterion("INSURANCECOMPANYTEL <>", value, "insurancecompanytel");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanytelGreaterThan(String value) {
            addCriterion("INSURANCECOMPANYTEL >", value, "insurancecompanytel");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanytelGreaterThanOrEqualTo(String value) {
            addCriterion("INSURANCECOMPANYTEL >=", value, "insurancecompanytel");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanytelLessThan(String value) {
            addCriterion("INSURANCECOMPANYTEL <", value, "insurancecompanytel");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanytelLessThanOrEqualTo(String value) {
            addCriterion("INSURANCECOMPANYTEL <=", value, "insurancecompanytel");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanytelLike(String value) {
            addCriterion("INSURANCECOMPANYTEL like", value, "insurancecompanytel");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanytelNotLike(String value) {
            addCriterion("INSURANCECOMPANYTEL not like", value, "insurancecompanytel");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanytelIn(List<String> values) {
            addCriterion("INSURANCECOMPANYTEL in", values, "insurancecompanytel");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanytelNotIn(List<String> values) {
            addCriterion("INSURANCECOMPANYTEL not in", values, "insurancecompanytel");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanytelBetween(String value1, String value2) {
            addCriterion("INSURANCECOMPANYTEL between", value1, value2, "insurancecompanytel");
            return (Criteria) this;
        }

        public Criteria andInsurancecompanytelNotBetween(String value1, String value2) {
            addCriterion("INSURANCECOMPANYTEL not between", value1, value2, "insurancecompanytel");
            return (Criteria) this;
        }

        public Criteria andPeriodvalidityIsNull() {
            addCriterion("PERIODVALIDITY is null");
            return (Criteria) this;
        }

        public Criteria andPeriodvalidityIsNotNull() {
            addCriterion("PERIODVALIDITY is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodvalidityEqualTo(String value) {
            addCriterion("PERIODVALIDITY =", value, "periodvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodvalidityNotEqualTo(String value) {
            addCriterion("PERIODVALIDITY <>", value, "periodvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodvalidityGreaterThan(String value) {
            addCriterion("PERIODVALIDITY >", value, "periodvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodvalidityGreaterThanOrEqualTo(String value) {
            addCriterion("PERIODVALIDITY >=", value, "periodvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodvalidityLessThan(String value) {
            addCriterion("PERIODVALIDITY <", value, "periodvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodvalidityLessThanOrEqualTo(String value) {
            addCriterion("PERIODVALIDITY <=", value, "periodvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodvalidityLike(String value) {
            addCriterion("PERIODVALIDITY like", value, "periodvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodvalidityNotLike(String value) {
            addCriterion("PERIODVALIDITY not like", value, "periodvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodvalidityIn(List<String> values) {
            addCriterion("PERIODVALIDITY in", values, "periodvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodvalidityNotIn(List<String> values) {
            addCriterion("PERIODVALIDITY not in", values, "periodvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodvalidityBetween(String value1, String value2) {
            addCriterion("PERIODVALIDITY between", value1, value2, "periodvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodvalidityNotBetween(String value1, String value2) {
            addCriterion("PERIODVALIDITY not between", value1, value2, "periodvalidity");
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