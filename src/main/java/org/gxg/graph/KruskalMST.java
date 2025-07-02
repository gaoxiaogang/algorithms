package org.gxg.graph;

import org.gxg.collection.LinkedQueue;
import org.gxg.tools.In;

import java.util.Arrays;

public class KruskalMST {
    private double weight;                        // weight of MST
    private final LinkedQueue<Edge> mst = new LinkedQueue<>();  // edges in MST

    public KruskalMST(EdgeWeightedGraph G) {

        // create array of edges, sorted by weight
        Edge[] edges = new Edge[G.E()];
        int t = 0;
        for (Edge e: G.edges()) {
            edges[t++] = e;
        }
        Arrays.sort(edges);

        // run greedy algorithm
        UF uf = new UF(G.V());
        for (int i = 0; i < G.E() && mst.size() < G.V() - 1; i++) {
            Edge e = edges[i];
            int v = e.either();
            int w = e.other(v);

            // v-w does not create a cycle
            if (uf.find(v) != uf.find(w)) {
                uf.union(v, w);     // merge v and w components
                mst.enqueue(e);     // add edge e to mst
                weight += e.weight();
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }

    public static void main(String[] args) {
        String file;
        file = "testData/graph/tinyEWG.txt";
//        file = "testData/graph/mediumEWG.txt";
        In in = new In(file);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        KruskalMST mst = new KruskalMST(G);
        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.printf("%.5f\n", mst.weight());
    }
}
