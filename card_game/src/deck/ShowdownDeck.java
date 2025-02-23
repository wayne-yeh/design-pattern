package deck;

import card.Card;
import card.ShowdownCard;

public class ShowdownDeck extends Deck {


	public ShowdownDeck() {
		initializeDeck();
	}

	@Override
	protected void initializeDeck() {
		String[] suits = {"heart", "diamond", "club", "spade"};
		String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

		for (String suit : suits) {
			for (String rank : ranks) {

				cards.add(new ShowdownCard(suit, rank));
			}
		}
	}

	@Override
	public Card drawCard() {
		if (cards.isEmpty()) {
			return null;
		}
		return cards.remove(0);
	}
}
