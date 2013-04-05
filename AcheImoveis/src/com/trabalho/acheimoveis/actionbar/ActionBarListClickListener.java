package com.trabalho.acheimoveis.actionbar;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.trabalho.acheimoveis.R;
import com.trabalho.acheimoveis.interfaces.ActionsButtonsActionBar;
import com.trabalho.acheimoveis.utils.GeocoderTask;
import com.trabalho.acheimoveis.utils.ToastHandler;
import com.trabalho.acheimoveis.view.FragmentMap;

public class ActionBarListClickListener implements ActionsButtonsActionBar {

	// private final TextChangeListener keyListener;
	private final FragmentActivity activity;
	// protected FragmentSearchResults fragSearchResults;
	// private final boolean firstSearch;
	// public static ActionBarListClickListener instance;
	private final Context context;
	private final GoogleMap map;
	private String searchString;
	private final FragmentManager manager;

	public ActionBarListClickListener(FragmentActivity activity, GoogleMap map, FragmentManager fragman) {

		this.activity = activity;
		this.context = activity.getBaseContext();
		this.map = map;
		this.manager = fragman;

	}

	public void loadMapLocation(String address) {
		new GeocoderTask(activity, this.map).execute(address);
		clearshowKeyboard(0);
	}

	public void clearshowKeyboard(int op) {

		if (activity != null) {
			InputMethodManager imm = (InputMethodManager) activity
					.getSystemService(activity.getApplicationContext().INPUT_METHOD_SERVICE);

			View currentfocus = activity.getWindow().getCurrentFocus();

			if (currentfocus != null) {
				if (op == 0)
					imm.hideSoftInputFromWindow(currentfocus.getWindowToken(),
							0);
				else
					imm.showSoftInput(currentfocus, 0);
			}

		}
	}

	public String mountAddress(String address) {
		String addr = address.replace(" ", "+");
		return addr;
	}

	@Override
	public void searchClicked() {

		StateActionBar state = new MakeSearchStateActionBar(this);
		AguilaActionBar.instanceActionBar.setStateActionBar(state);

		clearshowKeyboard(1);

		EditText editText = AguilaActionBar.instanceActionBar.getEditText();
		editText.clearFocus();
		editText.requestFocus();

		editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if ( AguilaActionBar.instanceActionBar
								.getEditTextContent().length() != 0) {
					String address = mountAddress(AguilaActionBar.instanceActionBar
							.getEditTextContent() + " manaus");

					loadMapLocation(address);

				} else {
					if (searchString.length() == 0) {
						new ToastHandler(
								activity,
								activity.getString(R.string.map_dialog_empty_search_field));
					}
				}
				return true;
			}
		});

	}

	@Override
	public void homeLogoClicked() {
		// TODO Auto-generated method stub

	}

	@Override
	public void makeSearchClicked() {
		// TODO Auto-generated method stub

	}

	@Override
	public void goMapClicked() {
		
		FragmentMap fragmap = new FragmentMap();
		
		FragmentTransaction frmT = this.manager.beginTransaction();
		frmT.replace(R.id.frame, fragmap, "FragMap");
		frmT.addToBackStack("FragMap");
		frmT.commit();

	}

	@Override
	public void cleanSearchClicked() {
		AguilaActionBar.instanceActionBar.setEditText("");

	}
}
