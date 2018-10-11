package com.segi.uhomecp.medicaltrans.mongodb.track.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.mongodb.track.dao.TrackOperations;
import com.segi.uhomecp.medicaltrans.mongodb.track.entity.Item;
import com.segi.uhomecp.medicaltrans.mongodb.track.entity.TaskTrack;


@Component
public class TrackRepositoryImpl implements TrackOperations {

    @Autowired
    private MongoOperations mongoOperations;


    public void save(TaskTrack track) {
        mongoOperations.save(track);
    }

	@Override
	public void push(Integer id, List<Item> itemList) {
		Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        Update up = new Update();
        up.push("flowList", itemList);
		mongoOperations.upsert(query, up, TaskTrack.class);
	}

	@Override
	public TaskTrack findById(Integer id) {
		return mongoOperations.findById(id, TaskTrack.class);
	}

	@Override
	public void push(Integer id, Item item) {
		Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        Update up = new Update();
        up.push("flowList", item);
		mongoOperations.upsert(query, up, TaskTrack.class);
	}

	@Override
	public void update(TaskTrack track) {
		Criteria criteria = Criteria.where("_id").is(track.getId());
        Query query = new Query(criteria);
        Update up = new Update();
        up.set("beginTime", track.getBeginTime());
		mongoOperations.upsert(query, up, TaskTrack.class);
	}
}
