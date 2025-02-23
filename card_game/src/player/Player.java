package player;

import java.util.ArrayList;
import java.util.List;

import card.Card;

public abstract class Player  {

	List<Card> cards = new ArrayList<>();
	String name;
	int points = 0;
	Card card;

	public abstract void unoChoose(Card card1);
	public abstract Card simpleChoose();

	public void addCard(Card card) {
		cards.add(card);
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	public void setName(String name){
		this.name = name;
	}
	public void addPoints() {
		points++;
	}
	public String getName() {
		return name;
	}
	public int getPoints() {
		return points;
	}
	public List<Card> getCards() {
		return cards;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append("\t");
		for (Card card : cards) {
			sb.append(card.getSuitOrColor()).append(" ").append(card.getRankOrNumber()).append("\t");
		}
		return sb.toString();
	}


}
