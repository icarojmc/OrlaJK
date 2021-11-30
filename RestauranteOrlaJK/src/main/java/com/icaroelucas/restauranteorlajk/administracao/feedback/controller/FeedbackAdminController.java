package com.icaroelucas.restauranteorlajk.administracao.feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icaroelucas.restauranteorlajk.administracao.feedback.service.FeedbackService;
import com.icaroelucas.restauranteorlajk.entities.feedback.repository.FeedbackRepository;

@Controller
@RequestMapping("/administracao/feedback")
public class FeedbackAdminController {

	@Autowired
	FeedbackRepository feedbackRepository;
	
	FeedbackService feedbackService = new FeedbackService();
	
	@GetMapping("")
	public String feedback(Model model) {
		model = feedbackService.iniciar(feedbackRepository).
		popularModel(model, 1);
		
		
		return "administracao/feedback/feedback";
	}
	
	@GetMapping("/pagina")
	public String feedbackPagina(Model model, @RequestParam int nPagina) {
		model = feedbackService.iniciar(feedbackRepository).
		popularModel(model, nPagina);
		
		
		return "administracao/feedback/feedback";
	}

}
