package com.example.big2.play;

import com.example.big2.card.Card;
import com.example.big2.card.pattern.CardPattern;
import com.example.big2.game.Game;
import com.example.big2.player.Player;

import java.util.List;

public class CheckHandler extends PlayHandler {

    public CheckHandler(PlayHandler next) {
        super(next);
        System.out.println("C3CheckHandler next: " + next);
    }

    @Override
    public void handle(Player player, List<Card> inputCards, Game game, List<CardPattern> cardPatterns) {
        boolean cardhasC3 = inputCards != null && inputCards.stream()
                .anyMatch(card -> card.getRank().equals("3") && card.getSuit().equals("C"));

        if (player.hasC3() && inputCards == null) {
            throw new IllegalArgumentException("Player " + player.getName() + " has C[3], can not give up");
        } else if (player.hasC3() && !cardhasC3) {
            throw new IllegalArgumentException("Player " + player.getName() + " has C[3], must play it first.");
        } else if (game.getLastPlayedPlayer() != null && game.getLastPlayedPlayer().equals(player)) {
            throw new IllegalArgumentException("Player " + player.getName() + " is the first player can not give up");
        } else if (next != null) {
            next.handle(player, inputCards, game, cardPatterns);
        }
    }
}
