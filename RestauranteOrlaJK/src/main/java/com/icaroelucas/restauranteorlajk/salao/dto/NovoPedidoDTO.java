package com.icaroelucas.restauranteorlajk.salao.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.icaroelucas.restauranteorlajk.entities.alimento.model.Alimento;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.pedido.model.Pedido;

public class NovoPedidoDTO {
	
	private Mesa mesa;
	private LocalDateTime horaDoPedido;
	private List<Alimento> alimentos;
	
	public Pedido toPedido() {
		Pedido pedido = new Pedido();
		pedido.setMesa(mesa);
		pedido.setHoraDoPedido(horaDoPedido);
		pedido.setAlimentos(alimentos);
		return pedido;
	}
	
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
	public void setHoraDoPedido(LocalDateTime horaDoPedido) {
		this.horaDoPedido = horaDoPedido;
	}
	
	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}
	
}
