package com.gpzuestc.hash.consistent.jedis;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Hashing {
    public static final Hashing MURMUR_HASH = new MurmurHash();
    public static final Hashing KETAMA_HASH = new KetamaHash();
    public ThreadLocal<MessageDigest> md5Holder = new ThreadLocal<MessageDigest>();
    public long hash(String key);
    public long hash(byte[] key);
    public long hash(byte[] key, int h);
    public String getName();
    public byte[] computeMd5(String k);
    
    public static final Hashing MD5 = new Hashing() {
        public long hash(String key) {
            return hash(SafeEncoder.encode(key));
        }

        public long hash(byte[] key) {
            try {
                if (md5Holder.get() == null) {
                    md5Holder.set(MessageDigest.getInstance("MD5"));
                }
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException("++++ no md5 algorythm found");
            }
            MessageDigest md5 = md5Holder.get();

            md5.reset();
            md5.update(key);
            byte[] bKey = md5.digest();
            long res = ((long) (bKey[3] & 0xFF) << 24)
                    | ((long) (bKey[2] & 0xFF) << 16)
                    | ((long) (bKey[1] & 0xFF) << 8) | (long) (bKey[0] & 0xFF);
            return res;
        }

		public String getName() {
			return "MD5";
		}


		public byte[] computeMd5(String k) {
			// TODO Auto-generated method stub
			return null;
		}

		
		public long hash(byte[] key, int h) {
			// TODO Auto-generated method stub
			return 0;
		}
    };
}