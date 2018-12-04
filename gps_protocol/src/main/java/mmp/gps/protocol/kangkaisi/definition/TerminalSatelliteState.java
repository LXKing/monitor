package mmp.gps.protocol.kangkaisi.definition;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;

public class TerminalSatelliteState implements IPacket {

    private short gpsModuleState;
    private short gpsStarsNumber;
    private String gpsStrength;
    private short notParticipateGpsStarsNumber;
    private String notParticipateGpsStrength;
    private Short beiDouModuleState;
    private Short beiDouStarsNumber;
    private String beiDouStarsNumberStrength;
    private Short notParticipateBeiDouStarsNumber;
    private String notParticipateBeiDouStarsNumberStrength;
    private Short extendedLength;
    private String extensionBit;


    public short getGpsModuleState() {
        return this.gpsModuleState;
    }

    public void setGpsModuleState(short gpsModuleState) {
        this.gpsModuleState = gpsModuleState;
    }

    public short getGpsStarsNumber() {
        return this.gpsStarsNumber;
    }

    public void setGpsStarsNumber(short gpsStarsNumber) {
        this.gpsStarsNumber = gpsStarsNumber;
    }

    public String getGpsStrength() {
        return this.gpsStrength;
    }

    public void setGpsStrength(String gpsStrength) {
        this.gpsStrength = gpsStrength;
    }

    public short getNotParticipateGpsStarsNumber() {
        return this.notParticipateGpsStarsNumber;
    }

    public void setNotParticipateGpsStarsNumber(short notParticipateGpsStarsNumber) {
        this.notParticipateGpsStarsNumber = notParticipateGpsStarsNumber;
    }

    public String getNotParticipateGpsStrength() {
        return this.notParticipateGpsStrength;
    }

    public void setNotParticipateGpsStrength(String notParticipateGpsStrength) {
        this.notParticipateGpsStrength = notParticipateGpsStrength;
    }

    public Short getBeiDouModuleState() {
        return this.beiDouModuleState;
    }

    public void setBeiDouModuleState(Short beiDouModuleState) {
        this.beiDouModuleState = beiDouModuleState;
    }

    public Short getBeiDouStarsNumber() {
        return this.beiDouStarsNumber;
    }

    public void setBeiDouStarsNumber(Short beiDouStarsNumber) {
        this.beiDouStarsNumber = beiDouStarsNumber;
    }

    public String getBeiDouStarsNumberStrength() {
        return this.beiDouStarsNumberStrength;
    }

    public void setBeiDouStarsNumberStrength(String beiDouStarsNumberStrength) {
        this.beiDouStarsNumberStrength = beiDouStarsNumberStrength;
    }

    public Short getNotParticipateBeiDouStarsNumber() {
        return this.notParticipateBeiDouStarsNumber;
    }

    public void setNotParticipateBeiDouStarsNumber(Short notParticipateBeiDouStarsNumber) {
        this.notParticipateBeiDouStarsNumber = notParticipateBeiDouStarsNumber;
    }

    public String getNotParticipateBeiDouStarsNumberStrength() {
        return this.notParticipateBeiDouStarsNumberStrength;
    }

    public void setNotParticipateBeiDouStarsNumberStrength(String notParticipateBeiDouStarsNumberStrength) {
        this.notParticipateBeiDouStarsNumberStrength = notParticipateBeiDouStarsNumberStrength;
    }

    public Short getExtendedLength() {
        return this.extendedLength;
    }

    public void setExtendedLength(Short extendedLength) {
        this.extendedLength = extendedLength;
    }

    public String getExtensionBit() {
        return this.extensionBit;
    }

    public void setExtensionBit(String extensionBit) {
        this.extensionBit = extensionBit;
    }

    public String toString() {
        return "TerminalSatelliteState [gpsModuleState=" + this.gpsModuleState + ", gpsStarsNumber=" + this.gpsStarsNumber + ", gpsStrength=" + this.gpsStrength + ", notParticipateGpsStarsNumber=" + this.notParticipateGpsStarsNumber + ", notParticipateGpsStrength=" + this.notParticipateGpsStrength + ", beiDouModuleState=" + this.beiDouModuleState + ", beiDouStarsNumber=" + this.beiDouStarsNumber + ", beiDouStarsNumberStrength=" + this.beiDouStarsNumberStrength + ", notParticipateBeiDouStarsNumber=" + this.notParticipateBeiDouStarsNumber + ", notParticipateBeiDouStarsNumberStrength=" + this.notParticipateBeiDouStarsNumberStrength + ", extendedLength=" + this.extendedLength + ", extensionBit=" + this.extensionBit + "]";
    }

    public int size() {
        return 0;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.gpsModuleState = io.getUbyte();
        this.gpsStarsNumber = io.getUbyte();

        int i;
        for (i = 0; i < this.gpsStarsNumber; ++i) {
            if (i == this.gpsStarsNumber - 1) {
                this.gpsStrength = this.gpsStrength + io.getUbyte() + ",";
            } else {
                this.gpsStrength = this.gpsStrength + io.getUbyte();
            }
        }

        this.notParticipateGpsStarsNumber = io.getUbyte();

        for (i = 0; i < this.notParticipateGpsStarsNumber; ++i) {
            if (i == this.notParticipateGpsStarsNumber - 1) {
                this.notParticipateGpsStrength = this.notParticipateGpsStrength + io.getUbyte() + ",";
            } else {
                this.notParticipateGpsStrength = this.notParticipateGpsStrength + io.getUbyte();
            }
        }

        this.beiDouModuleState = Short.valueOf(io.getUbyte());
        this.beiDouStarsNumber = Short.valueOf(io.getUbyte());

        for (i = 0; i < this.beiDouStarsNumber.shortValue(); ++i) {
            if (i == this.beiDouStarsNumber.shortValue() - 1) {
                this.beiDouStarsNumberStrength = this.beiDouStarsNumberStrength + io.getUbyte() + ",";
            } else {
                this.beiDouStarsNumberStrength = this.beiDouStarsNumberStrength + io.getUbyte();
            }
        }

        this.notParticipateBeiDouStarsNumber = Short.valueOf(io.getUbyte());

        for (i = 0; i < this.notParticipateBeiDouStarsNumber.shortValue(); ++i) {
            if (i == this.notParticipateBeiDouStarsNumber.shortValue() - 1) {
                this.notParticipateBeiDouStarsNumberStrength = this.notParticipateBeiDouStarsNumberStrength + io.getUbyte() + ",";
            } else {
                this.notParticipateBeiDouStarsNumberStrength = this.notParticipateBeiDouStarsNumberStrength + io.getUbyte();
            }
        }

        this.extendedLength = Short.valueOf(io.getUbyte());
    }

    public byte[] array() {
        return null;
    }
}
