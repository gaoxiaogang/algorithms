package org.gxg;

import org.gxg.tools.In;
import org.gxg.tools.StdOut;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
        //TIP 当文本光标位于高亮显示的文本处时按 <shortcut actionId="ShowIntentionActions"/>
        // 查看 IntelliJ IDEA 建议如何修正。
        System.out.print("Hello and welcome!");
        System.out.println("args[0] = " + args[0]);
        for (int i = 1; i <= 5; i++) {
            //TIP 按 <shortcut actionId="Debug"/> 开始调试代码。我们已经设置了一个 <icon src="AllIcons.Debugger.Db_set_breakpoint"/> 断点
            // 但您始终可以通过按 <shortcut actionId="ToggleLineBreakpoint"/> 添加更多断点。
            System.out.println("i = " + i);
        }

        //In in = new In();
        //System.out.print("请输入长度：");
        //int len = in.readInt();
        int[] numbers = new int[15];
        int idx = 0;
        String filename = "testData/ints.txt";
        In in = new In(filename);
        while (!in.isEmpty()) {
            int i = in.readInt();
            StdOut.println("接收到: "+i+",");
            numbers[idx] = i;
            idx++;
        }
        for (int i = 0; i< numbers.length; i++) {
            StdOut.printf("%d   %d ", i, numbers[i]);
            StdOut.println();
        }


    }
}