package com.batdemir.utilities;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.Objects;

public class UiUtil {

    private static UiUtil instance;

    private UiUtil() {

    }

    public static UiUtil getInstance() {
        if (instance == null)
            instance = new UiUtil();
        return instance;
    }

    @SuppressLint("MissingPermission")
    public void errorProcess(View view) {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", 0f, 1f, 0f, -1f, 0f);
        rotate.setRepeatCount(5);
        rotate.setDuration(25);
        rotate.start();
        Vibrator vibrator = (Vibrator) view.getContext().getSystemService(Context.VIBRATOR_SERVICE);
        Objects.requireNonNull(vibrator).vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
    }

    public void changeViewToProgress(View view) {
        ProgressBar progressBar = new ProgressBar(view.getContext(), (AttributeSet) null, 16842871);
        progressBar.setLayoutParams(view.getLayoutParams());
        progressBar.setIndeterminate(true);
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        viewGroup.addView(progressBar);
        view.setVisibility(View.GONE);
    }
}
