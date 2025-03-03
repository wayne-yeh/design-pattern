package com.example.big2.play;

import com.example.big2.card.Card;
import com.example.big2.game.Game;
import com.example.big2.player.Player;

public class C3CheckHandler extends PlayHandler {

    public C3CheckHandler(PlayHandler next) {
        super(next);
        System.out.println("C3CheckHandler next: " + next);
    }

    @Override
    public void handle(Player player, Card inputCard, Game game) {
        if (player.hasC3() && inputCard == null) {
            throw new IllegalArgumentException("Player " + player.getName() + " has C[3], can not give up");
        } else if (player.hasC3() && !(inputCard.getRank().equals("3") && inputCard.getSuit().equals("C"))) {
            throw new IllegalArgumentException("Player " + player.getName() + " has C[3], must play it first.");
        } else if (game.getLastPlayedPlayer() != null && game.getLastPlayedPlayer().equals(player)) {
            throw new IllegalArgumentException("Player " + player.getName() + " is the first player can not give up");
        } else if (next != null) {
            next.handle(player, inputCard, game);
        }
    }
}
