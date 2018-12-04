package mmp.gps.logic;

import mmp.gps.common.util.RSA;

public class Identity {

    private String account;
    private String id;
    private String name;
    private Long timestamp;
    private String roles;


    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getRoles() {
        return this.roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public static Identity parse(String token) {
        if (token != null && token.length() > 0) {
            try {
                String e = RSA.decrypt(token);
                String[] list = e.split("\t", 5);
                Identity identity = new Identity();
                identity.setAccount(list[0]);
                identity.setId(list[1]);
                identity.setName(list[2]);
                identity.setTimestamp(Long.valueOf(Long.parseLong(list[3])));
                identity.setRoles(list[4]);
                return identity;
            } catch (Exception var4) {
                return null;
            }
        } else {
            return null;
        }
    }
}
