package com.example.big2.card.pattern;

import com.example.big2.card.Card;

import java.util.List;

public interface CardPattern {
    boolean isValid(List<Card> cards);
    int compare(List<Card> cards1, List<Card> cards2);
}
