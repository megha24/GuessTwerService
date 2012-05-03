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
    public ResponseEntity<String> getQuestion(@PathVariable String questionNo, @RequestParam ("callback") String callback) {
        try {

            QuestionResult questionResult = gameService.getQuestion(Integer.parseInt(questionNo));
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<String>(callback+"("+mapper.writeValueAsString(questionResult).toString()+")", HttpStatus.OK);


        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/setHighestScore", method = RequestMethod.GET)
    public ResponseEntity<String> setHighestScore(@RequestParam("userName") String userName, @RequestParam("score") String score, @RequestParam("callback") String callback) {

        try {
            gameService.setOrUpdateScore(userName, Integer.parseInt(score));
            return new ResponseEntity<String>(callback+"('')",HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getThreeHighestScorers", method = RequestMethod.GET)
    public ResponseEntity<String> getThreeHighestScorers(@RequestParam ("callback") String callback) {
        try {
            List<User> user = gameService.getThreeHighestScorers();
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<String>(callback+"("+mapper.writeValueAsString(user).toString()+")", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getHighestScore/{userName}", method = RequestMethod.GET)
    public ResponseEntity<String> getUserHighestScore(@PathVariable String userName, @RequestParam("callback") String callback) {
        try {
            Integer score = gameService.getHighestScore(userName);
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<String>(callback+"("+mapper.writeValueAsString(score).toString()+")", HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }
}
