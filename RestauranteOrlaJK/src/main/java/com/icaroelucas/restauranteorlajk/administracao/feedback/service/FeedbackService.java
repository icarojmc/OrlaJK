package com.icaroelucas.restauranteorlajk.administracao.feedback.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.entities.feedback.model.Feedback;
import com.icaroelucas.restauranteorlajk.entities.feedback.repository.FeedbackRepository;

public class FeedbackService {

	FeedbackRepository feedbackRepository = null;
	
	public FeedbackService iniciar(FeedbackRepository feedbackRepository) {
		if (!foiIniciado())
			this.feedbackRepository = feedbackRepository;
		return this;
	}

	private boolean foiIniciado() {
		return feedbackRepository != null;
	}
	
	public Model popularModel(Model model, int nPagina) {
		Page<Feedback> page = buscaFeedback(nPagina);
		model.addAttribute("feedbacks", page.getContent());
		model.addAttribute("nPagina", nPagina);
		model.addAttribute("totalPaginas", page.getTotalPages());
		model.addAllAttributes(buscaResumos());
		return model;
	}

	private Map<String, Object> buscaResumos() {
		Map<String, Object> resumos = new HashMap<>();
		resumos.put("mediaNotaAtendimento", feedbackRepository.avgNotaAtendimento());
		resumos.put("mediaNotaComida", feedbackRepository.avgNotaComida());
		resumos.put("mediaNotaEstrutura", feedbackRepository.avgNotaEstrutura());
		resumos.put("contagemGeral", feedbackRepository.countFeedback());
		
		resumos.put("mediaNotaAtendimentoSemana", feedbackRepository.avgNotaAtendimentoSemana());
		resumos.put("mediaNotaComidaSemana", feedbackRepository.avgNotaComidaSemana());
		resumos.put("mediaNotaEstruturaSemana", feedbackRepository.avgNotaEstruturaSemana());
		resumos.put("contagemGeralSemana", feedbackRepository.countFeedbackSemana());
		
		return resumos;
	}

	private Page<Feedback> buscaFeedback(int nPagina) {
		
		Pageable pageable = PageRequest.of(nPagina -1, 5, Sort.by("dia").descending());
		
		
		return feedbackRepository.findAll(pageable);
	}
	
	
}
