package com.icaroelucas.restauranteorlajk.cozinha.dto;

import com.icaroelucas.restauranteorlajk.entities.produto.model.Produto;

public class UsaProdutoDTO {

	private long id;
	private float quantidade;
	
	public Produto subtrairDoProduto(Produto produto) {
		produto.setQuantidade(produto.getQuantidade() - quantidade);
		return produto;
		
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public float getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}
	
}
