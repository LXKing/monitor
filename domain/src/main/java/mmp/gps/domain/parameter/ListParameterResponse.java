package mmp.gps.domain.parameter;

import java.util.ArrayList;
import java.util.List;

public class ListParameterResponse {
    private int total;
    private List<ParameterInfo> parameters = new ArrayList<ParameterInfo>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ParameterInfo> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterInfo> parameters) {
        this.parameters = parameters;
    }

}
