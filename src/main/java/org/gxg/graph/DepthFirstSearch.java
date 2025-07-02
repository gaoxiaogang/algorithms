package org.gxg.graph;

import org.gxg.tools.In;

public class DepthFirstSearch {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int count;           // number of vertices connected to s

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        String file;
        file = "testData/graph/tinyG.txt";
//        file = "testData/graph/tinyCG.txt";
        In in = new In(file);
        Graph G = new Graph(in);
        int s = 0;
        DepthFirstSearch search = new DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v))
                System.out.print(v + " ");
        }

        System.out.println();
        if (search.count() != G.V()) System.out.println("NOT connected");
        else                         System.out.println("connected");

        in.close();
    }

}
