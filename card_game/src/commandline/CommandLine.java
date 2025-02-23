package commandline;

import java.util.List;
import java.util.Scanner;

import card.Card;

public class CommandLine {

	public Card execute(List<Card> cards) {

		if (cards == null || cards.isEmpty()) {
			return null;
		}

		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter the card suit (e.g., heart, spade, diamond, club): ");
		String suit = scanner.nextLine().trim();

		System.out.println("Please enter the card rank (e.g., A, 2, 3, 4...): ");
		String rank = scanner.nextLine().trim();


		Card selectedCard = null;

		// check rank and suit
		for (Card card : cards) {
			if (card.getRankOrNumber().equalsIgnoreCase(rank) && card.getSuitOrColor().equalsIgnoreCase(suit)) {
				selectedCard = card;
				break;
			}
		}

		if (selectedCard == null) {
			System.out.println("Invalid rank or suit. Please try again.");
			return execute(cards);
		}

		return selectedCard;
	}

	public Card execute(List<Card> cards, Card matchedcard) {
		if (cards == null || cards.isEmpty() || matchedcard == null) {
			return null;
		}

		Scanner scanner = new Scanner(System.in);

		System.out.println("Available cards:");
		printAvailableCards(cards);

		System.out.println("\nYou should enter: " + matchedcard.getSuitOrColor() + " " + matchedcard.getRankOrNumber());

		while (true) {
			System.out.println("\nPlease enter the card suit (\"BLUE\", \"RED\", \"YELLOW\", \"GREEN\"): ");
			String suit = scanner.nextLine().trim();

			System.out.println("Please enter the card rank (e.g., 1, 2, 3, 4...): ");
			String rank = scanner.nextLine().trim();

			for (Card card : cards) {
				if (card.getRankOrNumber().equalsIgnoreCase(rank) && card.getSuitOrColor().equalsIgnoreCase(suit)) {
					if (card.equals(matchedcard)) {
						return card;
					}
					System.out.println("The entered card does not match the required card. Try again.");
				}
			}

			System.out.println("Invalid card. Please enter a valid card from the list.");
		}
	}

	private static void printAvailableCards(List<Card> cards) {
		for (Card card : cards) {
			System.out.println(card.getSuitOrColor() + " " + card.getRankOrNumber());
		}
	}

}
