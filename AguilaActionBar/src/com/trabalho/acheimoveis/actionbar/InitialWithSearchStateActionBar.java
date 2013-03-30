package com.trabalho.acheimoveis.actionbar;

import android.view.View;
import android.view.View.OnClickListener;
import br.org.sidia.aguilaactionbar.R;

import com.trabalho.acheimoveis.actionbar.AguilaActionBar.FunctionsActionBar;
import com.trabalho.acheimoveis.interfaces.ActionsBunttosActionBar;

public class InitialWithSearchStateActionBar extends StateActionBar{
	
	private ActionsBunttosActionBar actions;

	public InitialWithSearchStateActionBar(ActionsBunttosActionBar action){
		
		actions = action;
	}


	@Override
	public void changeActionBar() {
		
		

		AguilaActionBar.instanceActionBar.new FunctionsActionBar().setHomeLogo(
	              R.drawable.ic_launcher, null);
			
		AguilaActionBar.instanceActionBar.new FunctionsActionBar().addActionIcon(
	              R.drawable.actionbar_pesquisar, true, View.VISIBLE,
	              onClickListener,0);		
		
	}
	
	private OnClickListener onClickListener = new OnClickListener() {
	    @Override
	    public void onClick(final View v) {
	     
	    	actions.searchClicked();
	    }
	};

}
