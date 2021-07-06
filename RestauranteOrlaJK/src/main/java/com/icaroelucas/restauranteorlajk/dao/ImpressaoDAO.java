package com.icaroelucas.restauranteorlajk.dao;

import java.math.BigDecimal;
import java.util.List;

import com.icaroelucas.restauranteorlajk.entities.model.Mesa;
import com.icaroelucas.restauranteorlajk.view.ContaView;

public class ImpressaoDAO {

	private Mesa mesa;
	private List<ContaView> conta;
	private BigDecimal total;
	private String permanencia;
	
	

	public ImpressaoDAO(Mesa mesa, List<ContaView> conta, BigDecimal total, String permanencia) {
		super();
		this.mesa = mesa;
		this.conta = conta;
		this.total = total;
		this.permanencia = permanencia;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public List<ContaView> getConta() {
		return conta;
	}

	public BigDecimal getTotal() {
		return total;
	}
	
	public String getPermanencia() {
		return permanencia;
	}
	
}
