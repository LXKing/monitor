package com.rayton.gps.util.enums;

public enum MediaTypes {
    // 0:图像;1:音频;2:视频;
    Photo("图像", 0), Audio("音频", 1), Video("视频", 2);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private MediaTypes(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (MediaTypes c : MediaTypes.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return "未知";
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
