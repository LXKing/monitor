package mmp.gps.logic.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ActiveMQConfiguration {

    private static String user;
    private static String password;
    private static String url;


    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUrl() {
        return url;
    }

    private static synchronized void load() {
        Properties properties = new Properties();

        try {
            String e = ActiveMQConfiguration.class.getResource("/").getPath();
            e = e.substring(1, e.indexOf("classes"));
            boolean windowsSystem = System.getProperty("os.name").toLowerCase().startsWith("windows");
            if (!windowsSystem && !e.startsWith("/")) {
                e = "/" + e;
            }

            properties.load(new FileInputStream(e + "activeMQ.properties"));
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    static {
        load();
    }
}
