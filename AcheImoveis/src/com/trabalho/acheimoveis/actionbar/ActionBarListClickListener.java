package com.trabalho.acheimoveis.actionbar;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.trabalho.acheimoveis.R;
import com.trabalho.acheimoveis.utils.Constants;
import com.trabalho.acheimoveis.utils.GeocoderTask;

public class ActionBarListClickListener implements OnClickListener {

	// private final TextChangeListener keyListener;
	private final Activity activity;
	// protected FragmentSearchResults fragSearchResults;
	// private final boolean firstSearch;
	// public static ActionBarListClickListener instance;
	private final Context context;
	private final GoogleMap map;

	public ActionBarListClickListener(Activity activity, Context context, GoogleMap map) {

		this.activity = activity;
		this.context = context;
		this.map = map;

	}
	
	public void loadMapLocation(String address){
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

	public void searchImgButton() {
		AguilaActionBar.instanceActionBar.removeAllItemsActionIcon();
        AguilaActionBar.instanceActionBar.setHomeLogo(
            R.drawable.actionbar_up_icon, this,
            Constants.HOME_IMG_BUTTON);
        AguilaActionBar.instanceActionBar.addActionEdiText(R.dimen.width_actionbar_search,
            R.dimen.height_actionbar_search);
        AguilaActionBar.instanceActionBar.addActionIcon(
            R.drawable.pesquisar_x, true, View.VISIBLE,
            this, Constants.CLOSE_IMG_BUTTON);
		clearshowKeyboard(1);
		
		EditText editText = AguilaActionBar.instanceActionBar.getEditText();

        editText.clearFocus();
        
        editText.requestFocus();
        
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                    && AguilaActionBar.instanceActionBar
                        .getEditTextContent().length() != 0) {
                	String address = mountAddress(AguilaActionBar.instanceActionBar.getEditTextContent() + ", manaus");
                	
                	loadMapLocation(address);
                	
                    
                    return true;
                }
                return true;
            }
        });

	}
	
	public String mountAddress(String address){
		String addr = address.replace(" ", "+");
		return addr;
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
		case Constants.CLOSE_IMG_BUTTON:
			closeImgButton();
			break;

		default:
			break;
		}

	}
}
