package mmp.gps.monitor.model.baseinfo;

import mmp.gps.domain.area.PolygonArea;

public class PolygonAreaModel extends PolygonArea {

    private String path;

    @Override
    public String toString() {
        return "PolygonAreaModel{" + "path='" + path + '\'' + '}';
    }

    /**
     * 获取路径点json字符
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置路径点json字符
     */
    public void setPath(String path) {
        this.path = path;
    }
}
