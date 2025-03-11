package com.example.big2.card.pattern;

import com.example.big2.card.Card;
import com.example.big2.card.factory.CardPatternFactory;

import java.util.List;

public abstract class CardPattern {

    public abstract boolean isValid(List<Card> cards);
    public abstract int compare(List<Card> cards1, List<Card> cards2);
}
