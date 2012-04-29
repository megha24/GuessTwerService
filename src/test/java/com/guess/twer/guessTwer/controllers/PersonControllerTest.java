package com.guess.twer.guessTwer.controllers;

import com.guess.twer.guessTwer.models.Person;
import com.guess.twer.guessTwer.models.QuestionResult;
import com.guess.twer.guessTwer.services.PersonService;
import junit.framework.TestCase;
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
public class PersonControllerTest {

    private PersonController personController;

    @Mock
    private PersonService personService;


    @Before
    public void setUp() {
        personController = new PersonController(personService);
    }

    @Test
    public void shouldReturnQuestionBasedOnQuestionNo() throws Exception {
        int questionNo = 1;

        ResponseEntity<QuestionResult> question = personController.getQuestion(questionNo);
        verify(personService).getQuestion(questionNo);
        assertEquals("should return OK status", HttpStatus.OK, question.getStatusCode());
    }

    @Test
    public void shouldReturnBadRequestOnException() throws Exception {
        int questionNo = 1;
        when(personController.getQuestion(questionNo)).thenThrow(Exception.class);
        ResponseEntity<QuestionResult> question = personController.getQuestion(questionNo);
        verify(personService).getQuestion(questionNo);
        assertEquals("should return OK status", HttpStatus.BAD_REQUEST, question.getStatusCode());
    }

}
