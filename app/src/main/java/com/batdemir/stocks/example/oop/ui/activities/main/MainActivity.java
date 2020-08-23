package com.batdemir.stocks.example.oop.ui.activities.main;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.batdemir.stocks.example.oop.R;
import com.batdemir.stocks.example.oop.databinding.ActivityMainBinding;
import com.batdemir.stocks.example.oop.ui.activities.base.activity.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainController> {
    @Override
    public void onCreated() {
        init(new ActivityBuilder().setShowHomeButton(true).setTitle(getString(R.string.title_main)).setElevation(16f).setTheme(R.style.AppThemeActionBar));
    }

    @Override
    public void getObjectReferences() {
        //Not implemented
    }

    @Override
    public void loadData() {
        getController().getList(MainController.Periods.ALL);
    }

    @Override
    public void setListeners() {
        //Not implemented
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_type, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.menu_item_all) {
            getController().getList(MainController.Periods.ALL);
            return true;
        } else if (item.getItemId() == R.id.menu_item_decreasing) {
            getController().getList(MainController.Periods.DECREASING);
            return true;
        } else if (item.getItemId() == R.id.menu_item_increasing) {
            getController().getList(MainController.Periods.INCREASING);
            return true;
        } else if (item.getItemId() == R.id.menu_item_volume30) {
            getController().getList(MainController.Periods.VOLUME_30);
            return true;
        } else if (item.getItemId() == R.id.menu_item_volume50) {
            getController().getList(MainController.Periods.VOLUME_50);
            return true;
        } else if (item.getItemId() == R.id.menu_item_volume100) {
            getController().getList(MainController.Periods.VOLUME_100);
            return true;
        }
        return false;
    }
}
