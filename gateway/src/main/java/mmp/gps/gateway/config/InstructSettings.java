package mmp.gps.gateway.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class InstructSettings {

    private String mqUrl;
    private String queueName;
    private String user;
    private String password;


    public String getMqUrl() {
        return this.mqUrl;
    }

    public void setMqUrl(String mqUrl) {
        this.mqUrl = mqUrl;
    }

    public String getQueueName() {
        return this.queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
