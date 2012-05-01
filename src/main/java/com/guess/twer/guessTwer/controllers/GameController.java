package com.guess.twer.guessTwer.controllers;

import com.guess.twer.guessTwer.models.QuestionResult;
import com.guess.twer.guessTwer.models.User;
import com.guess.twer.guessTwer.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(value = "/question/get/{questionNo}", method = RequestMethod.GET)
    public ResponseEntity<QuestionResult> getQuestion(@PathVariable int questionNo) {
        try {
            QuestionResult questionResult = gameService.getQuestion(questionNo);
            return new ResponseEntity<QuestionResult>(questionResult, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<QuestionResult>(HttpStatus.BAD_REQUEST);
        }

    }


    public ResponseEntity<Void> setHighestScore(String userName, int score) {
        try{
        gameService.setOrUpdateScore(userName, score);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }catch(Exception exception){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }
    
    public ResponseEntity<Integer> getUserHighestScore(String username){
        try{
            Integer score = gameService.getHighestScore(username);
            return new ResponseEntity<Integer>(score, HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
        }
    }
}
