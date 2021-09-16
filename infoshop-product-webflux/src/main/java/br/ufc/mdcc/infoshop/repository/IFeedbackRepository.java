package br.ufc.mdcc.infoshop.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import br.ufc.mdcc.infoshop.model.Feedback;
import reactor.core.publisher.Flux;

@Repository
public interface IFeedbackRepository extends ReactiveSortingRepository<Feedback, Integer>{

	Flux<Feedback> findAllByProductId(Integer productId);
}

