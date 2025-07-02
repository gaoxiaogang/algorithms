package org.gxg.graph;

import org.gxg.collection.LinkedStack;
import org.gxg.tools.In;

public class AcyclicLP {
    private final double[] distTo;          // distTo[v] = distance  of longest s->v path
    private final DirectedEdge[] edgeTo;    // edgeTo[v] = last edge on longest s->v path

    public AcyclicLP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.NEGATIVE_INFINITY;
        distTo[s] = 0.0;

        // relax vertices in topological order
        Topological topological = new Topological(G);
        if (!topological.hasOrder())
            throw new IllegalArgumentException("Digraph is not acyclic.");
        for (int v : topological.order()) {
            for (DirectedEdge e : G.adj(v))
                relax(e);
        }
    }

    // relax edge e, but update if you find a *longer* path
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] < distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] > Double.NEGATIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        LinkedStack<DirectedEdge> path = new LinkedStack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    public static void main(String[] args) {
        String file;
        file = "testData/graph/tinyEWDAG.txt";
        In in = new In(file);
        int s = 5;
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

        AcyclicLP lp = new AcyclicLP(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (lp.hasPathTo(v)) {
                System.out.printf("%d to %d (%.2f)  ", s, v, lp.distTo(v));
                for (DirectedEdge e : lp.pathTo(v)) {
                    System.out.print(e + "   ");
                }
                System.out.println();
            }
            else {
                System.out.printf("%d to %d         no path\n", s, v);
            }
        }
    }
}
