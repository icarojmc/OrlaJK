package com.icaroelucas.restauranteorlajk.administracao.setor.dto;

import com.icaroelucas.restauranteorlajk.entities.setor.model.Setor;

public class EdicaoSetorDTO {

	private long id;
	private String nome;
	private String descricao;
	
	public Setor toSetor() {
		Setor setor = new Setor();
		setor.setId(id);
		setor.setNome(nome);
		setor.setDescricao(descricao);
		return setor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
