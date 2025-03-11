package com.example.big2.card.factory;

import com.example.big2.card.Card;
import com.example.big2.card.pattern.*;

import java.util.List;

public class CardPatternFactory {

    public static CardPattern getPattern(List<Card> cards) {
        if (new FullHousePattern().isValid(cards)) return new FullHousePattern();
        if (new StraightPattern().isValid(cards)) return new StraightPattern();
        if (new PairPattern().isValid(cards)) return new PairPattern();
        if (new SinglePattern().isValid(cards)) return new SinglePattern();
        throw new IllegalArgumentException("Invalid card pattern");
    }
}
