package com.icaroelucas.restauranteorlajk.dao;

import java.util.List;

import com.icaroelucas.restauranteorlajk.entities.security.Usuario;

public class UsuarioEmEdicaoDAO {

	private Usuario usuario;
	private List<String> perfis;
	
	public UsuarioEmEdicaoDAO(Usuario usuario, List<String> perfis) {
		super();
		this.usuario = usuario;
		this.perfis = perfis;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public List<String> getPerfis() {
		return perfis;
	}
	
}
