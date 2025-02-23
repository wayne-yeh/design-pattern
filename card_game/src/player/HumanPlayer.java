package player;

import card.Card;
import commandline.CommandLine;

public class HumanPlayer extends Player {

	CommandLine commandLine = new CommandLine();


	@Override
	public Card simpleChoose() {
		Card selectedCard = commandLine.execute(cards);
		cards.remove(selectedCard);
		return selectedCard;
	}

	@Override
	public void unoChoose(Card matchedcard) {
		System.out.println("Card" + matchedcard.getSuitOrColor() + ", " + matchedcard.getRankOrNumber());
		Card selectedCard = commandLine.execute(cards, matchedcard);
		cards.remove(selectedCard);
	}

}
