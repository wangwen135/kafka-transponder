package com.chedaian.tools.handler.impl;

import java.util.Set;

import com.chedaia.server.common.PacketModel;
import com.chedaian.tools.config.PropertiesUtil;
import com.chedaian.tools.file.DeviceSnReader;
import com.chedaian.tools.handler.MessageHandler;
import com.chedaian.tools.utils.ByteUtils;

public class GatewayMessageHandler implements MessageHandler {

    private boolean enableFilter = false;
    private Set<String> includeSnSet;

    public GatewayMessageHandler(){
        String snFilter = PropertiesUtil.getString("transfer.gateway.deviceSn.filter");
        if ("enable".equalsIgnoreCase(snFilter)) {
            enableFilter = true;
            DeviceSnReader dsr = new DeviceSnReader();
            includeSnSet = dsr.getDeviceSnList();
        }
    }

    @Override
    public byte[] handle(byte[] bytes) {
        if (!enableFilter) {
            return bytes;
        }

        Object obj = ByteUtils.fromByte(bytes);

        if (obj == null) {
            return null;
        }

        if (obj instanceof PacketModel) {
            PacketModel pm = (PacketModel) obj;

            if (includeSnSet.contains(pm.getSn())) {
                return bytes;
            }
        }

        return null;
    }

}
