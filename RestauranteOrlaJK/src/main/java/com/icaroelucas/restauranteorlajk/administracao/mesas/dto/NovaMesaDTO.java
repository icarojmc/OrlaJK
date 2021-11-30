package com.icaroelucas.restauranteorlajk.administracao.mesas.dto;

import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.setor.model.Setor;

public class NovaMesaDTO {

	private int numeroDaMesa;
	private int nDeCadeiras;
	private Setor setor;
	
	public Mesa toMesa() {
		Mesa mesa = new Mesa();
		mesa.setnDeCadeiras(nDeCadeiras);
		mesa.setNumeroDaMesa(numeroDaMesa);
		mesa.setSetor(setor);
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
	
	public Setor getSetor() {
		return setor;
	}
	
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
}
