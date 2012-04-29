package com.guess.twer.guessTwer.controllers;

import com.guess.twer.guessTwer.models.Person;
import com.guess.twer.guessTwer.models.QuestionResult;
import com.guess.twer.guessTwer.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/question/get/{questionNo}", method = RequestMethod.GET)
    public ResponseEntity<QuestionResult> getQuestion(@PathVariable int questionNo) {
        try {
            QuestionResult questionResult = personService.getQuestion(questionNo);
            return new ResponseEntity<QuestionResult>(questionResult, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<QuestionResult>(HttpStatus.BAD_REQUEST);
        }

    }

}
