package com.guess.twer.guessTwer.controllers;

import com.guess.twer.guessTwer.models.QuestionResult;
import com.guess.twer.guessTwer.models.User;
import com.guess.twer.guessTwer.services.GameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    private GameController gameController;

    @Mock
    private GameService gameService;


    @Before
    public void setUp() {
        gameController = new GameController(gameService);
    }

    @Test
    public void shouldReturnQuestionBasedOnQuestionNo() throws Exception {
        String questionNo = "1";
        ResponseEntity<String> question = gameController.getQuestion(questionNo,"callback");
        verify(gameService).getQuestion(Integer.parseInt(questionNo));
        assertEquals("should return OK status", HttpStatus.OK, question.getStatusCode());
    }

    @Test
    public void shouldReturnBadRequestOnExceptionWhileRetreivingQuestion() throws Exception {
        String questionNo = "1";
        when(gameController.getQuestion(questionNo,"callback")).thenThrow(Exception.class);
        ResponseEntity<String> question = gameController.getQuestion(questionNo,"callback");
        verify(gameService).getQuestion(Integer.parseInt(questionNo));
        assertEquals("should return BAD REQUEST status", HttpStatus.BAD_REQUEST, question.getStatusCode());
    }

    @Test
    public void shouldUpdateOrInsertUserHighestScore() throws Exception {
        String userName = "userName";
        String score = "80";
        ResponseEntity<String> userResponseEntity = gameController.setHighestScore(userName, score,"callback");
        verify(gameService).setOrUpdateScore(userName, Integer.parseInt(score));
        assertEquals("should return OK status", HttpStatus.OK, userResponseEntity.getStatusCode());
    }

    @Test
    public void shouldReturnBadRequestOnExceptionWhileSettingScore() throws Exception {
        String userName = "userName";
        String score = "80";
        when(gameController.setHighestScore(userName, score,"callback")).thenThrow(Exception.class);
        ResponseEntity<String> userResponseEntity = gameController.setHighestScore(userName, score,"callback");
        verify(gameService).setOrUpdateScore(userName, Integer.parseInt(score));
        assertEquals("should return BAD REQUEST status", HttpStatus.BAD_REQUEST, userResponseEntity.getStatusCode());
    }

    @Test
    public void shouldReturnUserHighestScore() throws Exception {
        String username = "some username";
        String score = "90";
        when(gameService.getHighestScore(username)).thenReturn(Integer.valueOf(score));
        ResponseEntity<String> userHighestScore = gameController.getUserHighestScore(username,"callback");
        verify(gameService).getHighestScore(username);
        assertEquals("should return OK status", HttpStatus.OK, userHighestScore.getStatusCode());
        assertEquals("callback("+score.toString()+")", userHighestScore.getBody().toString());

    }

    @Test
    public void shouldThrowErrorwhileFetchingUserHighestScore() throws Exception {
        String username = "some username";
        when(gameController.getUserHighestScore(username,"callback")).thenThrow(Exception.class);
        ResponseEntity<String> userHighestScore = gameController.getUserHighestScore(username,"callback");
        verify(gameService).getHighestScore(username);
        assertEquals("should return BAD REQUEST status", HttpStatus.BAD_REQUEST, userHighestScore.getStatusCode());
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
        when(gameService.getThreeHighestScorers()).thenReturn(userList);
        ResponseEntity<String> userHighestScore = gameController.getThreeHighestScorers("callback");
        verify(gameService).getThreeHighestScorers();
        assertEquals("should return OK status", HttpStatus.OK, userHighestScore.getStatusCode());

    }

    @Test
    public void shouldThroawErrorwhileFetchingUserHighestScore() throws Exception {
        when(gameController.getThreeHighestScorers("callback")).thenThrow(Exception.class);
        ResponseEntity<String> highestScores = gameController.getThreeHighestScorers("callback");
        verify(gameService).getThreeHighestScorers();
        assertEquals("should return BAD REQUEST status", HttpStatus.BAD_REQUEST, highestScores.getStatusCode());
    }

}
