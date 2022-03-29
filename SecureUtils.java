// package com.avaya.smgr.tm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecureUtils {

    public static String getSecurePassword(String password, byte[] salt) {

        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        // same salt should be passed
        byte[] salt = getSalt();
        String password1 = getSecurePassword("wysiwyg0", salt);
        byte[] salt2 = getSalt();
        String password2 = getSecurePassword("marymary", salt);
        byte[] salt3 = getSalt();
        String password3 = getSecurePassword("abcd1234", salt);
        byte[] salt4 = getSalt();
        String password4 = getSecurePassword("password1", salt);
        StringBuilder sb = new StringBuilder();
        for (byte b: salt){
            sb.append(Integer.toHexString(0xFF & b));           
        }
        System.out.println(sb);

        sb = new StringBuilder();

        for (byte b: salt2){
            sb.append(Integer.toHexString(0xFF & b));           
        }
        System.out.println(sb);

        sb = new StringBuilder();

        for (byte b: salt3){
            sb.append(Integer.toHexString(0xFF & b));           
        }
        System.out.println(sb);

        sb = new StringBuilder();

        for (byte b: salt4){
            sb.append(Integer.toHexString(0xFF & b));           
        }
        System.out.println(sb);
        System.out.println(" wysiwyg0 " + password1);
         System.out.println(" marymary -> " + password2);
         System.out.println(" abcd1234 -> " + password3);
         System.out.println(" password1 -> " + password4);

        // System.out.println(" salt1 -> " + salt.toString());
        // System.out.println(" salt2 -> " + salt2.toString());
        // System.out.println(" salt3 -> " + salt3.toString());
        // System.out.println(" salt4 -> " + salt4.toString());
    }
}