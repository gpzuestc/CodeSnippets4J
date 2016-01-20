package com.gpzuestc.encrypt;

public class M3U8EncryptUtil {

    // 解密
    public static String decrypt(String data) {

        if (null == data || "".equals(data)) {
            return null;
        }
        String src = "";
        int flag = 0;
        if (data.contains("_")) {
            flag = data.indexOf('_');
            data = data.replaceAll("_", "");
        }
        int length = data.length();

        for (int i = 0; i < length; i++) {
            src += (getSingleEnc(data.charAt(i), i));
        }
        if (flag > 0) {
            src = insert(src, "_", flag);
        }

        return src;
    }

    // 加密
    private static String encrypt(String data) {

        if (null == data || "".equals(data)) {
            return null;
        }
        String src = "";

        int length = data.length();

        for (int i = 0; i < length; i++) {
            src += (getSingleDec(data.charAt(i), i));
        }
        return src;
    }

    private static char getSingleEnc(char s, int i) {
        int _cnt = i;
        int[] na = new int[] {23, 12, 131, 1321};
        int _sz = na.length;
        char _n = s;
        if (_n == '_') {
            return _n;
        }
        char _beg = 0x41;
        char _len = 26;
        if (_n >= 0x61) {
            _beg = 0x61;
        } else if (_n < 0x41) {
            _beg = 0x30;
            _len = 10;
        }
        int _c = _n - _beg;
        char tmp = (char) ((_c - na[_cnt++ % _sz] % _len + _len) % _len + _beg);
        return tmp;
    }

    private static char getSingleDec(char s, int i) {
        int _cnt = i;
        int[] na = new int[] {23, 12, 131, 1321};
        int _sz = na.length;
        char _n = s;
        if (_n == '_') {
            return _n;
        }
        char _beg = 0x41;
        char _len = 26;
        if (_n >= 0x61) {
            _beg = 0x61;
        } else if (_n < 0x41) {
            _beg = 0x30;
            _len = 10;
        }
        int _c = _n - _beg;
        char tmp = (char) ((_c + na[_cnt++ % _sz] % _len + _len) % _len + _beg);
        return tmp;
    }

    public static String insert(String src, String insertStr, int n) {
        String start = src.substring(0, n);
        String end = src.substring(n, src.length());
        start += insertStr;
        return start + end;
    }

    public static void main(String[] args) {
        System.out.println(encrypt("69914317"));
        String t = encrypt("abadfwesfdasd");
        System.out.println(t);
        System.out.println(decrypt(t));
        System.out.println(encrypt(String.valueOf(System.currentTimeMillis())));
    }
}
