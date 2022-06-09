package com.dilido.twoauth.utils;

import java.util.UUID;

public class RandomString {
    public static String getRandomString() {
        return UUID.randomUUID().toString();
    }
}
