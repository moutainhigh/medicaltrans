package com.segi.uhomecp.medicaltrans.mongodb.userstatus.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.mongodb.userstatus.dao.UserStatusOperations;
import com.segi.uhomecp.medicaltrans.mongodb.userstatus.entity.UserStautsCurrent;


@Component
public class UserStatusRepositoryImpl implements UserStatusOperations {

    @Autowired
    private MongoOperations mongoOperations;

	@Override
	public void save(UserStautsCurrent userStautsCurrent ) {
		  mongoOperations.save(userStautsCurrent);
	}
	
	@Override
	public UserStautsCurrent findById(String id) {
		return mongoOperations.findById(id, UserStautsCurrent.class);
	}

}
