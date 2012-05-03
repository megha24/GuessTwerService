package com.guess.twer.guessTwer.models;


import com.mongodb.util.TestCase;
import lombok.Getter;

public class GuessOption {
    @Getter
    private String text;
    @Getter
    private String value;



    public  GuessOption(){

    }
    public  GuessOption(String name){
        text = name;
        value= name;
        
    }
    
}
