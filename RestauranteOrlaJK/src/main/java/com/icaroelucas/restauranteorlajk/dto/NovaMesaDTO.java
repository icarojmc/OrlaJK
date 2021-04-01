package com.icaroelucas.restauranteorlajk.dto;

import com.icaroelucas.restauranteorlajk.model.Mesa;
import com.icaroelucas.restauranteorlajk.model.Setor;

public class NovaMesaDTO {

	private String numeroDaMesa;
	private String nDeCadeiras;
	private String setor;
	
	public Mesa toMesa() {
		
		Mesa mesa = new Mesa();
		mesa.setnDeCadeiras(Integer.parseInt(nDeCadeiras));
		mesa.setNumeroDaMesa(Integer.parseInt(numeroDaMesa));
		mesa.setSetor(Setor.valueOf(setor));
		
		return mesa;
	}
	
	
	public String getNumeroDaMesa() {
		return numeroDaMesa;
	}
	public void setNumeroDaMesa(String numeroDaMesa) {
		this.numeroDaMesa = numeroDaMesa;
	}
	public String getnDeCadeiras() {
		return nDeCadeiras;
	}
	public void setnDeCadeiras(String nDeCadeiras) {
		this.nDeCadeiras = nDeCadeiras;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	
	
}
