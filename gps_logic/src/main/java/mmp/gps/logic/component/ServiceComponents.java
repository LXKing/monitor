package mmp.gps.logic.component;

import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.portal.ServiceMethodAgent;
import mmp.gps.logic.portal.ServiceMethodInfo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ServiceComponents {

    private static HashMap services = new HashMap();
    private static ArrayList methods = null;


    public static ServiceMethodAgent getMethod(String method) {
        return (ServiceMethodAgent) services.get(method);
    }

    public static void regist(Object instance) {
        getMethods(instance.getClass(), instance);
    }

    private static void getMethods(Class c, Object instance) {
        Method[] list = c.getMethods();
        Method[] var3 = list;
        int var4 = list.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            Method m = var3[var5];
            if (m.isAnnotationPresent(ServiceMethod.class)) {
                ServiceMethod sm = (ServiceMethod) m.getAnnotation(ServiceMethod.class);
                ServiceMethodAgent info = new ServiceMethodAgent();
                info.setName(sm.value());
                info.setAllowAnoumous(sm.allowAnoumous());
                info.setDescription(sm.description());
                info.setInstance(instance);
                info.setMethod(m);
                services.put(sm.value(), info);
            }
        }

    }

    public static synchronized ArrayList getServiceMethods() {
        if (methods == null) {
            methods = new ArrayList();
            Iterator var0 = services.values().iterator();

            while (var0.hasNext()) {
                ServiceMethodAgent agent = (ServiceMethodAgent) var0.next();
                ServiceMethodInfo info = new ServiceMethodInfo();
                info.setName(agent.getName());
                info.setAllowAnoumous(agent.isAllowAnoumous());
                info.setDescription(agent.getDescription());
                methods.add(info);
            }
        }

        return methods;
    }

}
