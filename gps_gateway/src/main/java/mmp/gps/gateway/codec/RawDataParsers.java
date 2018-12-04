package mmp.gps.gateway.codec;

import mmp.gps.common.util.PackageUtil;
import mmp.gps.gateway.contract.IRawDataParser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

public class RawDataParsers {

    private static Logger cnsle = Logger.getLogger("cnsle");
    private static HashMap parsers;


    private static HashMap getParsers() {
        if (parsers == null) {
            init();
            cnsle.debug("RawDataParsers共：" + parsers.size() + "个数据解析器");
        }

        return parsers;
    }

    private static synchronized void init() {
        if (parsers == null) {
            parsers = new HashMap();
            List classes = PackageUtil.getSubClasses("mmp.gps.gateway.codec", IRawDataParser.class);
            Iterator var2 = classes.iterator();

            while (var2.hasNext()) {
                Class c = (Class) var2.next();

                try {
                    IRawDataParser e = (IRawDataParser) c.newInstance();
                    parsers.put(Integer.valueOf(e.getProtocolKind()), e);
                } catch (Exception var4) {
                    cnsle.debug("初始化RawDataParsers：" + var4.getMessage());
                }
            }

        }
    }

    public static IRawDataParser get(int protocolKind) {
        return (IRawDataParser) getParsers().get(Integer.valueOf(protocolKind));
    }
}
