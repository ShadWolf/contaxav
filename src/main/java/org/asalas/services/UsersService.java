package org.asalas.services;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.asalas.dto.Users;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class UsersService {
	//@Autowired
	//private MongoClient mongoClient;
	
    public List<Users> listSecUsersMongoDB() {
      	List<Users> lstUsers =  new ArrayList<>(); 
      	MongoClient mongoClient = new MongoClient(); 
    	MongoDatabase database = mongoClient.getDatabase("springsecurity");
        MongoCollection<Document> collection = database.getCollection("users");
        MongoCursor<Document> cursor = collection.find().iterator();
      	while(cursor.hasNext()) {
      		Document d = cursor.next(); 
      		Users e =  new Users(); 
      		e.setName(d.getString("name"));
      		e.setSurname(d.getString("surname"));
      		e.setPassword(d.getString("password"));
      		// e.setAuthorities((List<String>) d.get("authorities") );
      		lstUsers.add(e);
      	}
         return lstUsers;
    }
    
    public String addUser(Users userForm) {
     	MongoClient mongoClient = new MongoClient(); 
    	MongoDatabase database = mongoClient.getDatabase("springsecurity");
        MongoCollection<Document> collection = database.getCollection("users");
    	
        Document document = new Document();
    	document.put("name", userForm.getName());
    	document.put("surname", userForm.getSurname());
    	document.put("password", userForm.getPassword());
    	document.put("authorities", Arrays.asList("admin", "user"));
         
    	collection.insertOne(document);
    	return "Nouvel Utilisateur ajoute !";
    }
}
