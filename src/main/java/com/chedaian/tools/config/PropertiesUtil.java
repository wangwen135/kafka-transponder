package com.chedaian.tools.config;

import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {

    private final static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private final static String FILE_NAME = "system.properties";

    private static Properties properties = null;

    static {
        if (null == properties) {
            properties = new Properties();
            InputStreamReader reader = null;
            try {
                reader = new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(FILE_NAME),
                    "UTF-8");
                properties.load(reader);
                reader.close();
            } catch (Exception e) {
                logger.error("读取配置文件异常", e);
            } finally {
                if (reader != null) {
                    reader = null;
                }
            }
        }
    }

    private PropertiesUtil(){
    }

    public static String getString(String key) {
        return properties.getProperty(key);
    }
}
