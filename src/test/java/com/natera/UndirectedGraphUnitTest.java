package com.natera;

import com.natera.domain.Vertex;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class UndirectedGraphUnitTest {

    private UndirectedGraph graph = new UndirectedGraph();

    @Test
    public void shouldAddEdge() {
        //given
        final Vertex vertex1 = Vertex.builder().id("vertex1").build();
        final Vertex vertex2 = Vertex.builder().id("vertex2").build();
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);

        //when
        graph.addEdge(vertex1, vertex2);

        //then
        assertThat(vertex1.getEdges(), contains(vertex2));
        assertThat(vertex2.getEdges(), contains(vertex1));
    }

    @Test
    public void shouldCalculatePath() {
        //given
        final Vertex vertex1 = Vertex.builder().id("vertex1").build();
        final Vertex vertex2 = Vertex.builder().id("vertex2").build();
        final Vertex vertex3 = Vertex.builder().id("vertex2").build();
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