package com.read.test.netty.discard;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

/**
 * Created by yfwangrui on 2015/5/27.
 *
 *
 */
public class DiscardClientHandler extends SimpleChannelUpstreamHandler {

    private long transferredBytes;
    private final byte[] content;

    public DiscardClientHandler() {
        content = new byte[DiscardClient.SIZE];
    }

    public long getTransferredBytes() {
        return transferredBytes;
    }

    @Override
    public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
        if (e instanceof ChannelStateEvent) {
            if (((ChannelStateEvent) e).getState() != ChannelState.INTEREST_OPS) {
                System.err.println(e);
            }
        }

        // Let SimpleChannelHandler call actual event handler methods below.
        super.handleUpstream(ctx, e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        // Send the initial messages.
        generateTraffic(e);
    }

    @Override
    public void channelInterestChanged(ChannelHandlerContext ctx, ChannelStateEvent e) {
        // Keep sending messages whenever the current socket buffer has room.
        generateTraffic(e);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        // Server is supposed to send nothing.  Therefore, do nothing.
    }

    @Override
    public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e) {
        transferredBytes += e.getWrittenAmount();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        // Close the connection when an exception is raised.
        e.getCause().printStackTrace();
        e.getChannel().close();
    }

    private void generateTraffic(ChannelStateEvent e) {
        // Keep generating traffic until the channel is unwritable.
        // A channel becomes unwritable when its internal buffer is full.
        // If you keep writing messages ignoring this property,
        // you will end up with an OutOfMemoryError.
        Channel channel = e.getChannel();
        while (channel.isWritable()) {
            ChannelBuffer m = nextMessage();
            if (m == null) {
                break;
            }
            channel.write(m);
        }
    }

    private ChannelBuffer nextMessage() {
        return ChannelBuffers.wrappedBuffer(content);
    }
}
