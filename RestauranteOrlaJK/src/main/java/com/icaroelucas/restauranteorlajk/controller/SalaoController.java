package com.icaroelucas.restauranteorlajk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.service.mesas.SalaoService;

@Controller
@RequestMapping("/mesas")
public class SalaoController {

	@Autowired
	MesaRepository mesaRepository;
	
	SalaoService mesas = new SalaoService();

	@GetMapping("")
	public String home(Model model) {
		model = mesas.iniciar(mesaRepository)
				.popularModel(model);
		return "mesas/home";
	}
	
	@GetMapping("/setor")
	public String mesasPorSetor(Model model, @RequestParam String setor) {
		model = mesas.iniciar(mesaRepository)
				.popularModel(model, setor);
		return "mesas/home";
	}

	@GetMapping("/fechamesa")
	public RedirectView fechaMesa(Model model, @RequestParam String id) {
		mesas.fechaMesa(id);
		return new RedirectView("");
	}	
	
	@GetMapping("/abremesa")
	public RedirectView abreMesa(Model model, @RequestParam String id) {
		mesas.abreMesa(id);
		return new RedirectView("");
	}
}
