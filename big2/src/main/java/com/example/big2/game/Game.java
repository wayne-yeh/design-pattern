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
    private int passCount = 0;
    int numberOfPlayers = 4;
    List<Player> players = new ArrayList<>();
    List<Player> indexPlayers = new ArrayList<>();
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
        while (true) {playersExecuteAction();}
        
    }

    private void playersExecuteAction() {
        Scanner scanner = new Scanner(System.in);
        Map<Card, Player> cardPlayerMap = new HashMap<>();
        Player lastPlayedPlayer = null; // **記錄最後一次出牌的玩家**

        for (int i = 0; i < indexPlayers.size(); i++) {
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

            int input = Integer.parseInt(scanner.nextLine());

            if (input == -1) {
                System.out.printf("player %s give up\n", player.getName());
                System.out.println("-----------------------------");
                passCount++;

                if (passCount == 3) {
                    System.out.println("All other players passed. A new round starts.");

                    passCount = 0;
                    topPlay = null;
                    cardPlayerMap.clear();

                    // **讓最後一次出牌的玩家成為新的回合開始者**
                    if (lastPlayedPlayer != null) {
                        System.out.println("Winner is" +lastPlayedPlayer.getName());
                        i = indexPlayers.indexOf(lastPlayedPlayer) - 1; // **確保下一回合從 lastPlayedPlayer 開始**
                    }
                }
            } else {
                Card inputCard = handCards.get(input);
                try {
                    checkResonalbe(inputCard, topPlay);
                    Card chooseCard = handCards.remove(input);
                    cardPlayerMap.put(chooseCard, player);
                    topPlay = chooseCard;
                    lastPlayedPlayer = player; // **記錄最後一位成功出牌的玩家**
                    System.out.printf("player %s choose card is %s[%s]\n", player.getName(), chooseCard.getSuit(), chooseCard.getRank());
                    System.out.println("-----------------------------");
                    passCount = 0; // **有人出牌時，重置 passCount**
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    i--; // **讓玩家重新輸入**
                }
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
