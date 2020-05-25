package com.chedaian.tools.test.config;

import com.chedaian.tools.config.PropertiesUtil;

public class ConfigTest {

    public static void main(String[] args) {
        System.out.println(PropertiesUtil.getString("transfert.type"));
    }
}
