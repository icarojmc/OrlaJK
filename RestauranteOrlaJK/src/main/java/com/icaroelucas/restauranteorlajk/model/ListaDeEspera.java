package com.icaroelucas.restauranteorlajk.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "lista_de_espera")
public class ListaDeEspera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@ManyToOne
	Cliente cliente;
	
	LocalDateTime horaChegada;
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void setHoraChegada(LocalDateTime horaChegada) {
		this.horaChegada = horaChegada;
	}

	public long getId() {
		return id;
	}
	
	
}
