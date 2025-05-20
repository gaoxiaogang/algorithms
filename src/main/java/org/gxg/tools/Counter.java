package org.gxg.tools;

// 计数器类
public class Counter {
    private final String name;

    private int count;

    // 创建一个名为id的计数器
    public Counter(String id) {
        name = id;
    }

    // 将计数器的值加1
    public void increment() {
        count++;
    }

    // 该对象创建之后计数器被加1的次数
    public int tally() {
        return count;
    }

    // 对象的字符串表示
    public String toString() {
        return count + " " + name;
    }

    public static void main(String[] args) {
        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");
        heads.increment();
        heads.increment();
        tails.increment();
        System.out.println(heads + " " + tails);
        System.out.println(heads.tally() + tails.tally());
    }
}
