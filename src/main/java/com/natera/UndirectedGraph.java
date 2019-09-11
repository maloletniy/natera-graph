package com.natera;

import com.natera.domain.Vertex;

public class UndirectedGraph extends DirectedGraph {

    @Override
    public void addEdge(Vertex vertex1, Vertex vertex2) {
        if (graph.containsKey(vertex1.getId()) && graph.containsKey(vertex2.getId())) {
            vertex1.getEdges().add(vertex2);
            vertex2.getEdges().add(vertex1);
        }
    }

}
