package com.gpzuestc.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class UDPServer {
	 
	  /**
	   * @param args
	   * @throws InterruptedException 
	   */
	  public static void main(String[] args) throws InterruptedException {
	    Bootstrap b = new Bootstrap();
	    EventLoopGroup group = new NioEventLoopGroup();
	    b.group(group)
	        .channel(NioDatagramChannel.class)
	        .option(ChannelOption.SO_BROADCAST, true)
	        .handler(new UDPServerHandler());
	 
	        b.bind(9999).sync().channel().closeFuture().await();
	  }
	 
	}
