package com.icaroelucas.restauranteorlajk.estoque.dto;

import java.time.LocalDate;

import com.icaroelucas.restauranteorlajk.entities.produto.model.Medida;
import com.icaroelucas.restauranteorlajk.entities.produto.model.Produto;

public class EdicaoProdutoDTO {

	private String id;
	private String nome;
	private String quantidade;
	private String validade;
	private String medida;
	
	public Produto toProduto() {
		LocalDate data = LocalDate.parse(validade);
		Produto produto = new Produto();
		produto.setId(Long.parseLong(id));
		produto.setNome(nome);
		produto.setQuantidade(Float.parseFloat(quantidade));
		produto.setValidade(data);
		produto.setMedida(Medida.valueOf(medida));
		produto.setDisponivel(true);
		return produto;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	



	
	
	
	
}