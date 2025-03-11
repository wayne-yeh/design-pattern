package com.example.big2.test;

import com.example.big2.card.pattern.*;
import com.example.big2.game.Game;
import com.example.big2.play.CheckHandler;
import com.example.big2.play.PassHandler;
import com.example.big2.play.PlayCardHandler;
import com.example.big2.play.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class GameTest {


    public static void main(String[] args) {
        CheckHandler inputPlayerHandler = new CheckHandler(new PassHandler(new ValidationHandler(new PlayCardHandler(null))));
        List<CardPattern> cardPatterns = new ArrayList<>();

        cardPatterns.add(new SinglePattern());
        cardPatterns.add(new PairPattern());
        cardPatterns.add(new StraightPattern());
        cardPatterns.add(new FullHousePattern());

        Game game = new Game(inputPlayerHandler, cardPatterns);
        game.start();
    }
}
