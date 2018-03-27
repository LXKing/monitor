package com.rayton.gps.domain.fault;

import java.util.List;

public class FaultQueryResponse {
    private List<FaultQuery> faults;

    public List<FaultQuery> getFaults() {
        return faults;
    }

    public void setFaults(List<FaultQuery> faults) {
        this.faults = faults;
    }

}
