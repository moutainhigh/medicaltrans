package com.segi.uhomecp.medicaltrans.mongodb.transsource.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.mongodb.transsource.dao.TransSourceOperations;
import com.segi.uhomecp.medicaltrans.mongodb.transsource.entity.TransSource;
import com.segi.uhomecp.medicaltrans.mongodb.transtype.entity.TransType;

@Component
public class TransSourceRepositoryImpl implements TransSourceOperations {

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public void saveTransSource(TransSource transSource) {
		mongoOperations.save(transSource);
	}

	@Override
	public void updateTransSourceById(Integer id, TransSource transSource) {
		Update up = new Update();
		up.set("fromHouseNum", transSource.getFromHouseNum());
		up.set("fromHouseId1", transSource.getFromHouseId1());
		up.set("fromHouseAmount1", transSource.getFromHouseAmount1());
		up.set("fromHouseId2", transSource.getFromHouseId2());
		up.set("fromHouseAmount2", transSource.getFromHouseAmount2());
		up.set("fromHouseId3", transSource.getFromHouseId3());
		up.set("fromHouseAmount3", transSource.getFromHouseAmount3());
		up.set("fromHouseId4", transSource.getFromHouseId4());
		up.set("fromHouseAmount4", transSource.getFromHouseAmount4());
		up.set("fromHouseId5", transSource.getFromHouseId5());
		up.set("fromHouseAmount5", transSource.getFromHouseAmount5());
		// 符合条件则修改，不符合则新增
		mongoOperations.upsert(new Query(Criteria.where("_id").is(id)), up, TransSource.class);
	}

	@Override
	public TransSource findTransSourceById(Integer id) {
		return mongoOperations.findById(id, TransSource.class);
	}

	@Override
	public void deleteTransSourceById(Integer id) {
		mongoOperations.remove(new Query(Criteria.where("_id").is(id)), TransSource.class);
	}
}
