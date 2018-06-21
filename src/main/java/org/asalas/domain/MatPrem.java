package org.asalas.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class MatPrem {
	@Id
	private final ObjectId _id = new ObjectId();
	private String name;
	private Boolean alergen;
}
