package com.guess.twer.guessTwer.services;

import com.guess.twer.guessTwer.repositories.PersonRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest{
    
    private PersonService personService;
    
    @Mock
    private PersonRepository  personRepository;
    
    @Before
    public void setUp(){
        personService = new PersonService(personRepository);
    }
    
    @Test
    @Ignore
    public void shouldReturnQuestion() throws Exception {
        int questionNo = 4;
        personService.getQuestion(questionNo);
        verify(personRepository).getPersonInfo(questionNo);

    }
    
}
