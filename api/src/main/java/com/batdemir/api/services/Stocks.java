package com.batdemir.api.services;

import android.content.Context;

import com.android.batdemir.mylibrary.connection.ConnectService;
import com.android.batdemir.mylibrary.connection.ConnectServiceListener;
import com.android.batdemir.mylibrary.connection.RetrofitClient;
import com.batdemir.api.iservices.IStocks;
import com.batdemir.entity.model.stocks.StockDetailRequest;
import com.batdemir.entity.model.stocks.StocksRequest;
import com.batdemir.utilities.ApiUtil;

public class Stocks extends ConnectService {

    private IStocks iService;

    public Stocks(Context context, String operationType) {
        super(context, operationType);
        iService = RetrofitClient.getInstance().create(IStocks.class);
    }

    public void list(String token, StocksRequest request, ConnectServiceListener listener) {
        setConnectServiceListener(listener);
        execute(iService.list(ApiUtil.getInstance().getToken(token), request));
    }

    public void detail(String token, StockDetailRequest request, ConnectServiceListener listener) {
        setConnectServiceListener(listener);
        execute(iService.detail(ApiUtil.getInstance().getToken(token), request));
    }
}
