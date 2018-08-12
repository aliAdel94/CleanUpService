package com.mondiamedia.cleanup.kissapi;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages = "com.mondiamedia.cleanup.kissapi.repo")
public class SpringMongoConfig {

    private MongoClient getMongoClient() throws UnknownHostException {
        // Given (using real connection)
        final MongoCredential credential = MongoCredential.createMongoCRCredential("usALIS1XXf73a7cR", "KissAPI", "fPtDT4FX0uiEcj5XvhP0".toCharArray());
        final ServerAddress adr = new ServerAddress("ms1.mongo-db74-gt.liv.arvm.de", 27017);
        return new MongoClient(adr, Arrays.asList(credential));
    }

}
