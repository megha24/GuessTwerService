package com.guess.twer.guessTwer.services;

import com.guess.twer.guessTwer.models.Person;
import com.guess.twer.guessTwer.models.QuestionResult;
import com.guess.twer.guessTwer.models.User;
import com.guess.twer.guessTwer.repositories.GameRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@NoArgsConstructor
public class GameService {
    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public QuestionResult getQuestion(int questionNo) throws Exception {
        Person person = gameRepository.getPersonInfo(questionNo);
        int personCount = gameRepository.getPersonCount();
        int randomNumber = randomInteger(personCount, questionNo);

        List<Person> personNames = gameRepository.getThreePersonName(randomNumber);
        List<String> options = new ArrayList<String>();

        options.add(person.getName());
        options.add(personNames.get(0).getName());
        options.add(personNames.get(1).getName());
        options.add(personNames.get(2).getName());

        QuestionResult questionResult = new QuestionResult(person, options);

        return questionResult;
    }

    protected int randomInteger(int personCount, int questionNo) {
        if (questionNo < personCount - 3) {
            return generateRandomNo(questionNo + 1, personCount - 3);
        } else {
            return generateRandomNo(1, questionNo - 4);
        }
    }

    private int generateRandomNo(int minLimit, int maxLimit) {
        Random rand = new Random();
        return rand.nextInt(maxLimit - minLimit + 1) + minLimit;
    }


    public void setOrUpdateScore(String userName, int score) throws Exception {
        gameRepository.setOrUpdateScore(userName, score);
    }

    public List<User> getThreeHighestScores() throws Exception {
        return gameRepository.getThreeHighestScorers();
    }

    public Integer getHighestScore(String userName) {
        return gameRepository.getHighestScore(userName);
    }
}
