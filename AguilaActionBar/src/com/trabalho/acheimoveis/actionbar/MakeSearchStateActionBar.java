package com.trabalho.acheimoveis.actionbar;

import android.view.View;
import android.view.View.OnClickListener;
import br.org.sidia.aguilaactionbar.R;

import com.trabalho.acheimoveis.interfaces.ActionsBunttosActionBar;


public class MakeSearchStateActionBar extends StateActionBar{

	private ActionsBunttosActionBar actions;

	public MakeSearchStateActionBar(ActionsBunttosActionBar action){
		
		actions = action;
	}
	
	@Override
	public void changeActionBar() {
		
		AguilaActionBar.instanceActionBar.removeAllItemsActionIcon();
        AguilaActionBar.instanceActionBar.setHomeLogo(R.drawable.actionbar_up_icon, onClickListenerHome);
        
        AguilaActionBar.instanceActionBar.addActionEdiText(R.dimen.width_actionbar_search,
            R.dimen.height_actionbar_search);
        
        AguilaActionBar.instanceActionBar.addActionIcon(
	              R.drawable.pesquisar_x, true, View.VISIBLE,
	              onClickListenerCleanSearch,0);        

		
		
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
