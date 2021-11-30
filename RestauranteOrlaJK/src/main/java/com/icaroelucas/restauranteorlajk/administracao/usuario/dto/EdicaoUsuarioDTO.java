package com.icaroelucas.restauranteorlajk.administracao.usuario.dto;

import java.util.List;

import com.icaroelucas.restauranteorlajk.entities.usuario.model.Perfil;
import com.icaroelucas.restauranteorlajk.entities.usuario.model.Usuario;

public class EdicaoUsuarioDTO {

	private long id;
	private String nome;
	private String usuario;
	private List<String> perfil;
	
	public Usuario toUsuario(Usuario usuario, List<Perfil> perfis) {
		usuario.setNome(this.nome);
		usuario.setUsuario(this.usuario);
		usuario.setPerfis(perfis);

		return usuario;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public List<String> getPerfil() {
		return perfil;
	}
	public void setPerfil(List<String> perfil) {
		this.perfil = perfil;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
