package com.icaroelucas.restauranteorlajk.controller.administracao;

import java.util.List;
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
import com.icaroelucas.restauranteorlajk.model.security.Perfil;
import com.icaroelucas.restauranteorlajk.model.security.Usuario;
import com.icaroelucas.restauranteorlajk.repository.PerfilRepository;
import com.icaroelucas.restauranteorlajk.repository.UsuarioRepository;

@Controller
@RequestMapping("/administracao/usuarios")
public class UsuariosControler {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PerfilRepository perfilRepository;

	@GetMapping("")
	public String usuarios(Model model) {

		List<Usuario> usuarios = usuarioRepository.findAll();
		model.addAttribute("usuarios", usuarios);
		return "administracao/usuarios/listausuarios";
	}

	@GetMapping("/novo")
	public String adicionaNovoUsuario(Model model) {

		return "administracao/usuarios/novo";
	}

	@PostMapping("/novo")
	public String adicionadoNovoUsuario(Model model, NovoUsuarioDTO usuarioDTO) {

		Usuario usuario = usuarioDTO.toUsuario();

		for (Perfil perfil : usuario.getPerfis()) {
			perfilRepository.save(perfil);
		}
		usuarioRepository.save(usuario);

		List<Usuario> usuarios = usuarioRepository.findAll();
		model.addAttribute("usuarios", usuarios);
		return "administracao/usuarios/listausuarios";

	}

	@GetMapping("/edita")
	public String editaUsuario(Model model, @RequestParam String id) {

		String retorno = "administracao/usuarios/listausuarios";
		try {
			Usuario usuario = usuarioRepository.findById(Long.parseLong(id)).get();
			model.addAttribute("usuario", usuario);

			retorno = "administracao/usuarios/editausuario";

		} catch (NoSuchElementException e) {
			List<Usuario> usuarios = usuarioRepository.findAll();
			model.addAttribute("usuarios", usuarios);
			retorno = "administracao/usuarios/listausuarios";
		}

		return retorno;

	}

	@PostMapping("/edita")
	public String editadoUsuario(Model model, EditaUsuarioDTO usuarioDTO) {

		Usuario usuario = usuarioRepository.findById(Long.parseLong(usuarioDTO.getId())).get();

		List<Perfil> perfis = usuario.getPerfis();

		usuario = usuarioDTO.toUsuario(usuario);
		perfilRepository.saveAll(usuario.getPerfis());
		usuarioRepository.save(usuario);
		perfilRepository.deleteAll(perfis);

		List<Usuario> usuarios = usuarioRepository.findAll();
		model.addAttribute("usuarios", usuarios);
		return "administracao/usuarios/listausuarios";
	}

	@GetMapping("/deleta")
	public String deletaUsuario(Model model, @RequestParam String id) {

		try {
			Usuario usuario = usuarioRepository.findById(Long.parseLong(id)).get();

			usuarioRepository.delete(usuario);

			for (Perfil perfil : usuario.getPerfis()) {
				perfilRepository.delete(perfil);
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
		List<Usuario> usuarios = usuarioRepository.findAll();
		model.addAttribute("usuarios", usuarios);
		return "administracao/usuarios/listausuarios";
	}

}
