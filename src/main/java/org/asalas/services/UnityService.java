package org.asalas.services;

import java.util.List;

import org.asalas.domain.Unity;
import org.asalas.repositories.UnityRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface UnityService {

	List<Unity> listAll();
	Unity saveOrUpdate(Unity unit);
	Unity getById(String id);
	void delete(String id);
	
}
