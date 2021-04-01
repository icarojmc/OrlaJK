package com.icaroelucas.restauranteorlajk.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "alimento")
public class Alimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String descricao;
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@ManyToMany
	private List<Produto> produtos;
	
	@ManyToMany(mappedBy = "alimentos")
	private List<Pedido> pedidos;

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	
}
