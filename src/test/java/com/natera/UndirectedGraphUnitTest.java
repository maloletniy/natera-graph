package com.natera;

import com.natera.domain.Vertex;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;

public class UndirectedGraphUnitTest {

    private UndirectedGraph<String> graph = new UndirectedGraph<>();

    @Test
    public void shouldAddEdge() {
        //given
        final Vertex<String> vertex1 = Vertex.<String>builder().id("vertex1").build();
        final Vertex<String> vertex2 = Vertex.<String>builder().id("vertex2").build();
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);

        //when
        graph.addEdge(vertex1, vertex2);

        //then
        assertThat(vertex1.getEdges(), hasItem(vertex2));
        assertThat(vertex2.getEdges(), hasItem(vertex1));
    }

    @Test
    public void shouldCalculatePath() {
        //given
        final Vertex<String> vertex1 = Vertex.<String>builder().id("vertex1").build();
        final Vertex<String> vertex2 = Vertex.<String>builder().id("vertex2").build();
        final Vertex<String> vertex3 = Vertex.<String>builder().id("vertex2").build();
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addEdge(vertex1, vertex2);
        graph.addEdge(vertex2, vertex3);

        //when
        final Set<Vertex> path = graph.getPath(vertex1, vertex3);

        //then
        assertThat(path, contains(vertex1, vertex2));
    }
}