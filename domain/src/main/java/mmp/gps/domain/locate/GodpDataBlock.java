package mmp.gps.domain.locate;

/**
 * Godp数据块类
 */
public class GodpDataBlock {

    private String kind;
    private Object data;

    @Override
    public String toString() {
        return "GodpDataBlock{" + "enums='" + kind + '\'' + ", data=" + data + '}';
    }

    /**
     * 获取数据类型
     */
    public String getKind() {
        return kind;
    }

    /**
     * 设置数据类型
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * 获取数据
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置数据
     */
    public void setData(Object data) {
        this.data = data;
    }
}
