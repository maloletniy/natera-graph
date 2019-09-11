package com.natera;

import com.natera.domain.Vertex;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class UndirectedGraphUnitTest {

    private Graph graph = new UndirectedGraph();

    @Test
    public void shouldAddVertex() {

    }

    @Test
    public void shouldAddEdge() {

    }

    @Test
    public void shouldCalculatePath() {
        //given
        final Vertex vertex1 = Vertex.builder().id("vertex1").build();
        final Vertex vertex2 = Vertex.builder().id("vertex2").build();
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addEdge(vertex1, vertex2);

        //when
        final Set<Vertex> path = graph.getPath(vertex1, vertex2);

        //then
        assertThat(path, contains(vertex1, vertex2));
    }
}