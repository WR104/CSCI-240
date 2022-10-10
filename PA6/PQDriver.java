package PA6;

import net.datastructures.HeapPriorityQueue;

import java.io.*;
import java.util.Scanner;

public class PQDriver {
    static int[][] data = new int[4][5];

    public static void main(String[] args) throws IOException {
        HeapPriorityQueue<Integer, Integer> PQ;
        long startTime = System.currentTimeMillis();
        long endTime;

        PQ = new HeapPriorityQueue<>();
        new PQDriver().print("D:\\CSCI-240\\PA6\\small1k.txt", PQ, 0);
        endTime = System.currentTimeMillis();
        System.out.println("Run time: " + (endTime - startTime) + "ms\n");

        startTime = System.currentTimeMillis();
        PQ = new HeapPriorityQueue<>((a, b) -> a > b ? -1 : a.equals(b) ? 0 : 1);
        new PQDriver().print("D:\\CSCI-240\\PA6\\small1k.txt", PQ, 1);
        endTime = System.currentTimeMillis();
        System.out.println("Run time: " + (endTime - startTime) + "ms\n");

        startTime = System.currentTimeMillis();
        PQ = new HeapPriorityQueue<>();
        new PQDriver().print("D:\\CSCI-240\\PA6\\large100k.txt", PQ, 2);
        endTime = System.currentTimeMillis();
        System.out.println("Run time: " + (endTime - startTime) + "ms\n");

        startTime = System.currentTimeMillis();
        PQ = new HeapPriorityQueue<>((a, b) -> a > b ? -1 : a.equals(b) ? 0 : 1);
        new PQDriver().print("D:\\CSCI-240\\PA6\\large100k.txt", PQ, 3);
        endTime = System.currentTimeMillis();
        System.out.println("Run time: " + (endTime - startTime) + "ms\n");

        new PQDriver().write();
    }

    public void print(String path, HeapPriorityQueue<Integer, Integer> PQ, int k) throws IOException {
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext())
            PQ.insert(scanner.nextInt(), null);
        for (int i = 0; i < 5; i++) {
            System.out.print(PQ.min().getKey() + " ");
            data[k][i] = PQ.removeMin().getKey();
        }
        System.out.println();
    }

    public void write() throws IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("D:\\CSCI-240\\PA6\\output.txt"), "utf-8"
        ))) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 5; j++)
                    writer.write(data[i][j] + " ");
                writer.write("\n");
            }
        }
    }

}
