package mmp.gps.common.enums;

public enum MarkerType {

    CAR("car"), POI("poi");

    private String type;

    MarkerType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
