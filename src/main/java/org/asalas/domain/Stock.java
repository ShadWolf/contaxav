package org.asalas.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.math.BigDecimal;


@Document
public class Stock {
	@Id
	private final ObjectId _id = new ObjectId();
	private Unity unit;
	private BigDecimal Quantity;
	public Unity getUnit() {
		return unit;
	}
	public void setUnit(Unity unit) {
		this.unit = unit;
	}
	public BigDecimal getQuantity() {
		return Quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		Quantity = quantity;
	}
	public ObjectId get_id() {
		return _id;
	}
	
}
