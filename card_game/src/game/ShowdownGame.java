package game;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import card.Card;
import deck.Deck;
import player.Player;

public class ShowdownGame extends Game {

	private Map<Player, Card> map = new LinkedHashMap<>();
	int round = 1;

	public ShowdownGame(Deck deck) {
		super(deck);
	}

	@Override
	protected void takeTurn() {

		System.out.println("Round: " + round + " starts");

		for (Player player : players) {
			map.put(player, player.simpleChoose());
		}

		display();
		compare();
		round++;
		if (round == 14) {
			hasWinner = true;
		}
	}

	private void display() {
		for (Map.Entry<Player, Card> entry : map.entrySet()) {
			System.out.println("Player: " + entry.getKey().getName() + " , Card: " + entry.getValue().getSuitOrColor() + " " + entry.getValue().getRankOrNumber());
		}
	}

	private void compare() {
		List<String> rankOrder = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");
		List<String> suitOrder = Arrays.asList("club", "diamond", "heart", "spade");
		Player maxPlayer = null;
		Card maxCard = null;

		for (Map.Entry<Player, Card> entry : map.entrySet()) {
			Card card = entry.getValue();
			if (maxCard == null || isGreater(card, maxCard, rankOrder, suitOrder)) {
				maxCard = card;
				maxPlayer = entry.getKey();
			}
		}

		if (maxPlayer != null) {
			System.out.println("This round Winner: " + maxPlayer.getName() + ", Card: " + maxCard.getSuitOrColor() + " " + maxCard.getRankOrNumber());
			maxPlayer.addPoints();
		} else {
			System.out.println("No cards to play.");
		}
	}

	private static boolean isGreater(Card card1, Card card2, List<String> rankOrder, List<String> suitOrder) {
		int rank1 = rankOrder.indexOf(card1.getRankOrNumber());
		int rank2 = rankOrder.indexOf(card2.getRankOrNumber());

		if (rank1 != rank2) {
			return rank1 > rank2;
		}
		return suitOrder.indexOf(card1.getSuitOrColor()) > suitOrder.indexOf(card2.getSuitOrColor());
	}

	@Override
	protected void announceWinner() {
		int maxPoints = -1;
		Player winner = null;

		for (Player player : map.keySet()) {
			if (player.getPoints() > maxPoints) {
				maxPoints = player.getPoints();
				winner = player;
			}
		}

		if (winner != null) {
			System.out.printf("Final Winner is %s !!%n", winner.getName());
		} else {
			System.out.println("No final winner.");
		}
	}
}
