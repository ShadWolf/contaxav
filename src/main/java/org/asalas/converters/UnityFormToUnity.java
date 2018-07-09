package org.asalas.converters;

import org.asalas.commands.UnityForm;
import org.asalas.domain.Unity;
import org.asalas.services.UnityService;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UnityFormToUnity implements Converter< UnityForm, Unity> {	

	@Override
	public Unity convert(UnityForm unityForm) {
		Unity unity = new Unity();
		if(unityForm.getId() != null && !StringUtils.isEmpty(unityForm.getId()) ) 
		{
			unity.set_id(new ObjectId(unityForm.getId()));
		}
		unity.setBaseunit(unityForm.getBaseunit());
		unity.setConvamount(unityForm.getConvamount());
		unity.setName(unityForm.getName());
		unity.setSimbol(unityForm.getSimbol());
		return unity;
	}
}
