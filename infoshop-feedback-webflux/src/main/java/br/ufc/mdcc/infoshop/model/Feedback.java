package br.ufc.mdcc.infoshop.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class Feedback {
	@Id
	int id;
	String comment;
	int evaluation;
	int productId;

	public Feedback() {

	}

	public Feedback(String comment, int evaluation, int productId) {
		this.comment = comment;
		this.evaluation = evaluation;
		this.productId = productId;
	}

	public Feedback(int id, String comment, int evaluation, int productId) {
		super();
		this.id = id;
		this.comment = comment;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", comment=" + comment + ", evaluation=" + evaluation + ", productId="
				+ productId + "]";
	}

}
