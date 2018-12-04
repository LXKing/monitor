package mmp.gps.domain.statistics;

import java.util.Date;
import java.util.List;

public class HistoryOnlineOfflineStatisticsRequest {
    private List<String> numbers;
    private Date start;
    private Date end;

    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

}
