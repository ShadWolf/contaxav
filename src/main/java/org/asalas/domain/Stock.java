package org.asalas.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.math.BigDecimal;

@Data
@Document
public class Stock {
	@Id
	private final ObjectId _id = new ObjectId();
	private Unity unit;
	private BigDecimal Quantity;
}
