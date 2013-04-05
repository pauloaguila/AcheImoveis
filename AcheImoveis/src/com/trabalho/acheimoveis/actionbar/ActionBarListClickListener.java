package com.trabalho.acheimoveis.actionbar;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.trabalho.acheimoveis.interfaces.ActionsButtonsActionBar;
import com.trabalho.acheimoveis.utils.GeocoderTask;

public class ActionBarListClickListener implements ActionsButtonsActionBar {

	// private final TextChangeListener keyListener;
	private final Activity activity;
	// protected FragmentSearchResults fragSearchResults;
	// private final boolean firstSearch;
	// public static ActionBarListClickListener instance;
	private final Context context;
	private final GoogleMap map;
	private String searchString;

	public ActionBarListClickListener(Activity activity, Context context,
			GoogleMap map) {

		this.activity = activity;
		this.context = context;
		this.map = map;

	}

	public void loadMapLocation(String address) {
		new GeocoderTask(this.map).execute(address);
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

		// Get search string from search field
		searchString = AguilaActionBar.instanceActionBar.getEditTextContent();

		editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH
						&& searchString.length() != 0) {
					String address = mountAddress(searchString + ",manaus");

					loadMapLocation(address);

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
	public void cleanSearchClicked() {
		AguilaActionBar.instanceActionBar.setEditText("");

	}
}
