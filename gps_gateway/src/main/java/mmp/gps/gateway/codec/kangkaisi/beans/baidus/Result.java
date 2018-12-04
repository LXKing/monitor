package mmp.gps.gateway.codec.kangkaisi.beans.baidus;

public class Result {

    private String formatted_address;
    private int confidence;
    private String business;
    private AddressComponent addressComponent;
    private String sematic_description;
    private int cityCode;


    public String getFormatted_address() {
        return this.formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public int getConfidence() {
        return this.confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public String getBusiness() {
        return this.business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public AddressComponent getAddressComponent() {
        return this.addressComponent;
    }

    public void setAddressComponent(AddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }

    public String getSematic_description() {
        return this.sematic_description;
    }

    public void setSematic_description(String sematic_description) {
        this.sematic_description = sematic_description;
    }

    public int getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String toString() {
        return "Result [formatted_address=" + this.formatted_address + ", confidence=" + this.confidence + ", business=" + this.business + ", addressComponent=" + this.addressComponent + ", sematic_description=" + this.sematic_description + ", cityCode=" + this.cityCode + "]";
    }
}
