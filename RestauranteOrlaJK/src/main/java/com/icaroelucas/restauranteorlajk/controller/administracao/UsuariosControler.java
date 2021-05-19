package com.icaroelucas.restauranteorlajk.controller.administracao;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icaroelucas.restauranteorlajk.dto.EditaUsuarioDTO;
import com.icaroelucas.restauranteorlajk.dto.NovoUsuarioDTO;
import com.icaroelucas.restauranteorlajk.service.administracao.UsuarioService;

@Controller
@RequestMapping("/administracao/usuarios")
public class UsuariosControler {

	@Autowired
	UsuarioService usuarios;

	@GetMapping("")
	public String usuarios(Model model) {
		model.addAttribute("usuarios", usuarios.buscaUsuarios());
		return "administracao/usuarios/listausuarios";
	}

	@GetMapping("/novo")
	public String adicionaNovoUsuario(Model model) {
		return "administracao/usuarios/novo";
	}

	@PostMapping("/novo")
	public String adicionadoNovoUsuario(Model model, NovoUsuarioDTO usuarioDTO) {
		usuarios.adicionaUsuario(usuarioDTO);
		model.addAttribute("usuarios", usuarios.buscaUsuarios());
		return "administracao/usuarios/listausuarios";
	}

	@GetMapping("/edita")
	public String editaUsuario(Model model, @RequestParam String id) {
		try {
			model.addAttribute("usuarioeperfil", usuarios.editaUsuario(id));
			return "administracao/usuarios/editausuario";
		} catch (NoSuchElementException e) {
			model.addAttribute("usuarios", usuarios.buscaUsuarios());
			return "administracao/usuarios/listausuarios";
		}
	}

	@PostMapping("/edita")
	public String editadoUsuario(Model model, EditaUsuarioDTO usuarioDTO) {
		usuarios.editadoUsuario(usuarioDTO);
		model.addAttribute("usuarios", usuarios.buscaUsuarios());
		return "administracao/usuarios/listausuarios";
	}

	@GetMapping("/deleta")
	public String deletaUsuario(Model model, @RequestParam String id) {
		usuarios.deletaUsuario(id);
		model.addAttribute("usuarios", usuarios.buscaUsuarios());
		return "administracao/usuarios/listausuarios";
	}

}
