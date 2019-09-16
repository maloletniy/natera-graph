package com.natera.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode(of = "id")
public class Vertex<T> {
    private String id;
    private Set<Vertex<T>> edges;
    private T data;

    @Builder
    public Vertex(String id, T data) {
        this.id = id;
        this.data = data;
        this.edges = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id='" + id + '\'' +
                ", edges=" + edges.stream().map(Vertex::getId).collect(Collectors.joining()) +
                '}';
    }
}
