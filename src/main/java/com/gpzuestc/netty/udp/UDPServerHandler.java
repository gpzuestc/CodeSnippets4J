package com.gpzuestc.netty.udp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

public class UDPServerHandler extends
		SimpleChannelInboundHandler<DatagramPacket> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.netty.channel.SimpleChannelInboundHandler#messageReceived(io.netty
	 * .channel.ChannelHandlerContext, java.lang.Object)
	 */

	// @Override
	// protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket
	// packet) throws Exception {
	// // TODO Auto-generated method stub
	//
	// ByteBuf buf = (ByteBuf) packet.copy().content();
	// byte[] req = new byte[buf.readableBytes()];
	// buf.readBytes(req);
	// String body = new String(req, "UTF-8");
	// System.out.println(body);
	//
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.netty.channel.ChannelHandlerAdapter#channelRegistered(io.netty.channel
	 * .ChannelHandlerContext)
	 */
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelRegistered(ctx);
		// System.out.println("I got it!");
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg)
			throws Exception {

		ByteBuf buf = (ByteBuf) msg.copy().content();
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, "UTF-8");
		System.out.println(msg.sender());
		System.out.println(body);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.netty.channel.ChannelHandlerAdapter#channelReadComplete(io.netty.channel
	 * .ChannelHandlerContext)
	 */

}
