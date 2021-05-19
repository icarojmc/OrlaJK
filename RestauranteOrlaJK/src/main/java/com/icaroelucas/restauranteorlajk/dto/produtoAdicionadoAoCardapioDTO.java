package com.icaroelucas.restauranteorlajk.dto;

import com.icaroelucas.restauranteorlajk.model.Medida;
import com.icaroelucas.restauranteorlajk.model.Produto;

public class produtoAdicionadoAoCardapioDTO {
 private String nome;
 private String quantidade;
 private String medida;
 private String id;

 
 public Produto toProduto() {
	 
	 Produto produto = new Produto();
	 produto.setNome(nome);
	 produto.setQuantidade(Float.parseFloat(quantidade));
	 produto.setMedida(Medida.valueOf(medida));
	 return produto;
 }

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getQuantidade() {
	return quantidade;
}

public void setQuantidade(String quantidade) {
	this.quantidade = quantidade;
}

public String getMedida() {
	return medida;
}

public void setMedida(String medida) {
	this.medida = medida;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}
 
}
