package com.example.big2.game;

import com.example.big2.card.Card;
import com.example.big2.card.pattern.CardPattern;
import com.example.big2.card.pattern.CardPatternFactory;

import java.util.List;

public class GamePattern {
    private final CardPattern pattern;
    private final List<Card> cards;

    public GamePattern(List<Card> cards) {
        this.pattern = CardPatternFactory.getPattern(cards);
        this.cards = cards;
    }

    public boolean isValid() {
        return pattern.isValid(cards);
    }

    public int compare(GamePattern other) {
        return pattern.compare(this.cards, other.cards);
    }
}
