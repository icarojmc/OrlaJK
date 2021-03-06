package com.icaroelucas.restauranteorlajk.recepcao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.entities.cliente.repository.ClienteRepository;
import com.icaroelucas.restauranteorlajk.entities.espera.repository.ListaDeEsperaRepository;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.recepcao.dto.NovoClienteDTO;
import com.icaroelucas.restauranteorlajk.recepcao.service.RecepcaoService;

@Controller
@RequestMapping("/recepcao")
public class RecepcaoController {

	@Autowired
	MesaRepository mesaRepository;

	@Autowired
	ListaDeEsperaRepository esperaRepository;

	@Autowired
	ClienteRepository clienteRepository;

	RecepcaoService recepcaoService = new RecepcaoService();

	@GetMapping({ "", "/adicionar" })
	public String home(Model model) {

		model = recepcaoService.iniciar(mesaRepository, esperaRepository, clienteRepository)
				.popularModel(model);

		return "recepcao/home";
	}

	@GetMapping("/ocupar")
	public RedirectView ocupar(@RequestParam long id) {
		recepcaoService.alterarOcupacaoDaMesa(id);
		return new RedirectView("");
	}

	@PostMapping("/adicionar")
	public RedirectView adicionaAListaDeEspera(NovoClienteDTO novoCliente) {
		recepcaoService.adicionarClienteAEspera(novoCliente);
		return new RedirectView("");
	}

	@GetMapping("/remover")
	public RedirectView removerDaListaDeEspera(@RequestParam String id) {
		recepcaoService.removerClienteDaEspera(id);
		return new RedirectView("");
	}

	@GetMapping("/ocupa")
	public RedirectView ocupaMesa(@RequestParam long mesaid, @RequestParam long clienteid) {
		recepcaoService.ocuparMesa(mesaid, clienteid);
		return new RedirectView("");
	}
}
