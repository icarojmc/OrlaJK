package com.icaroelucas.restauranteorlajk.administracao.usuario.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.administracao.usuario.dto.EdicaoUsuarioDTO;
import com.icaroelucas.restauranteorlajk.administracao.usuario.dto.NovoUsuarioDTO;
import com.icaroelucas.restauranteorlajk.administracao.usuario.service.UsuarioService;
import com.icaroelucas.restauranteorlajk.entities.usuario.repository.PerfilRepository;
import com.icaroelucas.restauranteorlajk.entities.usuario.repository.UsuarioRepository;

@Controller
@RequestMapping("/administracao/usuarios")
public class UsuariosControler {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PerfilRepository perfilRepository;

	UsuarioService usuarioService = new UsuarioService();

	@GetMapping("")
	public String usuarios(Model model) {
		model = usuarioService.iniciar(usuarioRepository, perfilRepository).popularModel(model);
		return "administracao/usuarios/listausuarios";
	}

	@GetMapping("/novo")
	public String adicionaNovoUsuario(Model model) {
		return "administracao/usuarios/novo";
	}

	@PostMapping("/novo")
	public RedirectView adicionadoNovoUsuario(Model model, NovoUsuarioDTO usuarioDTO) {
		usuarioService.adicionaUsuario(usuarioDTO);
		return new RedirectView("");
	}

	@GetMapping("/edita")
	public Object editaUsuario(Model model, @RequestParam long id) {
		try {
			model = usuarioService.popularModel(model, id);
			return "administracao/usuarios/editausuario";
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
		return new RedirectView("");
	}

	@PostMapping("/edita")
	public RedirectView editadoUsuario(Model model, EdicaoUsuarioDTO usuarioDTO) {
		usuarioService.editadoUsuario(usuarioDTO);
		return new RedirectView("");
	}

	@GetMapping("/deleta")
	public RedirectView deletaUsuario(Model model, @RequestParam long id) {
		usuarioService.deletaUsuario(id);
		return new RedirectView("");
	}

}
