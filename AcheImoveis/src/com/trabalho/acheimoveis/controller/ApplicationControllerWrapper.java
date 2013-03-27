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
        PreferenceManager.setDefaultValues(this, R.xml.acheimoveispreferences,false);

        // Get reference for SharePreferences and make it available
        configPreference = AcheimoveisSharePreferences
            .getPreferences(getApplicationContext());

        // create the reference for database
        dataBaseControl = DataBaseControlModel
            .getInstance(getApplicationContext());

        // Load first configs
        this.loadFirstLoadConfigurationPreferences();

        // Set default contrast
        // this.setChangeContrast();
        // this.setSearchString("");

    }

    public void loadFirstLoadConfigurationPreferences() {

        final Editor editor = this.getConfigPreference().getPreferencesEditor();

        // First time using application
        if (this.getConfigPreference().getPreferences()
            .getBoolean(Constants.PREF_FIRST_LOAD, true)) {

            // Set the first configuration in preferences
            editor.putString(Constants.PREF_BOOK_SELECTED, "1");
            editor.putString(Constants.PREF_CHAP_SELECTED, "1");
            editor.putString(Constants.PREF_VERS_SELECTED, "1");

            // Commit changes on shared preferences
            editor.commit();
        }
    }

    public void setSearchString(String searchStr) {
        final Editor editor = this.getConfigPreference().getPreferencesEditor();
        editor.putString(Constants.PREF_SEARCH_STRING, searchStr);
        editor.commit();
    }

    public String getSearchString() {
        return this.getConfigPreference().getPreferences()
            .getString(Constants.PREF_SEARCH_STRING, null);
    }

   

    

    

    public boolean getFirtTimeOpenStatus() {

        return this.getConfigPreference().getPreferences()
            .getBoolean(Constants.PREF_FIRST_LOAD, true);

    }

    public void setFrstTimeOpened() {

        final Editor editor = this.getConfigPreference().getPreferencesEditor();

        // Set first time to false meaning that this is no longer the first
        // load
        editor.putBoolean(Constants.PREF_FIRST_LOAD, false);

        // Commit changes on shared preferences
        editor.commit();
    }

    public void setFirstSearch(boolean bol) {

        final Editor editor = this.getConfigPreference().getPreferencesEditor();

        // Set shared preferences information from the last read
        editor.putBoolean(Constants.PREF_FIRST_SEARCH, bol);

        // Commit changes on shared preferences
        editor.commit();
    }

    public boolean getFirtSearchStatus() {

        return this.getConfigPreference().getPreferences()
            .getBoolean(Constants.PREF_FIRST_SEARCH, true);

    }

    public boolean getContrastNow() {
        return this.getConfigPreference().getPreferences()
            .getBoolean(Constants.CONTRAST, false);
    }

    public void setChangeContrast() {

        final Editor editor = this.getConfigPreference().getPreferencesEditor();

        boolean constranstNow = this.getConfigPreference().getPreferences()
            .getBoolean(Constants.CONTRAST, false);

        if (constranstNow) {

            // Set first time to false meaning that this is no longer the first
            // load
            editor.putBoolean(Constants.CONTRAST, false);

        } else {
            editor.putBoolean(Constants.CONTRAST, true);
        }

        // Commit changes on shared preferences
        editor.commit();
    }

    public float getFontSizeNow() {
        return this.getConfigPreference().getPreferences()
            .getFloat(Constants.TEXT_FONTSIZE, 14);
    }

    public void setChangeFontSize(float b) {
        final Editor editor = this.getConfigPreference().getPreferencesEditor();

        editor.putFloat(Constants.TEXT_FONTSIZE, b);

        // Commit changes on shared preferences
        editor.commit();
    }

    public int getConfigFontSize() {
        return this.getConfigPreference().getPreferences()
            .getInt(Constants.CONFIG_FONTSIZE, 2);
    }

    public void setChangeConfigFontSize(int b) {
        final Editor editor = this.getConfigPreference().getPreferencesEditor();

        editor.putInt(Constants.CONFIG_FONTSIZE, b);

        // Commit changes on shared preferences
        editor.commit();
    }

    public void removeFirstSearch() {

        final Editor editor = this.getConfigPreference().getPreferencesEditor();

        // Set shared preferences information from the last read
        editor.putBoolean(Constants.PREF_FIRST_SEARCH, false);

        // Commit changes on shared preferences
        editor.commit();
    }

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

    /**
     * After load the set the preference for true
     */
    public void setPrefFirstLoadForTrue() {

        final Editor edt = this.getConfigPreference().getPreferencesEditor();
        edt.putBoolean(Constants.PREF_FIRST_LOAD, true);
    }

    public DataBaseControlModel getDataBaseControl() {
        return dataBaseControl;
    }

    public boolean getShowSpeak() {
        return this.getConfigPreference().getPreferences()
            .getBoolean(Constants.SHOW_SPEAK, true);
    }

    public void setShowSpeakRemove() {

        final Editor editor = this.getConfigPreference().getPreferencesEditor();

        // Set shared preferences information from the last read
        editor.putBoolean(Constants.SHOW_SPEAK, false);

        // Commit changes on shared preferences
        editor.commit();

    }

    public void setChangeContrast(boolean b) {
        final Editor editor = this.getConfigPreference().getPreferencesEditor();

        editor.putBoolean(Constants.CONTRAST, b);

        // Commit changes on shared preferences
        editor.commit();

    }

    public void setLastVerseOnTop(Integer lastVerseOnTop) {

        final Editor editor = this.getConfigPreference().getPreferencesEditor();

        // Set shared preferences information from the last read
        editor.putInt(Constants.PREF_VERS_ON_TOP, lastVerseOnTop);

        // Commit changes on shared preferences
        editor.commit();

    }

    public int getLastVerseOnTop() {
        return this.getConfigPreference().getPreferences()
            .getInt(Constants.PREF_VERS_ON_TOP, 0);
    }

    public void setLastVerseOfDay(Integer lastVerseDay) {

        final Editor editor = this.getConfigPreference().getPreferencesEditor();

        // Set shared preferences information from the last read
        editor.putInt(Constants.PREF_VERS_OF_DAY, lastVerseDay);

        // Commit changes on shared preferences
        editor.commit();

    }

    public int getLastVerseOfDay() {
        return this.getConfigPreference().getPreferences()
            .getInt(Constants.PREF_VERS_OF_DAY, 0);
    }

    public void setLastDateVerseOfDay(long dateTimeVerseDay) {

        final Editor editor = this.getConfigPreference().getPreferencesEditor();

        // Set shared preferences information from the last read
        editor.putLong(Constants.PREF_DATE_VERS_OF_DAY, dateTimeVerseDay);

        // Commit changes on shared preferences
        editor.commit();

    }

    public long getLastDateVerseOfDay() {
        return this.getConfigPreference().getPreferences()
            .getLong(Constants.PREF_DATE_VERS_OF_DAY, 0);
    }

}
