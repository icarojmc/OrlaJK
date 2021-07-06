package com.icaroelucas.restauranteorlajk.dto;

import java.util.List;

import com.icaroelucas.restauranteorlajk.entities.security.Usuario;

public class EditaUsuarioDTO {

	private String id;
	private String nome;
	private String usuario;
	private List<String> perfil;
	
	public Usuario toUsuario(Usuario usuario) {
		usuario.setNome(this.nome);
		usuario.setUsuario(this.usuario);
		usuario.apagaPerfis();
		if(perfil != null) {
			for (String string : perfil) {
				usuario.adicionaPerfil(string);
			}
		}
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
