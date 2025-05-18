package org.gxg.app.queue;

public class RandomQueue<E> {
    // 使用能够动态调整大小的数组来存储元素
    E[] Data;
    
    int N;// 元素数量

    @SuppressWarnings("unchecked")
    public RandomQueue() {
        Data = (E[]) new Object[52];// 52张牌
        N = 0;
    }

    // 队列是否为空
    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    // 添加1个元素
    public void enqueue(E element) {
        if (N == Data.length) {
            throw new ArrayIndexOutOfBoundsException("Array a is full!");
        }
        Data[N++] = element;
    }

    public int getRandomIndex() {
        int min = 0;
        int max = N - 1;
        return  (int)(Math.random() * (max - min + 1)) + min;
    }

    // 随机返回一个元素但不删除它
    public E sample() {
        if (N == 0) {
            return null;
        }
        int idx = getRandomIndex();
        return Data[idx];
    }

    // 随机返回一个元素，并将该元素从队列中删除
    // 提示: 随机交换某个元素（索引在 0 和 N-1 之间）和 末位元素（索引为N-1）的位置，然后删除并返回末位元素。
    public E dequeue() {
        if (N == 0) {
            return null;
        }
        int idx = getRandomIndex();
        E tmp = Data[idx];// 随机选中的元素

        Data[idx] = Data[N-1];
        Data[N-1] = null;
        N--;
        return tmp;
    }

    public static void main(String[] args) {
        // 基于RandomQueue，编写一个用例，使用 RandomQueue<Card> 给4个人发牌，每人13张。
        // Card类包含花色（红、黑、梅、方）、牌数（用int 1 到 13表示）

        // 生成52张牌
        RandomQueue<Card> queue = new RandomQueue<>();
        for (CardSuit suit: CardSuit.values()) {
            for (int i = 1; i <= 13; i++) {
                queue.enqueue(new Card(i, suit));
            }
        }

        // 分配给4个人
        Card[] person1 = new Card[13];
        Card[] person2 = new Card[13];
        Card[] person3 = new Card[13];
        Card[] person4 = new Card[13];
        for (int i = 0; i <= 12; i++) {
            person1[i] = queue.dequeue();
            person2[i] = queue.dequeue();
            person3[i] = queue.dequeue();
            person4[i] = queue.dequeue();
        }

        System.out.println(".........person 1's cards list.........");
        for (Card card : person1) {
            System.out.print(card);
        }
        System.out.println();

        System.out.println(".........person 2's cards list.........");
        for (Card card : person2) {
            System.out.print(card);
        }
        System.out.println();

        System.out.println(".........person 3's cards list.........");
        for (Card card : person3) {
            System.out.print(card);
        }
        System.out.println();

        System.out.println(".........person 4's cards list.........");
        for (Card card : person4) {
            System.out.print(card);
        }
        System.out.println();
    }
}
