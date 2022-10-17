package PA7;

import net.datastructures.ChainHashMap;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Exercise {
    static int[] small = new int[1000];
    static int[] large = new int[100000];
    static int[] nums = {13, 21, 5, 37, 15};

    public Exercise() {
    }

    public void Exercise1() throws IOException {
        Map<Integer, String> map = new HashMap<>();
        for (int num : nums)
            map.put(num, String.valueOf(num));
        System.out.println(map.get(10));
        System.out.println(map.get(21));
        map.remove(20);
        map.remove(37);
        System.out.println(map.get(37));

        Scanner scanner;
        long startTime, endTime;
        map = new HashMap<>();
        scanner = new Scanner(new File("D:\\CSCI-240\\PA6\\small1k.txt"));
        int i = 0;
        while (scanner.hasNextInt())
            small[i++] = scanner.nextInt();
        scanner = new Scanner(new File("D:\\CSCI-240\\PA6\\large100k.txt"));
        i = 0;
        while (scanner.hasNextInt())
            large[i++] = scanner.nextInt();

        startTime = System.currentTimeMillis();
        for (int j = 0; j < small.length; j++)
            map.put(small[j], String.valueOf(small[j]));
        endTime = System.currentTimeMillis();
        System.out.println("Running time for small1k = " + (endTime - startTime) + "ms");

        map = new HashMap<>();

        startTime = System.currentTimeMillis();
        for (int j = 0; j < large.length; j++)
            map.put(large[j], String.valueOf(large[j]));
        endTime = System.currentTimeMillis();
        System.out.println("Running time for large100k = " + (endTime - startTime) + "ms");
    }

    public void Exercise2() {
        ChainHashMap<Integer, String> map = new ChainHashMap<>(); //set N = 11
        for (int num : nums)
            map.put(num, String.valueOf(num));
        System.out.println(map.get(10));
        System.out.println(map.get(21));
        map.remove(20);
        map.remove(37);
        System.out.println(map.get(37));

        long startTime, endTime;
        map = new ChainHashMap<>();
        startTime = System.currentTimeMillis();
        for (int j = 0; j < small.length; j++)
            map.put(small[j], String.valueOf(small[j]));
        endTime = System.currentTimeMillis();
        System.out.println("Running time for small1k = " + (endTime - startTime) + "ms");

        map = new ChainHashMap<>();

        startTime = System.currentTimeMillis();
        for (int j = 0; j < large.length; j++)
            map.put(large[j], String.valueOf(large[j]));
        endTime = System.currentTimeMillis();
        System.out.println("Running time for large100k = " + (endTime - startTime) + "ms");
    }

    public int CylclicShiftHashCode(int N, String s) {
        int h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = (h << N) | (h >>> 27);
            h += (int) s.charAt(i);
        }
        return h;
    }

    public void Exercise3() throws IOException {
        System.out.println("Using Cyclic shift hash codes:");
        Scanner scanner = new Scanner(new File("D:\\CSCI-240\\PA7\\usdeclarPC.txt"));
        ArrayList<String> words = new ArrayList<>();
        ArrayList<Integer> hashList;
        Set<Integer> set;
        while (scanner.hasNext()) {              // put every word from file into an arrayList
            String word = scanner.next();
            char c = word.charAt(word.length() - 1);
            if (!Character.isAlphabetic(c))      //delete all the punctuation
                word = word.substring(0, word.length() - 1);
            words.add(word);
        }
        int[] testCSHC = {0, 1, 5, 13};        //cyclic shifts test number
        for (int i = 0; i < testCSHC.length; i++) {
            hashList = new ArrayList<>();
            for (String s : words)
                hashList.add(CylclicShiftHashCode(testCSHC[i], s));      //transfer every word into hash code,the add it to the hashlist
            set = new HashSet<>(hashList);  //remove all the collision
            System.out.println("When the shift is " + testCSHC[i] + ", the amount of collisions are " + (words.size() - set.size()));
            //the difference would be the amount of collisions
        }

    }
}
