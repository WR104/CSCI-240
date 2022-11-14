package PA10;

import java.util.ArrayList;

public class IntSet {
    final int N = 1000;
    int[] set = new int[N];

    public IntSet() {
    }

    public IntSet(int[] arr) {
        for (int i : arr) {
            if (i < N)
                set[i] = 1;
        }
    }

    public void insert(int i) {
        if (i < N)
            set[i] = 1;
    }

    public void remove(int i) {
        if (i < N)
            set[i] = 1;
    }

    public boolean find(int i) {
        if (i < N)
            return set[i] == 1;
        return false;
    }

    public void print() {
        java.util.ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0; i<N; i++){
            if(set[i] == 1)
                arr.add(i);
        }
        System.out.println(arr.toString());
    }

    public static IntSet setUnion(IntSet set1, IntSet set2) {
        IntSet set3 = new IntSet();
        for (int i = 0; i < set3.N; i++) {
            if (set1.find(i) || set2.find(i))
                set3.insert(i);
        }
        return set3;
    }

    public static IntSet setInter(IntSet set1, IntSet set2) {
        IntSet set3 = new IntSet();
        for (int i = 0; i < set3.N; i++) {
            if (set1.find(i) && set2.find(i))
                set3.insert(i);
        }
        return set3;
    }

    public static IntSet setDiff(IntSet set1, IntSet set2) {
        IntSet set3 = new IntSet();
        for (int i = 0; i < set3.N; i++) {
            if (set1.find(i) && !set2.find(i))
                set3.insert(i);
        }
        return set3;
    }
}
