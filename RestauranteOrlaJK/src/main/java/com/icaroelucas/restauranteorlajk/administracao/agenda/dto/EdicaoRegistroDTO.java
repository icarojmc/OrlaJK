package com.icaroelucas.restauranteorlajk.administracao.agenda.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.icaroelucas.restauranteorlajk.entities.agenda.model.Agenda;
import com.icaroelucas.restauranteorlajk.entities.agenda.model.LocalDeTrabalho;

public class EdicaoRegistroDTO {

	private long id;
	private String funcionario;
	private String entrada;
	private String saida;
	private LocalDeTrabalho local;
	
	public Agenda toAgenda() {
		
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm");
		LocalDateTime entrada = LocalDateTime.parse(this.entrada, pattern);
		
		LocalDate dataEntrada = entrada.toLocalDate();
		LocalTime horaEntrada = entrada.toLocalTime();
		
		LocalDateTime saida = LocalDateTime.parse(this.saida, pattern);
		LocalDate dataSaida = saida.toLocalDate();
		LocalTime horaSaida = saida.toLocalTime();
		
		Agenda agenda = new Agenda();
		agenda.setId(id);
		agenda.setFuncionario(funcionario);
		agenda.setDataEntrada(dataEntrada);
		agenda.setHoraEntrada(horaEntrada);
		agenda.setDataSaida(dataSaida);
		agenda.setHoraSaida(horaSaida);
		agenda.setLocal(local);
		return agenda;
	}
	
	public String getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public LocalDeTrabalho getLocal() {
		return local;
	}
	public void setLocal(LocalDeTrabalho local) {
		this.local = local;
	}

}
