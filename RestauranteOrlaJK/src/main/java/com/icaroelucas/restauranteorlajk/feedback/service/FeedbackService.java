package com.icaroelucas.restauranteorlajk.feedback.service;

import com.icaroelucas.restauranteorlajk.entities.feedback.repository.FeedbackRepository;
import com.icaroelucas.restauranteorlajk.feedback.dto.NovoFeedbackDTO;

public class FeedbackService {

	FeedbackRepository feedbackRepository = null;
	
	public FeedbackService iniciar(FeedbackRepository feedbackRepository) {
		if(!foiIniciado()) this.feedbackRepository = feedbackRepository;
		return this;
	}
	
	private boolean foiIniciado() {
		if(feedbackRepository != null) return true;
		return false;
	}

	public void novoFeedback(NovoFeedbackDTO feedback) {
		feedbackRepository.save(feedback.toFeedback());
		
	}
	
	

}
