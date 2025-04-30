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
    private static boolean isNumericChar(char c) {
        return c >= '0' && c <= '9';
    }
    public static void main(String[] args) {
        ArrayStack<Double> vals = new ArrayStack<>();
        String file = "testData/app.stack_RPN.txt";
        In in = new In(file);
        while (!in.isEmpty()) {
            char c = in.readChar();
            if (isNumericChar(c)) {// 遇到阿拉伯数字，继续往后找数字或小数点，组成一个完成的数后入栈
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                while (true) {
                    char tmpChar = in.readChar();
                    if (isNumericChar(tmpChar) || tmpChar == '.') {
                        sb.append(tmpChar);
                        continue;
                    }

                    c = tmpChar;
                    break;// 跳出循环
                }
                Double d = Double.parseDouble(sb.toString());
                vals.push(d);
                System.out.println("read Double: " + d);
            }// 数字入栈完毕

            System.out.println("read s: " + c);

            // TODO 其他字符的处理逻辑

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
