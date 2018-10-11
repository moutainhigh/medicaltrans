package com.segi.uhomecp.medicaltrans.mongodb.transway.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.mongodb.transway.dao.TransWayOperations;
import com.segi.uhomecp.medicaltrans.mongodb.transway.entity.TransWay;


@Component
public class TransWayRepositoryImpl implements TransWayOperations {

    @Autowired
    private MongoOperations mongoOperations;
	
    @Override
    public void saveTransWay(TransWay transWay) {
        mongoOperations.save(transWay);
    }

	@Override
	public void updateTransWayById(Integer id, TransWay transWay) {
        Update up = new Update();
        up.set("walkTypeAmount", transWay.getWalkTypeAmount());
        up.set("flatCartypeAmount", transWay.getFlatCartypeAmount());
        up.set("pushingBedTypeAmount", transWay.getPushingBedTypeAmount());
        up.set("wheelchairTypeAmount", transWay.getWheelchairTypeAmount());
        // 符合条件则修改，不符合则新增
		mongoOperations.upsert(new Query(Criteria.where("_id").is(id)), up, TransWay.class);
	}

	@Override
	public TransWay findTransWayById(Integer id) {
		return mongoOperations.findById(id, TransWay.class);
	}
	

	@Override
	public void deleteTransWayById(Integer id) {
		mongoOperations.remove(new Query(Criteria.where("_id").is(id)), TransWay.class);
	}
}
