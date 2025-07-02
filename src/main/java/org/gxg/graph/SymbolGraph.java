package org.gxg.graph;

import org.gxg.collection.LinkedQueue;
import org.gxg.searching.RedBlackBST;
import org.gxg.tools.In;

public class SymbolGraph {
    private final RedBlackBST<String, Integer> st;  // string -> index
    private final String[] keys;           // index  -> string
    private final Graph graph;             // the underlying graph

    public SymbolGraph(String filename, String delimiter) {
        st = new RedBlackBST<>();

        In in = new In(filename);
        // while (in.hasNextLine()) {
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            for (String s : a) {
                if (!st.contains(s))
                    st.put(s, st.size());
            }
        }

        // inverted index to get string keys in an array
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        // second pass builds the graph by connecting first vertex on each
        // line to all others
        graph = new Graph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = st.get(a[i]);
                graph.addEdge(v, w);
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int indexOf(String s) {
        return st.get(s);
    }

    public String nameOf(int v) {
        return keys[v];
    }

    public Graph graph() {
        return graph;
    }

    public static void main(String[] args) {
        String file;
        file = "testData/graph/routes.txt";
        String delimiter = " ";
        SymbolGraph sg = new SymbolGraph(file, delimiter);
        Graph graph = sg.graph();
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("JFK");
        queue.enqueue("LAX");
        while (!queue.isEmpty()) {
            String source = queue.dequeue();
            System.out.println(source);
            if (sg.contains(source)) {
                int s = sg.indexOf(source);
                for (int v : graph.adj(s)) {
                    System.out.println("   " + sg.nameOf(v));
                }
            }
            else {
                System.out.println("input not contain '" + source + "'");
            }
        }
    }
}
