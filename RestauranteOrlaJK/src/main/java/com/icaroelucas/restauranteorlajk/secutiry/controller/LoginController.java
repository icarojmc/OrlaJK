package com.icaroelucas.restauranteorlajk.secutiry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.administracao.configuracao.service.ConfiguracaoService;
import com.icaroelucas.restauranteorlajk.administracao.usuario.service.UsuarioService;
import com.icaroelucas.restauranteorlajk.entities.configuracao.repository.ConfiguracaoRepository;
import com.icaroelucas.restauranteorlajk.entities.usuario.repository.PerfilRepository;
import com.icaroelucas.restauranteorlajk.entities.usuario.repository.UsuarioRepository;
import com.icaroelucas.restauranteorlajk.secutiry.dto.RecuperaDTO;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PerfilRepository perfilRepository;
	
	@Autowired
	ConfiguracaoRepository configuracaoRepository;
	
	UsuarioService usuarioService = new UsuarioService();
	ConfiguracaoService configuracaoService = new ConfiguracaoService();
	
	@GetMapping("")
	public String login(Model model) {
		
		usuarioService.iniciar(usuarioRepository, perfilRepository);
		model = configuracaoService.iniciar(configuracaoRepository).popularPergunta(model);
		if(!usuarioService.primeiroAdmin()) {
			model = usuarioService.criarPrimeiroAdmin(model);
		}
		return "security/login";
	}
	
	@PostMapping("/recupera")
	public String recuperaAcesso(Model model, RecuperaDTO recuperaDTO) {
		
		usuarioService.iniciar(usuarioRepository, perfilRepository);
		if(configuracaoService.respostaCerta(recuperaDTO)) {
			model = usuarioService.criarRecuperacao(model);
		}
		return "security/login";
	}
	
}
