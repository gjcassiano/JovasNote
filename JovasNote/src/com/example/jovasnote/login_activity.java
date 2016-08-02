package com.example.jovasnote;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_activity extends Activity {
	
	private MainActivity main;
	
	public login_activity(MainActivity main) {
		this.main = main;
	}
	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {

		switch (id) {
		case 1:
			final AlertDialog alertDialog = (AlertDialog) dialog;
			Button loginbutton = (Button) alertDialog
					.findViewById(R.id.pessoa_btn_ok);
			Button cancelbutton = (Button) alertDialog
					.findViewById(R.id.pessoa_btn_cancel);
			final EditText userName = (EditText) alertDialog
					.findViewById(R.id.pessoa_sobre);
			final EditText password = (EditText) alertDialog
					.findViewById(R.id.password);

			loginbutton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					alertDialog.dismiss();
					main.showMsg("User Name : " + userName.getText().toString()
							+ "  Password : " + password.getText().toString());
				}
			});

			cancelbutton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					alertDialog.dismiss();
				}
			});
			break;
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		AlertDialog dialogDetails = null;

		switch (id) {
		case 1:
			LayoutInflater inflater = LayoutInflater.from(main);
			View dialogview = inflater.inflate(R.layout.dialog_login_layout, null);

			AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(main);
			dialogbuilder.setTitle("Login");
			dialogbuilder.setView(dialogview);
			dialogDetails = dialogbuilder.create();

			break;
		}

		return dialogDetails;
	}
	
	public void Show(int id){
		this.showDialog(id);
	}
}
