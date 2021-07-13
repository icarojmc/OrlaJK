package com.icaroelucas.restauranteorlajk.administracao.service;

import com.icaroelucas.restauranteorlajk.administracao.dto.NovaMesaDTO;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Setor;

public class CriadorDeMesa {

	private int cadeiras;
	private int nDaMesa;
	private String setor;
	
	public CriadorDeMesa incluirCadeiras(int cadeiras) {
		this.cadeiras = cadeiras;
		return this;
	}
	
	public CriadorDeMesa incluirNDaMesa(int nDaMesa) {
		this.nDaMesa = nDaMesa;
		return this;
		}
	
	public CriadorDeMesa incluirSetor(String setor) {
		this.setor = setor;
		return this;
	}
	
	public NovaMesaDTO executar() {
		NovaMesaDTO mesaDTO = new NovaMesaDTO();
		mesaDTO.setnDeCadeiras(cadeiras);
		mesaDTO.setNumeroDaMesa(nDaMesa);
		mesaDTO.setSetor(setor);
		return mesaDTO;
	}
}
