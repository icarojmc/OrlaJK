package com.icaroelucas.restauranteorlajk.administracao.setor.dto;

import com.icaroelucas.restauranteorlajk.entities.setor.model.Setor;

public class NovoSetorDTO {

	private String nome;
	private String descricao;
	
	public Setor toSetor() {
		Setor setor = new Setor();
		setor.setNome(nome);
		setor.setDescricao(descricao);
		return setor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
