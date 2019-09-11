package com.natera;

import com.natera.domain.Vertex;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class DirectedGraphUnitTest {

    private Graph graph = new DirectedGraph();

    @Test
    public void shouldAddVertex() {

    }

    @Test
    public void shouldAddEdge() {

    }

    @Test
    public void shouldHavePathFromAToB() {
        //given
        final Vertex a = Vertex.builder().id("a").build();
        final Vertex b = Vertex.builder().id("b").build();
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(a, b);

        //when
        final Set<Vertex> path = graph.getPath(a, b);

        //then
        assertThat(path, contains(a, b));
    }

    @Test
    public void shouldNotHavePathFromBToA() {
        //given
        final Vertex a = Vertex.builder().id("a").build();
        final Vertex b = Vertex.builder().id("b").build();
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(a, b);

        //when
        final Set<Vertex> path = graph.getPath(b, a);

        //then
        assertThat(path, not(contains(b, a)));
    }
}