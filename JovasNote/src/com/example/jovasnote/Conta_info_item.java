package com.example.jovasnote;

public class Conta_info_item {
	private String nome;
	private String sobre;
	private String date_start;
	private String date_end;
	private String valor;
	private int id;
	private Boolean pago;
	
	
	public Conta_info_item( int id,String nome, String sobre, String date_start,String date_end,String valor ,Boolean pago) {
		this.nome=nome;
		this.sobre = sobre;
		this.date_start = date_start;
		this.date_end = date_end;
		this.pago = pago;
		this.valor = valor;
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobre() {
		return sobre;
	}
	public void setSobre(String sobre) {
		this.sobre = sobre;
	}

	public Boolean getPago() {
		return pago;
	}
	public void setPago(Boolean pago) {
		this.pago = pago;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate_start() {
		return date_start;
	}

	public void setDate_start(String date_start) {
		this.date_start = date_start;
	}

	public String getDate_end() {
		return date_end;
	}

	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}
}
