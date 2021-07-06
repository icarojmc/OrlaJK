package com.icaroelucas.restauranteorlajk.dto;

import java.time.LocalDate;

import com.icaroelucas.restauranteorlajk.entities.model.Alimento;
import com.icaroelucas.restauranteorlajk.entities.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.model.Pedido;
import com.icaroelucas.restauranteorlajk.entities.model.RegistroDiario;

public class RegistroDiarioDTO {

	private String pedidos;

	
public RegistroDiario toRegistro(Mesa mesa) {
	RegistroDiario registro = new RegistroDiario();
		if(mesa.getClienteOcupante() != null) {
			registro.setCliente("[ Cliente: " + mesa.getClienteOcupante().getNome() + " - Telefone: " + mesa.getClienteOcupante().getTelefone() + " - Qtd de pessoas: " + mesa.getClienteOcupante().getQuantidade() + " ]");
		}
		else {
			registro.setCliente("[ Cliente não definido ]");
		}
		registro.setMesa("[ Mesa: " + mesa.getNumeroDaMesa() + " - Setor: " + mesa.getSetor() + " ]");
		this.pedidos = "";
		for (Pedido pedido : mesa.getPedidos()) {
			this.pedidos += "[ Pedido: " + pedido.getId() + " - Situacao: " + pedido.getSituacao();
			for (Alimento alimento : pedido.getAlimentos()) {
				this.pedidos += "[ Alimento: " + alimento.getNome() + " - Preço: R$" + alimento.getValor() + " ]";
			}
			this.pedidos += " ]";
		}
		registro.setPedidos(this.pedidos);
		registro.setEntrada(mesa.getChegada().toString());
		registro.setSaida(LocalDate.now().toString());
		registro.setDataDoRegistro(LocalDate.now());
		return registro;
	}
}
