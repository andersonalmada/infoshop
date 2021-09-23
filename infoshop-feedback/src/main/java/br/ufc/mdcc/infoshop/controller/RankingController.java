package br.ufc.mdcc.infoshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufc.mdcc.infoshop.model.Feedback;
import br.ufc.mdcc.infoshop.service.FeedbackService;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/feedbacks/ranking")
public class RankingController {

	@Autowired
	FeedbackService feedbackService;

	@Value("${endpoint.rankings}")
	String endpointRankings;

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<String> getEvaluation(@PathVariable("id") Integer id) {
		OkHttpClient client = new OkHttpClient();
		ObjectMapper objectMapper = new ObjectMapper();

		List<Feedback> feedbacks = feedbackService.getFeedbacksByProduct(id);

		FormBody formBody;

		try {
			formBody = new FormBody.Builder().add("feedbacks", objectMapper.writeValueAsString(feedbacks)).build();

			Request request = new Request.Builder().url(endpointRankings + "/compute").post(formBody).build();

			try (Response response = client.newCall(request).execute()) {
				return new ResponseEntity<String>(response.body().string(), HttpStatus.OK);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> getFeedback() {
		OkHttpClient client = new OkHttpClient();
		ObjectMapper objectMapper = new ObjectMapper();

		List<Feedback> feedbacks = feedbackService.getFeedbacks();

		FormBody formBody;

		try {
			formBody = new FormBody.Builder().add("feedbacks", objectMapper.writeValueAsString(feedbacks)).build();

			Request request = new Request.Builder().url(endpointRankings).post(formBody).build();

			try (Response response = client.newCall(request).execute()) {
				return new ResponseEntity<String>(response.body().string(), HttpStatus.OK);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
}
