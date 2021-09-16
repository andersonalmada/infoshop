package br.ufc.mdcc.infoshop.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import br.ufc.mdcc.infoshop.model.ProductFeedback;
import reactor.core.publisher.Flux;

@Repository
public interface IProductFeedbackRepository extends ReactiveSortingRepository<ProductFeedback, Integer>{

	Flux<ProductFeedback> findAllByProductId(Long productId);
}

