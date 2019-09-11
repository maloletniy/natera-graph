package com.natera;

import com.natera.domain.Vertex;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class UndirectedGraph implements Graph {
    private HashMap<String, Vertex> graph;

    public UndirectedGraph() {
        this.graph = new HashMap<>();
    }

    @Override
    public void addVertex(Vertex vertex) {
        graph.put(vertex.getId(), vertex);
    }

    @Override
    public void addEdge(Vertex vertex1, Vertex vertex2) {
        if (graph.containsKey(vertex1.getId()) && graph.containsKey(vertex2.getId())) {
            vertex1.getEdges().add(vertex2);
            vertex2.getEdges().add(vertex1);
        }
    }

    @Override
    public Set<Vertex> getPath(Vertex from, Vertex to) {
        final HashSet<String> visitedIds = new HashSet<>();
        final HashSet<Vertex> path = new HashSet<>();
        if (process(from, to, visitedIds, path)) {
            return path;
        }
        return Collections.emptySet();
    }

    private boolean process(Vertex origin, Vertex destination, Set<String> visitedIds, HashSet<Vertex> path) {
        for (Vertex linkedVertex : origin.getEdges()) {
            final boolean notVisited = !visitedIds.contains(linkedVertex.getId());
            if (notVisited) {
                path.add(linkedVertex);
                if (linkedVertex.equals(destination)) {
                    return true;
                } else {
                    final boolean noLink = !process(linkedVertex, linkedVertex, visitedIds, path);
                    if (noLink) {
                        path.remove(linkedVertex);
                    }
                }
                visitedIds.add(linkedVertex.getId());
            }
        }
        return false;
    }
}
