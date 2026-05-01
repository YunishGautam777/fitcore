package com.fitcore.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * BCrypt-based password hashing.
 */
public class PasswordUtil {

    private static final int WORK_FACTOR = 12;

    public static String hash(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt(WORK_FACTOR));
    }

    public static boolean verify(String plain, String hash) {
        if (plain == null || hash == null) return false;
        try {
            return BCrypt.checkpw(plain, hash);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
