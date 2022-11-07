package PA9;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Test {
    static List<PA9.Entry<Integer, String>> smallInt = new ArrayList<>();
    static List<PA9.Entry<String, Integer>> smallStr = new ArrayList<>();
    static List<PA9.Entry<Integer, String>> largeInt = new ArrayList<>();
    static List<PA9.Entry<String, Integer>> largeStr = new ArrayList<>();

    private static void readFile(String s) throws IOException {
        Scanner scanner;
        if(s.equals("small1k")) {
            scanner = new Scanner(new File("D:\\CSCI-240\\PA9\\small1k.txt"));
            while (scanner.hasNextInt()) {
                int key = scanner.nextInt();
                String value = String.valueOf(key);
                smallInt.add(new Entry<Integer, String>(key, value));
                smallStr.add(new Entry<String, Integer>(value, key));
            }
        }
        if(s.equals("large100k")) {
            scanner = new Scanner(new File("D:\\CSCI-240\\PA9\\large100k.txt"));
            while (scanner.hasNextInt()) {
                int key = scanner.nextInt();
                String value = String.valueOf(key);
                largeInt.add(new Entry<Integer, String>(key, value));
                largeStr.add(new Entry<String, Integer>(value, key));
            }
        }
    }

    private static class CompbyInteger implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    private static class CompbyString implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return Integer.parseInt(o1) - Integer.parseInt(o2);
        }
    }

    private static void print(List list, int a) {
        QuickSort3 QS = new QuickSort3();
        long start = System.currentTimeMillis();
        if (a == 1)
            QS.sort(list, new CompbyInteger());
        else if (a == 2)
            QS.sort(list, new CompbyString());
        long end = System.currentTimeMillis();
        System.out.println("Number of values sorted: " + list.size());
        System.out.println("Key data type: " +  (a == 1 ? "Integer" : "String"));
        System.out.println("Number of key comparisons: " + QS.comparisons);
        System.out.println("Data moves: " + QS.dataMoves);
        System.out.println("Running time: " + (end - start) + " ms");
        System.out.print("First five entries: ");
        for(int i=0;i<5;i++)
            System.out.print(list.get(i) + " ");
        System.out.println();
        System.out.print("Last five entries: ");
        for(int i=list.size()-5;i<list.size();i++)
            System.out.print(list.get(i) + " ");
        System.out.println();
        System.out.println();
    }


    public static void main(String[] args) throws IOException {
        QuickSort3 QS = new QuickSort3();
        Scanner scanner = new Scanner(System.in);
        String FileName;

        System.out.println("Please enter the 1st file name: ");
        FileName = scanner.nextLine();
        readFile(FileName);
        print(smallInt, 1);
        print(smallStr, 2);

        System.out.println("Please enter the 2nd file name: ");
        FileName = scanner.nextLine();
        readFile(FileName);
        print(largeInt, 1);
        print(largeStr, 2);
    }
}
