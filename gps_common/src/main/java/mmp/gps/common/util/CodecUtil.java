package mmp.gps.common.util;

import com.ning.http.util.Base64;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CodecUtil {

    public static String getEscapeParameter(ArrayList list, int index) {
        ArrayList raw = (ArrayList) list.get(index);
        String p = (String) raw.get(0);
        return p;
    }

    public static String getUnescapeParameter(ArrayList list, int index) {
        ArrayList raw = (ArrayList) list.get(index);
        String p = (String) raw.get(0);
        if (p != null && p.length() > 0) {
            byte[] data = Base64.decode(p);
            p = new String(data);
            p = Escapecoder.unescape(p);
        }

        return p;
    }

    public static String getUnescapeParameter(String[] list, int index) {
        String p = list[index];
        if (p != null && p.length() > 0) {
            byte[] data = Base64.decode(p);
            p = new String(data);
            p = Escapecoder.unescape(p);
        }

        return p;
    }

    public static String getUnescapeParameter(String p) {
        if (p != null && p.length() > 0) {
            byte[] data = Base64.decode(p);
            p = new String(data);
            p = Escapecoder.unescape(p);
        }

        return p;
    }

    public static long bitSwitch(List list) {
        long flag = 0L;

        byte bit;
        for (Iterator var4 = list.iterator(); var4.hasNext(); flag |= (long) (1 << bit)) {
            String item = (String) var4.next();
            bit = Byte.parseByte(item);
        }

        return flag;
    }

    public static long bitSwitch(String list) {
        return 0L;
    }
}
