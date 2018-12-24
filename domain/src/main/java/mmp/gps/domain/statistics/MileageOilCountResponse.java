package mmp.gps.domain.statistics;

import java.util.List;

public class MileageOilCountResponse {
    private List<MileageOilCountResult> results;

    public List<MileageOilCountResult> getResults() {
        return results;
    }

    public void setResults(List<MileageOilCountResult> results) {
        this.results = results;
    }
}
