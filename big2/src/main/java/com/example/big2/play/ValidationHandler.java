package com.example.big2.play;

import com.example.big2.card.Card;
import com.example.big2.game.Game;
import com.example.big2.play.PlayHandler;
import com.example.big2.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ValidationHandler extends PlayHandler {

    public ValidationHandler(PlayHandler next) {
        super(next);
    }

    @Override
    public void handle(Player player, List<Card> inputCards, Game game) {
        if (inputCards == null || inputCards.isEmpty()) {
            if (next != null) {
                next.handle(player, inputCards, game);
            }
            return;
        }

        List<Card> topPlay = game.getTopPlay();

        if (!topPlay.isEmpty()) {

            if (inputCards.size() == 1 ) {
                validateSingle(inputCards.get(0), topPlay.get(0));
            } else if (inputCards.size() == 2) {
                validatePair(inputCards);
            } else if (inputCards.size() == 5) {
                validateFullHouse(inputCards);
            }
        }



        if (next != null) {
            next.handle(player, inputCards, game);
        }
    }

    private void validateSingle(Card inputCard, Card topPlay) {
        if (topPlay != null) {
            int inputRank = getRankValue(inputCard.getRank());
            int topRank = getRankValue(topPlay.getRank());
            if (inputRank < topRank || (inputRank == topRank && compareSuit(inputCard.getSuit(), topPlay.getSuit()) <= 0)) {
                throw new IllegalArgumentException("The card you played is too small.");
            }
        }
    }

    private void validatePair(List<Card> cards) {
        if (!cards.get(0).getRank().equals(cards.get(1).getRank())) {
            throw new IllegalArgumentException("Invalid pair!");
        }
    }

    private void validateFullHouse(List<Card> cards) {
        Map<String, Long> rankCount = new HashMap<>();
        for (Card card : cards) {
            rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(), 0L) + 1);
        }

        boolean hasThree = rankCount.containsValue(3L);
        boolean hasTwo = rankCount.containsValue(2L);

        if (!(hasThree && hasTwo)) {
            throw new IllegalArgumentException("Invalid Full House!");
        }
    }

    private int getRankValue(String rank) {
        switch (rank) {
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            case "10": return 10;
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            case "A": return 14;
            case "2": return 15;
            default: throw new IllegalArgumentException("Invalid card rank: " + rank);
        }
    }

    private int compareSuit(String suit1, String suit2) {
        Map<String, Integer> suitOrder = Map.of("C", 1, "D", 2, "H", 3, "S", 4);
        return Integer.compare(suitOrder.get(suit1), suitOrder.get(suit2));
    }
}