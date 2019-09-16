package com.natera;

import com.natera.domain.Vertex;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class DirectedGraphUnitTest {

    private Graph<String> graph = new DirectedGraph<>();

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
        assertThat(vertex2.getEdges(), not(hasItem(vertex1)));
    }

    @Test
    public void shouldNotHavePathFromBToA() {
        //given
        final Vertex<String> a = Vertex.<String>builder().id("a").build();
        final Vertex<String> b = Vertex.<String>builder().id("b").build();
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
        final Vertex<String> a = Vertex.<String>builder().id("a").build();
        final Vertex<String> b = Vertex.<String>builder().id("b").build();
        final Vertex<String> c = Vertex.<String>builder().id("c").build();
        final Vertex<String> d = Vertex.<String>builder().id("d").build();
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
    public void shouldHaveDeadLeafInPath() {
        //given
        final Vertex<String> a = Vertex.<String>builder().id("a").build();
        final Vertex<String> b = Vertex.<String>builder().id("b").build();
        final Vertex<String> c = Vertex.<String>builder().id("c").build();
        final Vertex<String> d = Vertex.<String>builder().id("d").build();
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addEdge(a, b);
        graph.addEdge(a, c);
        graph.addEdge(c, d);

        //when
        final Set<Vertex> path = graph.getPath(a, d);

        //then
        assertThat(path, contains(a, c, d));
    }

    @Test
    public void shouldBuildPathWithLoopEdges() {
        //given
        final Vertex<String> a = Vertex.<String>builder().id("a").build();
        final Vertex<String> b = Vertex.<String>builder().id("b").build();
        final Vertex<String> c = Vertex.<String>builder().id("c").build();
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