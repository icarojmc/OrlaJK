package com.icaroelucas.restauranteorlajk.administracao.mesas.dto;

import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Setor;

public class NovaMesaDTO {

	private int numeroDaMesa;
	private int nDeCadeiras;
	private String setor;
	
	public Mesa toMesa() {
		Mesa mesa = new Mesa();
		mesa.setnDeCadeiras(nDeCadeiras);
		mesa.setNumeroDaMesa(numeroDaMesa);
		mesa.setSetor(Setor.valueOf(setor));
		return mesa;
	}
	
	public int getNumeroDaMesa() {
		return numeroDaMesa;
	}
	
	public void setNumeroDaMesa(int numeroDaMesa) {
		this.numeroDaMesa = numeroDaMesa;
	}
	
	public int getnDeCadeiras() {
		return nDeCadeiras;
	}
	
	public void setnDeCadeiras(int nDeCadeiras) {
		this.nDeCadeiras = nDeCadeiras;
	}
	
	public String getSetor() {
		return setor;
	}
	
	public void setSetor(String setor) {
		this.setor = setor;
	}
	
}
