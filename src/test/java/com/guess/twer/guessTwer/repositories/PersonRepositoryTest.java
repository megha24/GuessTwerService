package com.guess.twer.guessTwer.repositories;

import com.google.code.morphia.Datastore;
import com.guess.twer.guessTwer.helpers.DatabaseHelper;
import com.guess.twer.guessTwer.models.Person;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-app-context.xml"})
public class PersonRepositoryTest {

    private PersonRepository personRepository;
    private DatabaseHelper databaseHelper;

    @Autowired
    private Datastore datastore;

    @Before
    public void setUp() {
        databaseHelper = new DatabaseHelper(datastore);
        databaseHelper.remove(Person.class);
        personRepository = new PersonRepository(datastore);
    }

    @Test
    public void shouldGetPersonCount(){
        databaseHelper.addPerson(new Person("megha", null, null, null, null));
        databaseHelper.addPerson(new Person("mansi", null, null, null, null));
        databaseHelper.addPerson(new Person("inderpal", null, null, null, null));
        databaseHelper.addPerson(new Person("hephzibah", null, null, null, null));
        assertEquals(4,personRepository.getPersonCount());
    }

    @Test
    public void shouldReturnPersonInfo() {
        int questionNo = 2;
        databaseHelper.addPerson(new Person("megha", null, null, null, null));
        databaseHelper.addPerson(new Person("mansi", null, null, null, null));
        Person personInfo = personRepository.getPersonInfo(questionNo);
        assertEquals("mansi", personInfo.getName());
    }
    
    @Test
    public void shouldGetThreePersonInfo(){
        int questionNo = 2;
        databaseHelper.addPerson(new Person("megha", null, null, null, null));
        databaseHelper.addPerson(new Person("mansi", null, null, null, null));
        databaseHelper.addPerson(new Person("inderpal", null, null, null, null));
        databaseHelper.addPerson(new Person("hephzibah", null, null, null, null));
        List<Person> persons = personRepository.getThreePersonName(questionNo);
        assertEquals(3,persons.size());
        assertEquals("mansi",persons.get(0).getName());
        assertEquals("inderpal",persons.get(1).getName());
        assertEquals("hephzibah",persons.get(2).getName());
    }
}
