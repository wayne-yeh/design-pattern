package player;

import java.util.Random;

import card.Card;

public class AiPlayer extends Player{


	@Override
	public Card simpleChoose() {
		if (cards == null || cards.isEmpty()) {
			return null;
		}
		Random random = new Random();
		Card selectedCard = cards.get(random.nextInt(cards.size()));
		cards.remove(selectedCard);
		return selectedCard;
	}

	@Override
	public void unoChoose(Card matchedcard) {
		cards.remove(matchedcard);
	}
}
