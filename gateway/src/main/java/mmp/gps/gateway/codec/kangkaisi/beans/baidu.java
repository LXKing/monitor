package mmp.gps.gateway.codec.kangkaisi.beans;

import mmp.gps.gateway.codec.kangkaisi.beans.baidus.Result;

public class baidu {

    private int status;
    private Result result;


    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Result getResult() {
        return this.result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String toString() {
        return "baidu [status=" + this.status + ", result=" + this.result + "]";
    }
}
