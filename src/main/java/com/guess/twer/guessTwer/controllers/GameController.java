package com.guess.twer.guessTwer.controllers;

import com.guess.twer.guessTwer.models.QuestionResult;
import com.guess.twer.guessTwer.models.User;
import com.guess.twer.guessTwer.services.GameService;
import com.mongodb.util.JSON;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.mortbay.util.ajax.JSONObjectConvertor;
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
    public ResponseEntity<String> getQuestion(@PathVariable String questionNo, @RequestParam("callback") String callback) {
        try {

            QuestionResult questionResult = gameService.getQuestion(Integer.parseInt(questionNo));
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<String>(callback + "(" + mapper.writeValueAsString(questionResult).toString() + ")", HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/setHighestScore", method = RequestMethod.POST)
    public ResponseEntity<Void> setHighestScore(@RequestParam("userName") String userName, @RequestParam("score") String score) {

        try {
            gameService.setOrUpdateScore(userName, Integer.parseInt(score));
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getThreeHighestScorers", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getThreeHighestScorers() {
        try {
            List<User> user = gameService.getThreeHighestScorers();
            return new ResponseEntity<List<User>>(user, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getHighestScore/{userName}", method = RequestMethod.GET)
    public ResponseEntity<Integer> getUserHighestScore(@PathVariable String userName) {
        try {
            Integer score = gameService.getHighestScore(userName);
            return new ResponseEntity<Integer>(score, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
        }
    }
}
