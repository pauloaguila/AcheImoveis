package com.trabalho.acheimoveis.actionbar;

import android.view.View;
import android.view.View.OnClickListener;
import br.org.sidia.aguilaactionbar.R;

import com.trabalho.acheimoveis.actionbar.AguilaActionBar.FunctionsActionBar;
import com.trabalho.acheimoveis.interfaces.ActionsBunttosActionBar;


public class MakeSearchStateActionBar extends StateActionBar{

	private ActionsBunttosActionBar actions;

	public MakeSearchStateActionBar(ActionsBunttosActionBar action){
		
		actions = action;
	}
	
	@Override
	public void changeActionBar() {
		
		AguilaActionBar.instanceActionBar.new FunctionsActionBar().removeAllItemsActionIcon();
		AguilaActionBar.instanceActionBar.new FunctionsActionBar().setHomeLogo(R.drawable.actionbar_up_icon, onClickListenerHome);
        
		AguilaActionBar.instanceActionBar.new FunctionsActionBar().addActionEdiText(R.dimen.width_actionbar_search,
            R.dimen.height_actionbar_search);
        
		AguilaActionBar.instanceActionBar.new FunctionsActionBar().addActionIcon(
	              R.drawable.pesquisar_x, true, View.VISIBLE,
	              onClickListenerCleanSearch); 
		
	}
	
	
	private OnClickListener onClickListenerHome = new OnClickListener() {
	    @Override
	    public void onClick(final View v) {
	     
	    	actions.homeLogoClicked();
	    }
	};
	
	private OnClickListener onClickListenerCleanSearch = new OnClickListener() {
	    @Override
	    public void onClick(final View v) {
	     
	    	actions.cleanSearchClicked();
	    }
	};

}
