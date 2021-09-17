package br.ufc.mdcc.infoshop.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import br.ufc.mdcc.infoshop.model.Feedback;
import br.ufc.mdcc.infoshop.model.Product;
import br.ufc.mdcc.infoshop.repository.IProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
public class ProductService {

	@Autowired
	public IProductRepository productRepo;

	@Value("${endpoint.feedbacks}")
	String endpointFeedbacks;

	@Transactional
	public Mono<Product> addProduct(Product product) {
		return productRepo.save(product);
	}

	public Flux<Product> getProducts() {
		try {
			Flux<Feedback> feedbacks = WebClient.create().get().uri(new URI(endpointFeedbacks))
					.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(Feedback.class);

			Flux<Product> flux = productRepo.findAll()
					.flatMap(product -> Mono.just(product).zipWith(feedbacks.collectList())).map(result -> {
						Product t1 = result.getT1();

						List<Feedback> list = new ArrayList<Feedback>();

						for (Feedback feedback : result.getT2()) {
							if (t1.getId() == feedback.getProductId()) {
								list.add(feedback);
							}
						}

						t1.setFeedbacks(list);

						return result.getT1();
					});

			return flux;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Transactional
	public Mono<Void> removeProduct(Integer id) {
		try {
			Mono<Void> delete = WebClient.create().delete().uri(new URI(endpointFeedbacks + "/product/" + id)).retrieve()
					.bodyToMono(Void.class);
			
			return Mono.zip(delete, productRepo.deleteById(id)).map(Tuple2::getT1);
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public Mono<Product> getProduct(Integer id) {
		Mono<Product> product = productRepo.findById(id);

		try {
			Flux<Feedback> feedbacks = WebClient.create().get().uri(new URI(endpointFeedbacks + "/product/" + id))
					.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(Feedback.class);

			return Mono.zip(product, feedbacks.collectList()).map(result -> {
				Product t1 = result.getT1();
				t1.setFeedbacks(result.getT2());

				return result.getT1();
			});

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Transactional
	public Mono<Product> updateProduct(int id, Product product) {
		return productRepo.findById(id).flatMap(result -> {
			result.setName(product.getName());
			result.setPrice(product.getPrice());

			return productRepo.save(result);
		});
	}

	private Collection<Feedback> toFeedbacks(int productId, Collection<Feedback> feedbacks) {
		if (feedbacks == null) {
			return new LinkedHashSet<>();
		}

		return feedbacks.stream()
				.map(feedback -> new Feedback(feedback.getComment(), feedback.getEvaluation(), productId))
				.collect(Collectors.toSet());
	}

	public Flux<Feedback> getFeedbacksByProductId(Integer id) {
		return null;
	}
}
