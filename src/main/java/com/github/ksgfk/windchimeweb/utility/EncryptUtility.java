package com.github.ksgfk.windchimeweb.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtility {
    public static byte[] getSha256(String str) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes());
            messageDigest.digest();
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
