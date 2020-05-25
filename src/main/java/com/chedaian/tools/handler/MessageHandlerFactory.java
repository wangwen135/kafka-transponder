package com.chedaian.tools.handler;

import com.chedaian.tools.config.PropertiesUtil;
import com.chedaian.tools.handler.impl.GatewayMessageHandler;

public class MessageHandlerFactory {

    public MessageHandler getHandler() {
        String type = PropertiesUtil.getString("transfer.type");

        if ("gateway".equals(type)) {
            return new GatewayMessageHandler();
        }

        return null;
    }
}
