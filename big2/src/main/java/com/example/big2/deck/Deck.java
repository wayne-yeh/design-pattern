package com.example.big2.deck;

import com.example.big2.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> cards;

    public Deck() {
        initializeDeck();
    }

    private void initializeDeck() {
        String[] suits = {"S", "H", "D", "C"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        cards = new ArrayList<>();
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
        printCards();
    }

    private void printCards() {
        System.out.println("test");
        for (int i = cards.size() -1; i >= 0; i--) {
            System.out.printf("%s[%s] ", cards.get(i).getSuit(), cards.get(i).getRank());
        }
        System.out.println();

    }
}
