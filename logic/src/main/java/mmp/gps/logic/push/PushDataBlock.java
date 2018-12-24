package mmp.gps.logic.push;


public class PushDataBlock {

    private String kind;
    private Object data;


    public String getKind() {
        return this.kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
