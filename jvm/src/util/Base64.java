package util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @author teng.long@hand-china.com
 * @Name Base64
 * @Description
 * @Date 2018/9/5
 */
public class Base64 {


    private static String src = "t$WcFo%kbK";

    /**
     * 将src使用base64进行加密
     * @param src
     * @return
     */
    public static String jdkBase641(String src) {
            BASE64Encoder encoder = new BASE64Encoder();
            String jdkEncode = encoder.encode(src.getBytes());
            System.out.println("加密:" + jdkEncode);
            return jdkEncode;
    }

    /**
     * 将src使用base64进行解密
     * @param src
     * @return
     */
    public static String jdkBase642(String src){
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            src =  new String(decoder.decodeBuffer(src));
            System.out.println("解密:" + src);
            return src;
        } catch (IOException e) {
            e.printStackTrace();
            return "出现异常";
        }
    }


    public static void main(String[] args) {
        jdkBase641(src);
        jdkBase642(jdkBase641(src));
    }

}
