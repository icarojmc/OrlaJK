package com.icaroelucas.restauranteorlajk.controller.administracao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icaroelucas.restauranteorlajk.dto.RegistroDiarioDTO;
import com.icaroelucas.restauranteorlajk.model.Mesa;
import com.icaroelucas.restauranteorlajk.model.Pedido;
import com.icaroelucas.restauranteorlajk.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.repository.PedidoRepository;
import com.icaroelucas.restauranteorlajk.repository.RegistroDiarioRepository;

@Controller
@RequestMapping("/administracao")
public class AdministracaoController {

	@Autowired
	RegistroDiarioRepository registroDiarioRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	MesaRepository mesaRepository;
	
	@GetMapping("")
	public String home() {

		return "administracao/home";
	}

	@GetMapping("/fecharestaurante")
	public String fechaRestaurante() {

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

		return "/home";
	}
}
