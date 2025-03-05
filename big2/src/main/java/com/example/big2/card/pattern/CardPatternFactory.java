package com.example.big2.card.pattern;

import com.example.big2.card.Card;

import java.util.List;

public class CardPatternFactory {

    public static CardPattern getPattern(List<Card> cards) {

        if (new SinglePattern().isValid(cards)) return new SinglePattern();
        throw new IllegalArgumentException("Invalid card pattern");
    }
}
