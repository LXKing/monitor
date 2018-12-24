package mmp.gps.logic.portal;

import java.lang.reflect.Method;

public class ServiceMethodAgent {

    private Method method;
    private Class[] parameterTypes;
    private Object instance;
    private String name;
    private boolean allowAnoumous;
    private String description;


    public Method getMethod() {
        return this.method;
    }

    public void setMethod(Method method) {
        this.method = method;
        this.parameterTypes = method.getParameterTypes();
    }

    public Class[] getParameterTypes() {
        return this.parameterTypes;
    }

    public Object getInstance() {
        return this.instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAllowAnoumous() {
        return this.allowAnoumous;
    }

    public void setAllowAnoumous(boolean allowAnoumous) {
        this.allowAnoumous = allowAnoumous;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
