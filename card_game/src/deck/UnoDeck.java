package deck;

import java.util.ArrayList;
import java.util.List;

import card.Card;
import card.UnoCard;

public class UnoDeck extends Deck {

	List<Card> tableCards = new ArrayList<>();
	private Card lastCard;
	public UnoDeck() {
		initializeDeck();
	}

	@Override
	protected void initializeDeck() {
		String[] color = {"BLUE", "RED", "YELLOW", "GREEN"};
		String[] number = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

		for (String suit : color) {
			for (String rank : number) {
				cards.add(new UnoCard(suit, rank));
			}
		}
	}



	@Override
	public Card drawCard() {

		if (cards.isEmpty()) {
			System.out.println("Deck is empty! Trying to reset...");
			if (!resetDeck()) {
				System.out.println("Deck reset failed! No more cards.");
				return null;
			}
		}
		return cards.remove(0);
	}



	public void flop() {

		if (cards.isEmpty()) {
			resetDeck();
		}
		if (!cards.isEmpty()) {
			lastCard = cards.remove(0);
			tableCards.add(lastCard);
			System.out.println("last card: " + lastCard.getSuitOrColor() + ", " + lastCard.getRankOrNumber());
		} else {
			throw new IllegalStateException("Flop failed: No available cards!");
		}
	}


	public boolean resetDeck() {
		if (tableCards.size() <= 1) {
			System.out.println("Insufficient cards in deck, unable to reset!");
			return false;
		}

		Card last = lastCard;
		tableCards.remove(last);

		if (tableCards.isEmpty()) {
			System.out.println("Insufficient cards in deck, unable to reset!");
			return false;
		}

		System.out.println("Resetting deck with " + tableCards.size() + " cards.");
		cards.addAll(tableCards);
		tableCards.clear();
		tableCards.add(last);

		shuffle();
		return true;
	}


	public Card getLastCard() {
		if (lastCard == null) {
			flop();
		}
		return lastCard;
	}

	public void setLastCard(Card card) {
		lastCard = card;
		tableCards.add(card);
	}

	public List<Card> getTableCards() {
		return tableCards;
	}

	public void setTableCards(List<Card> tableCards) {
		this.tableCards = tableCards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card card : cards) {
			sb.append(card.toString()).append("\n");
		}
		return sb.toString();
	}
}
