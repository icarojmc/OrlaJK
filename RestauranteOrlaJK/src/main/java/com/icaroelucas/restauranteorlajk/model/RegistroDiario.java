package com.icaroelucas.restauranteorlajk.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "registro_diario")
public class RegistroDiario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cliente;
	private String mesa;
	private String pedidos;
	private String entrada;
	private String saida;
	private LocalDate dataDoRegistro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getMesa() {
		return mesa;
	}

	public void setMesa(String mesa) {
		this.mesa = mesa;
	}

	public String getPedidos() {
		return pedidos;
	}

	public void setPedidos(String pedidos) {
		this.pedidos = pedidos;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}

	public LocalDate getDataDoRegistro() {
		return dataDoRegistro;
	}

	public void setDataDoRegistro(LocalDate dataDoRegistro) {
		this.dataDoRegistro = dataDoRegistro;
	}
	
}
