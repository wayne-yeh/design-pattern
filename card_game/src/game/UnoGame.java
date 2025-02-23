package game;

import java.util.ArrayList;

import card.Card;
import deck.Deck;
import player.Player;

public class UnoGame extends Game {
	private final Deck deck;

	public UnoGame(Deck deck) {
        super(deck);
        this.deck = deck;
	}

	@Override
	public void takeTurn() {

		if (deck.getLastCard() == null) {
			System.out.println("Last card is null, resetting the deck...");
			deck.setLastCard(deck.drawCard());
		}

		for (Player player : players) {
			System.out.println("Last card: " + deck.getLastCard().getSuitOrColor() + ", " + deck.getLastCard().getRankOrNumber());
			System.out.println("Player " + player.getName() + " has:");
			player.getCards().forEach(card -> System.out.println("  " + card.getRankOrNumber() + " of " + card.getSuitOrColor()));

			boolean hasPlayed = false;
			for (Card card : new ArrayList<>(player.getCards())) {
				if (card.getSuitOrColor().equals(deck.getLastCard().getSuitOrColor()) || card.getRankOrNumber().equals(deck.getLastCard().getRankOrNumber())) {
					deck.setLastCard(card);
					System.out.println("Player " + player.getName() + " plays " + card.getSuitOrColor() + ", " + card.getRankOrNumber());
					player.unoChoose(card);
					deck.getTableCards().add(card);
					checkWinner(player);
					hasPlayed = true;
					break;
				}
			}

			if (!hasPlayed) {
				Card drawnCard = deck.drawCard();
				if (drawnCard != null) {
					player.addCard(drawnCard);
					System.out.println(player.getName() + " draws a card: " + drawnCard.getRankOrNumber() + " " + drawnCard.getSuitOrColor());
				}
			}

			if (hasWinner) {
				break;
			}
		}
	}



	@Override
	protected void announceWinner() {
		System.out.println("Uno Game finished!");
	}

	private void checkWinner(Player player) {
		if (player.getCards().isEmpty()) {
			System.out.println(player.getName() + " wins!");
			hasWinner = true;
		}
	}
}
