package mmp.gps.common.enums;

public enum UpgradeTypes {
    // 0:终端,12:道路运输证 IC 卡读卡器,52:北斗 卫星定位模块
    Device("终端升级", 0), IcCard("道路运输证IC卡读卡器升级", 12), BeiDouMoudle("北斗卫星定位模块升级", 52);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    UpgradeTypes(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (UpgradeTypes c : UpgradeTypes.values()) {
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
