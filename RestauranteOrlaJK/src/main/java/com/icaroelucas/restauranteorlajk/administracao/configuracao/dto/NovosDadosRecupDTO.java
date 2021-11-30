package com.icaroelucas.restauranteorlajk.administracao.configuracao.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.icaroelucas.restauranteorlajk.entities.configuracao.model.Configuracao;

public class NovosDadosRecupDTO {

	private String pergunta;
	private String resposta;
	
	public List<Configuracao> toConfiguracao() {
		
		List<Configuracao> configuracoes = new ArrayList<>();
		Configuracao pergunta = new Configuracao();
		pergunta.setDescricao("Pergunta secreta");
		pergunta.setDado(this.pergunta);
		
		Configuracao resposta = new Configuracao();
		resposta.setDescricao("Resposta secreta");
		resposta.setDado((new BCryptPasswordEncoder().encode(this.resposta)));
		
		configuracoes.add(pergunta);
		configuracoes.add(resposta);
		return configuracoes;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	
}
