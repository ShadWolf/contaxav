package org.asalas.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
@Document
public class Receipe {
	@Id
	private ObjectId _id;
	private String Nombre;
	private Ingredient Ingredient1;
	
	public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

}
