package com.icaroelucas.restauranteorlajk.dto;

import com.icaroelucas.restauranteorlajk.model.Cliente;

public class NovoClienteDTO {

	private String nome;
	private String telefone;
	private String quantidade;
	
	public Cliente toCliente() {
		
		Cliente cliente = new Cliente();
		
		cliente.setNome(nome);
		cliente.setTelefone(telefone);
		cliente.setQuantidade(Integer.parseInt(quantidade));
		
		return cliente;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
}
