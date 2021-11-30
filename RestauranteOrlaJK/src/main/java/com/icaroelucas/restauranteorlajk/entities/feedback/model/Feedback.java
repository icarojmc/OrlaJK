package com.icaroelucas.restauranteorlajk.entities.feedback.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "feedback")
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDate dia;
	private LocalTime hora;
	private int notaComida;
	private int notaAtendimento;
	private int notaEstrutura;
	private String comentario;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getDia() {
		return dia;
	}
	public void setDia(LocalDate dia) {
		this.dia = dia;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	public int getNotaComida() {
		return notaComida;
	}
	public void setNotaComida(int notaComida) {
		this.notaComida = notaComida;
	}
	public int getNotaAtendimento() {
		return notaAtendimento;
	}
	public void setNotaAtendimento(int notaAtendimento) {
		this.notaAtendimento = notaAtendimento;
	}
	public int getNotaEstrutura() {
		return notaEstrutura;
	}
	public void setNotaEstrutura(int notaEstrutura) {
		this.notaEstrutura = notaEstrutura;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	

}
