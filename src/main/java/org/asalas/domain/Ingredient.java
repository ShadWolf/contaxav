package org.asalas.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.math.BigDecimal;


@Document
public class Ingredient {
	@Id
	private final ObjectId _id = new ObjectId();
	private MatPrem matprem;
	private BigDecimal Quantity;
	private Unity unity;
	public MatPrem getMatprem() {
		return matprem;
	}
	public void setMatprem(MatPrem matprem) {
		this.matprem = matprem;
	}
	public BigDecimal getQuantity() {
		return Quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		Quantity = quantity;
	}
	public Unity getUnity() {
		return unity;
	}
	public void setUnity(Unity unity) {
		this.unity = unity;
	}
	public ObjectId get_id() {
		return _id;
	}
}
