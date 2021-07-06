package com.icaroelucas.restauranteorlajk.service.cozinha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icaroelucas.restauranteorlajk.entities.model.Pedido;
import com.icaroelucas.restauranteorlajk.entities.model.Situacao;
import com.icaroelucas.restauranteorlajk.repository.PedidoRepository;

@Service
public class CozinhaService {
	
	@Autowired
	PedidoRepository pedidoRepository;

	public List<Pedido> buscaPedidos() {
		List<Pedido> pedidos = pedidoRepository.findAllBySituacao(Situacao.REALIZADO);
		pedidos.addAll(pedidoRepository.findAllBySituacao(Situacao.EM_PREPARACAO));
		return pedidos;
	}
	
	public void preparaPedido(String id) {
		Pedido pedido = pedidoRepository.findById(Long.parseLong(id)).get();
		pedido.setSituacao(Situacao.EM_PREPARACAO);
		pedidoRepository.save(pedido);
	}
	
	public void finalizaPedido(String id) {
		Pedido pedido = pedidoRepository.findById(Long.parseLong(id)).get();
		pedido.setSituacao(Situacao.PRONTO);
		pedidoRepository.save(pedido);
	}
}
