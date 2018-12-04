package mmp.gps.common.util;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {


    public int code;

    public Integer count;

    public List<T> data = new ArrayList<>();

    public String msg;


    /**
     * 数据列表
     */
    public List<T> rows = new ArrayList<>();
    /**
     * 总记录数
     */
    public Integer total;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Page{" + "rows=" + rows + ", total=" + total + '}';
    }
}
