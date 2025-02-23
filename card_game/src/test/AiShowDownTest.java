package test;
import java.util.ArrayList;
import java.util.List;

import deck.ShowdownDeck;
import game.ShowdownGame;
import player.AiPlayer;
import player.Player;

public class AiShowDownTest {
	public static void main(String[] args) {




		List<Player> players = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			players.add(new AiPlayer());
		}

		ShowdownDeck showdownDeck = new ShowdownDeck();
		ShowdownGame simple = new ShowdownGame(showdownDeck);

		simple.start(players);


	}
}
