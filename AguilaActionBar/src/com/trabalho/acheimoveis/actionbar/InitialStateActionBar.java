package com.trabalho.acheimoveis.actionbar;

import br.org.sidia.aguilaactionbar.R;


public class InitialStateActionBar extends StateActionBar{

	@Override
	public void changeActionBar() {

		AguilaActionBar.instanceActionBar.setHomeLogo(
              R.drawable.ic_launcher, null);
	}

}
