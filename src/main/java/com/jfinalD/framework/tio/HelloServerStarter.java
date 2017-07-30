package com.jfinalD.framework.tio;

import com.jfinal.plugin.IPlugin;
import com.jfinalD.framework.config.Constants;
import com.jfinalD.framework.tio.server.HelloPacket;
import com.jfinalD.framework.tio.server.HelloServerAioHandler;
import org.tio.server.AioServer;
import org.tio.server.ServerGroupContext;
import org.tio.server.intf.ServerAioHandler;
import org.tio.server.intf.ServerAioListener;

import java.io.IOException;


public class HelloServerStarter implements IPlugin {
    private static ServerGroupContext<Object, HelloPacket, Object> serverGroupContext = null;
    private static AioServer<Object, HelloPacket, Object> aioServer = null; // 可以为空
    private static ServerAioHandler<Object, HelloPacket, Object> aioHandler = null;
    private static ServerAioListener<Object, HelloPacket, Object> aioListener = null;
    private static String serverIp = null;
    private static int serverPort = Constants.PORT;

    public static void main(String[] args) throws IOException {
        aioHandler = new HelloServerAioHandler();
        aioListener = null; // 可以为空
        serverGroupContext = new ServerGroupContext<>(aioHandler, aioListener);
        aioServer = new AioServer<>(serverGroupContext);
        aioServer.start(serverIp, serverPort);
    }

    @Override
    public boolean start() {
        aioHandler = new HelloServerAioHandler();
        aioListener = null; // 可以为空
        serverGroupContext = new ServerGroupContext<>(aioHandler, aioListener);
        aioServer = new AioServer<>(serverGroupContext);
        try {
            aioServer.start(serverIp, serverPort);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean stop() {
        return aioServer.stop();
    }
}