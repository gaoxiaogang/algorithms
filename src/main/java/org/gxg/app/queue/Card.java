package org.gxg.app.queue;

public class Card {
    private final int rank;// 牌面，1代表A、3代表3、11代表J、12代表Q、13代表K
    private final CardSuit suit;// 花色。黑桃（Spade）、红桃（Heart）、方块（Diamond）、梅花（Club）

    public Card(int cardRank, CardSuit cardSuit) {
        rank = cardRank;
        suit = cardSuit;
    }

    @Override
    public String toString() {
//        return "[rank: " + rank + ", suit: " + suit + ']';
        return "[" + rank + ',' + suit + ']';
    }
}


