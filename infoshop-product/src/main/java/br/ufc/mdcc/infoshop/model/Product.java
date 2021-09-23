package br.ufc.mdcc.infoshop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "products")
public class Product {
	@Id
	@GeneratedValue
	int id;
	String name;
	Float price;
	@ElementCollection
	List<Feedback> feedbacks;
	
	public Product() {
		
	}
	
	public Product(String name, Float price) {
		this.name = name;
		this.price = price;
		this.feedbacks = new ArrayList<Feedback>();
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

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", feedbacks=" + feedbacks + "]";
	}

}
