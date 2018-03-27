package com.rayton.gps.domain;

/**
 * 应用请求类
 *
 * @author 生
 */
public class AppRequest {
    private String method;
    private String token;
    private Object parameter;

    /**
     * 获取服务方法
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置服务方法
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 获取令牌
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置令牌
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取参数
     */
    public Object getParameter() {
        return parameter;
    }

    /**
     * 设置参数
     */
    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

}
