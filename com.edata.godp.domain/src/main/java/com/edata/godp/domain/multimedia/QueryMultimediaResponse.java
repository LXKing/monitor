package com.edata.godp.domain.multimedia;

import java.util.ArrayList;
import java.util.List;

public class QueryMultimediaResponse {
    private int total;
    private List<MultimediaInfo> rows = new ArrayList<MultimediaInfo>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MultimediaInfo> getRows() {
        return rows;
    }

    public void setRows(List<MultimediaInfo> rows) {
        this.rows = rows;
    }

}
