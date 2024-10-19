package com.kamyczki.stone.write.event;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;

@UtilityClass
public class StoneUtils {
    private static final String SYMBOLS = "ABCDEFGHJKLMNPQRSTUWXYZ23456789";
    private static final SecureRandom random = new SecureRandom();
    private static final int CODE_LENGTH = 4;

   static String newStoneId(){
        StringBuilder code = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));
        }
        return code.toString();
    }
}
