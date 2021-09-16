package br.ufc.mdcc.infoshop.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class ProductFeedback {
	@Id
	int id;
	int productId;
	int feedbackId;

	public ProductFeedback() {

	}
	
	public ProductFeedback(int productId, int feedbackId) {
		this.productId = productId;
		this.feedbackId = feedbackId;
	}

	public ProductFeedback(int id, int productId, int feedbackId) {
		super();
		this.id = id;
		this.productId = productId;
		this.feedbackId = feedbackId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	@Override
	public String toString() {
		return "ProductFeedback [id=" + id + ", productId=" + productId + ", feedbackId=" + feedbackId + "]";
	}

}
