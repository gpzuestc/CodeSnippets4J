package com.gpzuestc.framework.commons.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.telnet.TelnetClient;
import org.junit.Test;

public class NetDemo {

	@Test
	public void testTelnet(){
		TelnetClient telnet = new TelnetClient();
		try {
			telnet.connect("179.30.77.159", 6388);
			System.out.println(telnet.isAvailable());
			System.out.println(telnet.getInputStream());
			System.out.println(telnet.getOutputStream());
			OutputStream out = telnet.getOutputStream();
			out.write("ping\r\n".getBytes());
			out.flush();
			InputStream in = telnet.getInputStream();
			StringBuffer sb = new StringBuffer();
			int len = 7;
			byte[] buffer = new byte[len];
			while(true){
				int readSize = in.read(buffer);
				sb.append(new String(buffer));
				System.out.println(sb.toString());
				if(readSize < len || ('\n' == sb.charAt(readSize - 1) && '\r' == sb.charAt(readSize - 2))){
					break;
				}
			}
			System.out.println(1);
			telnet.disconnect();
			
//			Socket
//			socket = new Socket();
//			socket.setReuseAddress(true);
//			socket.setKeepAlive(true); // Will monitor the TCP connection is
//						   // valid
//			socket.setTcpNoDelay(true); // Socket buffer Whetherclosed, to
//						    // ensure timely delivery of data
//			socket.setSoLinger(true, 0); // Control calls close () method,
//						     // the underlying socket is closed
//						     // immediately
//            InetSocketAddress address = new InetSocketAddress("192.168.1.2", 6388);
//            socket.connect(address, 5000);
//            System.err.println(socket.isConnected());
//            System.out.println(socket.getRemoteSocketAddress());
//            System.out.println(socket.isBound());
//            System.out.println(socket != null && socket.isBound() && !socket.isClosed()
//            			&& socket.isConnected() && !socket.isInputShutdown()
//            			&& !socket.isOutputShutdown());
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
