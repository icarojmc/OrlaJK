package com.icaroelucas.restauranteorlajk.cozinha.service;

import java.util.List;

import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.entities.pedido.model.Pedido;
import com.icaroelucas.restauranteorlajk.entities.pedido.model.Situacao;
import com.icaroelucas.restauranteorlajk.entities.pedido.repository.PedidoRepository;


public class CozinhaService {
	
	PedidoRepository pedidoRepository = null;

	public CozinhaService iniciar(PedidoRepository pedidoRepository) {
		if(!foiIniciado()) this.pedidoRepository = pedidoRepository;
		return this;
	}
	
	private boolean foiIniciado() {
		if(pedidoRepository != null) return true;
		return false;
	}

	public Model popularModel(Model model) {
		List<Pedido> pedidos = buscarPedidos();
		model.addAttribute("pedidos", pedidos);
		return model;
	}
	
	public List<Pedido> buscarPedidos() {
		List<Pedido> pedidos = pedidoRepository.findAllBySituacao(Situacao.REALIZADO);
		pedidos.addAll(pedidoRepository.findAllBySituacao(Situacao.EM_PREPARACAO));
		return pedidos;
	}
	
	public void preparaPedido(long id) {
		Pedido pedido = pedidoRepository.findById(id).get();
		pedido.setSituacao(Situacao.EM_PREPARACAO);
		pedidoRepository.save(pedido);
	}
	
	public void finalizaPedido(long id) {
		Pedido pedido = pedidoRepository.findById(id).get();
		pedido.setSituacao(Situacao.PRONTO);
		pedidoRepository.save(pedido);
	}

	

	
}
