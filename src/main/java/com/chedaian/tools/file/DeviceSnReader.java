package com.chedaian.tools.file;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeviceSnReader {

    private final static Logger logger = LoggerFactory.getLogger(DeviceSnReader.class);

    public final static String DEFAULT_FILE_NAME = "deviceSn.txt";

    public final static String DEFAULT_CHARSET = "UTF-8";

    private Set<String> snSet;

    public DeviceSnReader(){
        snSet = readSN(DEFAULT_FILE_NAME, DEFAULT_CHARSET);
    }

    public DeviceSnReader(String fileName, String charset){
        snSet = readSN(fileName, charset);
    }

    public Set<String> getDeviceSnList() {
        return snSet;
    }

    private Set<String> readSN(String fileName, String charset) {
        Set<String> set = new HashSet<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(
                new InputStreamReader(DeviceSnReader.class.getClassLoader().getResourceAsStream(fileName), charset));

            // 读配置文件
            String line;
            while ((line = br.readLine()) != null) {
                if (StringUtils.isBlank(line)) {
                    continue;
                }
                line = line.trim();
                if (line.startsWith("#")) {
                    continue;
                }
                if (NumberUtils.isDigits(line)) {
                    set.add(line);
                } else {
                    logger.warn("设备列表文件【{}】出现了非法的设备号：{}  忽略这一条！", fileName, line);
                }
            }
            br.close();
        } catch (Exception e) {
            logger.error("读取设备列表异常", e);
        } finally {
            if (br != null) {
                br = null;
            }
        }
        return set;
    }
}
