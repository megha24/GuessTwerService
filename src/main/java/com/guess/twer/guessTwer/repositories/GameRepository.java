package com.guess.twer.guessTwer.repositories;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.guess.twer.guessTwer.models.Person;
import com.guess.twer.guessTwer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRepository {
    private Datastore datastore;

    @Autowired
    public GameRepository(Datastore datastore)  {
        this.datastore = datastore;
    }

    public Person getPersonInfo(int questionNo) {
        return datastore.find(Person.class).offset(questionNo-1).limit(1).asList().get(0);
    }

    public List<Person> getThreePersonName(int optionNumber) {
        return datastore.find(Person.class).offset(optionNumber-1).limit(3).asList();
    }

    public int getPersonCount() {
        return (int) datastore.find(Person.class).countAll();
    }

    public void setOrUpdateScore(String username, int score) {
        User user = datastore.find(User.class).get();
        if((user != null) && user.getHighestScore()>score){
            score=user.getHighestScore();
        }
        Query userQuery= datastore.find(User.class).filter("userName",username);
        UpdateOperations<User> updateUserScoreOperation = datastore.createUpdateOperations(User.class).set("highestScore", score);
        datastore.update(userQuery, updateUserScoreOperation, true);
    }

    public int getUserCount() {
        return (int) datastore.find(User.class).countAll();
    }

    public List<User> getHighestScore(String username) {
        return datastore.find(User.class).filter("userName", username).asList();
    }
}


