package com.icaroelucas.restauranteorlajk.controller.administracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icaroelucas.restauranteorlajk.service.administracao.AdministracaoService;

@Controller
@RequestMapping("/administracao")
public class AdministracaoController {

	@Autowired
	AdministracaoService administracao;
	
	@GetMapping("")
	public String home() {
		return "administracao/home";
	}

	@GetMapping("/fecharestaurante")
	public String fechaRestaurante() {
		administracao.fechaRestaurante();
		return "administracao/home";
	}
}
