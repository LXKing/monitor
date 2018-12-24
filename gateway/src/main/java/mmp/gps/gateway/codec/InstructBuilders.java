package mmp.gps.gateway.codec;

import mmp.gps.common.util.PackageUtil;
import mmp.gps.gateway.contract.IInstructBuilder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

public class InstructBuilders {

    private static Logger cnsle = Logger.getLogger("cnsle");
    private static HashMap builders;


    public static HashMap getBuilders() {
        if (builders == null) {
            init();
            cnsle.debug("InstructBuilders共：" + builders.size() + "个指令生成器");
        }

        return builders;
    }

    private static synchronized void init() {
        if (builders == null) {
            builders = new HashMap();
            List classes = PackageUtil.getSubClasses("mmp.gps.gateway.codec", IInstructBuilder.class);
            Iterator var2 = classes.iterator();

            while (var2.hasNext()) {
                Class c = (Class) var2.next();

                try {
                    IInstructBuilder e = (IInstructBuilder) c.newInstance();
                    builders.put(Integer.valueOf(e.getProtocolKind()), e);
                } catch (Exception var4) {
                    cnsle.debug("初始化InstructBuilders：" + var4.getMessage());
                }
            }

        }
    }

    public static IInstructBuilder get(int protocolKind) {
        return (IInstructBuilder) getBuilders().get(Integer.valueOf(protocolKind));
    }
}
