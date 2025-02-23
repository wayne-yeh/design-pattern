package com.example.big2.card;

public class Card {

    private String Suit;
    private String Rank;

    public Card(String suit, String rank) {
        this.Suit = suit;
        this.Rank = rank;
    }

    public String getSuit() {
        return Suit;
    }

    public void setSuit(String suit) {
        Suit = suit;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }
}
