package com.gpzuestc.hash.consistent;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public enum HashAlgorithm {

	/**
	 * MD5-based hash algorithm used by ketama.
	 */
	KETAMA_HASH,
	MURMUR_HASH,
	MD5_HASH,
	DJB_HASH;
	
	public long hash(byte[] digest, int nTime) {
		switch (this) {
		case MURMUR_HASH:
			return hash64A(ByteBuffer.wrap(digest), 0x1234ABCD);
		case KETAMA_HASH:
			long rv = ((long) (digest[3+nTime*4] & 0xFF) << 24)
				| ((long) (digest[2+nTime*4] & 0xFF) << 16)
				| ((long) (digest[1+nTime*4] & 0xFF) << 8)
				| (digest[0+nTime*4] & 0xFF);
		
			return rv & 0xffffffffL; /* Truncate to 32-bits */
		case MD5_HASH:
			ThreadLocal<MessageDigest> md5Holder = new ThreadLocal<MessageDigest>();
            try {
                if (md5Holder.get() == null) {
                    md5Holder.set(MessageDigest.getInstance("MD5"));
                }
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException("++++ no md5 algorythm found");
            }
            MessageDigest md5 = md5Holder.get();

            md5.reset();
            md5.update(digest);
            byte[] bKey = md5.digest();
            long res = ((long) (bKey[3] & 0xFF) << 24)
                    | ((long) (bKey[2] & 0xFF) << 16)
                    | ((long) (bKey[1] & 0xFF) << 8) | (long) (bKey[0] & 0xFF);
            return res;
		case DJB_HASH:
			int hash = 5381;
			String str = new String(digest);
			for (int i = 0; i < str.length(); i++) {
				hash = ((hash << 5) + hash) + str.charAt(i);
			}

			return (hash & 0x7FFFFFFF);
		}
		return 0;
	}

	/**
	 * Get the md5 of the given key.
	 */
	public byte[] computeMd5(String k) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 not supported", e);
		}
		md5.reset();
		byte[] keyBytes = null;
		try {
			keyBytes = k.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Unknown string :" + k, e);
		}
		
		md5.update(keyBytes);
		return md5.digest();
	}
	
	
	  public static long hash64A(ByteBuffer buf, int seed) {
	        ByteOrder byteOrder = buf.order();
	        buf.order(ByteOrder.LITTLE_ENDIAN);

	        long m = 0xc6a4a7935bd1e995L;
	        int r = 47;

	        long h = seed ^ (buf.remaining() * m);

	        long k;
	        while (buf.remaining() >= 8) {
	            k = buf.getLong();

	            k *= m;
	            k ^= k >>> r;
	            k *= m;

	            h ^= k;
	            h *= m;
	        }

	        if (buf.remaining() > 0) {
	            ByteBuffer finish = ByteBuffer.allocate(8).order(
	                    ByteOrder.LITTLE_ENDIAN);
	            // for big-endian version, do this first:
	            // finish.position(8-buf.remaining());
	            finish.put(buf).rewind();
	            h ^= finish.getLong();
	            h *= m;
	        }

	        h ^= h >>> r;
	        h *= m;
	        h ^= h >>> r;

	        buf.order(byteOrder);
	        return h;
	    }
}

