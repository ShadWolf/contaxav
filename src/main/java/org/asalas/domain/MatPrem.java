package org.asalas.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MatPrem {
	@Id
	private final ObjectId _id = new ObjectId();
	private String name;
	private Boolean alergen;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getAlergen() {
		return alergen;
	}
	public void setAlergen(Boolean alergen) {
		this.alergen = alergen;
	}
	public ObjectId get_id() {
		return _id;
	}

}
