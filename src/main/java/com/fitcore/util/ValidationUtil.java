package com.fitcore.util;

import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Pattern EMAIL =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PHONE = Pattern.compile("^[0-9]{10}$");

    public static boolean notBlank(String s)  { return s != null && !s.trim().isEmpty(); }
    public static boolean isEmail(String s)   { return s != null && EMAIL.matcher(s).matches(); }
    public static boolean isPhone(String s)   { return s != null && PHONE.matcher(s).matches(); }
    public static boolean validPassword(String s) { return s != null && s.length() >= 6; }
}
