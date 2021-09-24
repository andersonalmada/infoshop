package br.ufc.mdcc.infoshop.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class Product {
	@Id
	int id;
	String name;
	Float price;
	
	@Transient
	List<Feedback> feedbacks;

	public Product() {

	}

	public Product(String name, Float price) {
		this.name = name;
		this.price = price;
	}

	public Product(int id, String name, Float price, List<Feedback> feedbacks) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.feedbacks = feedbacks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public List<Feedback> setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
		return feedbacks;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", feedbacks=" + feedbacks + "]";
	}

}
