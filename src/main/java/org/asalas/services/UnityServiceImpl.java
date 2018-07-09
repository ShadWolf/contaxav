package org.asalas.services;

import java.util.ArrayList;
import java.util.List;

import org.asalas.converters.UnityFormToUnity;
import org.asalas.domain.Unity;
import org.asalas.repositories.UnityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ArrayUtils;

@Service
public class UnityServiceImpl implements UnityService {
	
	@Autowired
	private UnityRepository unityRepository;
	
	private UnityFormToUnity unityFormToUnity;

	@Autowired
	public void UnityServiceImpl(UnityRepository unityRepository, UnityFormToUnity unityFormToUnity ) {
		this.unityFormToUnity = unityFormToUnity;
		this.unityRepository = unityRepository;
	}

	@Override
	public List<Unity> listAll() {
		List<Unity> unities = new ArrayList<>();
		//unityRepository.findAll().forEach(unities::add);
		return unities;
	}

	@Override
	public Unity saveOrUpdate(Unity unit) {
		unityRepository.save(unit);
		return unit;
	}

	@Override
	public void delete(String id) {
		unityRepository.deleteById(id);
	}
	@Override
	public Unity getById(String id) {
		return unityRepository.findById(id).orElse(null);
	}

}
