package br.ufc.mdcc.infoshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.mdcc.infoshop.model.Feedback;

public interface IFeedbackRepository extends JpaRepository<Feedback, Integer> {

	@Transactional
	List<Feedback> deleteByProductId(Integer id);
}

