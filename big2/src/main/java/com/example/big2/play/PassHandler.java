package com.example.big2.play;

import com.example.big2.card.Card;
import com.example.big2.game.Game;
import com.example.big2.player.Player;

public class PassHandler extends PlayHandler {


    public PassHandler(PlayHandler next) {
        super(next);
    }

    @Override
    public void handle(Player player, Card inputCard, Game game) {
        if (inputCard == null) {
            System.out.printf("Player %s gives up this round.\n", player.getName());
            game.incrementPassCount();
            return;
        } else if (next != null) {
            next.handle(player, inputCard, game);
        }
    }
}
