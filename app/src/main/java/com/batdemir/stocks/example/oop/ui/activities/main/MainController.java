package com.batdemir.stocks.example.oop.ui.activities.main;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;

import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.android.batdemir.mydialog.ui.MyDialogStyle;
import com.android.batdemir.mylibrary.connection.ConnectServiceListener;
import com.android.batdemir.mylibrary.tools.Tool;
import com.batdemir.api.OperationTypes;
import com.batdemir.api.services.Stocks;
import com.batdemir.entity.model.stocks.Stock;
import com.batdemir.entity.model.stocks.StocksRequest;
import com.batdemir.entity.model.stocks.StocksResponse;
import com.batdemir.stocks.example.oop.databinding.ActivityMainBinding;
import com.batdemir.stocks.example.oop.helper.GlobalVariable;
import com.batdemir.stocks.example.oop.ui.activities.base.controller.BaseController;
import com.batdemir.stocks.example.oop.ui.activities.detail.DetailActivity;
import com.batdemir.stocks.example.oop.ui.adapters.RecyclerAdapterMain;
import com.batdemir.utilities.CryptUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Response;

public class MainController extends BaseController<ActivityMainBinding> {

    private List<Stock> list;
    private RecyclerAdapterMain adapterMain;

    public MainController(ActivityMainBinding binding) {
        super(binding);
        adapterMain = new RecyclerAdapterMain(getContext(), new ArrayList<>(), getListener());
        setRecyclerView();
    }

    void getList(Periods periods) {
        clearAdapter();
        new Stocks(getContext(), OperationTypes.STOCKS.name())
                .list(GlobalVariable.getHandshake().getAuthorization(), getRequest(periods), new ConnectServiceListener() {
                    @Override
                    public void onSuccess(String s, Response<?> response) {
                        StocksResponse body = (StocksResponse) response.body();
                        if (body == null) {
                            showMessage("Body is null");
                            return;
                        }
                        if (body.getStatus().isSuccess()) {
                            list = body.getStocks();
                            setOptimizeList(list);
                            fillList(list);
                        } else {
                            showMessage(body.getStatus().getError().getMessage());
                        }
                    }

                    @Override
                    public void onFailure(String s, Response<?> response) {
                        try {
                            showMessage(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    synchronized void setOptimizeList(List<Stock> list) {
        list.forEach(x -> x.setSymbol(CryptUtil.getInstance().decrypt(x.getSymbol(), GlobalVariable.getHandshake().getAesKey(), GlobalVariable.getHandshake().getAesIV())));
        Collections.sort(list, (o1, o2) -> o1.getSymbol().compareTo(o2.getSymbol()));
    }

    synchronized void fillList(List<Stock> list) {
        adapterMain.getModels().addAll(list);
        adapterMain.notifyItemRangeInserted(0, list.size());
        getBinding().recyclerView.setItemViewCacheSize(list.size());
    }

    private synchronized void clearAdapter() {
        int temp = adapterMain.getItemCount();
        adapterMain.getModels().clear();
        adapterMain.notifyItemRangeRemoved(0, temp);
    }

    private void setRecyclerView() {
        getBinding().recyclerView.setAdapter(adapterMain);
        getBinding().recyclerView.setItemViewCacheSize(adapterMain.getItemCount());
        getBinding().recyclerView.setHasFixedSize(true);
        getBinding().searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return searchAction(query);
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return searchAction(newText);
            }
        });
    }

    private boolean searchAction(String text) {
        List<Stock> result;
        clearAdapter();
        if (text.isEmpty()) {
            result = list;
        } else {
            result = list.stream().filter(x -> x.getSymbol().toLowerCase().trim().startsWith(text.toLowerCase().trim())).collect(Collectors.toList());
        }
        setOptimizeList(result);
        fillList(result);
        return false;
    }

    private RecyclerAdapterMain.ItemListener getListener() {
        return item -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", item.getId());
            Tool.getInstance(getContext()).move(DetailActivity.class, true, true, bundle);
        };
    }

    private void showMessage(String message) {
        new MyAlertDialog.Builder()
                .setMessage(message)
                .setStyle(MyDialogStyle.WARNING)
                .build()
                .show(getFragmentManager(), "");
    }

    private StocksRequest getRequest(Periods periods) {
        StocksRequest stocksRequest = new StocksRequest();
        stocksRequest.setPeriod(CryptUtil.getInstance().encrypt(periods.toString(), GlobalVariable.getHandshake().getAesKey(), GlobalVariable.getHandshake().getAesIV()));
        return stocksRequest;
    }

    public enum Periods {
        ALL("all"),
        INCREASING("increasing"),
        DECREASING("decreasing"),
        VOLUME_30("volume30"),
        VOLUME_50("volume50"),
        VOLUME_100("volume100");

        private String value;

        Periods(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
