package br.ufc.mdcc.infoshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "feedbacks")
public class Feedback {
	@Id
	@GeneratedValue
	int id;
	int productId;
	String comment;
	int evaluation;

	public Feedback() {

	}
	
	public Feedback(int productId, String comment, int evaluation) {
		super();
		this.productId = productId;
		this.comment = comment;
		this.evaluation = evaluation;
	}

	public Feedback(int id, int productId, String comment, int evaluation) {
		super();
		this.id = id;
		this.productId = productId;
		this.comment = comment;
		this.evaluation = evaluation;
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
		return "Feedback [id=" + id + ", productId=" + productId + ", comment=" + comment + ", evaluation=" + evaluation
				+ "]";
	}

}
