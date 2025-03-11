package com.example.big2.play;

import com.example.big2.card.Card;
import com.example.big2.card.pattern.CardPattern;
import com.example.big2.game.Game;
import com.example.big2.player.Player;

import java.util.List;

public abstract class PlayHandler {

    protected PlayHandler next;

    public PlayHandler (PlayHandler nextHandler) {
        this.next = nextHandler;
    }

    public void handle(Player player, List<Card> inputCards, Game game, List<CardPattern> cardPatterns) {
        if (next != null) {
            next.handle(player, inputCards, game, cardPatterns);
        }
    }
}
