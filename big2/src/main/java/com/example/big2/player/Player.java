package com.example.big2.player;

import com.example.big2.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Player extends PlayerHandler{

    private String name;
    private int index;
    private List<Card> handCards = new ArrayList<>();
    public Boolean hasC3 = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHandCards() {
        return handCards;
    }

    public void setHandCards(List<Card> handCards) {
        this.handCards = handCards;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Boolean getTopPlayer() {
        return hasC3;
    }

    public void setTopPlayer(Boolean topPlayer) {
        hasC3 = topPlayer;
    }
}
