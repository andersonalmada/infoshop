package br.ufc.mdcc.infoshop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufc.mdcc.infoshop.model.Feedback;
import br.ufc.mdcc.infoshop.model.Ranking;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/ranking")
public class RankingController {

	@RequestMapping(method = RequestMethod.POST)
	public Mono<List<Ranking>> getRanking(@RequestBody String feedbacks) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Feedback> listFeedbacks;		

		try {
			listFeedbacks = objectMapper.readValue(feedbacks, new TypeReference<List<Feedback>>() {
			});
			
			return Mono.just(computeRakingList(listFeedbacks));
			
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	private String computeEvaluation(List<Feedback> listFeedbacks) {
		double evaluatio = 0;

		for (Feedback feedback : listFeedbacks) {
			evaluatio += (double) feedback.getEvaluation();
		}

		return String.valueOf(evaluatio / (double) listFeedbacks.size());
	}

	private List<Ranking> computeRakingList(List<Feedback> listFeedbacks) {
		Map<Integer, Double> map = new HashMap<>();
		List<Ranking> list = new ArrayList<Ranking>();

		for (Feedback feedback : listFeedbacks) {
			map.put(feedback.getProductId(), 0.0);
		}

		for (Entry<Integer, Double> entry : map.entrySet()) {
			double evaluation = 0;
			int size = 0;

			for (Feedback feedback : listFeedbacks) {
				if (entry.getKey() == feedback.getProductId()) {
					evaluation += (double) feedback.getEvaluation();
					size++;
				}
			}

			list.add(new Ranking(entry.getKey(), evaluation / size));
		}

		return list;
	}
}
