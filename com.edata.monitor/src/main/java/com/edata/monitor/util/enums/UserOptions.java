package com.edata.monitor.util.enums;

public enum UserOptions {
    // 1地图设置,2监控地图工具栏
    MapOption("地图设置", 1), LoateMapToolbar("监控地图工具栏", 2);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private UserOptions(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (UserOptions c : UserOptions.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public static UserOptions of(int index) {
        for (UserOptions c : UserOptions.values()) {
            if (c.getIndex() == index) {
                return c;
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
