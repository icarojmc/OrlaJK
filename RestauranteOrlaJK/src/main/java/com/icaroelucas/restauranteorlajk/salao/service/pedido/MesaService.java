package com.icaroelucas.restauranteorlajk.salao.service.pedido;

import java.math.BigDecimal;

import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.entities.pedido.model.Pedido;

public class MesaService {

	MesaRepository mesaRepository = null;
	Mesa mesa = new Mesa();

	public void iniciar(MesaRepository mesaRepository) {
		if (!foiIniciado())
			this.mesaRepository = mesaRepository;
	}

	public boolean foiIniciado() {
		if (mesaRepository != null)
			return true;
		return false;
	}

	public Mesa buscarMesa(long mesaId) {
		this.mesa = mesaRepository.findById(mesaId).orElseThrow();
		return mesa;
	}

	public Mesa salvarMesa(Mesa mesa) {
		this.mesa = mesaRepository.save(mesa);
		return mesa;

	}

	public void adicionaAoTotalDaConta(BigDecimal total) {
		mesa.adicionaAoTotalDaConta(total);
		this.mesa = salvarMesa(mesa);

	}

	public void removerDoTotalDaConta(Pedido pedido) {
		buscarMesa(pedido.getIdDaMesa());
		if (pedido.getTotalDoPedido() != null) {
			mesa.removeDoTotalDaConta(pedido.getTotalDoPedido());
		}
		this.mesa = salvarMesa(mesa);
	}

}
