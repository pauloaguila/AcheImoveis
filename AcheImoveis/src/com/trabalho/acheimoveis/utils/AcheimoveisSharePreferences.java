package com.trabalho.acheimoveis.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AcheimoveisSharePreferences {

	private final SharedPreferences sharePreferences;
	private static AcheimoveisSharePreferences instance = null;

	// Create the instance of SharedPreferences class
	private AcheimoveisSharePreferences(Context context) {
		sharePreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
	}

	public static AcheimoveisSharePreferences getPreferences(Context context) {

		if (instance == null) {
			instance = new AcheimoveisSharePreferences(context);
		}
		return instance;
	}

	public SharedPreferences getPreferences() {
		return this.sharePreferences;
	}

	public SharedPreferences.Editor getPreferencesEditor() {
		return this.sharePreferences.edit();
	}
}
