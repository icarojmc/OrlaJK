package com.icaroelucas.restauranteorlajk.administracao.debito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.administracao.debito.dto.EdicaoDebitoDTO;
import com.icaroelucas.restauranteorlajk.administracao.debito.dto.NovoDebitoDTO;
import com.icaroelucas.restauranteorlajk.administracao.debito.service.DebitoService;
import com.icaroelucas.restauranteorlajk.entities.debito.repository.DebitoRepository;
import com.icaroelucas.restauranteorlajk.entities.fornecedor.repository.FornecedorRepository;

@Controller
@RequestMapping("/administracao/debito")
public class DebitoController {

	@Autowired
	DebitoRepository debitoRepository;
	
	@Autowired
	FornecedorRepository fornecedorRepository;
	
	DebitoService debitoService = new DebitoService();

	@GetMapping("")
	public String debitos(Model model) {
		model = debitoService.iniciar(debitoRepository)
		.popularModel(model);
		
		return "administracao/debito/debito";
	}
	
	@GetMapping("/novo")
	public String adicionaNovoDebito(Model model) {
		model = debitoService.popularModel(model, fornecedorRepository);
		return "administracao/debito/novo";
	}
	
	@PostMapping("/novo")
	public RedirectView adicionadoNovoDebito(NovoDebitoDTO debito) {
		debitoService.novoDebito(debito);
		return new RedirectView("");
	}
	
	@GetMapping("/edita")
	public String editaDebito(Model model, @RequestParam long id) {
		model = debitoService.popularModel(model, id, fornecedorRepository);
		return "administracao/debito/edita";
	}
	
	@PostMapping("/edita")
	public RedirectView editadoDebito(Model model, EdicaoDebitoDTO debito) {
		debitoService.editaDebito(debito);
		return new RedirectView("");
	}
	
	@GetMapping("/exclui")
	public RedirectView excluiDebito(Model model, @RequestParam long id) {
		debitoService.excluiDebito(id);
		return new RedirectView("");
	}
}
