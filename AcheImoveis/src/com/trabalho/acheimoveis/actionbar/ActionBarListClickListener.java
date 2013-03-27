package com.trabalho.acheimoveis.actionbar;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import br.org.sidia.aguilaactionbar.AguilaActionBar;

import com.trabalho.acheimoveis.utils.Constants;

public class ActionBarListClickListener implements OnClickListener {

	// private final TextChangeListener keyListener;
	private final Activity activity;
	// protected FragmentSearchResults fragSearchResults;
	// private final boolean firstSearch;
	// public static ActionBarListClickListener instance;

	private boolean flag = true;

	public ActionBarListClickListener(Activity activity) {

		this.activity = activity;

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

	public void searchImgButton() {

		clearshowKeyboard(1);

	}

	public void closeImgButton() {
		AguilaActionBar.instanceActionBar.setEditText("");
	}



	@Override
	public void onClick(View v) {
		int viewTag = Integer.parseInt("" + v.getTag());

		switch (viewTag) {
		case Constants.SEARCH_IMG_BUTTON:
			searchImgButton();
			break;

		default:
			break;
		}

	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
