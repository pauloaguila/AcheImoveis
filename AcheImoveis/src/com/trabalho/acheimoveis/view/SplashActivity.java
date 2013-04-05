package com.trabalho.acheimoveis.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.trabalho.acheimoveis.R;
import com.trabalho.acheimoveis.controller.ApplicationControllerWrapper;
import com.trabalho.acheimoveis.controller.SplashController;
import com.trabalho.acheimoveis.utils.AlertDialogManager;
import com.trabalho.acheimoveis.utils.MemoryHelper;

public class SplashActivity extends Activity implements Runnable {

	private Handler handler;
	private ApplicationControllerWrapper app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		// Get an intance of ApplicationControllerWrapper class
		app = (ApplicationControllerWrapper) getApplication();

		// Create the handler to handle the memory check and the database copy
		handler = new Handler();
		handler.postDelayed(this, 700);

	}

	@Override
	public void run() {

		// Call the Application Controller class to setup database file
		if (!SplashController.getInstance(getApplicationContext()).isValid()) {

			// Show dialog in case of no memory available to execute database
			// copy
			String dialogText = String.format(
					getString(R.string.splah_dialog_low_memoy_text),
					MemoryHelper.getTotalMemoryAvailable() / 1000000,
					MemoryHelper.getTotalMemoryNeeded() / 1000000);
			AlertDialogManager.showSplahErrorDialog(this, dialogText,
					getString(R.string.ok_button));

		} else {

			// After database copy, call the MenuActivity class
			Intent menu = new Intent(SplashActivity.this, MenuActivity.class);
			SplashActivity.this.startActivity(menu);
			finish();
		}

	}

	@Override
	protected void onPause() {
		super.onPause();

		// Callback all things executed inside the thread an finish the activity
		handler.removeCallbacks(this);
		finish();

	}

}
