package com.icaroelucas.restauranteorlajk.dto;

import com.icaroelucas.restauranteorlajk.model.Produto;

public class UsaProdutoDTO {

	private String id;
	private String quantidade;
	
	public Produto toProduto(Produto produto) {
		produto.setQuantidade(produto.getQuantidade() - Float.parseFloat(quantidade));
		return produto;
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
}
