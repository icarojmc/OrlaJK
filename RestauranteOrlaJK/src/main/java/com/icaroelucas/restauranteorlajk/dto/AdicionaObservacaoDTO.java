package com.icaroelucas.restauranteorlajk.dto;

import com.icaroelucas.restauranteorlajk.entities.model.Pedido;

public class AdicionaObservacaoDTO {

	private String observacao;
	private long id;
	
	public Pedido toPedido(Pedido pedido) {
		pedido.setObservacao(observacao);
		return pedido;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
}
