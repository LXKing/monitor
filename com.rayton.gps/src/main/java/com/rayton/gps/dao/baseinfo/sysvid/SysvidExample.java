package com.rayton.gps.dao.baseinfo.sysvid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysvidExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysvidExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
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

        public Criteria andSysvidIsNull() {
            addCriterion("SYSVID is null");
            return (Criteria) this;
        }

        public Criteria andSysvidIsNotNull() {
            addCriterion("SYSVID is not null");
            return (Criteria) this;
        }

        public Criteria andSysvidEqualTo(String value) {
            addCriterion("SYSVID =", value, "sysvid");
            return (Criteria) this;
        }

        public Criteria andSysvidNotEqualTo(String value) {
            addCriterion("SYSVID <>", value, "sysvid");
            return (Criteria) this;
        }

        public Criteria andSysvidGreaterThan(String value) {
            addCriterion("SYSVID >", value, "sysvid");
            return (Criteria) this;
        }

        public Criteria andSysvidGreaterThanOrEqualTo(String value) {
            addCriterion("SYSVID >=", value, "sysvid");
            return (Criteria) this;
        }

        public Criteria andSysvidLessThan(String value) {
            addCriterion("SYSVID <", value, "sysvid");
            return (Criteria) this;
        }

        public Criteria andSysvidLessThanOrEqualTo(String value) {
            addCriterion("SYSVID <=", value, "sysvid");
            return (Criteria) this;
        }

        public Criteria andSysvidLike(String value) {
            addCriterion("SYSVID like", value, "sysvid");
            return (Criteria) this;
        }

        public Criteria andSysvidNotLike(String value) {
            addCriterion("SYSVID not like", value, "sysvid");
            return (Criteria) this;
        }

        public Criteria andSysvidIn(List<String> values) {
            addCriterion("SYSVID in", values, "sysvid");
            return (Criteria) this;
        }

        public Criteria andSysvidNotIn(List<String> values) {
            addCriterion("SYSVID not in", values, "sysvid");
            return (Criteria) this;
        }

        public Criteria andSysvidBetween(String value1, String value2) {
            addCriterion("SYSVID between", value1, value2, "sysvid");
            return (Criteria) this;
        }

        public Criteria andSysvidNotBetween(String value1, String value2) {
            addCriterion("SYSVID not between", value1, value2, "sysvid");
            return (Criteria) this;
        }

        public Criteria andSysvidactivationdateIsNull() {
            addCriterion("SYSVIDACTIVATIONDATE is null");
            return (Criteria) this;
        }

        public Criteria andSysvidactivationdateIsNotNull() {
            addCriterion("SYSVIDACTIVATIONDATE is not null");
            return (Criteria) this;
        }

        public Criteria andSysvidactivationdateEqualTo(Date value) {
            addCriterion("SYSVIDACTIVATIONDATE =", value, "sysvidactivationdate");
            return (Criteria) this;
        }

        public Criteria andSysvidactivationdateNotEqualTo(Date value) {
            addCriterion("SYSVIDACTIVATIONDATE <>", value, "sysvidactivationdate");
            return (Criteria) this;
        }

        public Criteria andSysvidactivationdateGreaterThan(Date value) {
            addCriterion("SYSVIDACTIVATIONDATE >", value, "sysvidactivationdate");
            return (Criteria) this;
        }

        public Criteria andSysvidactivationdateGreaterThanOrEqualTo(Date value) {
            addCriterion("SYSVIDACTIVATIONDATE >=", value, "sysvidactivationdate");
            return (Criteria) this;
        }

        public Criteria andSysvidactivationdateLessThan(Date value) {
            addCriterion("SYSVIDACTIVATIONDATE <", value, "sysvidactivationdate");
            return (Criteria) this;
        }

        public Criteria andSysvidactivationdateLessThanOrEqualTo(Date value) {
            addCriterion("SYSVIDACTIVATIONDATE <=", value, "sysvidactivationdate");
            return (Criteria) this;
        }

        public Criteria andSysvidactivationdateIn(List<Date> values) {
            addCriterion("SYSVIDACTIVATIONDATE in", values, "sysvidactivationdate");
            return (Criteria) this;
        }

        public Criteria andSysvidactivationdateNotIn(List<Date> values) {
            addCriterion("SYSVIDACTIVATIONDATE not in", values, "sysvidactivationdate");
            return (Criteria) this;
        }

        public Criteria andSysvidactivationdateBetween(Date value1, Date value2) {
            addCriterion("SYSVIDACTIVATIONDATE between", value1, value2, "sysvidactivationdate");
            return (Criteria) this;
        }

        public Criteria andSysvidactivationdateNotBetween(Date value1, Date value2) {
            addCriterion("SYSVIDACTIVATIONDATE not between", value1, value2, "sysvidactivationdate");
            return (Criteria) this;
        }

        public Criteria andExpiredateIsNull() {
            addCriterion("EXPIREDATE is null");
            return (Criteria) this;
        }

        public Criteria andExpiredateIsNotNull() {
            addCriterion("EXPIREDATE is not null");
            return (Criteria) this;
        }

        public Criteria andExpiredateEqualTo(Date value) {
            addCriterion("EXPIREDATE =", value, "expiredate");
            return (Criteria) this;
        }

        public Criteria andExpiredateNotEqualTo(Date value) {
            addCriterion("EXPIREDATE <>", value, "expiredate");
            return (Criteria) this;
        }

        public Criteria andExpiredateGreaterThan(Date value) {
            addCriterion("EXPIREDATE >", value, "expiredate");
            return (Criteria) this;
        }

        public Criteria andExpiredateGreaterThanOrEqualTo(Date value) {
            addCriterion("EXPIREDATE >=", value, "expiredate");
            return (Criteria) this;
        }

        public Criteria andExpiredateLessThan(Date value) {
            addCriterion("EXPIREDATE <", value, "expiredate");
            return (Criteria) this;
        }

        public Criteria andExpiredateLessThanOrEqualTo(Date value) {
            addCriterion("EXPIREDATE <=", value, "expiredate");
            return (Criteria) this;
        }

        public Criteria andExpiredateIn(List<Date> values) {
            addCriterion("EXPIREDATE in", values, "expiredate");
            return (Criteria) this;
        }

        public Criteria andExpiredateNotIn(List<Date> values) {
            addCriterion("EXPIREDATE not in", values, "expiredate");
            return (Criteria) this;
        }

        public Criteria andExpiredateBetween(Date value1, Date value2) {
            addCriterion("EXPIREDATE between", value1, value2, "expiredate");
            return (Criteria) this;
        }

        public Criteria andExpiredateNotBetween(Date value1, Date value2) {
            addCriterion("EXPIREDATE not between", value1, value2, "expiredate");
            return (Criteria) this;
        }

        public Criteria andExpirenotifydateIsNull() {
            addCriterion("EXPIRENOTIFYDATE is null");
            return (Criteria) this;
        }

        public Criteria andExpirenotifydateIsNotNull() {
            addCriterion("EXPIRENOTIFYDATE is not null");
            return (Criteria) this;
        }

        public Criteria andExpirenotifydateEqualTo(Date value) {
            addCriterion("EXPIRENOTIFYDATE =", value, "expirenotifydate");
            return (Criteria) this;
        }

        public Criteria andExpirenotifydateNotEqualTo(Date value) {
            addCriterion("EXPIRENOTIFYDATE <>", value, "expirenotifydate");
            return (Criteria) this;
        }

        public Criteria andExpirenotifydateGreaterThan(Date value) {
            addCriterion("EXPIRENOTIFYDATE >", value, "expirenotifydate");
            return (Criteria) this;
        }

        public Criteria andExpirenotifydateGreaterThanOrEqualTo(Date value) {
            addCriterion("EXPIRENOTIFYDATE >=", value, "expirenotifydate");
            return (Criteria) this;
        }

        public Criteria andExpirenotifydateLessThan(Date value) {
            addCriterion("EXPIRENOTIFYDATE <", value, "expirenotifydate");
            return (Criteria) this;
        }

        public Criteria andExpirenotifydateLessThanOrEqualTo(Date value) {
            addCriterion("EXPIRENOTIFYDATE <=", value, "expirenotifydate");
            return (Criteria) this;
        }

        public Criteria andExpirenotifydateIn(List<Date> values) {
            addCriterion("EXPIRENOTIFYDATE in", values, "expirenotifydate");
            return (Criteria) this;
        }

        public Criteria andExpirenotifydateNotIn(List<Date> values) {
            addCriterion("EXPIRENOTIFYDATE not in", values, "expirenotifydate");
            return (Criteria) this;
        }

        public Criteria andExpirenotifydateBetween(Date value1, Date value2) {
            addCriterion("EXPIRENOTIFYDATE between", value1, value2, "expirenotifydate");
            return (Criteria) this;
        }

        public Criteria andExpirenotifydateNotBetween(Date value1, Date value2) {
            addCriterion("EXPIRENOTIFYDATE not between", value1, value2, "expirenotifydate");
            return (Criteria) this;
        }

        public Criteria andBussinessagentIsNull() {
            addCriterion("BUSSINESSAGENT is null");
            return (Criteria) this;
        }

        public Criteria andBussinessagentIsNotNull() {
            addCriterion("BUSSINESSAGENT is not null");
            return (Criteria) this;
        }

        public Criteria andBussinessagentEqualTo(String value) {
            addCriterion("BUSSINESSAGENT =", value, "bussinessagent");
            return (Criteria) this;
        }

        public Criteria andBussinessagentNotEqualTo(String value) {
            addCriterion("BUSSINESSAGENT <>", value, "bussinessagent");
            return (Criteria) this;
        }

        public Criteria andBussinessagentGreaterThan(String value) {
            addCriterion("BUSSINESSAGENT >", value, "bussinessagent");
            return (Criteria) this;
        }

        public Criteria andBussinessagentGreaterThanOrEqualTo(String value) {
            addCriterion("BUSSINESSAGENT >=", value, "bussinessagent");
            return (Criteria) this;
        }

        public Criteria andBussinessagentLessThan(String value) {
            addCriterion("BUSSINESSAGENT <", value, "bussinessagent");
            return (Criteria) this;
        }

        public Criteria andBussinessagentLessThanOrEqualTo(String value) {
            addCriterion("BUSSINESSAGENT <=", value, "bussinessagent");
            return (Criteria) this;
        }

        public Criteria andBussinessagentLike(String value) {
            addCriterion("BUSSINESSAGENT like", value, "bussinessagent");
            return (Criteria) this;
        }

        public Criteria andBussinessagentNotLike(String value) {
            addCriterion("BUSSINESSAGENT not like", value, "bussinessagent");
            return (Criteria) this;
        }

        public Criteria andBussinessagentIn(List<String> values) {
            addCriterion("BUSSINESSAGENT in", values, "bussinessagent");
            return (Criteria) this;
        }

        public Criteria andBussinessagentNotIn(List<String> values) {
            addCriterion("BUSSINESSAGENT not in", values, "bussinessagent");
            return (Criteria) this;
        }

        public Criteria andBussinessagentBetween(String value1, String value2) {
            addCriterion("BUSSINESSAGENT between", value1, value2, "bussinessagent");
            return (Criteria) this;
        }

        public Criteria andBussinessagentNotBetween(String value1, String value2) {
            addCriterion("BUSSINESSAGENT not between", value1, value2, "bussinessagent");
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
    }
}