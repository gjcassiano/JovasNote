package com.example.jovasnote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

	@Override
	public void onCreate(SQLiteDatabase db) {

        Toast.makeText(contexts, "Create", Toast.LENGTH_SHORT).show();
        
		db.execSQL("DROP TABLE contas;");
		db.execSQL("CREATE TABLE IF NOT CREATED contas ( id integer primarykey auto increment, nome varchar(64),sobre varchar (64),valor varchar(10), pago char, data_start varchar(10),data_end varchar(10));");
		Toast.makeText(contexts, "Create test", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS contas");
		 Toast.makeText(contexts, "onUpgrade", Toast.LENGTH_SHORT).show();
	}
	
	public void AddConta(String nome,String sobre,String valor,String data_start,String data_end,boolean pago){
		SQLiteDatabase sql = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("nome", nome);
		cv.put("sobre", sobre);
		cv.put("valor", valor);
		cv.put("data_start", data_start);
		cv.put("data_end", data_end);
		cv.put("pago", pago);
	
		sql.insertOrThrow(DATABASE_TABLE_NAME, null, cv);
	}
	public void GetContas(){
		SQLiteDatabase sql = this.getReadableDatabase();
		String [] from = {"id","nome","sobre","valor","data_start","data_end","pago"};
		Cursor c= sql.query("contas",from,null,null,null,null,null);
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
	}
}