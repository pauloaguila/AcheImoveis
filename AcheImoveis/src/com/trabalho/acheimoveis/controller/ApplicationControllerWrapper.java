package com.trabalho.acheimoveis.controller;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import com.trabalho.acheimoveis.R;
import com.trabalho.acheimoveis.model.DataBaseControlModel;
import com.trabalho.acheimoveis.utils.AcheimoveisSharePreferences;
import com.trabalho.acheimoveis.utils.Constants;

public class ApplicationControllerWrapper extends Application {

	// Variable singleton
	private static ApplicationControllerWrapper application;

	// activity using this application
	private Activity activityCorrent;

	// variable for control the preferences
	private AcheimoveisSharePreferences configPreference;

	// variable for control the access the database
	private DataBaseControlModel dataBaseControl;

	@Override
	public void onCreate() {
		super.onCreate();

		// Get instance of Application
		ApplicationControllerWrapper.application = (ApplicationControllerWrapper) getApplicationContext();

		// Load the preferences in application.
		PreferenceManager.setDefaultValues(this, R.xml.acheimoveispreferences,
				false);

		// Get reference for SharePreferences and make it available
		configPreference = AcheimoveisSharePreferences
				.getPreferences(getApplicationContext());

		// create the reference for database
		dataBaseControl = DataBaseControlModel
				.getInstance(getApplicationContext());

	}

	/*
	 * EXEMPLOS DE COMO SALVAR E RECUPERAR INFO DO SHARED PREFERENCES public
	 * String getSearchString() { return
	 * this.getConfigPreference().getPreferences()
	 * .getString(Constants.PREF_SEARCH_STRING, null); }
	 * 
	 * public boolean getFirtTimeOpenStatus() {
	 * 
	 * return this.getConfigPreference().getPreferences()
	 * .getBoolean(Constants.PREF_FIRST_LOAD, true);
	 * 
	 * }
	 */

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public boolean stopService(Intent name) {
		return super.stopService(name);
	}

	public static ApplicationControllerWrapper getApplication() {
		return application;
	}

	public static void setApplication(ApplicationControllerWrapper application) {
		ApplicationControllerWrapper.application = application;
	}

	public AcheimoveisSharePreferences getConfigPreference() {
		return configPreference;
	}

	public Activity getActivityCorrent() {
		return activityCorrent;
	}

	public void setActivityCorrent(Activity activityCorrent) {
		this.activityCorrent = activityCorrent;
	}

	public DataBaseControlModel getDataBaseControl() {
		return dataBaseControl;
	}

}