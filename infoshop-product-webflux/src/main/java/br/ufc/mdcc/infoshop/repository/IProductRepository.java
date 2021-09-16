package br.ufc.mdcc.infoshop.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import br.ufc.mdcc.infoshop.model.Product;

@Repository
public interface IProductRepository extends ReactiveSortingRepository<Product, Integer>{

}

