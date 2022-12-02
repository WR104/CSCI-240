package PA11;

import net.datastructures.Edge;
import net.datastructures.Graph;
import net.datastructures.Vertex;

import java.util.ArrayList;

public class AdjacencyMatrixGraph<V,E> implements Graph<V,E> {
    int N = 100;
    private ArrayList<Vertex<V>> vertices = new ArrayList<>();
    private ArrayList<Edge<E>> edges = new ArrayList<>();
    private Edge<E>[][] matrix;
    private int numVertices = 0;
    private int numEdges = 0;

    public AdjacencyMatrixGraph(){
        matrix = (Edge<E>[][]) new Edge[N][N];
    }
    public AdjacencyMatrixGraph(int n){
        matrix = (Edge<E>[][]) new Edge[n][n];
        N = n;
    }
    public int numVertices() {
        return numVertices;
    }

    public int numEdges() {
        return numEdges;
    }

    public Iterable<Vertex<V>> vertices() {
        return vertices;
    }

    public Iterable<Edge<E>> edges() {
        return edges;
    }

    public int outDegree(Vertex<V> v) throws IllegalArgumentException {
        int index = vertices.indexOf(v);
        int count = 0;
        if(index == -1)
            return -1;

        for(int i=0;i<numVertices;i++)
            if(matrix[index][i] != null)
                count += 1;
        return count;
    }

    public int inDegree(Vertex<V> v) throws IllegalArgumentException {
        return outDegree(v);
    }

    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) throws IllegalArgumentException {
        int index = vertices.indexOf(v);
        if(index == -1)
            return null;

        ArrayList<Edge<E>> count = new ArrayList<>();
        for(int i=0;i<numVertices;i++)
            if(matrix[index][i] != null)
                count.add(matrix[index][i]);
        return count;
    }

    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) throws IllegalArgumentException {
        return outgoingEdges(v);
    }

    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) throws IllegalArgumentException {
        int indexu = vertices.indexOf(u);
        int indexv = vertices.indexOf(v);
        if(indexu < 0 || indexv < 0)
            return null;

        return matrix[indexu][indexv];
    }

    public Vertex<V>[] endVertices(Edge<E> e) throws IllegalArgumentException {
        for(int i=0;i<numVertices;i++){
            for(int j=0;j<numVertices;j++)
                if(matrix[i][j] == e)
                    return  (Vertex<V>[]) new Vertex[]{vertices.get(i),vertices.get(j)};

        }
        return null;
    }

    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException {
        int index = vertices.indexOf(v);
        if(index < 0)
            return null;
        for(int i=0;i<numVertices;i++)
            if(matrix[index][i] == e)
                return vertices.get(i);

        return null;
    }

    public Vertex<V> insertVertex(V element) {
        if(numVertices >= N)
            throw new IllegalArgumentException("Maximum limit exceeded");
        InnerVertex<V> v = new InnerVertex<>(element);
        vertices.add(v);
        numVertices += 1;
        return v;
    }

    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException {
        int iu = vertices.indexOf(u);
        int iv = vertices.indexOf(v);
        if(iu < 0 || iv < 0)
            return null;
        InnerEdge<E> e = new InnerEdge<>(element);
        matrix[iu][iv] = e;
        matrix[iv][iu] = e;//if undirected
        return e;
    }

    public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
        int index = vertices.indexOf(v);
        if(index < 0)
            return;
        numEdges -= outDegree(v);
        if(vertices.get(index + 1) != null){//adjust the matrix
            for(int col = index; col < numVertices - 1; col++)
                for(int row =0; row < numVertices; row++) {
                    if(matrix[col][row] != null)
                        removeEdge(matrix[row][col]);
                    matrix[col][row] = matrix[col + 1][row];
                }

            for(int row = index; row < numVertices - 1; row++)
                for(int col = 0; col < numVertices; col++) {
                    if(matrix[col][row] != null)
                        removeEdge(matrix[row][col]);
                    matrix[col][row] = matrix[col][row + 1];
                }
        }
        numVertices -= 1;
        vertices.remove(v);
    }

    public void removeEdge(Edge<E> e) throws IllegalArgumentException {
        Vertex<V>[] vs = endVertices(e);
        if(vs.length != 2)
            throw new IllegalArgumentException("Edge is invalid");
        int i = vertices.indexOf(vs[0]);
        int j = vertices.indexOf(vs[1]);
        matrix[i][j] = null;
    }
    private class InnerVertex<V> implements Vertex<V>{
        V element;
        public InnerVertex(V e){
            element = e;
        }
        public V getElement() {
            return element;
        }
    }

    private class InnerEdge<E> implements Edge<E>{
        E element;
        public InnerEdge(E e){
            element = e;
        }
        public E getElement() {
            return element;
        }
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<numVertices;i++){
            Vertex<V> v = vertices.get(i);
            sb.append("Vertex " + v.getElement() + "\n");
            sb.append(outDegree(v) + " adjacencies: ");
            for(int j=0;j<numVertices;j++){
                if(matrix[i][j] != null) {
                    Edge<E> e = matrix[i][j];
                    sb.append(String.format(" (%s, %s)", opposite(v, e).getElement(), e.getElement()));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
