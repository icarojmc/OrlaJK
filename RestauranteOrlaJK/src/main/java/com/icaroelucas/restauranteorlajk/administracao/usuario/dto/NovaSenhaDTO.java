package com.icaroelucas.restauranteorlajk.administracao.usuario.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.icaroelucas.restauranteorlajk.entities.usuario.model.Usuario;

public class NovaSenhaDTO {

	private String senha;
	private String confirmacao;
	private long id;
	public Usuario toUsuario(Usuario usuario) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(this.senha));
		return usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getConfirmacao() {
		return confirmacao;
	}
	public void setConfirmacao(String confirmacao) {
		this.confirmacao = confirmacao;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
