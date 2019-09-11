package com.natera;

import com.natera.domain.Vertex;

import java.util.Set;

public interface Graph {

    void addVertex(Vertex vertex);

    void addEdge(Vertex vertex1, Vertex vertex2);

    Set<Vertex> getPath(Vertex from, Vertex to);
}
