package com.icaroelucas.restauranteorlajk.cozinha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.cozinha.service.CozinhaService;
import com.icaroelucas.restauranteorlajk.entities.pedido.repository.PedidoRepository;

@Controller
@RequestMapping("/cozinha")
public class CozinhaController {

	@Autowired
	PedidoRepository pedidoRepository;
	
	
	CozinhaService cozinhaService = new CozinhaService();

	@GetMapping("")
	public String cozinha(Model model) {

		model = cozinhaService.iniciar(pedidoRepository).
				popularModel(model);
		return "cozinha/home";
	}

	@GetMapping("/preparapedido")
	public RedirectView preparaPedido(Model model, @RequestParam long id) {
		cozinhaService.preparaPedido(id);
		return new RedirectView("");
	}

	@GetMapping("/finalizapedido")
	public RedirectView finalizaPedido(Model model, @RequestParam long id) {
		cozinhaService.finalizaPedido(id);
		return new RedirectView("");
	}

	
}
