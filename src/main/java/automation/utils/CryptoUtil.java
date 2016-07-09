package automation.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by shantonu on 7/9/16.
 * this contains all encryption supported by java8 default, if you need to apply company specific encoders/decoders, just add util here to support with existing.
 */
public class CryptoUtil {

    public static String encrypt(String item){
        byte[] encrypted =  Base64.getEncoder().encode(item.getBytes(StandardCharsets.ISO_8859_1));//default Charset
        return new String(encrypted);
    }
    public static String encrypt(String item, Charset charset){
        byte[] encrypted =  Base64.getEncoder().encode(item.getBytes(charset));
        return new String(encrypted);
    }
    public static String dencrypt(String item){
        byte[] encrypted =  Base64.getDecoder().decode(item);
        return new String(encrypted);
    }
    public static String dencrypt(String item, Charset charset){
        byte[] encrypted =  Base64.getDecoder().decode(item.getBytes(charset));
        return new String(encrypted);
    }
    public static String urlEncode(String item){
        byte[] encrypted =  Base64.getUrlEncoder().encode(item.getBytes(StandardCharsets.ISO_8859_1));
        return new String(encrypted);
    }

    public static String urlEncode(String item, Charset charset){
        byte[] encrypted =  Base64.getUrlEncoder().encode(item.getBytes(charset));
        return new String(encrypted);
    }
    public static String urlDecode(String item){
        byte[] encrypted =  Base64.getUrlDecoder().decode(item.getBytes(StandardCharsets.ISO_8859_1));
        return new String(encrypted);
    }

    public static String urlDecode(String item, Charset charset){
        byte[] encrypted =  Base64.getUrlDecoder().decode(item.getBytes(charset));
        return new String(encrypted);
    }
    public static String mimeEncrypt(String item){
        byte[] encrypted =  Base64.getMimeEncoder().encode(item.getBytes(StandardCharsets.ISO_8859_1));//default Charset
        return new String(encrypted);
    }
    public static String mimeEncrypt(String item, Charset charset){
        byte[] encrypted =  Base64.getMimeEncoder().encode(item.getBytes(charset));
        return new String(encrypted);
    }
    public static String mimeDecrypt(String item){
        byte[] encrypted =  Base64.getMimeDecoder().decode(item);
        return new String(encrypted);
    }
    public static String mimeDecrypt(String item, Charset charset){
        byte[] encrypted =  Base64.getMimeDecoder().decode(item.getBytes(charset));
        return new String(encrypted);
    }
}
