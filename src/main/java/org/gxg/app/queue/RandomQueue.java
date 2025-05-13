package org.gxg.app.queue;

public class RandomQueue<E> {
    // 使用能够动态调整大小的数组来存储元素
    E[] Data;
    
    int N;// 元素数量

    public RandomQueue() {
        // TODO
    }

    // 队列是否为空
    public boolean isEmpty() {
        // TODO
        return true;
    }

    // 添加1个元素
    public void enqueue(E element) {
        // TODO
    }

    // 随机返回一个元素但不删除它
    public E sample() {
        // TODO
        return null;
    }

    // 随机返回一个元素，并将该元素从队列中删除
    // 提示: 随机交换某个元素（索引在 0 和 N-1 之间）和 末位元素（索引为N-1）的位置，然后删除并返回末位元素。
    public E dequeue() {
        // TODO
        return null;
    }

    public static void main(String[] args) {
        // 基于RandomQueue，编写一个用例，使用 RandomQueue<Card> 给4个人发牌，每人13张。
        // Card类包含花色（红、黑、梅、方）、牌数（用int 1 到 13表示）
        // TODO

    }
}
