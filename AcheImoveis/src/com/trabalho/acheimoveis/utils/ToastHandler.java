package com.trabalho.acheimoveis.utils;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.trabalho.acheimoveis.controller.ApplicationControllerWrapper;

public class ToastHandler {

    private final Activity activity;
    private final int id;
    private final Typeface fontStyle;

    // Variable singleton
    private static ApplicationControllerWrapper application;

    public ToastHandler(Activity activity, int toastId) {

        this.activity = activity;

        // Sets the Toast windows parameters as id and duration
        this.id = toastId;
        this.fontStyle = Typeface.createFromAsset(activity.getAssets(), "fonts/robotoregular.ttf");

        // get the application in activity
        this.application = (ApplicationControllerWrapper) this.activity.getApplication();

        showToast();
    }

    public void showToast() {

        Toast toast = new Toast(activity);

        switch(id) {

        case 1:

            // Show Toast

            TextView textviewClipboard = new TextView(activity);
            textviewClipboard.setText("aaaa");//activity.getString(R.string.contextual_copy_action_result_text));
            textviewClipboard.setTypeface(fontStyle);
            textviewClipboard.setPadding(10, 10, 10, 10);
            textviewClipboard.setGravity(Gravity.CENTER);

            //textviewClipboard.setBackgroundDrawable(activity.getResources().getDrawable(R.color.blackActionBarDefaultTheme));
            textviewClipboard.setTextColor(Color.WHITE);

            toast.setView(textviewClipboard);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
            break;

        default:
            break;

        }

    }

}
