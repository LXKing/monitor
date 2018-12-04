package mmp.gps.gateway;

import mmp.gps.common.util.AppPathUtils;
import mmp.gps.common.util.ExcepPrinter;
import mmp.gps.common.util.LogPathIniter;
import mmp.gps.gateway.config.AppSettings;
import mmp.gps.gateway.config.ServerSettings;
import mmp.gps.gateway.contract.IServer;
import mmp.gps.gateway.mq.InstructReceiver;
import mmp.gps.gateway.test.Test;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class App {

    private static List servers = new ArrayList();
    private static Logger cnsle = Logger.getLogger("cnsle");


    public static void main(String[] args) {
        cnsle.debug("app start!");
        LogPathIniter.init();

        // gateway.xml 配置
        AppSettings settings = AppSettings.current();
        cnsle.debug("日志打印!" + AppPathUtils.getInstance().getLogPath() + settings.getConfigureSettings().getExcepLog());
        ExcepPrinter.init(AppPathUtils.getInstance().getLogPath() + settings.getConfigureSettings().getExcepLog());
        if (settings == null) {
            cnsle.debug("Error reading configuration file, the program won\'t run!");
        } else {
            Iterator iterator = settings.getServers().iterator();

            while (iterator.hasNext()) {
                ServerSettings e = (ServerSettings) iterator.next();
                String className = e.getClassName();
                // 初始化连接服务器
                IServer server = getInstance(className, ServerSettings.class, e);
                servers.add(server);
            }


            Test.testL();
            // 开启MQ
            InstructReceiver.start();
        }
    }

    public static IServer getInstance(String className, Class class1, ServerSettings setting) {
        try {
            Class ex = Class.forName(className);
            Constructor con = ex.getConstructor(new Class[]{class1});
            Object o = con.newInstance(new Object[]{setting});
            return (IServer) o;
        } catch (Exception var6) {
            ExcepPrinter.print(var6);
            return null;
        }
    }

    public static void stop(String[] args) {
        InstructReceiver.stop();
        if (servers != null) {
            Iterator iterator = servers.iterator();

            while (iterator.hasNext()) {
                IServer server = (IServer) iterator.next();
                server.close();
            }

            servers.clear();
            servers = null;
            System.exit(0);
        }
    }
}
