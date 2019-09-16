package com.natera;

import com.natera.domain.Vertex;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class DirectedGraph<T> implements Graph<T>{
    final HashMap<String, Vertex<T>> graph;

    public DirectedGraph() {
        this.graph = new HashMap<>();
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        graph.put(vertex.getId(), vertex);
    }

    @Override
    public void addEdge(Vertex<T> from, Vertex<T> to) {
        if (graph.containsKey(from.getId()) && graph.containsKey(to.getId())) {
            from.getEdges().add(to);
        }
    }

    @Override
    public Set<Vertex> getPath(Vertex<T> from, Vertex<T> to) {
        final HashSet<String> visitedIds = new HashSet<>();
        final Set<Vertex> path = new LinkedHashSet<>();
        visitedIds.add(from.getId());
        path.add(from);
        if (process(from, to, visitedIds, path)) {
            return path;
        }
        return Collections.emptySet();
    }

    private boolean process(Vertex<T> origin, Vertex<T> destination, Set<String> visitedIds, Set<Vertex> path) {
        for (Vertex<T> linkedVertex : origin.getEdges()) {
            final boolean notVisited = !visitedIds.contains(linkedVertex.getId());
            if (notVisited) {
                visitedIds.add(linkedVertex.getId());
                path.add(linkedVertex);
                if (linkedVertex.equals(destination)) {
                    return true;
                } else {
                    final boolean hasLink = process(linkedVertex, destination, visitedIds, path);
                    if (!hasLink) {
                        path.remove(linkedVertex);
                    }
                    return hasLink;
                }
            }
        }
        return false;
    }
}
