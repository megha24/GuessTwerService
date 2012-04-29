package com.guess.twer.guessTwer.helpers;

import com.google.code.morphia.Datastore;
import com.guess.twer.guessTwer.models.Person;

public class DatabaseHelper {
    private Datastore datastore;


    public DatabaseHelper(Datastore datastore) {
        this.datastore = datastore;

    }

    public void addPerson(Person person) {
        datastore.save(person);
    }

    public void remove(Class<Person> personClass) {
        datastore.delete(datastore.createQuery(personClass));
    }
}
