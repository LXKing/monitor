package mmp.gps.domain.push;

/**
 * 多媒体事件报告消息
 */
public class MultimediaEventReportMessage {
    /**
     * 设备号
     */
    public String number;
    /**
     * 多媒体数据ID
     */
    public long mediaId;

    /**
     * 媒体类型:0 图像，1 音频，2 视频
     */

    public byte mediaType;

    /**
     * 格式类型,0:JPEG;1:TIF;2:MP3;3:WAV;4:WMV; 其他保留
     */
    public byte formatType;

    /**
     * 事件项编码<br>
     * 0:平台下发指令;<br>
     * 1:定时动作;<br>
     * 2:抢劫报警触 发;<br>
     * 3:碰撞侧翻报警触发;<br>
     * 4:门开拍照;<br>
     * 5:门关拍照;<br>
     * 6:车门由开变关,时速从<20公里到超过20公里;<br>
     * 7:定距拍照;<br>
     * 其他保留
     */
    public byte eventType;
    /**
     * 通道ID
     */
    public byte channelId;

}
