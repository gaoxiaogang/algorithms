package org.gxg.app.stack;

import org.gxg.collection.ArrayStack;
import org.gxg.tools.In;

import java.util.Arrays;

/**
 * 逆波兰表示法，又称后缀表达式
 * 基于Stack数据结构实现
 * 1 3 4 + 2 * +
 * 算法描述：
 *  从左扫描，遇到数字入栈。遇到操作符则从栈里取出2个数字，与操作符计算后，再将结果入栈。
 *  全部扫描完后，栈里只会剩下最后一个数，即为表达式结果。
 *
 */
public class ReversePolishNotation {
    // 判断一个字符串是否是一个数
    private static boolean isNumeric(String str) {
        return str != null && str.matches("^[-+]?\\d+(\\.\\d+)?([eE][-+]?\\d+)?$");
    }
    public static void main(String[] args) {
        ArrayStack<Double> vals = new ArrayStack<>();
        String file = "testData/app.stack_RPN.txt";
        In in = new In(file);
        while (!in.isEmpty()) {
            String s = in.readString();
            System.out.println("read s: [" + s + "]");
            if (isNumeric(s)) {
                vals.push(Double.parseDouble(s));
                continue;
            }

            if (s.length() > 1) {
                throw new RuntimeException("无效的操作符");
            }
            // TODO 其他字符的处理逻辑
            double d1 = vals.pop();
            double d2 = vals.pop();

            char op = s.charAt(0);
            switch (op) {
                case '+':
                    vals.push(d2 + d1);
                    break;
                case '-':
                    vals.push(d2 - d1);
                    break;
                case '*':
                    vals.push(d2 * d1);
                    break;
                case '/':
                    vals.push(d2 / d1);
                    break;
            }
            /*
            if (s.equals("+")) vals.push(d2 + d1);
            else if (s.equals("-")) vals.push(d2 - d1);
            else if (s.equals("*")) vals.push(d2 * d1);
            else if (s.equals("/")) vals.push(d2 / d1);
             */
        }

        System.out.println("vals.size: " + vals.size());
        // 字符全部处理完毕，判断栈是否满足条件，不满足则说明给定表达式有问题。
        if (vals.size() != 1) throw new AssertionError();

        in.close();
        in = new In(file);
        System.out.println(Arrays.toString(in.readAllStrings()) + " = " + vals.pop());
        in.close();
    }
}
