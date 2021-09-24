package br.ufc.mdcc.infoshop.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import br.ufc.mdcc.infoshop.model.Feedback;
import br.ufc.mdcc.infoshop.service.FeedbackService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/feedbacks/ranking")
public class RankingController {

	@Autowired
	public FeedbackService feedbackService;

	@Value("${endpoint.ranking}")
	String endpointRanking;

	@RequestMapping(method = RequestMethod.GET)
	public Mono<List> getRanking() {
		Flux<Feedback> feedbacks = feedbackService.getFeedbacks();

		try {
			return WebClient.create().post().uri(new URI(endpointRanking))
					.accept(MediaType.APPLICATION_JSON)
					.body(feedbacks.collectList(), new ParameterizedTypeReference<List<Feedback>>() {}).retrieve().bodyToMono(List.class);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public Mono<List> getComputeMediaEvaluation(@PathVariable("id") Integer id) {
		Flux<Feedback> feedbacks = feedbackService.getFeedbacksByProduct(id);

		try {
			return WebClient.create().post().uri(new URI("http://localhost:31002/api/ranking"))
					.accept(MediaType.APPLICATION_JSON)
					.body(feedbacks.collectList(), new ParameterizedTypeReference<List<Feedback>>() {}).retrieve().bodyToMono(List.class);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
