package com.edata.monitor.util.enums;

public enum MediaEventTypes {
    PlatformOrder("平台下发指令", 0), TimedAction("定时动作", 1), RobberyAlarmTrigger("抢劫报警触", 2),
    RolloverCollisionAlarmTrigger("碰撞侧翻报警触发", 3), OpenDoor("门开拍照", 4), CloseDoor("门关拍照", 5), OpenToColse("车门由开变关," +
            "时速从<20km到超过20km", 6), FixedDistance("定距拍照", 7);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private MediaEventTypes(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (MediaEventTypes c : MediaEventTypes.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return "其他";
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
