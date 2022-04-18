package cl.medvet.medvetbackend.util;

import cl.medvet.medvetbackend.enums.UtilsEnums;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

public class PasswordEncryption {

    public String encode (String chain) {

        String encryption = "";

        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] passwordKey = md5.digest(UtilsEnums.SECRET_KEY.value.getBytes(StandardCharsets.UTF_8));
            byte[] BytesKey = Arrays.copyOf(passwordKey, 24);
            SecretKey key = new SecretKeySpec(BytesKey, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainTextBytes = chain.getBytes(StandardCharsets.UTF_8);
            byte[] buffer = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buffer);
            encryption = new String(base64Bytes);

        }catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Algo salió mal!");
        }

        return encryption;

    }

    public String decode(String encryptedChain) {
        String desencryptChain = "";

        try {

            byte[] message = Base64.decodeBase64(encryptedChain.getBytes(StandardCharsets.UTF_8));
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md5.digest(UtilsEnums.SECRET_KEY.value.getBytes(StandardCharsets.UTF_8));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plainText = decipher.doFinal(message);
            desencryptChain = new String(plainText, StandardCharsets.UTF_8);

        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "algo salio mal...");

        }

        return  desencryptChain;

    }
}
