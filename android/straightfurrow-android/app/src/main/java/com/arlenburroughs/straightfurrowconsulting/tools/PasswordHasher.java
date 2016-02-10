package com.arlenburroughs.straightfurrowconsulting.tools;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Arlen on 2/10/2016.
 */
public class PasswordHasher {

    //Empty Constructor for singleton purposes.
    private PasswordHasher(){}

    public static String hashPassword(String input){
        String password = ""+input.toString();

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //Convert byteData to hex String:
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
