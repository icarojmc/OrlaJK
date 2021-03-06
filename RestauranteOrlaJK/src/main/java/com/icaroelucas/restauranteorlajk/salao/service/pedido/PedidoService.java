package com.icaroelucas.restauranteorlajk.salao.service.pedido;

import java.math.BigDecimal;
import java.util.List;

import com.icaroelucas.restauranteorlajk.entities.alimento.model.Alimento;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.pedido.model.Pedido;
import com.icaroelucas.restauranteorlajk.entities.pedido.model.Situacao;
import com.icaroelucas.restauranteorlajk.entities.pedido.repository.PedidoRepository;

public class PedidoService {

	private PedidoRepository pedidoRepository = null;
	private Pedido pedido = null;

	public void iniciar(PedidoRepository pedidoRepository) {
		if (!foiIniciado())
			this.pedidoRepository = pedidoRepository;
	}

	public boolean foiIniciado() {
		if (pedidoRepository != null)
			return true;
		return false;
	}

	public Pedido buscarPedidoEmAndamento() {
		this.pedido = pedidoRepository.findById(pedido.getId()).orElseThrow();
		return pedido;
	}

	public Pedido buscarPedido(long id) {
		this.pedido = pedidoRepository.findById(id).orElseThrow();
		return this.pedido;
	}

	public Pedido salvarPedido(Pedido pedido) {
		
		this.pedido = pedidoRepository.save(pedido);
		return this.pedido;
	}

	public Pedido criarPedido(Mesa mesa) {
		this.pedido = new Pedido();
		this.pedido.setMesa(mesa);
		this.pedido.setSituacao(Situacao.EM_REALIZACAO);
		this.pedido = salvarPedido(pedido);
		return this.pedido;
	}

	public Pedido entregarPedido(long id, PedidoRepository pedidoRepository) {

		iniciar(pedidoRepository);
		buscarPedido(id);
		pedido.setSituacao(Situacao.ENTREGUE);
		this.pedido = pedidoRepository.save(pedido);
		return pedido;
	}

	public Pedido adicionarAlimento(Alimento alimento) {
		pedido.adicionaAlimento(alimento);
		this.pedido = salvarPedido(pedido);
		return this.pedido;

	}

	public Pedido confirmar() {
		pedido.setSituacao(Situacao.REALIZADO);
		return this.pedido;
	}

	public List<Alimento> buscarAlimentos() {
		return this.pedido.getAlimentos();
	}

	public void limparPedido() {
		this.pedido = null;
	}

	public BigDecimal firmarTotalDoPedido(List<Alimento> alimentos) {
		for (Alimento alimento : alimentos) {
			pedido.somarAoTotalDoPedido(alimento.getValor());
		}
		BigDecimal total = pedido.getTotalDoPedido();
		salvarPedido(pedido);
		limparPedido();

		return total;
	}

	public Pedido removerAlimento(Alimento alimento) {
		pedido.removeAlimento(alimento);
		salvarPedido(pedido);
		return pedido;
	}

	public Pedido adicionarObservacao(String observacao) {
		pedido.setObservacao(observacao);
		salvarPedido(pedido);
		return this.pedido;
	}

	public Mesa buscarMesa() {
		return this.pedido.getMesa();
	}

	public void removerPedido() {
		pedidoRepository.delete(pedido);
		this.pedido = null;
	}
}
