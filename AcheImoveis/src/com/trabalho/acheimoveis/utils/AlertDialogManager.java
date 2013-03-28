package com.trabalho.acheimoveis.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

public class AlertDialogManager {

	// Debug purposes
	public static final String TAG = AlertDialogManager.class.getSimpleName();

	private static ProgressDialog progressDialog;
	private static AlertDialog.Builder alertDialogBuild;

	public static AlertDialog.Builder showAlertDialog(Activity context,
			int idLayout) {

		LayoutInflater layoutInfla = LayoutInflater.from(context);
		View viewLayout = layoutInfla.inflate(idLayout, null);

		alertDialogBuild = new AlertDialog.Builder(context);

		alertDialogBuild.setInverseBackgroundForced(true);

		alertDialogBuild.setView(viewLayout);

		return alertDialogBuild;
	}

	public static void showAlertDialog(Context context, String title,
			String message, Boolean status) {

		alertDialogBuild = new AlertDialog.Builder(context);

		// Setting Dialog title
		alertDialogBuild.setTitle(title);

		// Setting Dialog Message
		alertDialogBuild.setMessage(message);

		alertDialogBuild.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});

		AlertDialog dialog = alertDialogBuild.create();
		dialog.show();

	}

	public static void showProgressDialog(Activity activity, String msg) {

		progressDialog = new ProgressDialog(activity);
		progressDialog.setMessage(msg);

		progressDialog.setCancelable(false);

		progressDialog.setCanceledOnTouchOutside(false);

		if (!progressDialog.isShowing()) {
			progressDialog.show();
		}

	}

	public static boolean dismissProgressDialog() {

		if (progressDialog != null) {

			try {
				progressDialog.dismiss();

			} catch (IllegalArgumentException e) {

			}

			return true;

		} else {
			return false;
		}
	}

	// Mmemory check error dialog
	public static void showSplahErrorDialog(final Activity activity,
			String contentText, String buttonText) {

		// Create the dialog
		alertDialogBuild = new AlertDialog.Builder(activity);
		alertDialogBuild
				.setMessage(contentText)
				.setCancelable(false)
				.setPositiveButton(buttonText,
						new DialogInterface.OnClickListener() {
							@Override
							// OK button click listener
							public void onClick(DialogInterface dialog, int id) {

								// Cloes the dialog
								dialog.dismiss();

								// Ends th activity
								activity.finish();

							}
						});
		AlertDialog alert = alertDialogBuild.create();
		alert.show();

	}
}
