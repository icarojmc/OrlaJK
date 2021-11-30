package com.icaroelucas.restauranteorlajk.administracao.fornecedor.dto;

import com.icaroelucas.restauranteorlajk.entities.fornecedor.model.Fornecedor;

public class EdicaoFornecedorDTO {

	private long id;
	private String nome;
	private String cnpj;
	private String endereco;
	private String telefone;
	
	public Fornecedor toFornecedor() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(id);
		fornecedor.setNome(nome);
		fornecedor.setCnpj(cnpj);
		fornecedor.setEndereco(endereco);
		fornecedor.setTelefone(telefone);
		return fornecedor;
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
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
}
