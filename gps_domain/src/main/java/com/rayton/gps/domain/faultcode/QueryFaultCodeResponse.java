package com.rayton.gps.domain.faultcode;

import java.util.List;

public class QueryFaultCodeResponse {
    private int total;
    private List<FaultCodeInfo> codes;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<FaultCodeInfo> getCodes() {
        return codes;
    }

    public void setCodes(List<FaultCodeInfo> codes) {
        this.codes = codes;
    }

}
