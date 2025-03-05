package com.example.big2.card.pattern;

import com.example.big2.card.Card;

import java.util.List;

public class SinglePattern implements CardPattern {

    @Override
    public boolean isValid(List<Card> cards) {
        return cards.size() == 1;
    }

    @Override
    public int compare(List<Card> cards1, List<Card> cards2) {
        return cards1.get(0).compareTo(cards2.get(0));
    }
}
