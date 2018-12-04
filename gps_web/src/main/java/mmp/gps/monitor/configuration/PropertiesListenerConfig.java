package mmp.gps.monitor.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;


public class PropertiesListenerConfig {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesListenerConfig.class);

    private static final Map<String, String> propertiesMap = new ConcurrentHashMap<>();

    private static void processProperties(Properties props) throws BeansException {

        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            logger.warn(keyStr);
            // logger.warn(props.get(keyStr).toString());
            try {
                // PropertiesLoaderUtils的默认编码是ISO-8859-1,在这里转码一下
                propertiesMap.put(keyStr, new String(props.getProperty(keyStr).getBytes("ISO-8859-1"), "utf-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadAllProperties(String propertyFileName) {
        Properties properties;
        try {
            properties = PropertiesLoaderUtils.loadAllProperties(propertyFileName);
            processProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String name) {
        return propertiesMap.get(name);
    }

    public static Map<String, String> getAllProperty() {
        return propertiesMap;
    }
}
