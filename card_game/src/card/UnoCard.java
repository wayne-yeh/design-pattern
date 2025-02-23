package card;

import java.util.*;

public class UnoCard extends Card {

    private String rank;
    private String suit;

    private static final Set<String> validateRank = new HashSet<>(Arrays.asList(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    ));
    private static final Set<String> validateSuit = new HashSet<>(Arrays.asList(
            "BLUE", "RED", "YELLOW", "GREEN"
    ));

    public UnoCard(String suit, String rank) {
        setSuit(suit);
        setRank(rank);
    }

    private void validateSuit(String suit) {
        if (!validateSuit.contains(suit)) {
            throw new IllegalArgumentException("Invalid suit! Allowed: " + validateSuit);
        }
    }

    private void validateRank(String rank) {
        if (!validateRank.contains(rank)) {
            throw new IllegalArgumentException("Invalid rank! Allowed: " + validateRank);
        }
    }

    public void setSuit(String suit) {
        validateSuit(suit);
        this.suit = suit;
    }

    public void setRank(String rank) {
        validateRank(rank);
        this.rank = rank;
    }

    public String getRankOrNumber() {
        return rank;
    }

    public String getSuitOrColor() {
        return suit;
    }
}
