package com.batdemir.stocks.example.oop.ui.activities.base.factory;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.viewbinding.ViewBinding;

import com.batdemir.stocks.example.oop.databinding.ActivityDetailBinding;
import com.batdemir.stocks.example.oop.databinding.ActivityHomeBinding;
import com.batdemir.stocks.example.oop.databinding.ActivityMainBinding;

public class BindingFactory {

    private static BindingFactory instance;

    private BindingFactory() {

    }

    public static synchronized BindingFactory getInstance() {
        if (instance == null)
            instance = new BindingFactory();
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <B extends ViewBinding> B getBinding(String strBinding, LayoutInflater inflater) {
        if (strBinding == null)
            throw new NullPointerException("Binding Not Found");

        if (strBinding.equalsIgnoreCase("Home")) {
            ActivityHomeBinding binding = ActivityHomeBinding.inflate(inflater);
            ((Activity) inflater.getContext()).setContentView(binding.getRoot());
            return (B) binding;
        }

        if (strBinding.equalsIgnoreCase("Main")) {
            ActivityMainBinding binding = ActivityMainBinding.inflate(inflater);
            ((Activity) inflater.getContext()).setContentView(binding.getRoot());
            return (B) binding;
        }

        if (strBinding.equalsIgnoreCase("Detail")) {
            ActivityDetailBinding binding = ActivityDetailBinding.inflate(inflater);
            ((Activity) inflater.getContext()).setContentView(binding.getRoot());
            return (B) binding;
        }

        throw new NullPointerException("Binding Not Found");
    }
}
