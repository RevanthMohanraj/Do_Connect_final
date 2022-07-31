package com.gl.capstoneproject.doconnect.Util;

import java.util.Random;

public class HashGenerator {
	
	public static String getHashCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        while (code.length() < 6) {
            int index = (int) (random.nextFloat() * characters.length());
            code.append(characters.charAt(index));
        }
        String hashcode = code.toString();
        return hashcode;
    }
}