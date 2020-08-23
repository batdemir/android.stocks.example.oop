package com.batdemir.utilities;

import java.util.regex.Pattern;

public class MethodHelper {

    private static MethodHelper ourInstance = null;

    private MethodHelper() {

    }

    public static MethodHelper getInstance() {
        if (ourInstance == null)
            ourInstance = new MethodHelper();
        return ourInstance;
    }

    public boolean isNumericValue(String str) {
        if (str == null)
            return false;
        if (str.isEmpty())
            return false;
        return str.matches(Pattern.compile("^-?[0-9]\\d*(\\.\\d+)?$").pattern());
    }
}
