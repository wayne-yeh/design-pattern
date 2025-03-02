package com.example.big2.game;

import com.example.big2.card.Card;
import com.example.big2.deck.Deck;
import com.example.big2.player.Player;

import java.util.*;

public class Game {

    private Card topPlay;
    private int round = 0;
    private int passCount = 0;
    int numberOfPlayers = 4;
    List<Player> players = new ArrayList<>();
    List<Player> indexPlayers = new ArrayList<>();
    private final Deck deck = new Deck();
    Player lastPlayedPlayer = null;


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
        while (true) {playersExecuteAction();}
        
    }

    private void playersExecuteAction() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < indexPlayers.size(); i++) {
            if (lastPlayedPlayer != null) {
                System.out.println("Last player is " +lastPlayedPlayer.getName());
            }
            System.out.println("passCount: " + passCount);
            Player player = indexPlayers.get(i);
            List<Card> handCards = player.getHandCards();
            System.out.println("Turn player " + player.getName() + " player index " + i);

            if (topPlay != null) {
                System.out.println("Player name " + player.getName() + " would like to do '-1' or 'choose index card higher than' " + topPlay.getSuit() + topPlay.getRank());
            }

            for (int index = 0; index < handCards.size(); index++) {
                System.out.print(index + "\t\t");
            }
            System.out.println();

            for (Card handCard : handCards) {
                System.out.printf("%s[%s]\t", handCard.getSuit(), handCard.getRank());
            }
            System.out.println();

            int cardIndex = Integer.parseInt(scanner.nextLine());

            if (cardIndex == -1) {
                System.out.printf("player %s give up\n", player.getName());
                System.out.println("-----------------------------");
                passCount++;

                if (passCount == 3) {
                    System.out.println("All other players passed. A new round starts.");

                    passCount = 0;
                    topPlay = null;
                }
            } else {
                Card inputCard = handCards.get(cardIndex);
                try {
                    checkPlayerHasC3(inputCard, player);
                    checkResonalbe(inputCard, topPlay);
                    chooseCard(cardIndex, player);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    i--;
                }
            }
        }
    }

    private void chooseCard(int cardIndex, Player player) {
        Card chooseCard = player.getHandCards().remove(cardIndex);
        topPlay = chooseCard;
        lastPlayedPlayer = player;
        player.setC3Player(false);
        System.out.printf("player %s choose card is %s[%s]\n", player.getName(), chooseCard.getSuit(), chooseCard.getRank());
        System.out.println("-----------------------------");
        passCount = 0;
    }

    private void checkPlayerHasC3(Card inputCard, Player player) {
        if (player.hasC3) {
            if (!inputCard.getRank().equals("3") || !inputCard.getSuit().equals("C")) {
                throw new IllegalArgumentException("Player " + player.getName()+" has C[3], need to throw C[3] first");
            }
        }
    }


    private void checkResonalbe(Card inputCard, Card topPlay) {
        if (topPlay == null) {
            return; 
        }

        int inputRank = getRankValue(inputCard.getRank());
        int topRank = getRankValue(topPlay.getRank());

        if (inputRank > topRank || (inputRank == topRank && compareSuit(inputCard.getSuit(), topPlay.getSuit()) > 0)) {
            return; 
        }

        throw new IllegalArgumentException("The card you played is smaller than the card on the deck, please choose a bigger card!");
    }

    // 轉換點數為數值（大老二的點數大小規則）
    private int getRankValue(String rank) {
        switch (rank) {
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            case "10": return 10;
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            case "A": return 14;
            case "2": return 15; // 2 是最大的
            default: throw new IllegalArgumentException("non card: " + rank);
        }
    }

    private int compareSuit(String suit1, String suit2) {
        Map<String, Integer> suitOrder = new HashMap<>();
        suitOrder.put("C", 1);
        suitOrder.put("D", 2);
        suitOrder.put("H", 3);
        suitOrder.put("S", 4);

        return Integer.compare(suitOrder.get(suit1), suitOrder.get(suit2));
    }

    private void decideTopPlay() {

        outerLoop:
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            for (Card card : player.getHandCards()) {
                if (card.getSuit().equals("C") && card.getRank().equals("3")) {
                    System.out.printf("Top player is %s", player.getName());
                    System.out.println();
                    player.setC3Player(true);
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
            System.out.println("player name " + indexPlayers.get(i).getName() +" player index " + i);
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
