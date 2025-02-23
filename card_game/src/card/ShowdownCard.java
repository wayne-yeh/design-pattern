package card;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ShowdownCard extends Card {

    private String rank;
    private String suit;

    private static final Set<String> validRank = new HashSet<>(Arrays.asList(
            "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"
    ));
    private static final Set<String> validSuit = new HashSet<>(Arrays.asList(
            "club", "diamond", "heart", "spade"
    ));

    public ShowdownCard(String color, String number) {
        setSuit(color);
        setRank(number);
    }

    private void validateSuitOrColor(String suit) {
        if (!validSuit.contains(suit)) {
            throw new IllegalArgumentException("Invalid suit! Allowed: " + validSuit);
        }
    }

    private void validateRankOrNumber(String rank) {
        if (!validRank.contains(rank)) {
            throw new IllegalArgumentException("Invalid rank! Allowed: " + validRank);
        }
    }

    public void setSuit(String suit) {
        validateSuitOrColor(suit);
        this.suit = suit;
    }

    public void setRank(String rank) {
        validateRankOrNumber(rank);
        this.rank = rank;
    }

    public String getRankOrNumber() {
        return rank;
    }

    public String getSuitOrColor() {
        return suit;
    }
}



