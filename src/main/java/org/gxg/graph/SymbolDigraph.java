package org.gxg.graph;

import org.gxg.collection.LinkedQueue;
import org.gxg.searching.RedBlackBST;
import org.gxg.tools.In;

public class SymbolDigraph {
    private final RedBlackBST<String, Integer> st;  // string -> index
    private final String[] keys;           // index  -> string
    private final Digraph graph;           // the underlying digraph

    public SymbolDigraph(String filename, String delimiter) {
        st = new RedBlackBST<>();

        // First pass builds the index by reading strings to associate
        // distinct strings with an index
        In in = new In(filename);
        while (in.hasNextLine()) {
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

        // second pass builds the digraph by connecting first vertex on each
        // line to all others
        graph = new Digraph(st.size());
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

    public Digraph digraph() {
        return graph;
    }

    public static void main(String[] args) {
        String file;
        file = "testData/graph/routes.txt";
        String delimiter = " ";
        SymbolDigraph sg = new SymbolDigraph(file, delimiter);
        Digraph graph = sg.digraph();

        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("JFK");
        queue.enqueue("LAX");
        while (!queue.isEmpty()) {
            String t = queue.dequeue();
            for (int v : graph.adj(sg.indexOf(t))) {
                System.out.println("   " + sg.nameOf(v));
            }
        }
    }
}
