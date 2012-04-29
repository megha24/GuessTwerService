package com.guess.twer.guessTwer.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
public class QuestionResult {
    private Person person;
    private List<String> guessOptions = new ArrayList<String>();
  }
