package com.example.big2.card;

import java.util.HashMap;
import java.util.Map;

public class Card {

    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }


    public int compareTo(Card other) {
        Map<String, Integer> rankOrder = new HashMap<>();
        rankOrder.put("3", 1);
        rankOrder.put("4", 2);
        rankOrder.put("5", 3);
        rankOrder.put("6", 4);
        rankOrder.put("7", 5);
        rankOrder.put("8", 6);
        rankOrder.put("9", 7);
        rankOrder.put("10", 8);
        rankOrder.put("J", 9);
        rankOrder.put("Q", 10);
        rankOrder.put("K", 11);
        rankOrder.put("A", 12);
        rankOrder.put("2", 13);

        int rankCompare = Integer.compare(rankOrder.get(this.rank), rankOrder.get(other.rank));
        if (rankCompare != 0) {
            return rankCompare;
        }

        Map<String, Integer> suitOrder = Map.of("S", 1, "H", 2, "D", 3, "C", 4);
        return Integer.compare(suitOrder.get(this.suit), suitOrder.get(other.suit));
    }
}
