package com.rayton.gps.util.enums;

public enum DictionaryKinds {
    PlateColor("车牌颜色", 1), VehicleColor("车辆颜色", 2), VehicleType("车辆类型", 3), CarryType("载运类型", 4), AdminArea("行政区域",
            5), VehicleVoltage("车辆电压", 6), SpeechType("语音类型", 7), DeviceModel("终端型号", 8), DeviceFactory("终端厂家", 9),
    MotorcadeType("车队类型", 10), MaintainType("保养类型", 11), PoiType("兴趣点类型", 12), IDType("证件类型", 13), AlarmProcessType
            ("报警处理方式", 14);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private DictionaryKinds(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (DictionaryKinds c : DictionaryKinds.values()) {
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
