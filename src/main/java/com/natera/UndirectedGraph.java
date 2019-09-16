package com.natera;

import com.natera.domain.Vertex;

public class UndirectedGraph<T> extends DirectedGraph<T> {

    @Override
    public void addEdge(Vertex<T> vertex1, Vertex<T> vertex2) {
        if (graph.containsKey(vertex1.getId()) && graph.containsKey(vertex2.getId())) {
            vertex1.getEdges().add(vertex2);
            vertex2.getEdges().add(vertex1);
        }
    }

}
