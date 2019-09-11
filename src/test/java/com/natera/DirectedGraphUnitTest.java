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
        assertThat(vertex2.getEdges(), not(contains(vertex1)));
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

    @Test
    public void shouldHavePathFromAToD() {
        //given
        final Vertex a = Vertex.builder().id("a").build();
        final Vertex b = Vertex.builder().id("b").build();
        final Vertex c = Vertex.builder().id("c").build();
        final Vertex d = Vertex.builder().id("d").build();
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addEdge(a, b);
        graph.addEdge(b, c);
        graph.addEdge(c, d);

        //when
        final Set<Vertex> path = graph.getPath(a, d);

        //then
        assertThat(path, contains(a, b, c, d));
    }

    @Test
    public void shouldBuildPathWithLoopEdges() {
        //given
        final Vertex a = Vertex.builder().id("a").build();
        final Vertex b = Vertex.builder().id("b").build();
        final Vertex c = Vertex.builder().id("c").build();
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addEdge(a, b);
        graph.addEdge(a, a);
        graph.addEdge(b, b);
        graph.addEdge(b, c);

        //when
        final Set<Vertex> path = graph.getPath(a, c);

        //then
        assertThat(path, contains(a, b, c));
    }
}