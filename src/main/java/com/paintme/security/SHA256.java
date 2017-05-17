package com.paintme.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SHA256 {
    public static String getSHA256SecurePassword(
            String password, byte[] salt, String algorithmName)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithmName);
        md.update(salt);

        byte[] bytes = md.digest(password.getBytes());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public static byte[] getSalt(String algorithmName)
            throws NoSuchAlgorithmException {
        byte[] salt = new byte[16];

        SecureRandom sr = SecureRandom.getInstance(algorithmName);
        sr.nextBytes(salt);

        return salt;
    }
}
