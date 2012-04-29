package com.guess.twer.guessTwer.mongo;

public enum MongoWriteConcern {

    NONE(-1),NORMAL(0),SAFE(1),REPLICA_SAFE(2);

    private int wcInt;

    MongoWriteConcern(int wcInt) {
        this.wcInt = wcInt;
    }

    public int getWcInt(){
        return this.wcInt;
    }
}
