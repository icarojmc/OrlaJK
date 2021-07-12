package com.icaroelucas.restauranteorlajk.administracao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icaroelucas.restauranteorlajk.administracao.dto.EdicaoMesaDTO;
import com.icaroelucas.restauranteorlajk.administracao.dto.NovaMesaDTO;
import com.icaroelucas.restauranteorlajk.administracao.service.MesasAdminService;

@Controller
@RequestMapping("/administracao/mesas")
public class MesasController {

	@Autowired
	MesasAdminService mesas;

	@GetMapping("")
	public String mesas(Model model) {
		model.addAttribute("mesas", mesas.buscaMesas());
		return "administracao/mesas/mesas";
	}

	@GetMapping("/novo")
	public String adicionaNovaMesa() {
		return "administracao/mesas/novo";
	}

	@PostMapping("/novo")
	public String adicionadaNovaMesa(Model model, NovaMesaDTO mesa) {
		mesas.novaMesa(mesa);
		model.addAttribute("mesas", mesas.buscaMesas());
		return "administracao/mesas/mesas";
	}

	@GetMapping("/edita")
	public String editaMesa(Model model, @RequestParam String id) {
		model.addAttribute("mesa", mesas.buscaMesa(id));
		return "administracao/mesas/edita";
	}

	@PostMapping("/edita")
	public String editadaMesa(Model model, EdicaoMesaDTO mesa) {
		mesas.editaMesa(mesa);
		model.addAttribute("mesas", mesas.buscaMesas());
		return "administracao/mesas/mesas";
	}

	@GetMapping("/exclui")
	public String excluiMesa(Model model, @RequestParam String id) {
		mesas.excluiMesa(id);
		model.addAttribute("mesas", mesas.buscaMesas());
		return "administracao/mesas/mesas";
	}

}
