package com.rayton.gps.dao.baseinfo.areaInDevice;


import java.util.List;

public interface IAreaInDeviceDao {
    int assignedPageVehiclesCount(long areaId, byte areaKind);

    List<AreaInDeviceInfo> assignedPageVehiclesDetail(long areaId, byte areaKind, int pageIndex, int pageSize);

    void addVehicle(String deviceNumber, long areaId, byte areaKind);

    void removeVehicle(String deviceNumber, long areaId, byte areaKind);

    void log(String serialNumber, String deviceNumber, long areaId, byte areaKind, byte action, String unid, String
            user);

    List<String> findDevice(long areaId, byte areaKind);

    void deleteAreaInDevice(long areaId, byte areaKind);

    void deleteAreaInMaplayer(long areaId, byte areaKind);
}
