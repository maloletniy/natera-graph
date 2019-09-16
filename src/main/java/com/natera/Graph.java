package com.natera;

import com.natera.domain.Vertex;

import java.util.Set;

public interface Graph<T> {

    void addVertex(Vertex<T> vertex);

    void addEdge(Vertex<T> vertex1, Vertex<T> vertex2);

    Set<Vertex> getPath(Vertex<T> from, Vertex<T> to);
}
