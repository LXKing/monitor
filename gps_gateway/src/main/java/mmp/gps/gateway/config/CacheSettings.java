package mmp.gps.gateway.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class CacheSettings {

    private int maxDataRows;


    public int getMaxDataRows() {
        return this.maxDataRows;
    }

    public void setMaxDataRows(int maxDataRows) {
        this.maxDataRows = maxDataRows;
    }
}
