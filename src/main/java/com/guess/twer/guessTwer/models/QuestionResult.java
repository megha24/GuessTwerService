package com.guess.twer.guessTwer.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
public class QuestionResult {
    @Getter
    private Person person;
    @Getter
    private List<String> guessOptions = new ArrayList<String>();

  }
