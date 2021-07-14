package com.icaroelucas.restauranteorlajk.administracao.registrodiario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.administracao.registrodiario.service.RegistroDiarioService;
import com.icaroelucas.restauranteorlajk.entities.registrodiario.repository.RegistroDiarioRepository;

@Controller
@RequestMapping("/administracao/registro")
public class RegistroController {

	@Autowired
	RegistroDiarioRepository registroDiarioRepository;

	RegistroDiarioService registroService = new RegistroDiarioService();

	@GetMapping("")
	public String registro(Model model) {

		model = registroService.iniciar(registroDiarioRepository).popularModel(model);
		return "administracao/registro/registro";
	}

	@GetMapping("/deleta")
	public RedirectView deletaRegistro(Model model, @RequestParam long id) {
		registroService.deletaRegistro(id);
		return new RedirectView("");
	}

}
