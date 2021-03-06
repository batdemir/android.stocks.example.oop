package com.batdemir.stocks.example.oop.ui.activities.base.factory;

import androidx.viewbinding.ViewBinding;

import com.batdemir.stocks.example.oop.databinding.ActivityDetailBinding;
import com.batdemir.stocks.example.oop.databinding.ActivityHomeBinding;
import com.batdemir.stocks.example.oop.databinding.ActivityMainBinding;
import com.batdemir.stocks.example.oop.ui.activities.base.controller.BaseController;
import com.batdemir.stocks.example.oop.ui.activities.detail.DetailController;
import com.batdemir.stocks.example.oop.ui.activities.home.HomeController;
import com.batdemir.stocks.example.oop.ui.activities.main.MainController;

public class ControllerFactory {

    private static ControllerFactory instance;

    private ControllerFactory() {

    }

    public static synchronized ControllerFactory getInstance() {
        return instance = instance == null ? new ControllerFactory() : instance;
    }

    @SuppressWarnings("unchecked")
    public <C extends BaseController<?>, B extends ViewBinding> C getController(String controller, B binding) {
        if (controller == null)
            throw new NullPointerException("Controller Not Found");

        if (controller.equalsIgnoreCase("Home"))
            return (C) new HomeController((ActivityHomeBinding) binding);

        if (controller.equalsIgnoreCase("Main"))
            return (C) new MainController((ActivityMainBinding) binding);

        if (controller.equalsIgnoreCase("Detail"))
            return (C) new DetailController((ActivityDetailBinding) binding);

        throw new NullPointerException("Controller Not Found");
    }
}
