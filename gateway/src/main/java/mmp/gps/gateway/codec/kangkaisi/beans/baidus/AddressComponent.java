package mmp.gps.gateway.codec.kangkaisi.beans.baidus;


public class AddressComponent {

    private String country;
    private int country_code;
    private String country_code_iso;
    private String country_code_iso2;
    private String province;
    private String city;
    private String district;
    private String town;
    private String street;
    private String street_number;
    private int adcode;
    private String direction;
    private String distance;


    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCountry_code() {
        return this.country_code;
    }

    public void setCountry_code(int country_code) {
        this.country_code = country_code;
    }

    public String getCountry_code_iso() {
        return this.country_code_iso;
    }

    public void setCountry_code_iso(String country_code_iso) {
        this.country_code_iso = country_code_iso;
    }

    public String getCountry_code_iso2() {
        return this.country_code_iso2;
    }

    public void setCountry_code_iso2(String country_code_iso2) {
        this.country_code_iso2 = country_code_iso2;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet_number() {
        return this.street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public int getAdcode() {
        return this.adcode;
    }

    public void setAdcode(int adcode) {
        this.adcode = adcode;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDistance() {
        return this.distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String toString() {
        return "AddressComponent [country=" + this.country + ", country_code=" + this.country_code + ", country_code_iso=" + this.country_code_iso + ", country_code_iso2=" + this.country_code_iso2 + ", province=" + this.province + ", city=" + this.city + ", district=" + this.district + ", town=" + this.town + ", street=" + this.street + ", street_number=" + this.street_number + ", adcode=" + this.adcode + ", direction=" + this.direction + ", distance=" + this.distance + "]";
    }
}
