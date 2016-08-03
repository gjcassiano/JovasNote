package com.example.jovasnote;

import java.util.ArrayList;
import java.util.List;

import android.R.color;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CustomAdapter extends BaseAdapter implements OnClickListener {

	private MainActivity ctx;
	private List<Conta_info_item> itemList;

	public CustomAdapter(MainActivity ctx, List<Conta_info_item> itemList) {
		this.ctx = ctx;
		this.itemList = itemList;

	}

	@Override
	public int getCount() {
		return itemList == null ? 0 : itemList.size();

	}

	@Override
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		return itemList == null ? null : itemList.get(pos);

	}

	@Override
	public long getItemId(int pos) {
		return itemList == null ? 0 : itemList.get(pos).hashCode();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		if (v == null) {
			LayoutInflater lInf = (LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = lInf.inflate(R.layout.item_lista, null);
			v.setTag(String.valueOf(position));
			v.setOnClickListener(this);
		
			
		}

		final TextView infonome = (TextView) v
				.findViewById(R.id.item_info_nome);
		TextView infosobre = (TextView) v.findViewById(R.id.item_info_desc);
		TextView infodate = (TextView) v.findViewById(R.id.item_info_date);
		final TextView infovalor = (TextView) v
				.findViewById(R.id.item_info_valor);

		TextView infopago = (TextView) v.findViewById(R.id.item_info_pagou);
		
		Conta_info_item c = itemList.get(position);
		
		infonome.setText(c.getNome());
		infosobre.setText(c.getSobre());
		infodate.setText(c.getDate_start() + " - " + c.getDate_end() );
		infovalor.setText(c.getValor());
		if(c.getPago()){
			infopago.setText("Valor Pago!");
		}else{
			infopago.setText("A Pagar!");
		}
		

		return v;
	}

	@Override
	public void onClick(View v) {
		Conta_info_item c = (Conta_info_item) getItem(Integer.parseInt(v.getTag().toString()));
	
		//c.getNome();
		//int pos = Integer.parseInt(v.getTag().toString());
		//Toast.makeText(ctx,"getNome:" + c.getNome() + "\n getTag: "+ v.getTag(), Toast.LENGTH_SHORT).show();
		
		exemplo_lista_single(c);
	}
	private AlertDialog alerta;
	private void exemplo_lista_single(final Conta_info_item c) {
        //Lista de itens
        ArrayList<String> itens = new ArrayList<String>();
        itens.add("Apagar");
        itens.add("Atualizar");
        itens.add("Pago Hoje");
        
        //adapter utilizando um layout customizado (TextView)
        ArrayAdapter adapter = new ArrayAdapter(ctx, R.layout.conta_select, itens);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.ctx);
        builder.setTitle(c.getNome() + " - " + c.getValor() );
        //define o diálogo como uma lista, passa o adapter.
        builder.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int op) {
               // Toast.makeText(ctx, "Id :" + c.getId() + " Op: " + arg1, Toast.LENGTH_SHORT).show();
                
                if(op==0){//Apagar
                	ctx.contadao.RemoveConta(c);
                }else if(op==1){//Atualizar
                	
                }else if(op==2){//Pagar hoje
                	
                }
                alerta.dismiss();
            }
        });

        alerta = builder.create();
        alerta.show();
    } 
}