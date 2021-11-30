package com.icaroelucas.restauranteorlajk.feedback.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.icaroelucas.restauranteorlajk.entities.feedback.model.Feedback;

public class NovoFeedbackDTO {

	private LocalDate dia;
	private LocalTime hora;
	private int notaComida;
	private int notaAtendimento;
	private int notaEstrutura;
	private String comentario;
	
	public Feedback toFeedback() {
		Feedback feedback = new Feedback();
		feedback.setDia(dia);
		feedback.setHora(hora);
		feedback.setNotaComida(notaComida);
		feedback.setNotaAtendimento(notaAtendimento);
		feedback.setNotaEstrutura(notaEstrutura);
		feedback.setComentario(comentario);
		feedback.setDia(LocalDate.now());
		feedback.setHora(LocalTime.now());
		return feedback;
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
