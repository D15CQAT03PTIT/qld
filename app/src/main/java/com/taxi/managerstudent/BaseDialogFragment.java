package com.taxi.managerstudent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

/**
 * Created by AnhNT on 5/6/16.
 */
public class BaseDialogFragment extends DialogFragment {

    protected Context context;

    protected int colorStatusBar = -1;
    protected boolean isFullScreen = true;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.getWindow().getAttributes().windowAnimations = R.style.inOutBottomDialog;
        if (isFullScreen) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Objects.requireNonNull(dialog.getWindow()).setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            }
        }
        if (colorStatusBar>0) {
            setStatusBarColorIfPossible(ContextCompat.getColor(context, colorStatusBar), dialog);
//            setStatusBarColorIfPossible(ContextCompat.getColor(context, R.color.gray_white), dialog);
        }
        return dialog;
    }

    public void setStatusBarColorIfPossible(int color, Dialog dialog) {
        if (dialog.getWindow() != null) {
            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.getWindow().setStatusBarColor(color);
            }
        }
    }

    public void setColorStatusBar(int colorStatusBar) {
        this.colorStatusBar = colorStatusBar;
    }

    public void setFullScreen(boolean fullScreen) {
        isFullScreen = fullScreen;
    }


    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            Fragment dialog = manager.findFragmentByTag(tag);
            if (dialog != null) {
                ft.remove(dialog);
                ft.commit();
            }
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }

    }
}
