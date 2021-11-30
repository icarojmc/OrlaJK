package com.icaroelucas.restauranteorlajk.salao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.entities.setor.model.Setor;
import com.icaroelucas.restauranteorlajk.entities.setor.repository.SetorRepository;
import com.icaroelucas.restauranteorlajk.salao.service.salao.SalaoService;

@Controller
@RequestMapping("/mesas")
public class SalaoController {

	@Autowired
	MesaRepository mesaRepository;
	
	@Autowired
	SetorRepository setorRepository;
	
	SalaoService salao = new SalaoService();

	@GetMapping("")
	public String home(Model model) {
		model = salao.iniciar(mesaRepository, setorRepository)
				.popularModel(model);
		return "mesas/home";
	}
	
	@GetMapping("/setor")
	public String mesasPorSetor(Model model, @RequestParam Setor setor) {
		model = salao.iniciar(mesaRepository, setorRepository)
				.popularModel(model, setor);
		return "mesas/home";
	}

	@GetMapping("/fechaabremesa")
	public RedirectView fechaMesa(Model model, @RequestParam long id) {
		salao.fechaAbreMesa(id);
		return new RedirectView("");
	}	
}
