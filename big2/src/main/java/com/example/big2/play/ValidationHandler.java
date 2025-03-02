package com.example.big2.play;

import com.example.big2.card.Card;
import com.example.big2.game.Game;
import com.example.big2.play.PlayHandler;
import com.example.big2.player.Player;

import java.util.HashMap;
import java.util.Map;

public class ValidationHandler extends PlayHandler {

    public ValidationHandler(PlayCardHandler next) {
        super(next);
    }

    @Override
    public void handle(Player player, Card inputCard, Game game) {
        Card topPlay = game.getTopPlay();
        if (topPlay != null) {
            int inputRank = getRankValue(inputCard.getRank());
            int topRank = getRankValue(topPlay.getRank());

            if (inputRank < topRank || (inputRank == topRank && compareSuit(inputCard.getSuit(), topPlay.getSuit()) <= 0)) {
                throw new IllegalArgumentException("The card you played is too small, please choose a bigger card.");
            }
        } else if (next != null) {
            next.handle(player, inputCard,game);
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
            default: throw new IllegalArgumentException("non card: " + rank);
        }
    }

    private int compareSuit(String suit1, String suit2) {
        Map<String, Integer> suitOrder = new HashMap<>();
        suitOrder.put("C", 1);
        suitOrder.put("D", 2);
        suitOrder.put("H", 3);
        suitOrder.put("S", 4);

        return Integer.compare(suitOrder.get(suit1), suitOrder.get(suit2));
    }
}
