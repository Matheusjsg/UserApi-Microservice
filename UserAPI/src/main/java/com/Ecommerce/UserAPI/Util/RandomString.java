package com.Ecommerce.UserAPI.Util;

import java.security.SecureRandom;

public class RandomString {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

    public static String generenateRandomString(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {

            int index = secureRandom.nextInt(CHARACTERS.length());

            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

}