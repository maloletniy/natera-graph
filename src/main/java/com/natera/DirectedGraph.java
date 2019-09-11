package com.natera;

import com.natera.domain.Vertex;

import java.util.HashMap;
import java.util.Set;

public class DirectedGraph implements Graph {
    private HashMap<String, Vertex> graph;

    public DirectedGraph() {
        this.graph = new HashMap<>();
    }

    @Override
    public void addVertex(Vertex vertex) {
        graph.put(vertex.getId(), vertex);
    }

    @Override
    public void addEdge(Vertex from, Vertex to) {
        if (graph.containsKey(from.getId()) && graph.containsKey(to.getId())) {
            return;
        }
        from.getEdges().add(to);
    }

    @Override
    public Set<Vertex> getPath(Vertex from, Vertex to) {
        return null;
    }
}
