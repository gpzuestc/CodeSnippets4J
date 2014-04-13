package com.gpzuestc.framework.jedis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * 
 * @author: guopengzhang@sohu-inc.com
 * @date: Jul 28, 2012
 * 
 */
public class OrigRedisClient {

	public static final byte DOLLAR_BYTE = '$';
	public static final byte ASTERISK_BYTE = '*';
	public static final byte PLUS_BYTE = '+';
	public static final byte MINUS_BYTE = '-';
	public static final byte COLON_BYTE = ':';

	private static final String charset = "UTF-8";
	private static final int size = 2048;
	private static byte buf[] = new byte[size];

	private static OutputStream os;
	private static InputStream is;

	private static int limit;
	private static int count;

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] argx) throws Exception {

		//
		String host = "10.1.104.167";
		int port = 6379;

		Socket socket = new Socket();
		socket.setReuseAddress(true);
		socket.setKeepAlive(true); 
		socket.setTcpNoDelay(true); 
		socket.setSoLinger(true, 0); 

		socket.connect(new InetSocketAddress(host, port));
		os = socket.getOutputStream();
		is = socket.getInputStream();

		// cmd: set sohu1 111
		byte[] command = "SET".getBytes(charset);
		final byte[][] args = new byte[2][];
		args[0] = "sohu1".getBytes(charset);
		args[1] = "111".getBytes(charset);

		/**
		 * [*号][消息元素数]\r\n ( 消息元素个数 = 参数个数 + 1)
		 * [$号][命令字节数]\r\n 
		 * [命令内容]\r\n
		 * [$号][参数字节数]\r\n 
		 * [参数内容]\r\n 
		 * [$号][参数字节数]\r\n 
		 * [参数内容]\r\n
		 */
		write(ASTERISK_BYTE);
		writeIntCrLf(args.length + 1);
		write(DOLLAR_BYTE);
		writeIntCrLf(command.length);
		write(command);
		writeCrLf();

		for (final byte[] arg : args) {
			write(DOLLAR_BYTE);
			writeIntCrLf(arg.length);
			write(arg);
			writeCrLf();
		}

		// 发送命令
		System.out.println("Send cmd：set sohu1 111");
		flush();

		byte[] resp = (byte[]) process();
		if (resp != null) {
			System.out.println("Response status：" + new String(resp, charset));
		}
		System.out.println("end");
		//

	}

	
	
	
	
	
	
	
	
	
	
	
	private static void write(byte b) throws IOException {
		buf[count++] = b;
		if (count == size) {
			flush();
		}
	}

	private static void write(byte[] bs) throws IOException {
		int len = bs.length;
		System.arraycopy(bs, 0, buf, count, len);
		count += len;
	}

	private static void flush() throws IOException {
		if (count > 0) {
			os.write(buf, 0, count);
			count = 0;
		}
		os.flush();
	}

	
	private static void writeIntCrLf1(int value) throws IOException {
		 byte[] b = new byte[4];

	     // 使用4个byte表示int
	        for (int i = 0; i < 4; i++) {
	            int offset = (b.length - 1 - i) * 8;  // 偏移量
	            b[i] = (byte) ((value >> offset) & 0xFF); //每次取8bit
	        }
	        write(b);
	        writeCrLf();
	}
	private static void writeIntCrLf(int value) throws IOException {

		if (value < 0) {
			os.write('-');
			value = -value;
		}

		int size = 0;
		while (value > sizeTable[size])
			size++;

		size++;
		if (size >= buf.length - count) {
			flush();
		}

		int q, r;
		int charPos = count + size;

		while (value >= 65536) {
			q = value / 100;
			r = value - ((q << 6) + (q << 5) + (q << 2));
			value = q;
			buf[--charPos] = DigitOnes[r];
			buf[--charPos] = DigitTens[r];
		}

		for (;;) {
			q = (value * 52429) >>> (16 + 3);
			r = value - ((q << 3) + (q << 1));
			buf[--charPos] = digits[r];
			value = q;
			if (value == 0)
				break;
		}
		count += size;

		writeCrLf();

	}

	private static void writeCrLf() throws IOException {
		write((byte) '\r');
		write((byte) '\n');
	}

	private static void fill() throws IOException {
		limit = is.read(buf);
		count = 0;
	}

	private static byte readByte() throws IOException {
		if (count == limit) {
			fill();
		}
		return buf[count++];
	}

	private static void processError(final InputStream is) throws Exception {
		String message = readLine();
		System.out.println(message);
	}

	private static String readLine() throws Exception {

		int b;
		byte c;
		StringBuilder sb = new StringBuilder();

		try {
			while (true) {
				if (count == limit) {
					fill();
				}
				if (limit == -1)
					break;

				b = buf[count++];
				if (b == '\r') {
					if (count == limit) {
						fill();
					}

					if (limit == -1) {
						sb.append((char) b);
						break;
					}

					c = buf[count++];
					if (c == '\n') {
						break;
					}
					sb.append((char) b);
					sb.append((char) c);
				} else {
					sb.append((char) b);
				}
			}
		} catch (IOException e) {
			throw new Exception(e);
		}
		String reply = sb.toString();
		if (reply.length() == 0) {
			throw new Exception(
					"It seems like server has closed the connection.");
		}
		return reply;

	}

	private static Long processInteger() throws Exception {
		String num = readLine();
		return Long.valueOf(num);
	}

	private static List<Object> processMultiBulkReply() throws NumberFormatException, Exception {
		int num = Integer.parseInt(readLine());
		if (num == -1) {
			return null;
		}
		List<Object> ret = new ArrayList<Object>(num);
		for (int i = 0; i < num; i++) {
			try {
				ret.add(process());
			} catch (Exception e) {
				ret.add(e);
			}
		}
		return ret;
	}

	private static Object process() throws Exception {
		try {
			byte b = readByte();
			if (b == MINUS_BYTE) {
				processError(is);
			} else if (b == ASTERISK_BYTE) {
				return processMultiBulkReply();
			} else if (b == COLON_BYTE) {
				return processInteger();
			} else if (b == DOLLAR_BYTE) {
				return processBulkReply();
			} else if (b == PLUS_BYTE) {
				return processStatusCodeReply();
			} else {
				throw new Exception("Unknown reply: " + (char) b);
			}
		} catch (IOException e) {
			throw new Exception(e);
		}
		return null;
	}

	private static byte[] processStatusCodeReply()
			throws Exception {
		return readLine().getBytes(charset);
	}

	private static byte[] processBulkReply() throws  Exception {
		int len = Integer.parseInt(readLine());
		if (len == -1) {
			return null;
		}
		byte[] read = new byte[len];
		int offset = 0;
		try {
			while (offset < len) {
				offset += is.read(read, offset, (len - offset));
			}
			// read 2 more bytes for the command delimiter
			readByte();
			readByte();
		} catch (IOException e) {
			throw new Exception(e);
		}

		return read;
	}

	private final static int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999,
			9999999, 99999999, 999999999, Integer.MAX_VALUE };

	private final static byte[] DigitTens = { '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1',
			'1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3',
			'3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4',
			'4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5',
			'5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7',
			'7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8',
			'8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9',
			'9', '9', '9', };

	private final static byte[] DigitOnes = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1',
			'2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
			'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', };

	private final static byte[] digits = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z' };
}
