package br.ufc.mdcc.infoshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.ResponseEntity.noContent;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.mdcc.infoshop.model.Feedback;
import br.ufc.mdcc.infoshop.service.FeedbackService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/feedbacks")
public class FeedbackController {

	@Autowired
	public FeedbackService feedbackService;

	@RequestMapping(method = RequestMethod.GET)
	public Flux<Feedback> getFeedbacks() {
		return feedbackService.getFeedbacks();
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public Mono<ResponseEntity<Feedback>> getFeedback(@PathVariable("id") Integer id) {
		return feedbackService.getFeedback(id).map(result -> ResponseEntity.status(HttpStatus.OK).body(result));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/product/{id}")
	public Flux<Feedback> getFeedbacksByProduct(@PathVariable("id") Integer id) {
		return feedbackService.getFeedbacksByProduct(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Mono<ResponseEntity<Feedback>> addFeedback(@RequestBody Feedback Feedback) {
		return feedbackService.addFeedback(Feedback).map(result -> ResponseEntity.status(HttpStatus.OK).body(result));
	}

	
	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public Mono<ResponseEntity<Feedback>> updateFeedback(@PathVariable("id") Integer id, @RequestBody Feedback Feedback) {
		return feedbackService.updateFeedback(id, Feedback)
				.map(result -> ResponseEntity.status(HttpStatus.OK).body(result));
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public Mono<ResponseEntity<Void>> deleteFeedback(@PathVariable("id") Integer id) {
		return feedbackService.removeFeedback(id).map(empty -> noContent().build());
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/product/{id}")
	public Mono<ResponseEntity<Void>> deleteAll(@PathVariable("id") Integer id) {
		System.out.println(id);
		return feedbackService.removeFeedbacksByProductId(id).map(empty -> noContent().build());
	}
}
