package com.batdemir.api.iservices;

import com.batdemir.entity.model.stocks.StockDetailRequest;
import com.batdemir.entity.model.stocks.StockDetailResponse;
import com.batdemir.entity.model.stocks.StocksRequest;
import com.batdemir.entity.model.stocks.StocksResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IStocks {
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/api/stocks/list")
    Call<StocksResponse> list(
            @HeaderMap Map<String, String> header,
            @Body StocksRequest request
    );

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/api/stocks/detail")
    Call<StockDetailResponse> detail(
            @HeaderMap Map<String, String> header,
            @Body StockDetailRequest request
    );
}
