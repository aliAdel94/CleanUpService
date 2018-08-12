package com.mondiamedia.cleanup.kissapi.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mondiamedia.cleanup.kissapi.entity.KissAPI;

@Repository
public interface KissAPIRepository extends MongoRepository<KissAPI, Long>, customKissAPIRepository {

}
