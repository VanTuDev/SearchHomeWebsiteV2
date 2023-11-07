package com.example.demo.Utils;

import java.util.Random;
import java.util.HashMap;

public class RandomStringGenerator {
    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int STRING_LENGTH = 10;
    private static final long EXPIRATION_TIME_MS = 900000; // 15 minutes in milliseconds

    private static HashMap<String, Long> generatedStrings = new HashMap<>();

    public static String generateRandomString() {
        String randomString = generateRandomString(STRING_LENGTH);
        generatedStrings.put(randomString, System.currentTimeMillis() + EXPIRATION_TIME_MS);
        return randomString;
    }

    public static boolean isStringValid(String str) {
        Long expirationTime = generatedStrings.get(str);
        if (expirationTime == null) {
            return false;
        }
        long currentTime = System.currentTimeMillis();
        return currentTime <= expirationTime;
    }

    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    public static String generateRandomNumber() {
        int length = generateRandomNumber(1, 10);
        StringBuilder randomNumberString = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // Tạo một chữ số ngẫu nhiên từ 0 đến 9
            randomNumberString.append(digit);
        }
        return randomNumberString.toString();
    }
    public static String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        return randomString.toString();
    }
}
