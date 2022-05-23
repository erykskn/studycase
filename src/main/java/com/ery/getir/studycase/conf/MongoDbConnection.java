package com.ery.getir.studycase.conf;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class MongoDbConnection extends AbstractMongoClientConfiguration {
    private final String DB_NAME = "test";
    private final String CONNECTION_STRING = "mongodb://localhost:27017/";

    @Override
    protected String getDatabaseName() {
        return DB_NAME;
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.example");
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(CONNECTION_STRING + DB_NAME);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }
}
