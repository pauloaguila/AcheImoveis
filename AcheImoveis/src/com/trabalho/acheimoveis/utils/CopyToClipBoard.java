package com.trabalho.acheimoveis.utils;

import android.app.Activity;
import android.content.Context;
import android.text.ClipboardManager;

public class CopyToClipBoard {

	private final Activity activity;

	public CopyToClipBoard(Activity activity) {

		this.activity = activity;

		// Copy the text to Clip Board area
		ClipboardManager clipboard = (ClipboardManager) activity
				.getSystemService(Context.CLIPBOARD_SERVICE);
		clipboard.setText(mountMensagem());

		// Call Toast after copy is succeded
		new ToastHandler(activity, "");

	}

	private String mountMensagem() {

		StringBuffer mensagemFormat = new StringBuffer();

		// Return the final message to be copied into clipboard
		return mensagemFormat.toString();
	}
}
