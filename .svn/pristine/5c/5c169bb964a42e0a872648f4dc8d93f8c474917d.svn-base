package com.edata.monitor.util.enums;

public enum UpgradeResults {
    // 0:成功,1:失败,2:取消
    Success("成功", 0), Fail("失败", 1), Cancel("取消", 2);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private UpgradeResults(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (UpgradeResults c : UpgradeResults.values()) {
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
