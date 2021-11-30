package com.icaroelucas.restauranteorlajk.administracao.setor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.administracao.setor.dto.EdicaoSetorDTO;
import com.icaroelucas.restauranteorlajk.administracao.setor.dto.NovoSetorDTO;
import com.icaroelucas.restauranteorlajk.administracao.setor.service.SetorService;
import com.icaroelucas.restauranteorlajk.entities.setor.repository.SetorRepository;

@Controller
@RequestMapping("/administracao/setor")
public class SetorController {

	@Autowired
	SetorRepository setorRepository;
	
	SetorService setorService = new SetorService();

	@GetMapping("")
	public String setor(Model model) {
		model = setorService.iniciar(setorRepository)
		.popularModel(model);
		
		return "administracao/setor/setor";
	}

	@GetMapping("/novo")
	public String adicionaNovoSetor() {
		return "administracao/setor/novo";
	}

	@PostMapping("/novo")
	public RedirectView adicionadaNovoSetor(NovoSetorDTO setor) {
		setorService.novoSetor(setor);
		return new RedirectView("");
	}

	@GetMapping("/edita")
	public String editaSetor(Model model, @RequestParam long id) {
		model = setorService.popularModel(model, id);
		return "administracao/setor/edita";
	}

	@PostMapping("/edita")
	public RedirectView editadoSetor(Model model, EdicaoSetorDTO setor) {
		setorService.editaSetor(setor);
		return new RedirectView("");
	}

	@GetMapping("/exclui")
	public RedirectView excluiSetor(Model model, @RequestParam long id) {
		setorService.excluiSetor(id);
		return new RedirectView("");
	}

}
