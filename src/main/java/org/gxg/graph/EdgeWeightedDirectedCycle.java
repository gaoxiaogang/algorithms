package org.gxg.graph;

import org.gxg.collection.LinkedStack;
import org.gxg.tools.StdRandom;

public class EdgeWeightedDirectedCycle {
    private final boolean[] marked;             // marked[v] = has vertex v been marked?
    private final DirectedEdge[] edgeTo;        // edgeTo[v] = previous edge on path to v
    private final boolean[] onStack;            // onStack[v] = is vertex on the stack?
    private LinkedStack<DirectedEdge> cycle;    // directed cycle (or null if no such cycle)

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo  = new DirectedEdge[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    // check that algorithm computes either the topological order or finds a directed cycle
    private void dfs(EdgeWeightedDigraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();

            // short circuit if directed cycle found
            if (cycle != null) return;

                // found new vertex, so recur
            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            }

            // trace back directed cycle
            else if (onStack[w]) {
                cycle = new LinkedStack<>();

                DirectedEdge f = e;
                while (f.from() != w) {
                    cycle.push(f);
                    f = edgeTo[f.from()];
                }
                cycle.push(f);

                return;
            }
        }

        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }

    public static void main(String[] args) {

        // create random DAG with V vertices and E edges; then add F random edges
        int V = 10;
        int E = 20;
        int F = 12;
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for (int i = 0; i < E; i++) {
            int v, w;
            do {
                v = StdRandom.uniformInt(V);
                w = StdRandom.uniformInt(V);
            } while (v >= w);
            double weight = StdRandom.uniformDouble(0.0, 1.0);
            G.addEdge(new DirectedEdge(v, w, weight));
        }

        // add F extra edges
        for (int i = 0; i < F; i++) {
            int v = StdRandom.uniformInt(V);
            int w = StdRandom.uniformInt(V);
            double weight = StdRandom.uniformDouble(0.0, 1.0);
            G.addEdge(new DirectedEdge(v, w, weight));
        }

        System.out.println(G);

        // find a directed cycle
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        if (finder.hasCycle()) {
            System.out.print("Cycle: ");
            for (DirectedEdge e : finder.cycle()) {
                System.out.print(e + " ");
            }
            System.out.println();
        }

        // or give topological sort
        else {
            System.out.println("No directed cycle");
        }
    }
}
