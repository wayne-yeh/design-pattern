package com.example.big2.play;

import com.example.big2.card.Card;
import com.example.big2.card.factory.CardPatternFactory;
import com.example.big2.card.pattern.CardPattern;
import com.example.big2.game.Game;
import com.example.big2.play.PlayHandler;
import com.example.big2.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ValidationHandler extends PlayHandler{


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

        CardPattern cardPattern = CardPatternFactory.getPattern(inputCards);
        List<Card> topPlay = game.getTopPlay();

        if (!topPlay.isEmpty()) {

            int result = cardPattern.compare(inputCards, topPlay);
            if (result == -1) {
                throw new IllegalArgumentException("The card you played is too small.");
            }
        }

        if (next != null) {
            next.handle(player, inputCards, game);
        }
    }

}