package mmp.gps.common.util;

public class LogPathIniter {

    public static void init() {
        System.setProperty("log_dir", AppPathUtils.getInstance().getLogPath());
        System.setProperty("his_dir", AppPathUtils.getInstance().getHisPath());
    }
}
