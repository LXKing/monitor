package mmp.gps.protocol.kangkaisi;


public interface IPacket {

    int size();

    void from(byte[] var1);

    byte[] array();
}
