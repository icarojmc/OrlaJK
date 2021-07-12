package com.icaroelucas.restauranteorlajk.administracao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icaroelucas.restauranteorlajk.administracao.dto.EdicaoUsuarioDTO;
import com.icaroelucas.restauranteorlajk.administracao.dto.NovoUsuarioDTO;
import com.icaroelucas.restauranteorlajk.administracao.dto.UsuarioEmEdicaoDAO;
import com.icaroelucas.restauranteorlajk.entities.usuario.model.Perfil;
import com.icaroelucas.restauranteorlajk.entities.usuario.model.Usuario;
import com.icaroelucas.restauranteorlajk.entities.usuario.repository.PerfilRepository;
import com.icaroelucas.restauranteorlajk.entities.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PerfilRepository perfilRepository;
	
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
	
	public UsuarioEmEdicaoDAO editaUsuario(String id)throws NoSuchElementException {
		Usuario usuario = usuarioRepository.findById(Long.parseLong(id)).get();
		List<String> perfis = new ArrayList<>();
		for (Perfil perfil : usuario.getPerfis()) {
			perfis.add(perfil.getNome());
		}
		return new UsuarioEmEdicaoDAO(usuario, perfis);
		
	}
	
	public void editadoUsuario(EdicaoUsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioRepository.findById(Long.parseLong(usuarioDTO.getId())).get();
		List<Perfil> perfis = usuario.getPerfis();
		usuario = usuarioDTO.toUsuario(usuario);
		perfilRepository.saveAll(usuario.getPerfis());
		usuarioRepository.save(usuario);
		perfilRepository.deleteAll(perfis);
	}
	
	public void deletaUsuario(String id) {
		try {
			Usuario usuario = usuarioRepository.findById(Long.parseLong(id)).get();

			usuarioRepository.delete(usuario);

			for (Perfil perfil : usuario.getPerfis()) {
				perfilRepository.delete(perfil);
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
	}
}
