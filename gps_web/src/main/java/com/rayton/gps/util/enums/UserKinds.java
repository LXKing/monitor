package com.rayton.gps.util.enums;

public enum UserKinds {
    // 1企业管理员，2企业用户，3车主用户，4设备用户
    CompanyAdmin("企业管理员", 1), CompanyUser("企业用户", 2), Owner("车主用户", 3), Device("设备用户", 4);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private UserKinds(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (UserKinds c : UserKinds.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
