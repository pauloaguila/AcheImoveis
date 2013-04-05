package com.trabalho.acheimoveis.view;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.trabalho.acheimoveis.R;
import com.trabalho.acheimoveis.actionbar.ActionBarListClickListener;
import com.trabalho.acheimoveis.actionbar.AguilaActionBar;
import com.trabalho.acheimoveis.actionbar.InitialStateMenu;
import com.trabalho.acheimoveis.actionbar.StateActionBar;

public class MenuActivity extends FragmentActivity {
	
	private ActionBarListClickListener clickListener;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main);
        
        clickListener = new ActionBarListClickListener(this, null, getSupportFragmentManager());
        changeActionBar();
	}
	
    public void changeActionBar() {
        	StateActionBar state = new InitialStateMenu(clickListener);
        	AguilaActionBar.instanceActionBar.setStateActionBar(state);

    }

}
