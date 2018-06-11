package com.rayton.gps.util;

import java.util.List;

public class AnotherBean {

    public List<Client> Client;
    public List<Group> Group;
    public List<Device> Device;


    public List<AnotherBean.Client> getClient() {
        return Client;
    }

    public void setClient(List<AnotherBean.Client> client) {
        Client = client;
    }

    public List<AnotherBean.Group> getGroup() {
        return Group;
    }

    public void setGroup(List<AnotherBean.Group> group) {
        Group = group;
    }

    public List<AnotherBean.Device> getDevice() {
        return Device;
    }

    public void setDevice(List<AnotherBean.Device> device) {
        Device = device;
    }

    public class Client {
        public String MSName;
        public String address;
        public String clientId;
        public String clientName;
        public String company;
        public String email;
        public String clientType;


        public String creClientCount;
        public String creGroupCount;
        public String creMsCount;

        public String id;

        public String remark;
        public String servId;
        public String whoOpr;
        public String wsId;
        public String grpId;
        public String[] grpList;
        public String grpName;
        public String grpPId;
        public String grpType;
        public String mSType;
        public String msId;
        public String person;
        public String phone;
        public String pid;
        public String pttGrp;
    }

    public class Group {
        public String clientId;
        public String grpCreateTime;
        public String grpId;
        public String grpName;
        public String grpPId;
        public String grpType;
        public String msId;
        public String[] msList;

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getGrpCreateTime() {
            return grpCreateTime;
        }

        public void setGrpCreateTime(String grpCreateTime) {
            this.grpCreateTime = grpCreateTime;
        }

        public String getGrpId() {
            return grpId;
        }

        public void setGrpId(String grpId) {
            this.grpId = grpId;
        }

        public String getGrpName() {
            return grpName;
        }

        public void setGrpName(String grpName) {
            this.grpName = grpName;
        }

        public String getGrpPId() {
            return grpPId;
        }

        public void setGrpPId(String grpPId) {
            this.grpPId = grpPId;
        }

        public String getGrpType() {
            return grpType;
        }

        public void setGrpType(String grpType) {
            this.grpType = grpType;
        }

        public String getMsId() {
            return msId;
        }

        public void setMsId(String msId) {
            this.msId = msId;
        }

        public String[] getMsList() {
            return msList;
        }

        public void setMsList(String[] msList) {
            this.msList = msList;
        }
    }

    public class Device {

        public String MSName;
        public String clientId;
        public String grpId;
        public String[] grpList;
        public String mSType;
        public String msId;

        public String getMSName() {
            return MSName;
        }

        public void setMSName(String MSName) {
            this.MSName = MSName;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getGrpId() {
            return grpId;
        }

        public void setGrpId(String grpId) {
            this.grpId = grpId;
        }

        public String[] getGrpList() {
            return grpList;
        }

        public void setGrpList(String[] grpList) {
            this.grpList = grpList;
        }

        public String getmSType() {
            return mSType;
        }

        public void setmSType(String mSType) {
            this.mSType = mSType;
        }

        public String getMsId() {
            return msId;
        }

        public void setMsId(String msId) {
            this.msId = msId;
        }
    }

}
