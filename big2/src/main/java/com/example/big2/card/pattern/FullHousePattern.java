package com.example.big2.card.pattern;

import com.example.big2.card.Card;

import java.util.List;

public class FullHousePattern implements CardPattern {

    @Override
    public boolean isValid(List<Card> cards) {
        if (cards.size() != 5) return false;
        cards.sort(Card::compareTo);
        boolean firstThree = cards.get(0).getRank().equals(cards.get(1).getRank()) &&
                cards.get(1).getRank().equals(cards.get(2).getRank()) &&
                cards.get(3).getRank().equals(cards.get(4).getRank());
        boolean lastThree = cards.get(2).getRank().equals(cards.get(3).getRank()) &&
                cards.get(3).getRank().equals(cards.get(4).getRank()) &&
                cards.get(0).getRank().equals(cards.get(1).getRank());
        return firstThree || lastThree;
    }

    @Override
    public int compare(List<Card> cards1, List<Card> cards2) {
        return cards1.get(2).compareTo(cards2.get(2));
    }
}
