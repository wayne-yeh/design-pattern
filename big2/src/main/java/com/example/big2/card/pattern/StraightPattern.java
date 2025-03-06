package com.example.big2.card.pattern;

import com.example.big2.card.Card;

import java.util.Collections;
import java.util.List;

public class StraightPattern extends CardPattern {

    @Override
    public boolean isValid(List<Card> cards) {
        if (cards.size() != 5) return false;

        // 排序
        Collections.sort(cards);

        // 檢查是否為連續數字
        for (int i = 1; i < 5; i++) {
            if (cards.get(i).getRank() != cards.get(i - 1).getRank() + 1) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int compare(List<Card> cards1, List<Card> cards2) {
        cards1.sort(Card::compareTo);
        cards2.sort(Card::compareTo);
        return cards1.get(4).compareTo(cards2.get(4)); // 取最大牌來比較
    }

}
