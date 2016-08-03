package com.example.jovasnote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class ContaDAO extends SQLiteOpenHelper{
	private final static String DATABASE_NAME = "jovas.db";
	private final static int DATABASE_VERSION = 1;
	private final static String DATABASE_TABLE_NAME = "contas";
	private MainActivity contexts;
	public ContaDAO(MainActivity context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.contexts = context;
	}
	
	public void CreateBaseifnotExits(){
		this.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS  contas ( id integer primarykey auto increment, nome varchar(64),sobre varchar (64),valor varchar(10), pago char, data_start varchar(10),data_end varchar(10));");
	
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		CreateBaseifnotExits();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	public void AddConta(Conta_info_item c){
		SQLiteDatabase sql = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("nome", c.getNome());
		cv.put("sobre", c.getSobre());
		cv.put("valor", c.getValor());
		cv.put("data_start", c.getDate_start());
		cv.put("data_end", c.getDate_end());
		cv.put("pago", c.getPago());
		sql.insertOrThrow(DATABASE_TABLE_NAME, null, cv);
		this.contexts.refreshConta();
	}
	public void RemoveConta(Conta_info_item c){
		SQLiteDatabase sql = this.getWritableDatabase();
		try {
			sql.execSQL(String.format("delete from contas where nome = '%s' AND  sobre = '%s' AND valor = '%s' AND data_start = '%s' AND data_end = '%s'",c.getNome(),c.getSobre(),c.getValor(),c.getDate_start(),c.getDate_end()));

		} catch (Exception e) {
			Log.i("jovas",e.getMessage());
		}
		this.contexts.refreshConta();
	}
	
	public void GetContas(){
		SQLiteDatabase sql = this.getReadableDatabase();
		String [] from = {"id","nome","sobre","valor","data_start","data_end","pago"};
		Cursor c=null;
		try {
			c= sql.query("contas",from,null,null,null,null,null);
			
		} catch (Exception e) {
			Log.i("jovas",e.getMessage());
			contexts.showMsg("Erro ao ler o Banco de Dados!");
		}
		contexts.itemList.clear();
		
		while(c.moveToNext()){
		
			int id =  c.getInt(0);
			String nome = c.getString(1);
			String sobre = c.getString(2);
			String valor = c.getString(3);
			String data_start = c.getString(4);
			String data_end = c.getString(5);
			boolean pago = c.getString(6).equals("1");
			contexts.itemList.add(new Conta_info_item(id,nome, sobre, data_start, data_end, valor, pago));
		}
		//this.contexts.refreshConta();
	}
}