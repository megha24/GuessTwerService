package com.guess.twer.guessTwer.mongo;

import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;

@Component
public class C4Mongo extends Mongo{


        @Autowired
        public C4Mongo(@Value("${mongodb.hostname}") String hostName, @Value("${mongodb.port}") int hostPort, C4MongoOptions mongoOptions , @Value("${mongodb.writeConcern}") String writeConcern) throws UnknownHostException, MongoException {
            super(new ServerAddress(hostName, hostPort), mongoOptions);
            setWriteConcern(new WriteConcern(MongoWriteConcern.valueOf(writeConcern).getWcInt()));
        }


}
