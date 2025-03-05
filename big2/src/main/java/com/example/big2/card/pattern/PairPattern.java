package com.example.big2.card.pattern;

import com.example.big2.card.Card;

import java.util.List;


 public class PairPattern implements CardPattern {
        @Override
        public boolean isValid(List<Card> cards) {
            return cards.size() == 2 && cards.get(0).getRank().equals(cards.get(1).getRank());
        }

     @Override
     public int compare(List<Card> cards1, List<Card> cards2) {

         int rankCompare = cards1.get(0).compareTo(cards2.get(0));
         if (rankCompare != 0) {
             return rankCompare; // 點數不同，直接比較點數
         }

         Card maxCard1 = getMaxSuitCard(cards1.get(0), cards1.get(1));
         Card maxCard2 = getMaxSuitCard(cards2.get(0), cards2.get(1));

         return maxCard1.compareTo(maxCard2);
     }


     private Card getMaxSuitCard(Card card1, Card card2) {
         return card1.compareTo(card2) > 0 ? card1 : card2;
     }

 }


