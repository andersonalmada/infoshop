package br.ufc.mdcc.infoshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.mdcc.infoshop.model.Feedback;
import br.ufc.mdcc.infoshop.model.Product;
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
	
	public Mono<Product> getFeedbacksByProduct(Product product) {
		Mono<Product> setFeedbacks = feedRepo.findAllByProductId(product.getId()).collectList().flatMap(result -> {
			product.setFeedbacks(result);
			return Mono.just(product);
		});
		
		return setFeedbacks;
	}
	
	public Mono<Feedback> getFeedback(Integer id) {
		return feedRepo.findById(id);	}
	
	@Transactional
	public Mono<Void> removeFeedback(Integer id) {
		return feedRepo.deleteById(id);
	}	
	
	public Mono<Integer> removeFeedbacksByProductId(Integer id) {
		return feedRepo.deleteAllByProductId(id);
	}
	
	@Transactional
	public Mono<Feedback> updateFeedback(int id, Feedback feedback) {
		return feedRepo.findById(id).flatMap(result -> {
			result.setComment(feedback.getComment());
			result.setEvaluation(feedback.getEvaluation());
			
			return feedRepo.save(result);
		});
	}
}
