package org.generation.utils;
//package org.generation.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtil {
    public static String salt ="#MAGDGEAAYEB_!32767";

    public static String createHash(String value) {
        String res=value+salt;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //SHA-1 SHA-256 SHA-384 SHA-512
            md.update(value.getBytes());
            byte[] resultado = md.digest();
            int max = resultado.length;
            String tmp;
            res ="";
            for (int i=0; i<max; i++) {
                tmp = Integer.toHexString(0xFF & resultado[i]);
                res += (tmp.length()<2)?0+tmp:tmp;
            }// for i

        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }//catch
        return res;
    }// createHash

    public static  boolean verifyHash(String original, String hash) {
         String res=createHash(original);
        return res.equalsIgnoreCase(hash);
    }// verifyHash

    public static boolean verifyPasswd(String original, String newPasswd) {
        return  createHash(original).equalsIgnoreCase(createHash(newPasswd));
    }// verifyHash

}//class SHAUtil
