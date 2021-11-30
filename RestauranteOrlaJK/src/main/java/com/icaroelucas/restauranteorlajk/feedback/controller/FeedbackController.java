package com.icaroelucas.restauranteorlajk.feedback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.entities.feedback.repository.FeedbackRepository;
import com.icaroelucas.restauranteorlajk.feedback.dto.NovoFeedbackDTO;
import com.icaroelucas.restauranteorlajk.feedback.service.FeedbackService;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	FeedbackService feedbackService = new FeedbackService();
	
	@GetMapping("")
	public String feedback() {
		feedbackService.iniciar(feedbackRepository);
		return "feedback/feedback";
	}
	
	@PostMapping("")
	public String novoAdicionado(Model model, NovoFeedbackDTO feedback) {
		feedbackService.novoFeedback(feedback);
		model.addAttribute("enviado", true);
		feedbackService.iniciar(feedbackRepository);
		return "feedback/enviado";
	}

}
