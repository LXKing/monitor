package com.rayton.gps.dao.baseinfo.dictionary;

public class DictionaryItemInfo {
    private long id;
    private Long pid;
    private String name;
    private String code;
    private String index;

    @Override
    public String toString() {
        return "DictionaryItemInfo{" + "id=" + id + ", pid=" + pid + ", name='" + name + '\'' + ", code='" + code +
                '\'' + ", index='" + index + '\'' + '}';
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
}
