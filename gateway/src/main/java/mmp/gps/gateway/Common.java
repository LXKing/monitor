package mmp.gps.gateway;


public class Common {

    private static int serialNumber = '\ufffe';
    public static final String Instructs = "instructs";
    public static final String Number = "number";
    public static final String NetKind = "netkind";
    public static final String ProtocolKind = "protocolkind";
    public static final int UDP = 2;
    public static final int TCP = 1;


    public static synchronized int getSerialNumber() {
        if (serialNumber > '\uffff') {
            serialNumber = 0;
        }

        return serialNumber++;
    }
}
