package com.guess.twer.guessTwer.repositories;

import com.google.code.morphia.Datastore;
import com.guess.twer.guessTwer.helpers.DatabaseHelper;
import com.guess.twer.guessTwer.models.Person;
import com.guess.twer.guessTwer.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-app-context.xml"})
public class GameRepositoryTest {

    private GameRepository gameRepository;
    private DatabaseHelper databaseHelper;

    @Autowired
    private Datastore datastore;

    @Before
    public void setUp() {
        databaseHelper = new DatabaseHelper(datastore);
        databaseHelper.remove(Person.class);
        gameRepository = new GameRepository(datastore);
        databaseHelper.addPerson(new Person("megha", null, null, null, null));
        databaseHelper.addPerson(new Person("mansi", null, null, null, null));
        databaseHelper.addPerson(new Person("inderpal", null, null, null, null));
        databaseHelper.addPerson(new Person("hephzibah", null, null, null, null));
    }

    @Test
    public void shouldGetPersonCount(){

        assertEquals(4, gameRepository.getPersonCount());
    }

    @Test
    public void shouldReturnPersonInfo() {
        int questionNo = 2;
        Person personInfo = gameRepository.getPersonInfo(questionNo);
        assertEquals("mansi", personInfo.getName());
    }
    
    @Test
    public void shouldGetThreePersonInfo(){
        int questionNo = 2;

        List<Person> persons = gameRepository.getThreePersonName(questionNo);
        assertEquals(3,persons.size());
        assertEquals("mansi",persons.get(0).getName());
        assertEquals("inderpal",persons.get(1).getName());
        assertEquals("hephzibah",persons.get(2).getName());
    }
    
    @Test
    public void shouldSetOrUpdateScore(){
        int score = 100;
        String username = "some Username";
        gameRepository.setOrUpdateScore(username,score);
        assertThat(gameRepository.getUserCount(), is(not(0)));
    }
}
