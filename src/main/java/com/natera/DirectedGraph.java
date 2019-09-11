package com.natera;

import com.natera.domain.Vertex;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class DirectedGraph implements Graph {
    final HashMap<String, Vertex> graph;

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
            from.getEdges().add(to);
        }
    }

    @Override
    public Set<Vertex> getPath(Vertex from, Vertex to) {
        final HashSet<String> visitedIds = new HashSet<>();
        final Set<Vertex> path = new LinkedHashSet<>();
        visitedIds.add(from.getId());
        path.add(from);
        if (process(from, to, visitedIds, path)) {
            return path;
        }
        return Collections.emptySet();
    }

    private boolean process(Vertex origin, Vertex destination, Set<String> visitedIds, Set<Vertex> path) {
        for (Vertex linkedVertex : origin.getEdges()) {
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
