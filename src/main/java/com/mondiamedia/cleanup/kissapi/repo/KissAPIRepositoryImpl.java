package com.mondiamedia.cleanup.kissapi.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mondiamedia.cleanup.kissapi.entity.KissAPI;

public class KissAPIRepositoryImpl implements customKissAPIRepository {

    @Autowired
    public MongoTemplate mongoTemplate;

    @Override
    public void update(final String userId, final String updatedUserId) {

        final Query query = new Query(Criteria.where("owner").is(userId));
        final Update update = new Update();
        update.set("owner", updatedUserId);

        try {
            mongoTemplate.updateFirst(query, update, KissAPI.class);
        } catch (final Exception e) {
            final Error er = new Error("can`t update kissApi");
            throw er;
        }

    }

}
