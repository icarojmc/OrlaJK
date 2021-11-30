package com.icaroelucas.restauranteorlajk.administracao.configuracao.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.administracao.configuracao.dto.EdicaoDadosRecupDTO;
import com.icaroelucas.restauranteorlajk.administracao.configuracao.dto.NovosDadosRecupDTO;
import com.icaroelucas.restauranteorlajk.entities.configuracao.model.Configuracao;
import com.icaroelucas.restauranteorlajk.entities.configuracao.repository.ConfiguracaoRepository;
import com.icaroelucas.restauranteorlajk.secutiry.dto.RecuperaDTO;

public class ConfiguracaoService {

	ConfiguracaoRepository configuracaoRepository = null;
	
	public ConfiguracaoService iniciar(ConfiguracaoRepository configuracaoRepository) {
		if(!foiIniciado()) {
			this.configuracaoRepository = configuracaoRepository;
		}
		return this;
	}
	
	private boolean foiIniciado() {
		return configuracaoRepository != null;
	}
	
	public Model popularModel(Model model) {
		
		List<Configuracao> dados = buscarDados();
		
		for (Configuracao configuracao : dados) {
			if(configuracao.getDescricao().equals("Pergunta secreta")) {
				model.addAttribute("pergunta", configuracao);
			}
			if(configuracao.getDescricao().equals("Resposta secreta")) {
				model.addAttribute("resposta", configuracao);
			}
			
		}
		
		return model.addAttribute("configuracoes", dados);
	}

	private List<Configuracao> buscarDados() {
		return configuracaoRepository.findAll();
	}

	public void novosDadosRecup(NovosDadosRecupDTO dados) {
		List<Configuracao> list = dados.toConfiguracao();
		for (Configuracao configuracao : list) {
			configuracaoRepository.save(configuracao);
		}
	}

	public void edicaoDadosRecup(EdicaoDadosRecupDTO dados) {
		List<Configuracao> list = dados.toConfiguracao();
		for (Configuracao configuracao : list) {
			configuracaoRepository.save(configuracao);
		}
	}

	public Model popularPergunta(Model model) {
		Configuracao configuracao = configuracaoRepository.findByDescricao("Pergunta secreta");
		model.addAttribute("pergunta", configuracao.getDado());
		return model;
	}

	public boolean respostaCerta(RecuperaDTO recuperaDTO) {
		Configuracao resposta = configuracaoRepository.findByDescricao("Resposta secreta");
		
		boolean equals = new BCryptPasswordEncoder().matches(recuperaDTO.toResposta().getDado(), resposta.getDado());
		System.out.println("-------------------------------------------- " + equals);
		System.out.println(resposta.getDado() + " - " + recuperaDTO.toResposta().getDado());
		return equals;
	}

}
