package mmp.gps.domain.statistics;

import java.util.List;

public class CurrentOnlineOfflineStatisticsRequest {
    private List<String> numbers;

    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }
}
