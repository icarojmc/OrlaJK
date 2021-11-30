package com.icaroelucas.restauranteorlajk.administracao.configuracao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.administracao.configuracao.dto.EdicaoDadosRecupDTO;
import com.icaroelucas.restauranteorlajk.administracao.configuracao.dto.NovosDadosRecupDTO;
import com.icaroelucas.restauranteorlajk.administracao.configuracao.service.ConfiguracaoService;
import com.icaroelucas.restauranteorlajk.entities.configuracao.repository.ConfiguracaoRepository;

@Controller
@RequestMapping("/administracao/config")
public class ConfiguracaoController {

	@Autowired
	ConfiguracaoRepository configuracaoRepository;
	
	ConfiguracaoService configuracaoService = new ConfiguracaoService();
	
	@GetMapping("")
	public String feedback(Model model) {
		model = configuracaoService.iniciar(configuracaoRepository).
		popularModel(model);
		
		return "administracao/config/config";
	}
	
	@PostMapping("/novosdadosrecup")
	public RedirectView adicionadoNovoAdmin(NovosDadosRecupDTO dados) {
		configuracaoService.novosDadosRecup(dados);
		return new RedirectView("");
	}
	
	@PostMapping("/editadadosrecup")
	public RedirectView editaAdmin(EdicaoDadosRecupDTO dados) {
		configuracaoService.edicaoDadosRecup(dados);
		return new RedirectView("");
	}

}
