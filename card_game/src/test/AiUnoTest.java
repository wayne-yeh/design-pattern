package test;
import java.util.ArrayList;
import java.util.List;

import deck.Deck;
import deck.ShowdownDeck;
import deck.UnoDeck;
import game.ShowdownGame;
import game.UnoGame;
import player.AiPlayer;
import player.Player;

public class AiUnoTest {
	public static void main(String[] args) {

		List<Player> players = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			players.add(new AiPlayer());
		}

		Deck unoDeck = new UnoDeck();
		UnoGame unoGame = new UnoGame(unoDeck);

		unoGame.start(players);


	}
}
