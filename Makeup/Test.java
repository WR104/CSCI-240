package Makeup;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        int[] small1k = new int[1000];
        int[] large100k = new int[100000];
        long start, end;
        int k = 0;
        long res = 0;
        Scanner scanner = new Scanner(new File("/Volumes/D/CSCI-240/Makeup/small1k.txt"));
        while(scanner.hasNext())
            small1k[k++] = scanner.nextInt();
        k = 0;
        scanner = new Scanner(new File("/Volumes/D/CSCI-240/Makeup/large100k.txt"));
        while(scanner.hasNext())
            large100k[k++] = scanner.nextInt();

        start = System.currentTimeMillis();
        res = FindInversions.method1(small1k);
        end = System.currentTimeMillis();
        System.out.println("Using method 1 on small1k.txt");
        System.out.println("The number of inversions: " + res);
        System.out.println("Run time: " + (end - start) + "ms\n");

        start = System.currentTimeMillis();
        res = FindInversions.method2(small1k);
        end = System.currentTimeMillis();
        System.out.println("Using method 2 on small1k.txt");
        System.out.println("The number of inversions: " + res);
        System.out.println("Run time: " + (end - start) + "ms\n");

        start = System.currentTimeMillis();
        res = FindInversions.method1(large100k);
        end = System.currentTimeMillis();
        System.out.println("Using method 1 on large100k.txt");
        System.out.println("The number of inversions: " + res);
        System.out.println("Run time: " + (end - start) + "ms\n");

        start = System.currentTimeMillis();
        res = FindInversions.method2(large100k);
        end = System.currentTimeMillis();
        System.out.println("Using method 2 on large100k.txt");
        System.out.println("The number of inversions: " + res);
        System.out.println("Run time: " + (end - start) + "ms\n");
    }
}
