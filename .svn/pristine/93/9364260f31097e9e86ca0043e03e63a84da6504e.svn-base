package com.edata.monitor.util.enums;

public enum MediaFormatTypes {
    Jpg("JPEG", 0), Tif("TIF", 1), Mp3("MP3", 2), Wav("WAV", 3), Wmv("WMV", 4);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private MediaFormatTypes(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (MediaFormatTypes c : MediaFormatTypes.values()) {
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
