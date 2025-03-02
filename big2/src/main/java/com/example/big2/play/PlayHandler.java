package com.example.big2.play;

import com.example.big2.card.Card;
import com.example.big2.game.Game;
import com.example.big2.player.Player;

public abstract class PlayHandler {

    protected PlayHandler next;

    public PlayHandler (PlayHandler nextHandler) {
        this.next = nextHandler;
    }

    public void handle(Player player, Card inputCard, Game game) {
        if (next != null) {
            next.handle(player, inputCard, game);
        }
    }
}
