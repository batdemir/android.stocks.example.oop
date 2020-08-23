package com.batdemir.stocks.example.oop.ui.activities.home;

import android.os.Build;
import android.view.View;

import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.android.batdemir.mydialog.ui.MyDialogStyle;
import com.android.batdemir.mylibrary.connection.ConnectServiceListener;
import com.android.batdemir.mylibrary.tools.Tool;
import com.batdemir.api.OperationTypes;
import com.batdemir.api.services.Handshake;
import com.batdemir.entity.model.handshake.HandshakeRequest;
import com.batdemir.entity.model.handshake.HandshakeResponse;
import com.batdemir.stocks.example.oop.databinding.ActivityHomeBinding;
import com.batdemir.stocks.example.oop.helper.GlobalVariable;
import com.batdemir.stocks.example.oop.ui.activities.base.controller.BaseController;
import com.batdemir.stocks.example.oop.ui.activities.main.MainActivity;
import com.batdemir.utilities.UiUtil;

import java.io.IOException;
import java.util.UUID;

import retrofit2.Response;

public class HomeController extends BaseController<ActivityHomeBinding> {
    public HomeController(ActivityHomeBinding binding) {
        super(binding);
    }

    View.OnClickListener onClickListener() {
        return v -> {
            UiUtil.getInstance().changeViewToProgress(v);
            new Handshake(getContext(), OperationTypes.HANDSHAKE.name())
                    .start(getRequest(), new ConnectServiceListener() {
                        @Override
                        public void onSuccess(String s, Response<?> response) {
                            HandshakeResponse body = (HandshakeResponse) response.body();
                            if (body == null) {
                                showMessage("Body is null");
                                return;
                            }
                            if (body.getStatus().isSuccess()) {
                                GlobalVariable.setHandshake(body);
                                Tool.getInstance(getContext()).move(MainActivity.class, true, true, null);
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
        };
    }

    private void showMessage(String message) {
        new MyAlertDialog.Builder()
                .setMessage(message)
                .setStyle(MyDialogStyle.WARNING)
                .build()
                .show(getFragmentManager(), "");
    }

    private HandshakeRequest getRequest() {
        HandshakeRequest handshakeRequest = new HandshakeRequest();
        handshakeRequest.setDeviceId(UUID.randomUUID().toString());
        handshakeRequest.setSystemVersion(Build.VERSION.RELEASE);
        handshakeRequest.setPlatformName("Android");
        handshakeRequest.setDeviceModel(Build.MODEL);
        handshakeRequest.setManifacturer(Build.MANUFACTURER);
        return handshakeRequest;
    }
}
