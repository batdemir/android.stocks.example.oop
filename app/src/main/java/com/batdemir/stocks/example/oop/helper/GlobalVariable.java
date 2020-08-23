package com.batdemir.stocks.example.oop.helper;

import com.batdemir.entity.model.handshake.HandshakeResponse;

import java.util.Locale;

public class GlobalVariable {
    private GlobalVariable() {

    }

    private static Locale locale = null;

    public static Locale getLocale() {
        return locale;
    }

    public static void setLocale(Locale locale) {
        GlobalVariable.locale = locale;
    }

    private static HandshakeResponse handshake = null;

    public static HandshakeResponse getHandshake() {
        return handshake;
    }

    public static void setHandshake(HandshakeResponse handshake) {
        GlobalVariable.handshake = handshake;
    }
}
