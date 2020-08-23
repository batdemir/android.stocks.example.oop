package com.batdemir.api.iservices;

import com.batdemir.entity.model.handshake.HandshakeRequest;
import com.batdemir.entity.model.handshake.HandshakeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IHandshake {

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/api/handshake/start")
    Call<HandshakeResponse> start(@Body HandshakeRequest request);
}
