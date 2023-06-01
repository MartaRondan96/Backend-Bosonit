package com.concretepage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages = "com.concretepage.repository")
public class MongoDBConfig extends AbstractMongoConfiguration {
	@Override
	public String getDatabaseName() {
		return "myMongoDB";
	}
	@Override
	@Bean
	public MongoClient mongoClient() {
		ServerAddress address = new ServerAddress("127.0.0.1", 27017);
		MongoCredential credential = MongoCredential.createCredential("mdbUser", getDatabaseName(), "cp".toCharArray());
		MongoClientOptions options = new MongoClientOptions.Builder().build();
        
		MongoClient client = new MongoClient(address, credential, options);
		return client;
	}
}