package com.example.big2.card.factory;

import com.example.big2.card.Card;
import com.example.big2.card.pattern.*;

import java.util.*;

public class CardPatternFactory {
    private static List<CardPattern> PATTERNS = new ArrayList<>();

    public static void registerPatternList(List<CardPattern> patterns) {
        PATTERNS = patterns;
    }

    public static CardPattern getPattern(List<Card> cards) {
        for (CardPattern pattern : PATTERNS) {
            if (pattern.isValid(cards)) {
                return pattern;
            }
        }
        throw new IllegalArgumentException("Invalid card pattern");
    }
}
