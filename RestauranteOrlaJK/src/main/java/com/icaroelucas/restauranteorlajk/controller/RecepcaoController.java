package com.icaroelucas.restauranteorlajk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icaroelucas.restauranteorlajk.dto.NovoClienteDTO;
import com.icaroelucas.restauranteorlajk.service.recepcao.ClientesEmEspera;
import com.icaroelucas.restauranteorlajk.service.recepcao.ListaDaRecepcao;

@Controller
@RequestMapping("/recepcao")
public class RecepcaoController {

	@Autowired
	ListaDaRecepcao listas;
	
	@Autowired
	ClientesEmEspera espera;

	@GetMapping({"", "/adicionar"})
	public String home(Model model) {
		model.addAttribute("listas", listas.recupera());
		return "recepcao/home";
	}

	@GetMapping("/ocupar")
	public String ocupar(Model model, @RequestParam String id) {
		listas.ocupaMesa(id);
		model.addAttribute("listas", listas.recupera());
		return "recepcao/home";
	}

	@GetMapping("/liberar")
	public String liberar(Model model, @RequestParam String id) {
		listas.liberaMesa(id);
		model.addAttribute("listas", listas.recupera());
		return "recepcao/home";
	}

	@PostMapping("/adicionar")
	public String adicionaAListaDeEspera(Model model, NovoClienteDTO novoCliente) {
		espera.novoCliente(novoCliente);
		model.addAttribute("listas", listas.recupera());
		return "recepcao/home";
	}

	@GetMapping("/remover")
	public String removerDaListaDeEspera(Model model, @RequestParam String id) {
		espera.removeDaLista(id);
		model.addAttribute("listas", listas.recupera());
		return "recepcao/home";
	}

	@GetMapping("/ocupa")
	public String ocupaMesa(Model model, @RequestParam String mesaid, @RequestParam String clienteid) {
		espera.ocupaMesaComCliente(mesaid, clienteid);
		model.addAttribute("listas", listas.recupera());
		return "recepcao/home";
	}
}
