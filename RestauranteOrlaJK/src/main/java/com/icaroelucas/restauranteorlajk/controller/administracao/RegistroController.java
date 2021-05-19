package com.icaroelucas.restauranteorlajk.controller.administracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icaroelucas.restauranteorlajk.service.administracao.RegistroDiarioService;

@Controller
@RequestMapping("/administracao/registro")
public class RegistroController {

	@Autowired
	RegistroDiarioService registros;
	
	@GetMapping("")
	public String registro(Model model) {
		model.addAttribute("registros", registros.buscaRegistros());
		return "administracao/registro/registro";
	}
	
	@GetMapping("/deleta")
	public String deletaRegistro(Model model, @RequestParam String id) {
		registros.deletaRegistro(id);
		model.addAttribute("registros", registros.buscaRegistros());
		return "administracao/registro/registro";
	}
	
}
