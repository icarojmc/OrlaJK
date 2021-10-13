package com.icaroelucas.restauranteorlajk.caixa.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.caixa.dto.ImpressaoDTO;
import com.icaroelucas.restauranteorlajk.caixa.service.CaixaService;
import com.icaroelucas.restauranteorlajk.entities.cliente.repository.ClienteRepository;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.entities.pedido.repository.PedidoRepository;
import com.icaroelucas.restauranteorlajk.entities.registrodiario.repository.RegistroDiarioRepository;

@Controller
@RequestMapping("/caixa")
public class CaixaController {

	@Autowired
	RegistroDiarioRepository registroDiarioRepository;
	@Autowired
	MesaRepository mesaRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	
	CaixaService caixaService = new CaixaService();


	@GetMapping("")
	public String caixa(Model model) {
		model = caixaService.iniciar(registroDiarioRepository, mesaRepository, pedidoRepository, clienteRepository)
		.popularModel(model);
		return "caixa/home";
	}

	@GetMapping("/disponibilizamesa")
	public RedirectView disponibilizaMesa(Model model, @RequestParam String id) {
		try {
			caixaService.disponibilizaMesa(id);
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
		return new RedirectView("");
	}

	@GetMapping("/imprimeconta")
	public Object imprimeConta(Model model, @RequestParam String id) {

		try {			
			ImpressaoDTO conta = caixaService.imprimeConta(id);
			caixaService.popularModel(model, conta);
			return "caixa/conta";
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
		return new RedirectView("");
	}

}
