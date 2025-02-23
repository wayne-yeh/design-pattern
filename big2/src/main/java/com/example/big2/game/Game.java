package com.example.big2.game;

import com.example.big2.card.pattern.CardPattern;
import com.example.big2.deck.Deck;
import com.example.big2.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private CardPattern topPlay;
    private int round = 0;
    int numberOfPlayers = 4;
    private final Deck deck = new Deck();

    public CardPattern getTopPlay() {
        return topPlay;
    }

    public void setTopPlay(CardPattern topPlay) {
        this.topPlay = topPlay;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void start() {
        deck.shuffle();
        enterPlayersName();

    }

    private void enterPlayersName() {
        Scanner scanner = new Scanner(System.in);
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player());
        }
        int j = 1;
        for (Player player : players) {
            System.out.print("Enter name for player " + j + ": ");
            String name = scanner.nextLine();
            players.get(j-1).setName(name);  // 設定玩家名稱，注意索引是從 0 開始的
            System.out.println("Player " + j + " name set to: " + name);
            j++;
        }
        System.out.println(deck.getCards().get(0).getSuit());


    }


}
