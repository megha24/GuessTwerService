package com.guess.twer.guessTwer.services;

import com.guess.twer.guessTwer.models.Person;
import com.guess.twer.guessTwer.models.QuestionResult;
import com.guess.twer.guessTwer.models.User;
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
        assertEquals(person1.getName(), question.getGuessOptions().get(0).getText());
        assertEquals("person2", question.getGuessOptions().get(1).getText());
        assertEquals("person3", question.getGuessOptions().get(2).getText());
        assertEquals("person4", question.getGuessOptions().get(3).getText());
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
        int score = 100;
        gameService.setOrUpdateScore(username, score);
        verify(gameRepository).setOrUpdateScore(username, score);

    }

    @Test
    public void shouldReturnThreeHighestScores() throws Exception {
        ArrayList<User> userList = new ArrayList<User>();
        User user1 = new User("user1", 90);
        User user2 = new User("user2", 80);
        User user3 = new User("user3", 70);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        when(gameRepository.getThreeHighestScorers()).thenReturn(userList);
        List<User> userHighestScoreList = gameService.getThreeHighestScorers();
        assertEquals(userList, userHighestScoreList);
    }

    @Test
    public void shouldGetHighestScore() throws Exception {
        String username = "some user";
        int score = 90;
        when(gameRepository.getHighestScore(username)).thenReturn(score);
        Integer highestScore = gameService.getHighestScore(username);
        assertEquals(new Integer(score), highestScore);
    }

}
    
