package com.guess.twer.guessTwer.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
public class QuestionResult {
    @Setter
    @Getter
    private Person person;
    @Getter
    private List<GuessOption> guessOptions = new ArrayList<GuessOption>();
    public void setGuessOptions(List<GuessOption> guessOptions){
        Collections.shuffle(guessOptions);
        this.guessOptions = guessOptions;
    }

  }
