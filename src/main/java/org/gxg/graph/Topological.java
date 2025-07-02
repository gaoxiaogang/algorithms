package org.gxg.graph;

import org.gxg.tools.In;

public class Topological {
    private Iterable<Integer> order;  // topological order
    private int[] rank;               // rank[v] = rank of vertex v in order

    public Topological(Digraph G) {
        DirectedCycle finder = new DirectedCycle(G);
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
            rank = new int[G.V()];
            int i = 0;
            for (int v : order)
                rank[v] = i++;
        }
    }

    public Topological(EdgeWeightedDigraph G) {
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean hasOrder() {
        return order != null;
    }

    @Deprecated
    public boolean isDAG() {
        return hasOrder();
    }

    public int rank(int v) {
        if (hasOrder()) return rank[v];
        else            return -1;
    }

    public static void main(String[] args) {
        String file;
        file = "testData/graph/jobs.txt";
        In in = new In(file);
        String delimiter = "/";
        SymbolDigraph sg = new SymbolDigraph(file, delimiter);
        Topological topological = new Topological(sg.digraph());
        for (int v : topological.order()) {
            System.out.println(sg.nameOf(v));
        }
    }
}
