package com.icaroelucas.restauranteorlajk.administracao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.administracao.dto.EdicaoMesaDTO;
import com.icaroelucas.restauranteorlajk.administracao.dto.NovaMesaDTO;
import com.icaroelucas.restauranteorlajk.administracao.service.MesasAdminService;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;

@Controller
@RequestMapping("/administracao/mesas")
public class MesasController {

	@Autowired
	MesaRepository mesaRepository;
	
	MesasAdminService mesaService = new MesasAdminService();

	@GetMapping("")
	public String mesas(Model model) {
		model = mesaService.iniciar(mesaRepository)
		.popularModel(model);
		
		return "administracao/mesas/mesas";
	}

	@GetMapping("/novo")
	public String adicionaNovaMesa() {
		return "administracao/mesas/novo";
	}

	@PostMapping("/novo")
	public RedirectView adicionadaNovaMesa(NovaMesaDTO mesa) {
		mesaService.novaMesa(mesa);
		return new RedirectView("");
	}

	@GetMapping("/edita")
	public String editaMesa(Model model, @RequestParam long id) {
		model = mesaService.popularModel(model, id);
		return "administracao/mesas/edita";
	}

	@PostMapping("/edita")
	public RedirectView editadaMesa(Model model, EdicaoMesaDTO mesa) {
		mesaService.editaMesa(mesa);
		return new RedirectView("");
	}

	@GetMapping("/exclui")
	public RedirectView excluiMesa(Model model, @RequestParam String id) {
		mesaService.excluiMesa(id);
		return new RedirectView("");
	}

}
