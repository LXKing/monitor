package mmp.gps.common.util;

import java.io.File;

public class AppPathUtils {

    private static final String WIN = "windows";
    private static final String OS = System.getProperty("os.name");
    private static AppPathUtils instance = new AppPathUtils();


    public static AppPathUtils getInstance() {
        return instance;
    }

    public String getInstallPath() {
        String installPath = System.getProperty("user.dir") + File.separator;
        if (OS != null && OS.toLowerCase().contains("windows")) {
            installPath = installPath.replaceAll("\\\\", "\\\\\\\\");
        }

        return installPath;
    }

    public String getConfigPath() {
        String configPath = System.getProperty("user.dir") + File.separator + "configs" + File.separator;
        if (OS != null && OS.toLowerCase().contains("windows")) {
            configPath = configPath.replaceAll("\\\\", "\\\\\\\\");
        }

        return configPath;
    }

    public String getLogPath() {
        String logPath = System.getProperty("user.dir") + File.separator + "logs" + File.separator;
        if (OS != null && OS.toLowerCase().contains("windows")) {
            logPath = logPath.replaceAll("\\\\", "\\\\\\\\");
        }

        return logPath;
    }

    public String getHisPath() {
        String hisPath = System.getProperty("user.dir") + File.separator + "his" + File.separator;
        if (OS != null && OS.toLowerCase().contains("windows")) {
            hisPath = hisPath.replaceAll("\\\\", "\\\\\\\\");
        }

        return hisPath;
    }
}
