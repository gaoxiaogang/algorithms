package org.gxg.app.searching;

import org.gxg.searching.SequentialSearchST;
import org.gxg.tools.In;

// 符号表的用例
public class FrequencyCounter {
    // Do not instantiate.
    private FrequencyCounter() { }

    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen = 0;// 最小长度
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();

        String file;
        file = "testData/searching/simple.txt";
//        file = "testData/searching/leipzig100k.txt";
        In in = new In(file);

        // compute frequency counts
        while (!in.isEmpty()) {
            String key = in.readString();
            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        System.out.println(max + " " + st.get(max));
        System.out.println("distinct = " + distinct);
        System.out.println("words    = " + words);

        in.close();
    }
}
