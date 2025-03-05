package com.example.big2.game;

import com.example.big2.card.Card;
import com.example.big2.deck.Deck;
import com.example.big2.play.*;

import com.example.big2.player.Player;

import java.util.*;

public class Game {

    private Card topPlay;
    private int round = 0;
    private static int passCount = 0;
    int numberOfPlayers = 4;
    List<Player> players = new ArrayList<>();
    List<Player> indexPlayers = new ArrayList<>();
    private final Deck deck = new Deck();
    Player lastPlayedPlayer = null;
    boolean isAgain = false;
    private PlayHandler clientPlayerHandler;

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void start() {
        clientPlayerHandler = new CheckHandler(new PassHandler(new ValidationHandler(new PlayCardHandler(null))));

        deck.shuffle();
        enterPlayersName();
        playersGetCards();
        decideTopPlay();
        while (true) {playersExecuteAction();}
        
    }

    private void playersExecuteAction() {
        Scanner scanner = new Scanner(System.in);

        for (round = 0; round < indexPlayers.size(); round++) {
            while (true) {
                if (lastPlayedPlayer != null) {
                    System.out.println("Last player is " + lastPlayedPlayer.getName());
                }
                System.out.println("passCount: " + passCount);
                Player player = indexPlayers.get(round);
                List<Card> handCards = player.getHandCards();
                System.out.println("Turn player " + player.getName() + " player index " + round);

                if (topPlay != null) {
                    System.out.printf("Player name %s would like to do '-1' or choose index card higher than %s[%s] \n", player.getName(), topPlay.getSuit(), topPlay.getRank());
                }

                for (int index = 0; index < handCards.size(); index++) {
                    System.out.print(index + "\t\t");
                }
                System.out.println();

                for (Card handCard : handCards) {
                    System.out.printf("%s[%s]\t", handCard.getSuit(), handCard.getRank());
                }
                System.out.println();

                String input = scanner.nextLine();

                if (input.equals("-1")) {
                    try {
                        clientPlayerHandler.handle(player, null, this);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        List<Card> selectedCards = new ArrayList<>();
                        String[] indexes = input.split(",");
                        for (String index : indexes) {
                            int cardIndex = Integer.parseInt(index.trim());
                            selectedCards.add(handCards.get(cardIndex));
                        }
                        clientPlayerHandler.handle(player, selectedCards, this);
                        break;
                    } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                        System.out.println("Invalid input: " + e.getMessage());
                    }
                }
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

    public void incrementPassCount() {
        Game.passCount++;
    }

    public Card getTopPlay() {
        return topPlay;
    }

    public void setTopPlay(Card topPlay) {
        this.topPlay = topPlay;
    }

    public void setLastPlayedPlayer(Player player) {
        this.lastPlayedPlayer = player;
    }

    public void resetPassCount() {
        Game.passCount = 0;
    }

    public Player getLastPlayedPlayer(){
        return lastPlayedPlayer;
    }
}
