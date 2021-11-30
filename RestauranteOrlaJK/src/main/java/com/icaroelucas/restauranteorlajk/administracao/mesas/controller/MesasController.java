package com.icaroelucas.restauranteorlajk.administracao.mesas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.administracao.mesas.dto.EdicaoMesaDTO;
import com.icaroelucas.restauranteorlajk.administracao.mesas.dto.NovaMesaDTO;
import com.icaroelucas.restauranteorlajk.administracao.mesas.service.MesasAdminService;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.entities.setor.repository.SetorRepository;

@Controller
@RequestMapping("/administracao/mesas")
public class MesasController {

	@Autowired
	MesaRepository mesaRepository;
	
	@Autowired
	SetorRepository setorRepository;
	
	MesasAdminService mesaService = new MesasAdminService();

	@GetMapping("")
	public String mesas(Model model) {
		model = mesaService.iniciar(mesaRepository)
		.popularModel(model);
		
		return "administracao/mesas/mesas";
	}

	@GetMapping("/novo")
	public String adicionaNovaMesa(Model model) {
		model = mesaService.popularModel(model, setorRepository);
		return "administracao/mesas/novo";
	}

	@PostMapping("/novo")
	public RedirectView adicionadaNovaMesa(NovaMesaDTO mesa) {
		mesaService.novaMesa(mesa);
		return new RedirectView("");
	}

	@GetMapping("/edita")
	public String editaMesa(Model model, @RequestParam long id) {
		model = mesaService.popularModel(model, id, setorRepository);
		return "administracao/mesas/edita";
	}

	@PostMapping("/edita")
	public RedirectView editadaMesa(Model model, EdicaoMesaDTO mesa) {
		mesaService.editaMesa(mesa);
		return new RedirectView("");
	}

	@GetMapping("/exclui")
	public RedirectView excluiMesa(Model model, @RequestParam long id) {
		mesaService.excluiMesa(id);
		return new RedirectView("");
	}

}
