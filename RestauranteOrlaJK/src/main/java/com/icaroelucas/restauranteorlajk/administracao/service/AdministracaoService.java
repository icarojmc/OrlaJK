package com.icaroelucas.restauranteorlajk.administracao.service;

import java.math.BigDecimal;
import java.util.List;

import com.icaroelucas.restauranteorlajk.administracao.registrodiario.dto.RegistroDiarioDTO;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.entities.pedido.model.Pedido;
import com.icaroelucas.restauranteorlajk.entities.pedido.repository.PedidoRepository;
import com.icaroelucas.restauranteorlajk.entities.registrodiario.repository.RegistroDiarioRepository;


public class AdministracaoService {

	MesaRepository mesaRepository = null;
	PedidoRepository pedidoRepository = null;
	RegistroDiarioRepository registroDiarioRepository = null;
	
	
	public void iniciar(RegistroDiarioRepository registroDiarioRepository, PedidoRepository pedidoRepository,
			MesaRepository mesaRepository) {
		if(!foiIniciado()) {
			this.mesaRepository = mesaRepository;
			this.pedidoRepository = pedidoRepository;
			this.registroDiarioRepository = registroDiarioRepository;
		}
	}
	
	private boolean foiIniciado() {
		return mesaRepository != null && pedidoRepository != null && registroDiarioRepository != null;
	}

	public void fechaRestaurante() {
		List<Mesa> mesas = mesaRepository.findAll();
		RegistroDiarioDTO registro = new RegistroDiarioDTO();
		for (Mesa mesa : mesas) {
			if (mesa.isOcupada()) {
				registroDiarioRepository.save(registro.toRegistro(mesa));
				List<Pedido> pedidos = mesa.getPedidos();
				for (Pedido pedido : pedidos) {
					pedidoRepository.delete(pedido);
				}
				mesa.limpaPedidos();
				mesa.setFechada(false);
				mesa.setOcupada(false);
				mesa.setTotalDaConta(new BigDecimal("0.0"));
				mesaRepository.save(mesa);
			}
		}
		
	}





	

	
}
