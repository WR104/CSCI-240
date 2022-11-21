package PA11;

import net.datastructures.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GraphMethods {
    private static final int MAX = Integer.MAX_VALUE;
    public static <V, E> void DFShelper(Graph<V, E> g, Vertex<V> u,
                                        Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
        known.add(u);                              // u has been discovered
        System.out.print(u.getElement() + " ");
        for (Edge<E> e : g.outgoingEdges(u)) {     // for every outgoing edge from u
            Vertex<V> v = g.opposite(u, e);
            if (!known.contains(v)) {
                forest.put(v, e);                      // e is the tree edge that discovered v
                System.out.print(e.getElement() + " ");
                DFShelper(g, v, known, forest);              // recursively explore from v
            }
        }
    }

    public static <V, E> Map<Vertex<V>, Edge<E>> DFS(Graph<V, E> g, Vertex<V> v) {
        Set<Vertex<V>> known = new HashSet<>();
        Map<Vertex<V>, Edge<E>> forest = new ProbeHashMap<>();
        if (v != null)
            DFShelper(g, v, known, forest);
        for (Vertex<V> u : g.vertices())
            if (!known.contains(u))
                DFShelper(g, u, known, forest);            // (re)start the DFS process at u
        return forest;
    }

    public static <V> int[][] convertToMatrix(AdjacencyListGraph<V, Integer> g){
        int n = g.numVertices();
        int[][] A = new int[n][n];
        java.util.ArrayList<Vertex<V>> vlist = g.getVertices();
        for (int i = 0; i < n; i++) {
            Vertex<V> v = vlist.get(i);
            for (int j = 0; j < n; j++) {
                if (i != j)
                    if (g.getEdge(v, vlist.get(j)) != null)
                        A[i][j] = g.getEdge(v, vlist.get(j)).getElement();
                    else
                        A[i][j] = MAX;

            }
        }
        return A;
    }
     public static void print2dArray(int[][] A){
         for (int[] i : A) {
             for (int j : i)
                 if (j != MAX)
                     System.out.print(j + " ");
                 else
                     System.out.print("∞ ");
             System.out.println();
         }
     }
    public static <V> int[][] transitiveClosure(AdjacencyListGraph<V, Integer> g, int[][] A) {
        int n = g.numVertices();

        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (A[i][k] != MAX && A[k][j] != MAX)
                        A[i][j] = Math.min(A[i][j], A[i][k] + A[k][j]);
        return A;
    }
    public static <V,E> void printUpdatedGraph(AdjacencyListGraph<V,E> g,int[][] A){
        int n = g.numVertices();
        java.util.ArrayList<Vertex<V>> vlist = g.getVertices();
        for(int i=0; i<n;i++){
            V vertex = vlist.get(i).getElement();
            System.out.println("Vertex: " + vertex);
            for(int j=0; j<n; j++){
                if(A[i][j] != 0 && A[i][j] != MAX){
                    V v = vlist.get(j).getElement();
                    System.out.print("(" + v + "," + A[i][j] + ") ");
                }
            }
            System.out.println();
        }
    }

    public static  <V,E> java.util.Map<Vertex<V>, Integer> shortestPath(AdjacencyListGraph<V,Integer> g, Vertex<V> Vertex, Vertex<V> End){
        int n = g.numVertices();
        java.util.ArrayList<Vertex<V>> vlist = g.getVertices();
        int[][] weight = convertToMatrix(g);
        boolean[] visit = new boolean[n]; //if a vertex has been visited
        java.util.Map<Vertex<V>,Integer> map = new HashMap<>();//result
        java.util.ArrayList<Vertex<V>> path = new java.util.ArrayList<>();
        int start = vlist.indexOf(Vertex);
        int end = vlist.indexOf(End);
        for(int i=0; i<n;i++)
            map.put(vlist.get(i),weight[start][i]);

        path.add(Vertex);
        //everytime find the shortest path for one vertex
        for(int i=1;i<n;i++){
            int p = 0;
            int min = MAX;
            //find a not visited vertex that is most close to the start vertex
            for(int j=0;j<n;j++){
                if(j!= start && !visit[j] && map.get(vlist.get(j))<min){
                   min = map.get(vlist.get(j));
                   p = j;
                }
            }
            visit[p] = true;

            for(int j=0;j<n;j++){
                //p can not reach j
                if(j==p || weight[p][j] == MAX){
                    continue;
                }
                Vertex<V> minVertex = null;
                //update the shortest path length
                if(map.get(vlist.get(p)) + weight[p][j] < map.get(vlist.get(j))){
                    map.put(vlist.get(j),map.get(vlist.get(p))+weight[p][j]);
                    minVertex = vlist.get(j);
                }
                //update the vertex into path
                if(i == end - 2) {
                    path.add(vlist.get(p));
                    path.add(vlist.get(j));
                }
            }
        }

        //print the shortest path from given vertex to any vertex
        for(java.util.Map.Entry<Vertex<V>,Integer> entry : map.entrySet())
            System.out.println(entry.getKey().getElement()+ ": " + entry.getValue());

        //print the path from source vertex to a destination vertex
        System.out.println("\nPath from " + Vertex.getElement() + " to " + End.getElement());
        for(net.datastructures.Vertex<V> v : path)
            System.out.print(v.getElement() + " -> ");
        System.out.println(End.getElement());

        return map;
    }

}
