package com.icaroelucas.restauranteorlajk.administracao.cardapio.dto;

import com.icaroelucas.restauranteorlajk.entities.produto.model.Medida;
import com.icaroelucas.restauranteorlajk.entities.produto.model.Produto;

public class produtoAdicionadoAoCardapioDTO {
 private String nome;
 private float quantidade;
 private String medida;
 private long id;

 
 public Produto toProduto() {
	 
	 Produto produto = new Produto();
	 produto.setNome(nome);
	 produto.setQuantidade(quantidade);
	 produto.setMedida(Medida.valueOf(medida));
	 return produto;
 }

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public float getQuantidade() {
	return quantidade;
}

public void setQuantidade(float quantidade) {
	this.quantidade = quantidade;
}

public String getMedida() {
	return medida;
}

public void setMedida(String medida) {
	this.medida = medida;
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}
 
}
