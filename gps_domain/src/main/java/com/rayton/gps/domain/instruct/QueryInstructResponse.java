package com.rayton.gps.domain.instruct;

import java.util.ArrayList;
import java.util.List;

public class QueryInstructResponse {
    private int total;
    private List<InstructInfo> instructs = new ArrayList<InstructInfo>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<InstructInfo> getInstructs() {
        return instructs;
    }

    public void setInstructs(List<InstructInfo> instructs) {
        this.instructs = instructs;
    }

}
