package com.rayton.gps.domain.feature;

public class ExistFeatureRequest {
    private String id;
    private int kind;
    private String command;
    private boolean checkId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public boolean isCheckId() {
        return checkId;
    }

    public void setCheckId(boolean checkId) {
        this.checkId = checkId;
    }

}
