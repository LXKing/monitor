package mmp.gps.gateway.codec.jtt808.Handler;

import java.util.Date;
import java.util.Map;

class DataFrameFuture implements Runnable {

    private String key;


    public String getKey() {
        return this.key;
    }

    public DataFrameFuture(String key) {
        this.key = key;
    }

    public void run() {
        Map map = DataFrames.getDataFrames();
        DataFrame frame = (DataFrame) map.get(this.key);
        Date now = new Date();
        long time = (now.getTime() - frame.getLastUploadTime().getTime()) / 1000L;
        if (!frame.isCompleted() && time <= 30L) {
            DataFrames.resend(frame);
        } else {
            frame.getFuture().cancel(true);
            map.remove(this.key);
        }
    }
}
