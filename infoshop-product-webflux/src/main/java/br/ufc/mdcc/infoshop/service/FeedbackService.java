package br.ufc.mdcc.infoshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.mdcc.infoshop.model.Feedback;
import br.ufc.mdcc.infoshop.repository.IFeedbackRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FeedbackService {

	@Autowired
	public IFeedbackRepository feedRepo;

	@Transactional
	public Mono<Feedback> addFeedback(Feedback feedback) {
		return feedRepo.save(feedback);
	}

	public Flux<Feedback> getFeedbacks() {
		return feedRepo.findAll();
	}
	
	public Flux<Feedback> getFeedbacksByProduct(int productId) {
		return feedRepo.findAllByProductId(productId);
	}
}
