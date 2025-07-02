package org.gxg.graph;

import org.gxg.collection.LinkedStack;
import org.gxg.tools.In;

public class DirectedCycle {
    private final boolean[] marked;        // marked[v] = has vertex v been marked?
    private final int[] edgeTo;            // edgeTo[v] = previous vertex on path to v
    private final boolean[] onStack;       // onStack[v] = is vertex on the stack?
    private LinkedStack<Integer> cycle;    // directed cycle (or null if no such cycle)

    public DirectedCycle(Digraph G) {
        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo  = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v] && cycle == null) dfs(G, v);
    }

    // run DFS and find a directed cycle (if one exists)
    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {

            // short circuit if directed cycle found
            if (cycle != null) return;

                // found new vertex, so recur
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }

            // trace back directed cycle
            else if (onStack[w]) {
                cycle = new LinkedStack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        String file;
        file = "testData/graph/tinyDG.txt";
//        file = "testData/graph/mediumDG.txt";
        In in = new In(file);
        Digraph G = new Digraph(in);

        DirectedCycle finder = new DirectedCycle(G);
        if (finder.hasCycle()) {
            System.out.print("Directed cycle: ");
            for (int v : finder.cycle()) {
                System.out.print(v + " ");
            }
            System.out.println();
        }

        else {
            System.out.println("No directed cycle");
        }
        System.out.println();
    }
}
