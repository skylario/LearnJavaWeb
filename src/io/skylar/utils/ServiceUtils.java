package io.skylar.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ServiceUtils {

    public static byte[] mod5(String s) {

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] bytes = messageDigest.digest(s.getBytes());

        return Base64.getEncoder().encode(bytes);

    }
}
