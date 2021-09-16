package br.ufc.mdcc.infoshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.mdcc.infoshop.model.Feedback;
import br.ufc.mdcc.infoshop.model.Product;
import br.ufc.mdcc.infoshop.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/products")
public class ProductController {

	@Autowired
	public ProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public Flux<Product> getProducts() {
		return productService.getProducts();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Mono<ResponseEntity<Product>> addProduct(@RequestBody Product product) {
		return productService.addProduct(product).map(result -> ResponseEntity.status(HttpStatus.OK).body(result));
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public Flux<Feedback> getProduct(@PathVariable("id") Integer id) {
		return productService.getFeedbacksByProductId(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product Product) {
		Product result = productService.updateProduct(id, Product.getName(), Product.getPrice());

		if (result != null) {
			return new ResponseEntity<Product>(result, HttpStatus.OK);
		}

		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
		if (productService.removeProduct(id)) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
