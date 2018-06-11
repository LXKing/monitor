package com.rayton.gps.dao.baseinfo.dictionary;

import java.sql.Timestamp;

public class DictionaryItem {

    private long id;
    private Long pid;
    private int kind;
    private String name;
    private String code;
    private String index;
    private Timestamp editTime;

    @Override
    public String toString() {
        return "DictionaryItem{" + "id=" + id + ", pid=" + pid + ", enums=" + kind + ", name='" + name + '\'' + ", " + "code='" + code + '\'' + ", index='" + index + '\'' + ", editTime=" + editTime + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }


}
