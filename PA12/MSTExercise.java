package PA12;

import PA11.AdjacencyListGraph;
import net.datastructures.*;


public class MSTExercise {
    AdjacencyListGraph<String,Integer> g = new AdjacencyListGraph<>(false);

    public MSTExercise(){
        Vertex<String> A,B,C,D,E;
        A = g.insertVertex("A");
        B = g.insertVertex("B");
        C = g.insertVertex("C");
        D = g.insertVertex("D");
        E = g.insertVertex("E");

        g.insertEdge(A, B, 3);
        g.insertEdge(A, D, 5);
        g.insertEdge(A, E, 5);
        g.insertEdge(B, C, 4);
        g.insertEdge(C, D, 2);
        g.insertEdge(D, E, 5);
        g.insertEdge(C, E, 3);
        PositionalList<Edge<Integer>> spTree = MST();
        int total = 0;
        for(Edge<Integer> e : spTree){
            Vertex[] endpoints = g.endVertices(e);
            System.out.print("(" + endpoints[0].getElement() + ", " + endpoints[1].getElement() + ") ");
            System.out.println(e.getElement());
            total += e.getElement();
        }
        System.out.println("Total Cost: " + total);

    }
    public PositionalList<Edge<Integer>> MST() {
        PositionalList<Edge<Integer>> tree = new LinkedPositionalList<>();
        PriorityQueue<Integer, Edge<Integer>> pq = new HeapPriorityQueue<>();
        Partition<Vertex<String>> forest = new Partition<>();
        Map<Vertex<String>,Position<Vertex<String>>> positions = new ProbeHashMap<>();
    
        for (Vertex<String> v : g.vertices())
          positions.put(v, forest.makeCluster(v));
    
        for (Edge<Integer> e : g.edges())
          pq.insert(e.getElement(), e);
    
        int size = g.numVertices();
        while (tree.size() != size - 1 && !pq.isEmpty()) {
          Entry<Integer, Edge<Integer>> entry = pq.removeMin();
          Edge<Integer> edge = entry.getValue();
          Vertex<String>[] endpoints = g.endVertices(edge);
          Position<Vertex<String>> a = forest.find(positions.get(endpoints[0]));
          Position<Vertex<String>> b = forest.find(positions.get(endpoints[1]));
          if (a != b) {
            tree.addLast(edge);
            forest.union(a,b);
          }
        }
    
        return tree;
      }
}
