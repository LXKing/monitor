package mmp.gps.gateway.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class ConfigureSettings {

    private String excepLog;


    public String getExcepLog() {
        return this.excepLog;
    }

    public void setExcepLog(String excepLog) {
        this.excepLog = excepLog;
    }
}
