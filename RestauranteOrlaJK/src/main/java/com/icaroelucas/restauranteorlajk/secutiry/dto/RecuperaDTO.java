package com.icaroelucas.restauranteorlajk.secutiry.dto;

import com.icaroelucas.restauranteorlajk.entities.configuracao.model.Configuracao;

public class RecuperaDTO {

	private String resposta;
	
	public Configuracao toResposta() {
		Configuracao resposta = new Configuracao();
		resposta.setDescricao("Resposta secreta");
		resposta.setDado(this.resposta);
		return resposta;
	}

	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	
	

}
