package com.example.big2.play;

import com.example.big2.card.Card;
import com.example.big2.game.Game;
import com.example.big2.player.Player;

public class PlayCardHandler extends PlayHandler {

    public PlayCardHandler(PlayHandler next) {
        super(next);
    }

    @Override
    public void handle(Player player, Card inputCard, Game game) {

        if (inputCard != null) {
            player.getHandCards().remove(inputCard);
            game.setTopPlay(inputCard);
            game.setLastPlayedPlayer(player);
            player.setC3Player(false);
            game.resetPassCount();
            System.out.printf("Player %s plays %s[%s]\n", player.getName(), inputCard.getSuit(), inputCard.getRank());
        } else if (next != null) {
            handle(player, inputCard, game);
        }
    }
}
