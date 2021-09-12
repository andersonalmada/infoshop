package br.ufc.mdcc.infoshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.mdcc.infoshop.model.Feedback;
import br.ufc.mdcc.infoshop.repository.IFeedbackRepository;

@Service
public class FeedbackService {

	@Autowired
	IFeedbackRepository feedbackRepo;

	public Feedback addFeedback(int productId, String comment, int evaluation) {
		Feedback feedback = new Feedback(productId, comment, evaluation);
		return feedbackRepo.save(feedback);
	}

	public boolean removeFeedback(Integer id) {
		if (feedbackRepo.existsById(id)) {
			feedbackRepo.deleteById(id);
			return true;
		}

		return false;
	}

	public List<Feedback> getFeedbacks() {
		return feedbackRepo.findAll();
	}

	public Feedback getFeedback(Integer id) {
		Optional<Feedback> result = feedbackRepo.findById(id);

		if (result.isPresent()) {
			return result.get();
		}

		return null;
	}

	public Feedback updateFeedback(Integer id, String comment, int evaluation) {
		Optional<Feedback> result = feedbackRepo.findById(id);

		if (result.isPresent()) {
			result.get().setComment(comment);
			result.get().setEvaluation(evaluation);
			feedbackRepo.save(result.get());
			return result.get();
		}

		return null;
	}

	public boolean removeAll(Integer id) {
		if (feedbackRepo.deleteByProductId(id) != null) {
			return true;
		}

		return false;
	}

	public List<Feedback> getFeedbacksByProduct(Integer id) {
		List<Feedback> result = feedbackRepo.findByProductId(id);

		if (result != null) {
			return result;
		}

		return null;
	}
}
