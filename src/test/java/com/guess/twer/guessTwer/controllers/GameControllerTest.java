package com.guess.twer.guessTwer.controllers;

import com.guess.twer.guessTwer.models.Person;
import com.guess.twer.guessTwer.models.QuestionResult;
import com.guess.twer.guessTwer.services.GameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

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
        int questionNo = 1;
        ResponseEntity<QuestionResult> question = gameController.getQuestion(questionNo);
        verify(gameService).getQuestion(questionNo);
        assertEquals("should return OK status", HttpStatus.OK, question.getStatusCode());
    }

    @Test
    public void shouldReturnBadRequestOnExceptionWhileRetreivingQuestion() throws Exception {
        int questionNo = 1;
        when(gameController.getQuestion(questionNo)).thenThrow(Exception.class);
        ResponseEntity<QuestionResult> question = gameController.getQuestion(questionNo);
        verify(gameService).getQuestion(questionNo);
        assertEquals("should return BAD REQUEST status", HttpStatus.BAD_REQUEST, question.getStatusCode());
    }

    @Test
    public void shouldUpdateOrInsertUserHighestScore() throws Exception {
        String userName = "userName";
        int score = 80;
        ResponseEntity<Void> userResponseEntity = gameController.setHighestScore(userName, score);
        verify(gameService).setOrUpdateScore(userName, score);
        assertEquals("should return OK status", HttpStatus.OK,userResponseEntity.getStatusCode());
    }

    @Test
    public void shouldReturnBadRequestOnExceptionWhileSettingScore() throws Exception {
        String userName = "userName";
        int score = 80;;
        when(gameController.setHighestScore(userName, score)).thenThrow(Exception.class);
        ResponseEntity<Void> userResponseEntity = gameController.setHighestScore(userName, score);
        verify(gameService).setOrUpdateScore(userName, score);
        assertEquals("should return BAD REQUEST status", HttpStatus.BAD_REQUEST, userResponseEntity.getStatusCode());
    }
    
    @Test
    public void shouldReturnUserHighestScore(){
        String username= "some username";
        Integer score = 90;
        when(gameService.getHighestScore(username)).thenReturn(score);
        ResponseEntity<Integer> userHighestScore = gameController.getUserHighestScore(username);
        verify(gameService).getHighestScore(username);
        assertEquals("should return OK status", HttpStatus.OK,userHighestScore.getStatusCode());
        assertEquals(score.toString(),userHighestScore.getBody().toString());

    }

    @Test
    public void shouldThrowErrorwhileFetchingUserHighestScore(){
        String username= "some username";
        when(gameController.getUserHighestScore(username)).thenThrow(Exception.class);
        ResponseEntity<Integer> userHighestScore = gameController.getUserHighestScore(username);
        verify(gameService).getHighestScore(username);
        assertEquals("should return BAD REQUEST status", HttpStatus.BAD_REQUEST, userHighestScore.getStatusCode());
    }
}
