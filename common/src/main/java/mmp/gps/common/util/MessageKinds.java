package mmp.gps.common.util;

/**
 * 消息类型
 */
public class MessageKinds {
    /**
     * 服务器时间戳
     */
    public static final String server_timestamp = "server.timestamp";
    /**
     * 指令结果
     */
    public static final String device_instruct_result = "device.instruct.result";
    /**
     * 设备事件报告
     */
    public static final String device_event_report = "device.event.report";
    /**
     * 设备实时数据
     */
    public static final String device_realtime_data = "device.realtime.data";
    /**
     * 设备实时位置数据
     */
    public static final String device_realtime_track = "device.realtime.track";
    /**
     * 设备进入休眠通知
     */
    public static final String device_sleep_start_notify = "device.sleep.start.notify";
    /**
     * 车辆系统匹配报告
     */
    public static final String vehicle_system_match_report = "vehicle.system.match.report";
    /**
     * 自动停止维修模式通知
     */
    public static final String auto_stop_repair_mode_notify = "auto.stop.repair.mode.notify";
    /**
     * 信息点播/取消
     */
    public static final String device_information_on_demand_request = "device.information.on.demand.request";
    /**
     * 电子运单上报
     */
    public static final String device_electronic_waybill_report = "device.electronic.waybill.report";
    /**
     * 驾驶员信息报告
     */
    public static final String device_driverinfo_report = "device.driverinfo.report";
    /**
     * 多媒体事件报告
     */
    public static final String device_multimedia_event_report = "device.multimedia.event.report";
    /**
     * 多媒体上传进度
     */
    public static final String device_multimedia_upload_percent = "device.multimedia.upload.percent";
    /**
     * 多媒体检索应答
     */
    public static final String device_multimedia_retrieval_reply = "device.multimedia.retrieval.reply";
    /**
     * 设备数据透传到服务器
     */
    public static final String device_data_transfer = "device.data.transfer";
    /**
     * 设备在线离线通知
     */
    public static final String gateway_statistics_device_onlineoffline = "gateway.statistics.device.onlineoffline";
    /**
     * 设备升级结果报告
     */
    public static final String device_upgrade_result_report = "device.upgrade.result.report";

}
