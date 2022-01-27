package com.supertal.assignment.config;

import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 27/01/2022 - 13:46
 */

@Configuration
public class DatabaseConfiguration {
    @Value("${mongodb.host:'localhost'}")
    private String dbHost;
    @Value("${mongodb.port:27017}")
    private Integer dbPort;
    @Value("${mongodb.database:'country_db'}")
    private String dbName;
    @Value("${mongodb.authentication-database:'admin'}")
    private String authDB;
    @Value("${mongodb.username:'root'}")
    private String dbUserName;
    @Value("${mongodb.password:'root'}")
    private String dbPassword;

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(MongoClient mongoClient) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, dbName);
    }

    /*
     * Factory bean that creates the com.mongodb.client.MongoClient instance
     */
    public @Bean
    MongoClientFactoryBean mongo() {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setHost(dbHost);
        mongo.setPort(dbPort);
        MongoCredential[] mongoCredentials = new MongoCredential[1];
        MongoCredential mongoCredential = MongoCredential.createCredential(dbUserName, authDB, dbPassword.toCharArray());
        mongoCredentials[0] = mongoCredential;
        mongo.setCredential(mongoCredentials);
        return mongo;
    }
}
