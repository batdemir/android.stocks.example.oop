package com.batdemir.utilities;

import java.util.HashMap;
import java.util.Map;

public class ApiUtil {
    private static ApiUtil instance;

    private ApiUtil() {

    }

    public static ApiUtil getInstance() {
        if (instance == null)
            instance = new ApiUtil();
        return instance;
    }

    public String getBaseUrlFormatter(String str) {
        String beginPrefix = "http://";
        String beginPrefixSsl = "https://";
        String endPrefix = "";

        if (str.startsWith(beginPrefix)) str = str.replace(beginPrefix, "");
        if (str.startsWith(beginPrefixSsl)) str = str.replace(beginPrefixSsl, "");

        str = str.replaceAll("[^\\d.:]", "");
        str = beginPrefix + str + endPrefix;
        return str;
    }

    public Map<String, String> getToken(String token) {
        Map<String, String> header = new HashMap<>();
        header.put("X-VP-Authorization", token);
        return header;
    }
}
