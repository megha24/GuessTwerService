package com.guess.twer.guessTwer.services;

import com.guess.twer.guessTwer.models.Person;
import com.guess.twer.guessTwer.models.QuestionResult;
import com.guess.twer.guessTwer.repositories.PersonRepository;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@NoArgsConstructor
public class PersonService {
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public QuestionResult getQuestion(int questionNo) throws Exception {
        Person person = personRepository.getPersonInfo(questionNo);
        List<Person> personNames = null;
        int personCount = personRepository.getPersonCount();
        int randomNumber = randomInteger(personCount);
        if (randomNumber == questionNo)
            randomNumber++;
        personNames = personRepository.getThreePersonName(randomNumber);
        List<String> options = new ArrayList<String>();
        options.add(personNames.get(0).getName());
        options.add(personNames.get(1).getName());
        options.add(personNames.get(2).getName());
        QuestionResult questionResult = new QuestionResult(person, options);

        return questionResult;
    }

    private int randomInteger(int personCount){

        Random randomGenerator = new Random();
        return randomGenerator.nextInt(personCount - 3);

    }
}
