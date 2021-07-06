package com.icaroelucas.restauranteorlajk.service.administracao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icaroelucas.restauranteorlajk.dto.RegistroDiarioDTO;
import com.icaroelucas.restauranteorlajk.entities.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.model.Pedido;
import com.icaroelucas.restauranteorlajk.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.repository.PedidoRepository;
import com.icaroelucas.restauranteorlajk.repository.RegistroDiarioRepository;

@Service
public class AdministracaoService {

	@Autowired
	RegistroDiarioRepository registroDiarioRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	MesaRepository mesaRepository;
	
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
