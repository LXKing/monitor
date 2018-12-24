package mmp.gps.domain.device;

public class DeviceRegistrationDto {
    /**
     * 设备号
     */
    public String number;
    /**
     * 获取省域ID
     */
    public int provinceId;

    /**
     * 县域ID
     */

    public int cityId;

    /**
     * 制造商ID
     */
    public String factoryId;

    /**
     * 终端型号
     */
    public String model;

    /**
     * 终端ID
     */
    public String deviceId;

    /**
     * 车牌颜色
     */
    public short vehiclePlateColor;

    /**
     * 车辆标识
     */
    public String vehicleId;

}
