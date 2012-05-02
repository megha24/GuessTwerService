package com.guess.twer.guessTwer.helpers;

import com.google.code.morphia.Datastore;
import com.guess.twer.guessTwer.models.Person;
import com.guess.twer.guessTwer.models.User;

public class DatabaseHelper {
    private Datastore datastore;


    public DatabaseHelper(Datastore datastore) {
        this.datastore = datastore;

    }

    public void addPerson(Person person) {
        datastore.save(person);
    }

    public void removePerson(Class<Person> personClass) {
        datastore.delete(datastore.createQuery(personClass));
    }

    public void addUser(User user) {
        datastore.save(user);
    }
    public void removeUser(Class<User> userClass) {
        datastore.delete(datastore.createQuery(userClass));
    }
}
