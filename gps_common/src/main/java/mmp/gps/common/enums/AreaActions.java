package mmp.gps.common.enums;

public enum AreaActions {
    // 0:更新区域; 1:追加区域; 2:修改区域
    Update("更新区域", 0), Append("追加区域", 1), Edit("修改区域", 2), Remove("删除", 3);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    AreaActions(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (AreaActions c : AreaActions.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return "未知";
    }

    public static AreaActions of(int index) {
        for (AreaActions c : AreaActions.values()) {
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
