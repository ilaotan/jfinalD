package com.ilaotan.framework.tio.server;

import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.AioHandler;

import java.nio.ByteBuffer;

public abstract class HelloAbsAioHandler implements AioHandler<Object, HelloPacket, Object> {
    /**
     * 编码：把业务消息包编码为可以发送的ByteBuffer
     */
    @Override
    public ByteBuffer encode(HelloPacket packet, GroupContext<Object, HelloPacket, Object> groupContext,
                             ChannelContext<Object, HelloPacket, Object> channelContext) {
        byte[] body = packet.getBody();
        int bodyLen = 0;
        if (body != null) {
            bodyLen = body.length;
        }

        int allLen = HelloPacket.HEADER_LENGHT + bodyLen;
        ByteBuffer buffer = ByteBuffer.allocate(allLen);
        buffer.order(groupContext.getByteOrder());

        buffer.putInt(bodyLen);

        if (body != null) {
            buffer.put(body);
        }
        return buffer;
    }

    /**
     * 解码：把接收到的ByteBuffer，解码成应用可以识别的业务消息包
     */
    @Override
    public HelloPacket decode(ByteBuffer buffer, ChannelContext<Object, HelloPacket, Object> channelContext) throws
            AioDecodeException {
        int readableLength = buffer.limit() - buffer.position();
        if (readableLength < HelloPacket.HEADER_LENGHT) {
            return null;
        }

        int bodyLength = buffer.getInt();

        if (bodyLength < 0) {
            throw new AioDecodeException("bodyLength [" + bodyLength + "] is not right, remote:" + channelContext
                    .getClientNode());
        }

        int neededLength = HelloPacket.HEADER_LENGHT + bodyLength;
        int test = readableLength - neededLength;
        // 不够消息体长度(剩下的buffe组不了消息体)
        if (test < 0) {
            return null;
        }
        else {
            HelloPacket imPacket = new HelloPacket();
            if (bodyLength > 0) {
                byte[] dst = new byte[bodyLength];
                buffer.get(dst);
                imPacket.setBody(dst);
            }
            return imPacket;
        }
    }
}