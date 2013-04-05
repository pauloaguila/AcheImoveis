package com.trabalho.acheimoveis.actionbar;

import android.view.View;
import android.view.View.OnClickListener;
import br.org.sidia.aguilaactionbar.R;

import com.trabalho.acheimoveis.interfaces.ActionsButtonsActionBar;

public class InitialStateMenu extends StateActionBar{

	private ActionsButtonsActionBar actions;

	public InitialStateMenu(ActionsButtonsActionBar action){
		
		actions = action;
	}


	@Override
	public void changeActionBar() {
	
		AguilaActionBar.instanceActionBar.new FunctionsActionBar().removeAllItemsActionIcon();
		AguilaActionBar.instanceActionBar.new FunctionsActionBar().setHomeLogo(
	              R.drawable.ic_launcher, null);
			
		AguilaActionBar.instanceActionBar.new FunctionsActionBar().addActionIcon(
	              R.drawable.actionbar_contraste, true, View.VISIBLE,
	              onClickListener);		
		
	}
	
	private OnClickListener onClickListener = new OnClickListener() {
	    @Override
	    public void onClick(final View v) {
	    	actions.goMapClicked();
	    }
	};

}
