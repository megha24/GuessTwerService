package com.guess.twer.guessTwer.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.google.code.morphia.annotations.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String role;
    @Getter
    @Setter
    private String office;
    @Getter
    @Setter
    private String imageUri;
    @Getter
    @Setter
    private String country;
}