package com.icaroelucas.restauranteorlajk.dto;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.icaroelucas.restauranteorlajk.model.security.Usuario;

public class NovoUsuarioDTO {

	private String nome;
	
	private String usuario;
	private String senha;
	private List<String> perfil;

	public Usuario toUsuario() {

		Usuario usuario = new Usuario();

		usuario.setNome(this.nome);
		usuario.setUsuario(this.usuario);
		usuario.setSenha(new BCryptPasswordEncoder().encode(this.senha));

		if (perfil != null) {
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<String> getPerfil() {
		return perfil;
	}

	public void setPerfil(List<String> perfil) {
		this.perfil = perfil;
	}

}
