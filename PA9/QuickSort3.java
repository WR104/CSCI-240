package PA9;

import java.util.*;

public class QuickSort3 {
    int comparisons = 0;
    int dataMoves = 0;

    public <K, V> void sort(List<PA9.Entry<K, V>> list, Comparator<K> comp) {
        quicksort(list, comp, 0, list.size() - 1);
    }

    public <K> K medianPivot(K a, K b, K c, Comparator<K> comp){
        List<K> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        Collections.sort(list,comp);
        return list.get(1);
    }
    public <K, V> void quicksort(List<PA9.Entry<K, V>> list, Comparator<K> comp, int low, int high) {
        if (low >= high)
            return;

        int i = low, j = high;
        K pivot;
        if(list.size() <= 3)
            pivot = list.get(low).getKey();
        else {
            pivot = medianPivot(list.get(low).getKey(), list.get((low + high) / 2).getKey(), list.get(high).getKey(), comp);
            if(pivot.getClass() == String.class) {
                if (pivot.equals(list.get((low + high) / 2).getKey()))
                    Collections.swap(list, low, (low + high) / 2);
                else if (pivot.equals(list.get(high).getKey()))
                    Collections.swap(list, low, high);
            }
            if(pivot.getClass() == Integer.class) {
                if (pivot == list.get((low + high) / 2).getKey())
                    Collections.swap(list, low, (low + high) / 2);
                else if (pivot == list.get(high).getKey())
                    Collections.swap(list, low, high);
            }
        }

        while (i < j) {
            while (i < j && comp.compare(list.get(j).getKey(), pivot) > 0) {
                j--;
                comparisons += 1;
            }
            while (i < j && comp.compare(list.get(i).getKey(), pivot) <= 0) {
                i++;
                comparisons += 1;
            }
            Collections.swap(list, i, j);
            dataMoves += 1;
        }
        Collections.swap(list, low, j);
        dataMoves += 1;
        quicksort(list, comp, low, j - 1);
        quicksort(list, comp, j + 1, high);

    }

    public <K, V> void print(List<PA9.Entry<K, V>> list) {
        for (PA9.Entry entry : list) {
            System.out.print(entry.getKey() + " ");
        }
        System.out.println();
    }

}
