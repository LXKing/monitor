package mmp.gps.monitor.model.baseinfo;

import java.util.Map;

public class UserOptionModel {

    private int type;
    private Map<String, Object> options;

    @Override
    public String toString() {
        return "UserOptionModel{" + "type=" + type + ", options=" + options + '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Map<String, Object> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }

}
