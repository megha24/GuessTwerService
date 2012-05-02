package com.guess.twer.guessTwer.services;

import com.guess.twer.guessTwer.models.Person;
import com.guess.twer.guessTwer.models.QuestionResult;
import com.guess.twer.guessTwer.repositories.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    private GameService gameServiceSpy;


    @Mock
    private GameRepository gameRepository;
    private GameService gameService;
    

    @Before
    public void setUp() {
        gameService = new GameService(gameRepository);
        gameServiceSpy = Mockito.spy(gameService);
    }

    @Test
    public void shouldReturnQuestion() throws Exception {
        int questionNo = 46;
        int personCount = 50;
        when(gameRepository.getPersonCount()).thenReturn(personCount);
        final Person person1 = new Person("person1", "role", "office", "image.jpg", "country");
        when(gameRepository.getPersonInfo(questionNo)).thenReturn(person1);
        doReturn(47).when(gameServiceSpy).randomInteger(personCount, questionNo);
        List<Person> personNames = new ArrayList<Person>() {{
            add(new Person("person2", "role", "office", "image.jpg", "country"));
            add(new Person("person3", "role", "office", "image.jpg", "country"));
            add(new Person("person4", "role", "office", "image.jpg", "country"));
        }};
        when(gameRepository.getThreePersonName(47)).thenReturn(personNames);
        QuestionResult question = gameServiceSpy.getQuestion(questionNo);

        verify(gameRepository).getPersonInfo(questionNo);
        assertEquals(person1, question.getPerson());
        assertEquals(person1.getName(), question.getGuessOptions().get(0));
        assertEquals("person2", question.getGuessOptions().get(1));
        assertEquals("person3", question.getGuessOptions().get(2));
        assertEquals("person4", question.getGuessOptions().get(3));
    }

    @Test
    public void shouldReturnRandomNumberAs47() {
        int personCount = 50;
        int questionNo = 46;
        int randomNumber = gameService.randomInteger(personCount, questionNo);
        assertTrue(randomNumber <= personCount - 3 && randomNumber >= questionNo + 1);

    }

    @Test
    public void shouldReturnRandomNumberBetween6And47() {
        int personCount = 50;
        int questionNo = 5;
        int randomNumber = gameService.randomInteger(personCount, questionNo);
        assertTrue(randomNumber <= personCount - 3 && randomNumber >= questionNo + 1);

    }
    
    @Test
    public void shouldSetScore() throws Exception {
        String username = "someUser";
        int score =100;
        gameService.setOrUpdateScore(username, score);
        verify(gameRepository).setOrUpdateScore(username,score);

    }

    @Test
    public void shouldGetHighestScore(){
        String username= "someUser";

    }
}
    
