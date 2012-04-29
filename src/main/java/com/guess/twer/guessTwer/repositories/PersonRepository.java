package com.guess.twer.guessTwer.repositories;

import com.google.code.morphia.Datastore;
import com.guess.twer.guessTwer.models.Person;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class PersonRepository {
    private Datastore datastore;

    @Autowired
    public PersonRepository(Datastore datastore) {
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
}
