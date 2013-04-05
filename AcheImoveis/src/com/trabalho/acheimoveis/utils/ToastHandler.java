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
	private final Typeface fontStyle;
	private String text;

	// Variable singleton
	private static ApplicationControllerWrapper application;

	public ToastHandler(Activity activity, String text) {

		this.activity = activity;

		// Sets the Toast windows parameters as id and duration
		this.text = text;
		this.fontStyle = Typeface.createFromAsset(activity.getAssets(),
				"fonts/robotoregular.ttf");

		// get the application in activity
		this.application = (ApplicationControllerWrapper) this.activity
				.getApplication();

		showToast();
	}

	// Show Toast
	public void showToast() {

		Toast toast = new Toast(activity);

		TextView textviewClipboard = new TextView(activity);
		textviewClipboard.setText(text);
		textviewClipboard.setTypeface(fontStyle);
		textviewClipboard.setPadding(10, 10, 10, 10);
		textviewClipboard.setGravity(Gravity.CENTER);

		textviewClipboard.setBackgroundColor(Color.BLACK);
		textviewClipboard.setTextColor(Color.WHITE);

		toast.setView(textviewClipboard);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.show();

	}

}
