package com.guess.twer.guessTwer.models;

import com.google.code.morphia.annotations.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Getter
    @Setter
    private String userName;
    @Getter
    @Setter
    private int highestScore;

}
