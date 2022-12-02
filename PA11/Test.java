package PA11;

import net.datastructures.Edge;
import net.datastructures.Vertex;

public class Test {
    public static void main(String[] args){
        AdjacencyMatrixGraph<String,Integer> graph = new AdjacencyMatrixGraph<>();
        Vertex<String> A,B,C,D;
        Edge<Integer> AB,AC,AD,BC,BD,CD;
        //Test Case 1
        A = graph.insertVertex("A");
        B = graph.insertVertex("B");
        AB = graph.insertEdge(A,B,100);
        C = graph.insertVertex("C");
        AC = graph.insertEdge(A,C,200);
        System.out.println(graph);

        //Test Case 2
        graph = new AdjacencyMatrixGraph<>();
        A = graph.insertVertex("A");
        B = graph.insertVertex("B");
        C = graph.insertVertex("C");
        D = graph.insertVertex("D");
        AB = graph.insertEdge(A,B,100);
        AD = graph.insertEdge(A,D,100);
        BC = graph.insertEdge(B,C,100);
        CD = graph.insertEdge(C,D,100);
        AC = graph.insertEdge(A,C,200);
        BD = graph.insertEdge(B,D,200);
        System.out.println(graph);
    }
}
