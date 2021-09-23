package br.ufc.mdcc.infoshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.mdcc.infoshop.model.Product;

public interface IProductRepository extends JpaRepository<Product, Integer> {

}

