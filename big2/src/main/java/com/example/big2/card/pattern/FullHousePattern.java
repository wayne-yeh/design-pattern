package com.example.big2.card.pattern;

import com.example.big2.card.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullHousePattern extends CardPattern {

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
        int threeOfAKind1 = getThreeOfAKindRank(cards1);
        int threeOfAKind2 = getThreeOfAKindRank(cards2);
        return Integer.compare(threeOfAKind1, threeOfAKind2);
    }

    private int getThreeOfAKindRank(List<Card> cards) {
        Map<String, Integer> rankCount = new HashMap<>();
        for (Card card : cards) {
            rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(), 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : rankCount.entrySet()) {
            if (entry.getValue() == 3) {
                return new Card("", entry.getKey()).compareTo(new Card("", "3"));
            }
        }
        return -1;
    }
}
