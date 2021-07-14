package com.icaroelucas.restauranteorlajk.administracao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icaroelucas.restauranteorlajk.administracao.service.AdministracaoService;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.entities.pedido.repository.PedidoRepository;
import com.icaroelucas.restauranteorlajk.entities.registrodiario.repository.RegistroDiarioRepository;

@Controller
@RequestMapping("/administracao")
public class AdministracaoController {

	@Autowired
	RegistroDiarioRepository registroDiarioRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	MesaRepository mesaRepository;
	
	AdministracaoService administracaoService = new AdministracaoService();
	
	@GetMapping("")
	public String home() {
		administracaoService.iniciar(registroDiarioRepository, pedidoRepository, mesaRepository);
		return "administracao/home";
	}

	@GetMapping("/fecharestaurante")
	public String fechaRestaurante() {
		administracaoService.fechaRestaurante();
		return "administracao/home";
	}
}
