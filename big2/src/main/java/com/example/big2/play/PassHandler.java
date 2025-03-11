package com.example.big2.play;

import com.example.big2.card.Card;
import com.example.big2.card.pattern.CardPattern;
import com.example.big2.game.Game;
import com.example.big2.player.Player;

import java.util.List;

public class PassHandler extends PlayHandler {


    public PassHandler(PlayHandler next) {
        super(next);
    }

    @Override
    public void handle(Player player,List<Card> inputCards, Game game, List<CardPattern> cardPatterns) {
        if (inputCards == null) {
            System.out.printf("Player %s gives up this round.\n", player.getName());
            game.incrementPassCount();
            return;
        } else if (next != null) {
            next.handle(player, inputCards, game, cardPatterns);
        }
    }
}
