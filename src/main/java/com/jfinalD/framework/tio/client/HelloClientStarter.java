package com.jfinalD.framework.tio.client;

import com.jfinalD.framework.config.Constants;
import com.jfinalD.framework.tio.server.HelloPacket;
import org.tio.client.AioClient;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientGroupContext;
import org.tio.client.ReconnConf;
import org.tio.client.intf.ClientAioHandler;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Aio;
import org.tio.core.Node;

public class HelloClientStarter {
    private static Node serverNode = null;
    private static AioClient<Object, HelloPacket, Object> aioClient;
    private static ClientGroupContext<Object, HelloPacket, Object> clientGroupContext = null;
    private static ClientAioHandler<Object, HelloPacket, Object> aioClientHandler = null;
    private static ClientAioListener<Object, HelloPacket, Object> aioListener = null;
    private static ReconnConf<Object, HelloPacket, Object> reconnConf = new ReconnConf<Object, HelloPacket, Object>(
            5000L);// 用来自动连接的，不想自动连接请传null

    public static void main(String[] args) throws Exception {
        String serverIp = "127.0.0.1";
        int serverPort = Constants.PORT;
        serverNode = new Node(serverIp, serverPort);
        aioClientHandler = new HelloClientAioHandler();
        aioListener = null;

        clientGroupContext = new ClientGroupContext<>(aioClientHandler, aioListener, reconnConf);
        aioClient = new AioClient<>(clientGroupContext);

        ClientChannelContext<Object, HelloPacket, Object> clientChannelContext = aioClient.connect(serverNode);

        // 以下内容不是启动的过程，而是属于发消息的过程
        HelloPacket packet = new HelloPacket();
        packet.setBody("hello world".getBytes(HelloPacket.CHARSET));
        Aio.send(clientChannelContext, packet);
    }
}