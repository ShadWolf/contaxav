package org.asalas.services;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.asalas.domain.MongoUserDetails;
import org.asalas.dto.Users;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Created by gkatzioura on 9/27/16.
 * 
 */
@Component
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
	private MongoClient mongoClient;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String uname) throws UsernameNotFoundException {

		MongoDatabase database = mongoClient.getDatabase("springsecurity");
		MongoCollection<Document> collection = database.getCollection("users");
		Document document = collection.find(Filters.eq("name", uname)).first();
		System.out.println("loadUserByUsername collection: " + collection.toString());
		System.out.println("loadUserByUsername uname: " + uname);
		System.out.println("document name: " + document.getString("name"));
		if (document != null) {
			String name = document.getString("name");
			String surname = document.getString("surname");
			String password = passwordEncoder.encode(document.getString("password"));
			List<String> authorities = (List<String>) document.get("authorities");
			MongoUserDetails mongoUserDetails = new MongoUserDetails(uname, password,
					authorities.toArray(new String[authorities.size()]));
			return mongoUserDetails;
		}

		return null;
	}
}
