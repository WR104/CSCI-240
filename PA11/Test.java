package PA11;

import net.datastructures.Edge;
import net.datastructures.Vertex;

import java.util.Map;

public class Test {
    public static void main(String[] args){
        Vertex<String> A,B,C,D,E;
        Edge<Integer> e;
        AdjacencyListGraph<String,Integer> graph = new AdjacencyListGraph<>(true);
        A = graph.insertVertex("A");
        B = graph.insertVertex("B");
        C = graph.insertVertex("C");
        D = graph.insertVertex("D");
        E = graph.insertVertex("E");
        graph.insertEdge(B,A,3);
        graph.insertEdge(B,C,4);
        graph.insertEdge(A,E,10);
        graph.insertEdge(A,D,5);
        graph.insertEdge(C,D,2);
        graph.insertEdge(D,E,3);
        graph.insertEdge(E,C,6);

        Map<Vertex<String>,Integer> map = GraphMethods.shortestPath(graph,B,E);

    }
}
