package mmp.gps.protocol.jtt808.body;


public class RouteDrivingInfo {

    private long routeId;
    private int seconds;
    private byte result;


    public long getRouteId() {
        return this.routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public byte getResult() {
        return this.result;
    }

    public void setResult(byte result) {
        this.result = result;
    }
}
