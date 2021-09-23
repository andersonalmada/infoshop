package br.ufc.mdcc.infoshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@CrossOrigin
@RequestMapping(path = "/api/feedbacks")
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Feedback>> getFeedbacks() {
		return new ResponseEntity<List<Feedback>>(feedbackService.getFeedbacks(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<Feedback> getFeedback(@PathVariable("id") Integer id) {
		Feedback result = feedbackService.getFeedback(id);

		if (result != null) {
			return new ResponseEntity<Feedback>(result, HttpStatus.OK);
		}

		return new ResponseEntity<Feedback>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/product/{id}")
	public ResponseEntity<List<Feedback>> getFeedbacksByProduct(@PathVariable("id") Integer id) {
		List<Feedback> result = feedbackService.getFeedbacksByProduct(id);

		if (result != null) {
			return new ResponseEntity<List<Feedback>>(result, HttpStatus.OK);
		}

		return new ResponseEntity<List<Feedback>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback) {
		return new ResponseEntity<Feedback>(
				feedbackService.addFeedback(feedback.getProductId(), feedback.getComment(), feedback.getEvaluation()),
				HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public ResponseEntity<Feedback> updateFeedback(@PathVariable("id") Integer id, @RequestBody Feedback feedback) {
		Feedback result = feedbackService.updateFeedback(id, feedback.getComment(), feedback.getEvaluation());

		if (result != null) {
			return new ResponseEntity<Feedback>(result, HttpStatus.OK);
		}

		return new ResponseEntity<Feedback>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity<Void> deleteFeedback(@PathVariable("id") Integer id) {
		if (feedbackService.removeFeedback(id)) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/product/{id}")
	public ResponseEntity<Void> deleteAllFeedbacksByProduct(@PathVariable("id") Integer id) {
		if (feedbackService.removeAll(id)) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
