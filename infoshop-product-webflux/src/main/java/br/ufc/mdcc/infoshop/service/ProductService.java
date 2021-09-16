package br.ufc.mdcc.infoshop.service;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.mdcc.infoshop.model.Feedback;
import br.ufc.mdcc.infoshop.model.Product;
import br.ufc.mdcc.infoshop.model.ProductFeedback;
import br.ufc.mdcc.infoshop.repository.IFeedbackRepository;
import br.ufc.mdcc.infoshop.repository.IProductFeedbackRepository;
import br.ufc.mdcc.infoshop.repository.IProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	@Autowired
	public IProductRepository productRepo;

	@Autowired
	public IFeedbackRepository feedRepo;

	@Autowired
	public IProductFeedbackRepository productItemRepo;

	@Transactional
	public Mono<Product> addProduct(Product product) {
		return productRepo.save(product)
				// Save the links to the tags
				.flatMap(savedItem -> feedRepo.saveAll(toFeedbacks(savedItem.getId(), savedItem.getFeedbacks()))
						.collectList().then(Mono.just(savedItem)));
	}

	public Flux<Product> getProducts() {
		Flux<Product> flux = productRepo.findAll()
				.flatMap(product -> Mono.just(product)
						.zipWith(feedRepo.findAllByProductId(product.getId()).collectList())
						).map(result -> {
							Product t1 = result.getT1();
							t1.setFeedbacks(result.getT2());
							
							return result.getT1();
						});
		
		return flux;
	}

	public boolean removeProduct(Integer id) {
		return false;
	}

	public Product getProduct(Integer id) {

		return null;
	}

	public Product updateProduct(Integer id, String name, Float price) {

		return null;
	}

	private Mono<Product> loadRelations(Product product) {
		Mono<Product> mono = Mono.just(product);

		mono.zipWith(feedRepo.findAllByProductId(product.getId()).collectList())
				.map(result -> result.getT1().setFeedbacks(result.getT2()));

		return mono;
	}

	private Collection<ProductFeedback> toProductFeedbacks(Integer productId, Collection<Feedback> feedbacks) {
		if (feedbacks == null) {
			return new LinkedHashSet<>();
		}

		return feedbacks.stream().map(feedback -> new ProductFeedback(productId, feedback.getId()))
				.collect(Collectors.toSet());
	}

	private Collection<Feedback> toFeedbacks(int productId, Collection<Feedback> feedbacks) {
		if (feedbacks == null) {
			return new LinkedHashSet<>();
		}

		return feedbacks.stream()
				.map(feedback -> new Feedback(feedback.getDescription(), feedback.getEvaluation(), productId))
				.collect(Collectors.toSet());
	}

	public Flux<Feedback> getFeedbacksByProductId(Integer id) {
		return feedRepo.findAllByProductId(id);
	}
}
