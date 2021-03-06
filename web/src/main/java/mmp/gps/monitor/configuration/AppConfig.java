package mmp.gps.monitor.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 网站配置
 */
@Component
public class AppConfig {

    @Value("${AppConfig.godpPortal}")
    private String godpPortal;

    @Value("${AppConfig.godpUser}")
    private String godpUser;

    @Value("${AppConfig.godpPassword}")
    private String godpPassword;

    @Value("${AppConfig.operateLogCacheRows}")
    private int operateLogCacheRows = 1;

    @Value("${AppConfig.topCompanyId}")
    private String topCompanyId;

    /**
     * 获取GOP地址
     */
    public String getGodpPortal() {
        return godpPortal;
    }

    /**
     * 设置GOP地址
     */
    public void setGodpPortal(String godpPortal) {
        this.godpPortal = godpPortal;
    }

    /**
     * 获取GOP用户帐号
     */
    public String getGodpUser() {
        return godpUser;
    }

    /**
     * 设置GOP用户帐号
     */
    public void setGodpUser(String godpUser) {
        this.godpUser = godpUser;
    }

    /**
     * 获取GOP用户密码
     */
    public String getGodpPassword() {
        return godpPassword;
    }

    /**
     * 设置GOP用户密码
     */
    public void setGodpPassword(String godpPassword) {
        this.godpPassword = godpPassword;
    }

    /**
     * 获取操作日志缓存行数
     */
    public int getOperateLogCacheRows() {
        return operateLogCacheRows;
    }

    /**
     * 设置操作日志缓存行数
     */
    public void setOperateLogCacheRows(int operateLogCacheRows) {
        this.operateLogCacheRows = operateLogCacheRows;
    }

    /**
     * 获取顶级企业ID
     */
    public String getTopCompanyId() {
        return topCompanyId;
    }

    /**
     * 设置顶级企业ID
     */
    public void setTopCompanyId(String topCompanyId) {
        this.topCompanyId = topCompanyId;
    }

    @Override
    public String toString() {
        return "AppConfig{" + "godpPortal='" + godpPortal + '\'' + ", godpUser='" + godpUser + '\'' + ", " + "godpPassword='" + godpPassword + '\'' + ", operateLogCacheRows=" + operateLogCacheRows + ", " + "topCompanyId='" + topCompanyId + '\'' + '}';
    }
}
