package mmp.gps.domain.firmware;

import java.sql.Timestamp;
import java.util.Date;

public class FirmwareDto {
    public String id;
    public short factoryId;
    public String name;
    public String version;
    public Date uploadTime;
    public int fileSize;
    public byte[] content;
    public short checkCode;
    public String description;
    public Timestamp editTime;
}
