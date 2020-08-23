package com.batdemir.entity.model.stocks;

import com.batdemir.entity.model.core.BaseEntity;

import java.util.List;

public class StocksResponse extends BaseEntity {
    private List<Stock> stocks;

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}

