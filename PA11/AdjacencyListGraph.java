package PA11;

import net.datastructures.Edge;
import net.datastructures.Graph;
import net.datastructures.Vertex;

import java.util.ArrayList;
import java.util.List;

//undirected graph using Adjacency List(string for vertex and int for edge)
public class AdjacencyListGraph<V, E> implements Graph<V, E> {
    private boolean isDirected;
    private ArrayList<Vertex<V>> vertices = new ArrayList<>();
    private ArrayList<Edge<E>> edges = new ArrayList<>();

    //private ArrayList<ArrayList<Pair<V, E>>> list = new ArrayList<ArrayList<Pair<V, E>>>();

    public AdjacencyListGraph(boolean directed) { isDirected = directed; }
    public int numVertices() {
        return vertices.size();
    }

    public int numEdges() {
        return edges.size();
    }

    public ArrayList<Vertex<V>> getVertices() { return vertices; }
    public ArrayList<Edge<E>> getEdges() { return edges; }
    public Iterable<Vertex<V>> vertices() {
        return vertices;
    }

    public Iterable<Edge<E>> edges() {
        return edges;
    }

    public int outDegree(Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> vert = validate(v);
        return vert.outgoing.size();
    }

    public int inDegree(Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> vert = validate(v);
        return vert.incoming.size();
    }

    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> vert = validate(v);
        return vert.outgoing;
    }

    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> vert = validate(v);
        return vert.incoming;
    }

    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> vertex = validate(u);
        return vertex.getEdge(v);
    }

    public Vertex<V>[] endVertices(Edge<E> e) throws IllegalArgumentException {
        InnerEdge<E> edge = validate(e);
        return edge.getEndpoints();
    }

    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException {
        InnerEdge<E> edge = validate(e);
        Vertex<V>[] endpoints = edge.getEndpoints();
        if (endpoints[0] == v)
            return endpoints[1];
        else if (endpoints[1] == v)
            return endpoints[0];
        else
            throw new IllegalArgumentException("v is not incident to this edge");
    }

    public Vertex<V> insertVertex(V element) {
        InnerVertex<V> v = new InnerVertex<>(element, isDirected);
        vertices.add(v);
        v.setPosition(vertices.size());
        return v;
    }

    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException {
        if (getEdge(u, v) == null) {
            InnerEdge<E> e = new InnerEdge<>(u, v, element);
            edges.add(e);
            e.setPosition(edges.size());
            InnerVertex<V> origin = validate(u);
            InnerVertex<V> dest = validate(v);
            origin.outgoing.add(e);
            dest.incoming.add(e);
            return e;
        } else
            throw new IllegalArgumentException("Edge from u to v exists");
    }

    public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> target = validate(v);
        for(Vertex<V> vert : vertices){
            InnerVertex<V> vertex = validate(vert);
            vertex.outgoing.removeIf(e -> opposite(vert, e) == v);
            vertex.incoming.removeIf(e -> opposite(vert, e) == v);
        }
        vertices.remove(v);
        target.setPosition(-1);
    }

    public void removeEdge(Edge<E> e) throws IllegalArgumentException {
        InnerEdge<E> edge = validate(e);
        Vertex<V>[] verts = edge.getEndpoints();
        InnerVertex<V> v0 = validate(verts[0]);
        InnerVertex<V> v1 = validate(verts[1]);
        v0.outgoing.remove(e);
        v0.incoming.remove(e);
        v1.outgoing.remove(e);
        v1.incoming.remove(e);
        edges.remove(e);
        edge.setPosition(-1);
    }

    private InnerVertex<V> validate(Vertex<V> v) {
        if (!(v instanceof InnerVertex)) throw new IllegalArgumentException("Invalid vertex");
        InnerVertex<V> vert = (InnerVertex<V>) v;
        if (!vert.validate(this)) throw new IllegalArgumentException("Invalid vertex");
        return vert;
    }

    private InnerEdge<E> validate(Edge<E> e) {
        if (!(e instanceof InnerEdge)) throw new IllegalArgumentException("Invalid edge");
        InnerEdge<E> edge = (InnerEdge<E>) e;
        if (!edge.validate(this)) throw new IllegalArgumentException("Invalid edge");
        return edge;
    }

    private class InnerVertex<V> implements Vertex<V> {
        private V element;
        private int position;   //position in the list
        public List<Edge<E>> outgoing, incoming;

        public InnerVertex(V e, boolean graphIsDirected) {
            element = e;
            outgoing = new ArrayList<>();
            if(graphIsDirected)
                incoming = new ArrayList<>();
            else
                incoming = outgoing;
        }

        public boolean validate(Graph<V, E> graph) {
            return (AdjacencyListGraph.this == graph && position != -1);
        }

        public V getElement() {
            return element;
        }

        public void setPosition(int p) {
            position = p;
        }

        public int getPosition() {
            return position;
        }

        public Edge<E> getEdge(Vertex<V> v) {
            for (Edge<E> e : outgoing) {
                InnerEdge<E> edge = AdjacencyListGraph.this.validate(e);
                if (edge.getEndpoints()[1] == v)
                    return e;
            }
            return null;
        }
    }

    private class InnerEdge<E> implements Edge<E> {
        private E element;
        private int position;
        private Vertex<V>[] endpoints;

        public InnerEdge(Vertex<V> u, Vertex<V> v, E e) {
            element = e;
            endpoints = (Vertex<V>[]) new Vertex[]{u, v}; //array of length 2
        }

        public E getElement() { return element; }

        public Vertex<V>[] getEndpoints() { return endpoints; }

        public boolean validate(Graph<V, E> graph) {
            return AdjacencyListGraph.this == graph && position != -1;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int p) {
            position = p;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex<V> v : vertices) {
            sb.append("Vertex " + v.getElement() + "\n");
            sb.append(" " + outDegree(v) + " adjacencies: ");
            for (Edge<E> e : outgoingEdges(v))
                sb.append(String.format(" (%s, %s)", opposite(v, e).getElement(), e.getElement()));
            sb.append("\n");
        }
        return sb.toString();
    }
}
