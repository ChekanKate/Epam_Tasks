package com.epam.rd.java.basic.practice3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part4 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(hash("asdf", "SHA-256"));
    }

    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(input.getBytes());
        byte[] ahash = digest.digest();
        for(int i = 0; i < ahash.length; i++){
            System.out.print(ahash[i] + " ");
        }

        StringBuilder result = new StringBuilder();
        for (byte aByte : ahash) {
            int decimal = (int) aByte & 0XFF;               // bytes widen to int, need mask, prevent sign extension
            // get last 8 bits
            String hex = Integer.toHexString(decimal);
            if (hex.length() % 2 == 1) {                    // if half hex, pad with zero, e.g \t
                hex = "0" + hex;
            }
            result.append(hex);
        }
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[ahash.length * 2];
        for ( int j = 0; j < ahash.length; j++ ) {
            int v = ahash[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
