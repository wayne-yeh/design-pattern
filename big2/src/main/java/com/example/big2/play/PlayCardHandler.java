package com.example.big2.play;

import com.example.big2.card.Card;
import com.example.big2.card.factory.CardPatternFactory;
import com.example.big2.game.Game;
import com.example.big2.player.Player;

import java.util.List;

public class PlayCardHandler extends PlayHandler {

        public PlayCardHandler(PlayHandler next) {
            super(next);
        }

        @Override
        public void handle(Player player, List<Card> inputCards, Game game) {
            if (inputCards != null && !inputCards.isEmpty()) {
                player.getHandCards().removeAll(inputCards);
                game.setTopPlay(inputCards);
                game.setLastPlayedPlayer(player);
                game.resetPassCount();
                player.hasC3 = false;
                System.out.printf("Player %s plays %s\n", player.getName(), inputCards.stream().map(Card::toString).toList());
            } else if (next != null) {
                next.handle(player, inputCards, game);
            }
        }

}
