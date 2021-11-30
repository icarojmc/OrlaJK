package com.icaroelucas.restauranteorlajk.administracao.agenda.dto;

import java.time.LocalDateTime;

import com.icaroelucas.restauranteorlajk.entities.agenda.model.Agenda;
import com.icaroelucas.restauranteorlajk.entities.agenda.model.LocalDeTrabalho;

public class RegistroView {

	long id;
	String funcionario;
	LocalDateTime entrada;
	LocalDateTime saida;
	LocalDeTrabalho local;
	
	public RegistroView(Agenda agenda) {
		id = agenda.getId();
		funcionario = agenda.getFuncionario();
		entrada = agenda.getDataEntrada().atTime(agenda.getHoraEntrada());
		saida = agenda.getDataSaida().atTime(agenda.getHoraSaida());
		local = agenda.getLocal();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}

	public LocalDateTime getSaida() {
		return saida;
	}

	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}

	public LocalDeTrabalho getLocal() {
		return local;
	}

	public void setLocal(LocalDeTrabalho local) {
		this.local = local;
	}
	
	
}
