package PA12;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import PA11.AdjacencyListGraph;
import net.datastructures.Edge;
import net.datastructures.Entry;
import net.datastructures.HeapAdaptablePriorityQueue;
import net.datastructures.ProbeHashMap;
import net.datastructures.Vertex;

public class Flights {
    AdjacencyListGraph<String, Double> graph;
    ArrayList<Vertex<String>> vertices;
    Map<String, Vertex<String>> map;

    public Flights() throws IOException {
        graph = new AdjacencyListGraph<>(true);
        vertices = new ArrayList<>();
        map = new HashMap<>();

        Scanner scanner = new Scanner(new File("/Volumes/D/CSCI-240/PA12/PA12Flights.txt"));
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            String source = str.substring(0, 3);
            String destination = str.substring(6, 9);
            Double cost = Double.parseDouble(str.split("    ")[1]);
            if (map.get(source) == null)
                map.put(source, graph.insertVertex(source));
            if (map.get(destination) == null)
                map.put(destination, graph.insertVertex(destination));

            graph.insertEdge(map.get(source), map.get(destination), cost);
        }

        menu();
    }

    public void info() {
        System.out.println("0. Display all flights");
        System.out.println("1. Find a cheapest flight from one airport to another airport");
        System.out.println("2. Find a cheapest round trip from one airport to another airport");
        System.out.println("3. Find an order to visit all airports starting from an airport");
        System.out.println("Q. Exit");
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        info();
        String choice = scanner.nextLine();
        while (!"Q".equals(choice)) {
            if ("0".equals(choice))
                print();
            else if ("1".equals(choice) || "2".equals(choice)) {
                System.out.println("Please enter the source airport code and destination airport code");
                String[] str = scanner.nextLine().split(" ");
                Vertex<String> u = map.get(str[0]);
                Vertex<String> v = map.get(str[1]);
                if (u == null || v == null)
                    break;

                printPath(shortestLengths(u, v));
                if("2".equals(choice))
                    printPath(shortestLengths(v, u));
            }
            else if("3".equals(choice)){
                System.out.print("Please enter the starting airport: ");
                String start = scanner.nextLine();
                net.datastructures.Map<Vertex<String>, Edge<Double>> forest = DFS(map.get(start));
                System.out.print(map.get(start).getElement());
                for(Entry<Vertex<String>, Edge<Double>> entry : forest.entrySet())
                    System.out.print(" -> " + entry.getKey().getElement());
                System.out.println();
            }
            System.out.println();
            info();
            choice = scanner.nextLine();
        }
    }
    public void print() {
        System.out.println(graph);
    }

    public void printPath(ArrayList<String> paths){
        System.out.print(paths.get(0));
                for(int i = 1; i< paths.size() - 1; i++){
                    if(i % 2 != 0)
                        System.out.print(" -$" + paths.get(i));
                    else
                        System.out.print(" --> " + paths.get(i));
                }
                System.out.println(" total: $" + paths.get(paths.size()-1));
    }

    public ArrayList<String> shortestLengths(Vertex<String> src, Vertex<String> end) {
        net.datastructures.Map<Vertex<String>, Double> d = new ProbeHashMap<>();
        net.datastructures.Map<Vertex<String>, Double> cloud = new ProbeHashMap<>();
        net.datastructures.Map<Vertex<String>, Vertex<String>> prevs = new ProbeHashMap<>();
        net.datastructures.AdaptablePriorityQueue<Double, Vertex<String>> pq;
        pq = new HeapAdaptablePriorityQueue<>();
        net.datastructures.Map<Vertex<String>, Entry<Double, Vertex<String>>> pqTokens;
        pqTokens = new ProbeHashMap<>();

        for (Vertex<String> v : graph.vertices()) {
            if (v == src)
                d.put(v, 0.0);
            else
                d.put(v, Double.MAX_VALUE);
            pqTokens.put(v, pq.insert(d.get(v), v));
        }
        while (!pq.isEmpty()) {
            Entry<Double, Vertex<String>> entry = pq.removeMin();
            Double key = entry.getKey();
            Vertex<String> u = entry.getValue();
            cloud.put(u, key);
            pqTokens.remove(u);
            for (Edge<Double> e : graph.outgoingEdges(u)) {
                Vertex<String> v = graph.opposite(u, e);
                if (cloud.get(v) == null) {
                    double wgt = e.getElement();
                    if (d.get(u) + wgt < d.get(v)) {
                        d.put(v, d.get(u) + wgt);
                        prevs.put(v, u);
                        pq.replaceKey(pqTokens.get(v), d.get(v));
                    }
                }
            }
        }

        Vertex<String> prevsVertex = prevs.get(end);
        ArrayList<Vertex<String>> reverseVertices = new ArrayList<>();
        reverseVertices.add(end);
        while (prevsVertex != null) {
            reverseVertices.add(prevsVertex);
            prevsVertex = prevs.get(prevsVertex);
        }
        ArrayList<String> paths = new ArrayList<>();
        Vertex<String> curr = reverseVertices.get(reverseVertices.size() - 1);
        paths.add(curr.getElement());
        for (int i = reverseVertices.size() - 2; i >= 0; i--) {
            curr = reverseVertices.get(i);
            double cost = graph.getEdge(reverseVertices.get(i+1), curr).getElement();
            paths.add(String.valueOf(cost));
            paths.add(curr.getElement());
        }
        paths.add(String.valueOf(cloud.get(end)));
        return paths;
    }

    public void DFShelper(Vertex<String> u, Set<Vertex<String>> known, net.datastructures.Map<Vertex<String>, Edge<Double>> forest) {
        known.add(u);                              // u has been discovered
        for (Edge<Double> e : graph.outgoingEdges(u)) {     // for every outgoing edge from u
            Vertex<String> v = graph.opposite(u, e);
            if (!known.contains(v)) {
                forest.put(v, e);                      // e is the tree edge that discovered v
                DFShelper(v, known, forest);              // recursively explore from v
            }
        }
    }

    public net.datastructures.Map<Vertex<String>, Edge<Double>> DFS(Vertex<String> v) {
        Set<Vertex<String>> known = new HashSet<>();
        net.datastructures.Map<Vertex<String>, Edge<Double>> forest = new ProbeHashMap<>();
        if (v != null)
            DFShelper(v, known, forest);
        for (Vertex<String> u : graph.vertices())
            if (!known.contains(u))
                DFShelper(u, known, forest);
        return forest;
    }
}
