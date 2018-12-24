package mmp.gps.gateway.config;

import mmp.gps.common.util.JaxbUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement
public class AppSettings {

    private static Logger cnsle = Logger.getLogger("cnsle");
    private static AppSettings appSettings = null;
    private InstructSettings instruct;
    private ConfigureSettings configureSettings;
    private CacheSettings cache;
    private GodpSettings godpSettings;
    private List servers;


    public static AppSettings current() {
        if (appSettings == null) {
            load();
        }

        return appSettings;
    }

    public static void refresh() {
        appSettings = null;
        load();
    }

    private static synchronized void load() {
        if (appSettings == null) {
            File file = new File("gateway.xml");
            if (!file.exists()) {
                cnsle.debug("gateway.xml not found!");
            } else {
                BufferedReader reader = null;

                try {
                    InputStreamReader e = new InputStreamReader(new FileInputStream(file), "utf-8");
                    reader = new BufferedReader(e);
                    StringBuilder buffer = new StringBuilder();
                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    String xml = buffer.toString();
                    appSettings = (AppSettings) JaxbUtil.converyToJavaBean(xml, AppSettings.class);
                } catch (Exception var14) {
                    cnsle.debug("gateway.xml load error:" + var14.getMessage());
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException var13) {
                            var13.printStackTrace();
                        }
                    }

                }

            }
        }
    }

    @XmlElement(
            name = "instruct"
    )
    public InstructSettings getInstruct() {
        return this.instruct;
    }

    public void setInstruct(InstructSettings instruct) {
        this.instruct = instruct;
    }

    @XmlElement(
            name = "configure"
    )
    public ConfigureSettings getConfigureSettings() {
        return this.configureSettings;
    }

    public void setConfigureSettings(ConfigureSettings configureSettings) {
        this.configureSettings = configureSettings;
    }

    @XmlElement(
            name = "cache"
    )
    public CacheSettings getCache() {
        return this.cache;
    }

    public void setCache(CacheSettings cache) {
        this.cache = cache;
    }

    @XmlElement(
            name = "godp"
    )
    public GodpSettings getGodpSettings() {
        return this.godpSettings;
    }

    public void setGodpSettings(GodpSettings godpSettings) {
        this.godpSettings = godpSettings;
    }

    @XmlElementWrapper(
            name = "servers"
    )
    @XmlElement(
            name = "server"
    )
    public List getServers() {
        return this.servers;
    }

    public void setServers(List servers) {
        this.servers = servers;
    }
}
