package com.batdemir.stocks.example.oop.ui.activities.detail;

import com.batdemir.stocks.example.oop.R;
import com.batdemir.stocks.example.oop.databinding.ActivityDetailBinding;
import com.batdemir.stocks.example.oop.ui.activities.base.activity.BaseActivity;

public class DetailActivity extends BaseActivity<ActivityDetailBinding, DetailController> {
    @Override
    public void onCreated() {
        init(new ActivityBuilder().setTitle(getString(R.string.title_detail)).setShowHomeButton(true).setElevation(16f).setTheme(R.style.AppThemeActionBar));
    }

    @Override
    public void getObjectReferences() {
        //Not implemented
    }

    @Override
    public void loadData() {
        getController().getData();
    }

    @Override
    public void setListeners() {
        //Not implemented
    }
}
