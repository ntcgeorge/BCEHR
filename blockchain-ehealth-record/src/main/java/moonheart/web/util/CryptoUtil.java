package moonheart.web.util;

import java.security.MessageDigest;
import java.util.UUID;
import org.springframework.util.DigestUtils;

/**
 * Cryptography utility
 * 
 * @author George
 */

 public class CryptoUtil {

    /**
     * SHA256 Hash Function
     */
    public static String SHA256(String str) {
        String enCoder = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA256");
            md.update(str.getBytes("UTF-8"));
            enCoder = byte2Hex(md.digest());
        } catch(Exception e) {
            System.out.println("error to get Hash" + e.getMessage());
        }
        return enCoder;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        String temp;
        for(int i=0; i<bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if(temp.length() == 0) {
                sb.append("0");
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    public static String MD5(String str) {
		String resultStr = DigestUtils.md5DigestAsHex(str.getBytes());
		return resultStr.substring(4, resultStr.length());
	}

	public static String UUID() {
		return UUID.randomUUID().toString().replaceAll("\\-", "");
	}

 }
