package com.spoof.xuanke.util;

import java.util.UUID;

public class UuidUtil {

    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String uuidStr=uuid.toString();
        return uuidStr;
    }
}
