package mmp.gps.domain.push;

/**
 * 数据透传消息
 */
public class DataTransferMessage {
    /**
     * 设备号
     */
    public String number;
    /**
     * 类型
     */
    public int type;
    /**
     * 数据
     */
    public byte[] data;

}
