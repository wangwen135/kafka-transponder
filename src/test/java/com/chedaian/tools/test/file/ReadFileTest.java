package com.chedaian.tools.test.file;

import com.chedaian.tools.file.DeviceSnReader;

public class ReadFileTest {

    public static void main(String[] args) {
        DeviceSnReader dsr = new DeviceSnReader();

        System.out.println(dsr.getDeviceSnList());
    }
}
