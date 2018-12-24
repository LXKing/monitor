package mmp.gps.protocol.gbt19056;


public class Gbt19056Util {

    public static void xor(byte[] raw) {
        byte A = 0;
        int length = raw.length - 1;

        for (int i = 0; i < length; ++i) {
            A ^= raw[i];
        }

        raw[length] = A;
    }
}
