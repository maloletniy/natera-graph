package com.natera.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode(of = "id")
public class Vertex {
    private String id;
    private Set<Vertex> edges;

    @Builder
    public Vertex(String id, Object data) {
        this.id = id;
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
