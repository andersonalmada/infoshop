package br.ufc.mdcc.infoshop.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufc.mdcc.infoshop.model.Feedback;
import br.ufc.mdcc.infoshop.model.Product;
import br.ufc.mdcc.infoshop.repository.IProductRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class ProductService {

	@Autowired
	IProductRepository productRepo;

	@Value("${endpoint.feedbacks}")
	String endpointFeedbacks;

	public Product addProduct(String name, Float price) {
		Product Product = new Product(name, price);
		return productRepo.save(Product);
	}

	public boolean removeProduct(Integer id) {
		if (productRepo.existsById(id)) {
			
			OkHttpClient client = new OkHttpClient();

			Request request = new Request.Builder().url(endpointFeedbacks + "/product/" + id).delete().build();

			try (Response response = client.newCall(request).execute()) {
				productRepo.deleteById(id);
				return true;	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		return false;
	}

	public List<Product> getProducts() {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url(endpointFeedbacks).build();

		try (Response response = client.newCall(request).execute()) {
			ObjectMapper objectMapper = new ObjectMapper();
			List<Feedback> feedbacks = objectMapper.readValue(response.body().string(),
					new TypeReference<List<Feedback>>() {
					});

			if (feedbacks != null) {
				System.out.println(feedbacks);
				List<Product> products = productRepo.findAll();

				if (products != null) {

					for (Product product : products) {
						List<Feedback> feedbacksOfProdutct = new ArrayList<Feedback>();

						for (Feedback feedback : feedbacks) {
							if (product.getId() == feedback.getProductId()) {
								feedbacksOfProdutct.add(feedback);
							}
						}

						product.setFeedbacks(feedbacksOfProdutct);
					}

					return products;
				}
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public Product getProduct(Integer id) {
		Optional<Product> result = productRepo.findById(id);

		if (result.isPresent()) {
			return result.get();
		}

		return null;
	}

	public Product updateProduct(Integer id, String name, Float price) {
		Optional<Product> result = productRepo.findById(id);

		if (result.isPresent()) {
			result.get().setName(name);
			result.get().setPrice(price);
			productRepo.save(result.get());
			return result.get();
		}

		return null;
	}
}
