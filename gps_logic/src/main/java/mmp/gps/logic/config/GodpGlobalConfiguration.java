package mmp.gps.logic.config;


public class GodpGlobalConfiguration {

    private int offlineTimeout;
    public static GodpGlobalConfiguration current;


    public int getOfflineTimeout() {
        return this.offlineTimeout;
    }

    public void setOfflineTimeout(int offlineTimeout) {
        this.offlineTimeout = offlineTimeout;
    }

    public GodpGlobalConfiguration() {
        current = this;
    }
}
