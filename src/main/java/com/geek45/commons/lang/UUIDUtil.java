package com.geek45.commons.lang;

import java.util.UUID;

public class UUIDUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
