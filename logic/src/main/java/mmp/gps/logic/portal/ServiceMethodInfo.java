package mmp.gps.logic.portal;


public class ServiceMethodInfo {

    private String name;
    private boolean allowAnoumous;
    private String description;


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
