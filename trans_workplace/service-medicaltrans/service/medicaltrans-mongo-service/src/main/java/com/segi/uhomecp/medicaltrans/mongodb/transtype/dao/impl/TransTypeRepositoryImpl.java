package com.segi.uhomecp.medicaltrans.mongodb.transtype.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.mongodb.transtype.dao.TransTypeOperations;
import com.segi.uhomecp.medicaltrans.mongodb.transtype.entity.TransType;

@Component
public class TransTypeRepositoryImpl implements TransTypeOperations {

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public void saveTransType(TransType transType) {
		mongoOperations.save(transType);
	}

	@Override
	public void updateTransTypeById(Integer id, TransType transType) {
		Update up = new Update();
		up.set("walkTypeAmount", transType.getDrugTransTypeAmount());
		up.set("flatCartypeAmount", transType.getDrugTransTypeAvgTime());
		up.set("pushingBedTypeAmount", transType.getSampleTransTypeAmount());
		up.set("wheelchairTypeAmount", transType.getSampleTransTypeAvgTime());
		up.set("pushingBedTypeAmount", transType.getBloodTransTypeAmount());
		up.set("wheelchairTypeAmount", transType.getBloodTransTypeAvgTime());
		up.set("goodTransTypeAmount", transType.getPatientTransTypeAmount());
		up.set("goodTransTypeAvgTime", transType.getPatientTransTypeAvgTime());
		up.set("goodTransTypeAmount", transType.getGoodTransTypeAmount());
		up.set("goodTransTypeAvgTime", transType.getGoodTransTypeAvgTime());
		up.set("bookTransTypeAmount", transType.getBookTransTypeAmount());
		up.set("bookTransTypeAvgTime", transType.getBookTransTypeAvgTime());
		// 符合条件则修改，不符合则新增
		mongoOperations.upsert(new Query(Criteria.where("_id").is(id)), up, TransType.class);
	}

	@Override
	public TransType findTransTypeById(Integer id) {
		return mongoOperations.findById(id, TransType.class);
	}

	@Override
	public void deleteTransTypeById(Integer id) {
		mongoOperations.remove(new Query(Criteria.where("_id").is(id)), TransType.class);
	}
}
