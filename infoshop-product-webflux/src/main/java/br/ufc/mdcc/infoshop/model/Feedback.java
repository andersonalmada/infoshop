package br.ufc.mdcc.infoshop.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class Feedback {
	@Id
	int id;
	String description;
	int evaluation;
	int productId;

	public Feedback() {

	}

	public Feedback(String description, int evaluation, int productId) {
		this.description = description;
		this.evaluation = evaluation;
		this.productId = productId;
	}

	public Feedback(int id, String description, int evaluation, int productId) {
		super();
		this.id = id;
		this.description = description;
		this.evaluation = evaluation;
		this.productId = productId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", description=" + description + ", evaluation=" + evaluation + ", productId="
				+ productId + "]";
	}

}
