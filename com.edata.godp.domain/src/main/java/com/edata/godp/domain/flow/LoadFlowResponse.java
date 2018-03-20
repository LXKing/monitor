package com.edata.godp.domain.flow;

import java.util.List;

public class LoadFlowResponse {
    private List<Flow> flows;

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }
}
