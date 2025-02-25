package com.example.big2.game;

import com.example.big2.card.Card;
import com.example.big2.card.pattern.CardPattern;
import com.example.big2.deck.Deck;
import com.example.big2.player.Player;

import java.util.*;

public class Game {

    public Player topPlayer = null;
    private Card topPlay;
    private int round = 0;
    int numberOfPlayers = 4;
    List<Player> players = new ArrayList<>();
    List<Player> indexPlayers = new ArrayList<>();
    int passCount;
    private final Deck deck = new Deck();


    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void start() {
        deck.shuffle();
        enterPlayersName();
        playersGetCards();
        decideTopPlay();
        playersExecuteAction();
        
    }

    private void playersExecuteAction() {

        Scanner scanner = new Scanner(System.in);
        int passCount = 0;
        Map<Card, Player> cardPlayerMap = new HashMap<>();
        for (int i = 0; i < indexPlayers.size(); i++) {
            System.out.print("passsss"+passCount);
            Player player = indexPlayers.get(i);
            List<Card> HandCards = player.getHandCards();
            System.out.println("Turn player " + player.getName() +" player index " + i);
            System.out.println("Player name " + player.getName() +" would like to do '-1' or 'choose index card'");
            for (int index = 0; index < HandCards.size(); index++) {
                System.out.print(index+"\t\t");
            }
            System.out.println();
            for (Card handCard : HandCards) {
                System.out.printf("%s[%s]\t", handCard.getSuit(), handCard.getRank());
            }

            System.out.println();
            int input = Integer.parseInt(scanner.nextLine());
            if (input == -1) {
                System.out.printf("player %s give up\n", player.getName());
                System.out.println("-----------------------------");
                passCount++;
                System.out.printf("passconunt is "+passCount);
                continue;
            } else {
                Card chooseCard = HandCards.remove(input);
                cardPlayerMap.put(chooseCard,player);
                topPlay = chooseCard;
                System.out.printf("player %s choose card is %s[%s]\n", player.getName(), chooseCard.getSuit(), chooseCard.getRank());
                System.out.println("-----------------------------");
            }
            System.out.print("eeeeeeeee");
            if (passCount == 2) {
                System.out.printf("top player is %s",cardPlayerMap.get(topPlay));
                cardPlayerMap.clear();
            }

        }

    }

    private void decideTopPlay() {

        outerLoop:
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            for (Card card : player.getHandCards()) {
                if (card.getSuit().equals("C") && card.getRank().equals("3")) {
                    System.out.printf("Top player is %s", player.getName());
                    System.out.println();
                    player.setTopPlayer(true);
                    indexPlayers.add(players.get((i+0)%4));
                    indexPlayers.add(players.get((i+1)%4));
                    indexPlayers.add(players.get((i+2)%4));
                    indexPlayers.add(players.get((i+3)%4));
                    break outerLoop;
                }
            }
            System.out.println();
        }

       for (int i = 0; i < indexPlayers.size(); i++) {
            System.out.println("player name " + indexPlayers.get(i).getName() +"player index " + i);
       }
    }

    private void playersGetCards() {
        int i = 0;
        while (!deck.getCards().isEmpty()){
            List<Card> currentCards = players.get(i % 4).getHandCards();
            currentCards.add(deck.drawCard());
            i++;
        }

        for (Player player : players) {
            System.out.printf("%s: ", player.getName());
            for (Card card : player.getHandCards()) {
                System.out.printf("%s%s ", card.getSuit(),card.getRank());
            }
            System.out.println();
        }
    }

    private void enterPlayersName() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player());
        }
        int j = 1;
        for (Player player : players) {
            System.out.print("Enter name for player " + j + ": ");
            String name = scanner.nextLine();
            players.get(j-1).setName(name);
            System.out.println("Player " + j + " name set to: " + name);
            j++;
        }


    }


}
