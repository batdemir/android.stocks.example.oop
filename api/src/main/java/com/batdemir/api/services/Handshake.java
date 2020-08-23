package com.batdemir.api.services;

import android.content.Context;

import com.android.batdemir.mylibrary.connection.ConnectService;
import com.android.batdemir.mylibrary.connection.ConnectServiceListener;
import com.android.batdemir.mylibrary.connection.RetrofitClient;
import com.batdemir.api.iservices.IHandshake;
import com.batdemir.entity.model.handshake.HandshakeRequest;

public class Handshake extends ConnectService {

    private IHandshake iService;

    public Handshake(Context context, String operationType) {
        super(context, operationType);
        iService = RetrofitClient.getInstance().create(IHandshake.class);
    }


    public void start(HandshakeRequest request, ConnectServiceListener listener) {
        setConnectServiceListener(listener);
        execute(iService.start(request));
    }
}
