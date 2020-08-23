package com.batdemir.utilities;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptUtil {

    private static CryptUtil instance;

    private CryptUtil() {

    }

    public static CryptUtil getInstance() {
        if (instance == null) {
            instance = new CryptUtil();
        }
        return instance;
    }

    public String encrypt(String value, String keyStr, String ivStr) {
        String escapedString;
        try {
            byte[] key = Base64.getDecoder().decode(keyStr.getBytes());
            byte[] ivs = Base64.getDecoder().decode(ivStr.getBytes());
            Cipher cipher = Cipher.getInstance("AES_256/CBC/PKCS7Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES256");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(ivs);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, paramSpec);
            escapedString = Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes())).trim();

            return escapedString;
        } catch (Exception e) {
            e.printStackTrace();
            return value;
        }
    }

    public String decrypt(String value, String keyStr, String ivStr) {
        String escapedString;
        try {
            byte[] key = Base64.getDecoder().decode(keyStr.getBytes());
            byte[] ivs = Base64.getDecoder().decode(ivStr.getBytes());
            byte[] val = Base64.getDecoder().decode(value.getBytes());
            Cipher cipher = Cipher.getInstance("AES_256/CBC/NoPadding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES256");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(ivs);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, paramSpec);
            escapedString = new String(cipher.doFinal(val)).trim();

            return escapedString;
        } catch (Exception e) {
            e.printStackTrace();
            return value;
        }
    }

}
