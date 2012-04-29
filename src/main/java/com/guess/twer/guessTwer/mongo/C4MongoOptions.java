package com.guess.twer.guessTwer.mongo;

import com.mongodb.MongoOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class C4MongoOptions extends MongoOptions {

    @Autowired
    public C4MongoOptions(@Value("${mongodb.threadsAllowedToBlockForConnectionMultiplier}") int threadsAllowedToBlockForConnectionMultiplier,
                          @Value("${mongodb.connectionsPerHost}") int connectionsPerHost,
                          @Value("${mongodb.socketTimeout}") int mongoSocketTimeout) {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
        this.connectionsPerHost = connectionsPerHost;
        this.socketTimeout = mongoSocketTimeout ;
    }
}
