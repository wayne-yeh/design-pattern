package game;

import java.util.List;

import deck.Deck;
import deck.ShowdownDeck;
import deck.UnoDeck;
import player.AiPlayer;
import player.Player;

import javax.swing.plaf.LabelUI;

public abstract class Game {
	public boolean hasWinner = false;

	Deck deck;

	List<Player> players;

	Game(Deck deck) {
		this.deck = deck;
	}

	public final void start(List<Player> players) {
		this.players = players;

		players.get(0).setName("P1");
		players.get(1).setName("P2");
		players.get(2).setName("P3");
		players.get(3).setName("P4");

		deck.shuffle();
		getCard(players);

		while (!hasWinner) {
			takeTurn();
		}
		announceWinner();
	}

	private void getCard(List<Player> players) {

		if (deck instanceof ShowdownDeck) {
			for (int round = 0; round < 13; round++) {
				players.get(0).addCard(deck.drawCard());
				players.get(1).addCard(deck.drawCard());
				players.get(2).addCard(deck.drawCard());
				players.get(3).addCard(deck.drawCard());
			}
		}

		if (deck instanceof UnoDeck) {
			for (int round = 0; round < 5; round++) {
				players.get(0).addCard(deck.drawCard());
				players.get(1).addCard(deck.drawCard());
				players.get(2).addCard(deck.drawCard());
				players.get(3).addCard(deck.drawCard());
			}
		}

	}

	protected abstract void takeTurn();

	protected abstract void announceWinner();
}
