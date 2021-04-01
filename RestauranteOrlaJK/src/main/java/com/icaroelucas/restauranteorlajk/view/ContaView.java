package com.icaroelucas.restauranteorlajk.view;

import java.math.BigDecimal;
import java.util.List;

public class ContaView {

	String nome;
	BigDecimal valor;
	
	
	
	public ContaView(String nome, BigDecimal valor) {
		super();
		this.nome = nome;
		this.valor = valor;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
}
