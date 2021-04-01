package com.icaroelucas.restauranteorlajk.dto;

import java.math.BigDecimal;

import com.icaroelucas.restauranteorlajk.model.Alimento;
import com.icaroelucas.restauranteorlajk.model.Tipo;

public class EditadoAlimentoDTO {

	private String id;
	private String nome;
	private String descricao;
	private BigDecimal valor;
	private String tipo;
	
	public Alimento toAlimento() {
		Alimento alimento = new Alimento();
		
		alimento.setId(Long.parseLong(id));
		alimento.setNome(nome);
		alimento.setDescricao(descricao);
		alimento.setValor(valor);
		alimento.setTipo(Tipo.valueOf(tipo));
		
		return alimento;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
}
