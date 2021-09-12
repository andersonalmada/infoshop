package br.ufc.mdcc.infoshop.model;

public class Ranking {

	int productId;
	double evaluation;

	public Ranking() {

	}

	public Ranking(int productId, double evaluation) {
		super();
		this.productId = productId;
		this.evaluation = evaluation;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(double evaluation) {
		this.evaluation = evaluation;
	}

	@Override
	public String toString() {
		return "Ranking [productId=" + productId + ", evaluation=" + evaluation + "]";
	}

}
