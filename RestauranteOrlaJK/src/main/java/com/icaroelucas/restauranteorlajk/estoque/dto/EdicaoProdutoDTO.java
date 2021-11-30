package com.icaroelucas.restauranteorlajk.estoque.dto;

import java.time.LocalDate;

import com.icaroelucas.restauranteorlajk.entities.fornecedor.model.Fornecedor;
import com.icaroelucas.restauranteorlajk.entities.produto.model.Medida;
import com.icaroelucas.restauranteorlajk.entities.produto.model.Produto;
import com.icaroelucas.restauranteorlajk.entities.produto.model.TipoDeProduto;

public class EdicaoProdutoDTO {

	private String id;
	private String nome;
	private float quantidade;
	private String validade;
	private String fabricante;
	private Fornecedor fornecedor;
	private float temperatura;
	private String medida;
	private String tipo;
	
	public Produto toProduto() {
		LocalDate data = LocalDate.parse(validade);
		Produto produto = new Produto();
		produto.setId(Long.parseLong(id));
		produto.setNome(nome);
		produto.setQuantidade(quantidade);
		produto.setValidade(data);
		produto.setMedida(Medida.valueOf(medida));
		produto.setTipo(TipoDeProduto.valueOf(tipo));
		produto.setFabricante(fabricante);
		produto.setFornecedor(fornecedor);
		produto.setTemperatura(temperatura);
		produto.setDisponivel(true);
		return produto;
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

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public float getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(float temperatura) {
		this.temperatura = temperatura;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
}
