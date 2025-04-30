package org.gxg.app.stack;

import org.gxg.collection.ArrayStack;
import org.gxg.tools.In;

import java.util.Arrays;

/**
 * 中缀表达式，我们最熟悉的数学表达式
 * 为简化问题，我们规定表达式必须包含括号，示例：
 *  (1 + ((22.3 + 30) * (4 * 5)))
 * 基于2个stack数据结构实现，1个栈保存运算符，1个栈保存操作数。
 * 算法描述：
 *  从左开始扫描，
 *  1、忽略左括号
 *  2、将操作数压入操作数栈
 *  3、将运算符压入运算符栈
 *  4、遇到右括号，弹出1个运算符，弹出2个操作数，并将运算结果压入操作数栈。
 *  处理完最后一个右括号，操作数栈里只会剩下最后一个数，即为表达式结果。
 *
 *
 */
public class InfixExpression {
    private static boolean isNumericChar(char c) {
        return c >= '0' && c <= '9';
    }
    public static void main(String[] args) {
        ArrayStack<Double> vals = new ArrayStack<>();
        ArrayStack<Character> ops = new ArrayStack<>();
        String file = "testData/app.stack_infix.txt";
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
            }

            System.out.println("read s: " + c);

            if (c == ' ' || c == '(')
                continue;// 忽略空格 和 左括号
            if (c == '+' || c == '-' || c == '*' || c == '/') ops.push(c);
            else if (c == ')') {
                char op = ops.pop();
                double d1 = vals.pop();
                double d2 = vals.pop();
                if (op == '+') vals.push(d2 + d1);
                else if (op == '-') vals.push(d2 - d1);
                else if (op == '*') vals.push(d2 * d1);
                else if (op == '/') vals.push(d2 / d1);
            }
        }
        System.out.println("ops.size: " + ops.size() + ", vals.size: " + vals.size());
        // 字符全部处理完毕，判断栈是否满足条件，不满足则说明给定表达式有问题。
        if (!ops.isEmpty()) throw new AssertionError();
        if (vals.size() != 1) throw new AssertionError();

        in.close();
        in = new In(file);
        System.out.println(Arrays.toString(in.readAllStrings()) + " = " + vals.pop());
        in.close();
    }
}
