package com.icaroelucas.restauranteorlajk.administracao.mesas.dto;

import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.setor.model.Setor;

public class EdicaoMesaDTO {

	private String id;
	private String numeroDaMesa;
	private String nDeCadeiras;
	private Setor setor;
	
	public Mesa toMesa() {
		Mesa mesa = new Mesa();
		mesa.setId(Long.parseLong(id));
		mesa.setnDeCadeiras(Integer.parseInt(nDeCadeiras));
		mesa.setNumeroDaMesa(Integer.parseInt(numeroDaMesa));
		mesa.setSetor(setor);
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
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
