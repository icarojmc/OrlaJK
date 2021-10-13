package com.icaroelucas.restauranteorlajk.administracao.usuario.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.administracao.usuario.dto.EdicaoUsuarioDTO;
import com.icaroelucas.restauranteorlajk.administracao.usuario.dto.NovoUsuarioDTO;
import com.icaroelucas.restauranteorlajk.administracao.usuario.dto.UsuarioEmEdicaoDAO;
import com.icaroelucas.restauranteorlajk.entities.usuario.model.Perfil;
import com.icaroelucas.restauranteorlajk.entities.usuario.model.Usuario;
import com.icaroelucas.restauranteorlajk.entities.usuario.repository.PerfilRepository;
import com.icaroelucas.restauranteorlajk.entities.usuario.repository.UsuarioRepository;

public class UsuarioService {

	UsuarioRepository usuarioRepository = null;
	PerfilRepository perfilRepository = null;
	
	public UsuarioService iniciar(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository) {
		if(!foiIniciado()) {
			this.perfilRepository = perfilRepository;
			this.usuarioRepository = usuarioRepository;
		}
		
		return this;
	}
	
	private boolean foiIniciado() {
		return usuarioRepository != null && perfilRepository != null;
	}
	
	public Model popularModel(Model model) {
		model.addAttribute("usuarios", buscaUsuarios());
		return model;
	}
	
	public Model popularModel(Model model, long id) {
		model.addAttribute("usuarioeperfil", editaUsuario(id));
		return model;
	}

	public List<Usuario> buscaUsuarios(){
		return usuarioRepository.findAll();
	}
	
	public void adicionaUsuario(NovoUsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioDTO.toUsuario();
		for (Perfil perfil : usuario.getPerfis()) {
			perfilRepository.save(perfil);
		}
		usuarioRepository.save(usuario);
	}
	
	public UsuarioEmEdicaoDAO editaUsuario(long id) throws NoSuchElementException {
		Usuario usuario = usuarioRepository.findById(id).get();
		List<String> perfis = new ArrayList<>();
		for (Perfil perfil : usuario.getPerfis()) {
			perfis.add(perfil.getNome());
		}
		return new UsuarioEmEdicaoDAO(usuario, perfis);
		
	}
	
	public void editadoUsuario(EdicaoUsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioRepository.findById(usuarioDTO.getId()).get();
		List<Perfil> perfisAntigos = usuario.getPerfis();
		usuario = usuarioDTO.toUsuario(usuario);
		perfilRepository.saveAll(usuario.getPerfis());
		usuarioRepository.save(usuario);
		perfilRepository.deleteAll(perfisAntigos);
	}
	
	public void deletaUsuario(long id) {
		try {
			Usuario usuario = usuarioRepository.findById(id).get();
			usuarioRepository.delete(usuario);
			for (Perfil perfil : usuario.getPerfis()) {
				perfilRepository.delete(perfil);
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
	}

	

	
}
