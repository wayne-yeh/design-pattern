package test;
import java.util.List;

import deck.UnoDeck;
import game.UnoGame;
import player.HumanPlayer;
import player.Player;

public class HumanUnoTest {
	public static void main(String[] args) {

		Player humanPlayer1 = new HumanPlayer();
		Player humanPlayer2 = new HumanPlayer();
		Player humanPlayer3 = new HumanPlayer();
		Player humanPlayer4 = new HumanPlayer();

		humanPlayer1.setName("P1");
		humanPlayer2.setName("P2");
		humanPlayer3.setName("P3");
		humanPlayer3.setName("P4");

		UnoDeck unoDeck = new UnoDeck();
		UnoGame unoGame = new UnoGame(unoDeck);

		unoDeck.shuffle();
		unoDeck.flop();
		for (int round = 0; round < 5; round++) {
			humanPlayer1.addCard(unoDeck.drawCard());
			humanPlayer2.addCard(unoDeck.drawCard());
			humanPlayer3.addCard(unoDeck.drawCard());
			humanPlayer4.addCard(unoDeck.drawCard());
		}
		List<Player> players = List.of(humanPlayer1, humanPlayer2, humanPlayer3, humanPlayer4);

		players.forEach(player -> {
			System.out.println("Player " + (players.indexOf(player) + 1) + "'s hand:");
			player.getCards().forEach(card ->
			System.out.println("  " + card.getRankOrNumber() + " " + card.getSuitOrColor())
					);
		});


	}
}