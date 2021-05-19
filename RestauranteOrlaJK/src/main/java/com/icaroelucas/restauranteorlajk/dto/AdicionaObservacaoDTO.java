package com.icaroelucas.restauranteorlajk.dto;

import com.icaroelucas.restauranteorlajk.model.Pedido;

public class AdicionaObservacaoDTO {

	private String observacao;
	private String id;
	
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
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
}
