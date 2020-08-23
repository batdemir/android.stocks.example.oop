package com.batdemir.stocks.example.oop.ui.activities.detail;

import android.app.Activity;

import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.android.batdemir.mydialog.ui.MyDialogStyle;
import com.android.batdemir.mylibrary.connection.ConnectServiceListener;
import com.batdemir.api.OperationTypes;
import com.batdemir.api.services.Stocks;
import com.batdemir.entity.model.stocks.StockDetailRequest;
import com.batdemir.entity.model.stocks.StockDetailResponse;
import com.batdemir.stocks.example.oop.R;
import com.batdemir.stocks.example.oop.databinding.ActivityDetailBinding;
import com.batdemir.stocks.example.oop.helper.GlobalVariable;
import com.batdemir.stocks.example.oop.ui.activities.base.controller.BaseController;
import com.batdemir.utilities.CryptUtil;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class DetailController extends BaseController<ActivityDetailBinding> {
    private int id;
    private StockDetailResponse stockDetailResponse;

    public DetailController(ActivityDetailBinding binding) {
        super(binding);
        id = ((Activity) getContext()).getIntent().getIntExtra("id", 0);
    }

    void getData() {
        new Stocks(getContext(), OperationTypes.STOCK_DETAIL.name())
                .detail(GlobalVariable.getHandshake().getAuthorization(), getRequest(), new ConnectServiceListener() {
                    @Override
                    public void onSuccess(String s, Response<?> response) {
                        StockDetailResponse body = (StockDetailResponse) response.body();
                        if (body == null) {
                            showMessage("Body is null");
                            return;
                        }
                        if (body.getStatus().isSuccess()) {
                            stockDetailResponse = body;
                            fillUi();
                            setChart();
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

    private void showMessage(String message) {
        new MyAlertDialog.Builder()
                .setMessage(message)
                .setStyle(MyDialogStyle.WARNING)
                .build()
                .show(getFragmentManager(), "");
    }

    private void fillUi() {
        getBinding().txtEditSymbol.setText(String.format(GlobalVariable.getLocale(), "%s", CryptUtil.getInstance().decrypt(stockDetailResponse.getSymbol(), GlobalVariable.getHandshake().getAesKey(), GlobalVariable.getHandshake().getAesIV())));
        getBinding().txtEditPrice.setText(String.format(GlobalVariable.getLocale(), "%.2f", stockDetailResponse.getPrice()));
        getBinding().txtEditDifference.setText(String.format(GlobalVariable.getLocale(), "%.2f", stockDetailResponse.getChannge()));
        getBinding().txtEditVolume.setText(String.format(GlobalVariable.getLocale(), "%.2f", stockDetailResponse.getVolume()));
        getBinding().txtEditOffer.setText(String.format(GlobalVariable.getLocale(), "%.2f", stockDetailResponse.getOffer()));
        getBinding().txtEditBid.setText(String.format(GlobalVariable.getLocale(), "%.2f", stockDetailResponse.getBid()));
        getBinding().txtEditDailyMin.setText(String.format(GlobalVariable.getLocale(), "%.2f", stockDetailResponse.getMinimum()));
        getBinding().txtEditDailyMax.setText(String.format(GlobalVariable.getLocale(), "%.2f", stockDetailResponse.getMaximum()));
        getBinding().txtEditCount.setText(String.format(GlobalVariable.getLocale(), "%d", stockDetailResponse.getCount()));
        getBinding().txtEditMax.setText(String.format(GlobalVariable.getLocale(), "%.2f", stockDetailResponse.getMaximum()));
        getBinding().txtEditMin.setText(String.format(GlobalVariable.getLocale(), "%.2f", stockDetailResponse.getMinimum()));
        getBinding().imgStatus.setImageResource(stockDetailResponse.isUp() ? R.drawable.ic_up : R.drawable.ic_down);
        getBinding().imgStatus.setImageTintList(getContext().getColorStateList(stockDetailResponse.isUp() ? R.color.darkGreen : R.color.red));
    }

    private void setChart() {
        List<Entry> entryList = new ArrayList<>();
        stockDetailResponse.getGraphicData().forEach(x -> entryList.add(new Entry(x.getDay(), x.getValue())));

        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
        LineDataSet lineDataSet = new LineDataSet(entryList, "Datas");
        iLineDataSets.add(lineDataSet);
        getBinding().chart.setData(new LineData(iLineDataSets));
        getBinding().chart.setDescription(null);
        getBinding().chart.invalidate();
    }

    private StockDetailRequest getRequest() {
        StockDetailRequest request = new StockDetailRequest();
        request.setId(CryptUtil.getInstance().encrypt(String.valueOf(id), GlobalVariable.getHandshake().getAesKey(), GlobalVariable.getHandshake().getAesIV()));
        return request;
    }
}
