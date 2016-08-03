package com.example.jovasnote;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Conta_activity extends Activity {
	
	private MainActivity main;
	
	public Conta_activity(MainActivity main) {
		this.main = main;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {

		switch (id) {
		case 1:
			final AlertDialog alertDialog = (AlertDialog) dialog;
			
			final EditText nome = (EditText) alertDialog
					.findViewById(R.id.pessoa_dc);
			final EditText valor = (EditText) alertDialog
					.findViewById(R.id.pessoa_valor);
			final EditText sobre = (EditText) alertDialog
					.findViewById(R.id.pessoa_sobre);
			final EditText data_end = (EditText) alertDialog
					.findViewById(R.id.pessoa_data_end);
			final EditText data_start = (EditText) alertDialog
					.findViewById(R.id.pessoa_data_start);
			
			final CheckBox pago_condi = (CheckBox) alertDialog.findViewById(R.id.pessoa_pagou);
			final Boolean pago = !pago_condi.isChecked();
			
			Button btnok = (Button) alertDialog
					.findViewById(R.id.pessoa_btn_ok);
			Button cancel = (Button) alertDialog
					.findViewById(R.id.pessoa_btn_cancel);
		
			btnok.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					alertDialog.dismiss();
					main.contadao.AddConta(new Conta_info_item(0,nome.getText().toString(), sobre.getText().toString(), data_start.getText().toString(), data_end.getText().toString(), valor.getText().toString(), pago));
					main.showMsg("Adicionado com Sucesso.");
					main.refreshConta();
				}
			});

			cancel.setOnClickListener(new View.OnClickListener() {

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
			View dialogview = inflater.inflate(R.layout.dialog_op_layout, null);

			AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(main);
			dialogbuilder.setTitle("Imforme:");
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
