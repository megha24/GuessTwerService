package com.guess.twer.guessTwer.controllers;

import com.guess.twer.guessTwer.models.QuestionResult;
import com.guess.twer.guessTwer.models.User;
import com.guess.twer.guessTwer.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(value = "/question/get/{questionNo}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<QuestionResult> getQuestion(@PathVariable String questionNo) {
        try {
            QuestionResult questionResult = gameService.getQuestion(Integer.parseInt(questionNo));
            return new ResponseEntity<QuestionResult>(questionResult, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<QuestionResult>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/setHighestScore", method = RequestMethod.POST)
    public ResponseEntity<Void> setHighestScore(@RequestParam("userName")String userName, @RequestParam("score")String score) {
        try{
            gameService.setOrUpdateScore(userName, Integer.parseInt(score));
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getHighestScore/{userName}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUserHighestScore(@PathVariable String userName){
        try{
            List<User> user = gameService.getHighestScore(userName);
            return new ResponseEntity<List<User>>(user, HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
        }
    }
}
