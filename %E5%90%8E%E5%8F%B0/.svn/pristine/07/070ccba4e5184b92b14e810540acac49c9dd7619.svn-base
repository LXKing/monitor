package com.rayton.gps.util.enums;

public enum DrivingRecorderActions {
    SetVehileInfo("设置车辆信息", 0x82), SetInstallDate("设置记录仪初次安装日期", 0x83), SetStatusName("设置状态量配置信息", 0x84), SetTime
            ("设置记录仪时间", 0xC2), SetPulse("设置记录仪脉冲系数", 0xC3), SetInitalMileage("设置初始里程", 0xC4);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private DrivingRecorderActions(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (DrivingRecorderActions c : DrivingRecorderActions.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return "未知";
    }

    public static DrivingRecorderActions of(int index) {
        for (DrivingRecorderActions c : DrivingRecorderActions.values()) {
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
