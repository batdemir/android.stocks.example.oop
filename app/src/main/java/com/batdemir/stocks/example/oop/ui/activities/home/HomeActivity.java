package com.batdemir.stocks.example.oop.ui.activities.home;

import com.batdemir.stocks.example.oop.R;
import com.batdemir.stocks.example.oop.databinding.ActivityHomeBinding;
import com.batdemir.stocks.example.oop.ui.activities.base.activity.BaseActivity;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeController> {
    @Override
    public void onCreated() {
        init(new ActivityBuilder().setTitle(getString(R.string.title_home)).setElevation(16f).setFirstActivity(true).setTheme(R.style.AppThemeActionBar));
    }

    @Override
    public void getObjectReferences() {
        //Not implemented
    }

    @Override
    public void loadData() {
        //Not implemented
    }

    @Override
    public void setListeners() {
        getBinding().btnImkb.setOnClickListener(getController().onClickListener());
    }
}
