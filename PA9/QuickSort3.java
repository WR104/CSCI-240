package PA9;

import java.util.*;

public class QuickSort3 {
    int comparisons = 0;
    int dataMoves = 0;

    public <K, V> void sort(List<PA9.Entry<K, V>> list, Comparator<K> comp) {
        quicksort(list, comp, 0, list.size() - 1);
    }

    public <K, V> void quicksort(List<PA9.Entry<K, V>> list, Comparator<K> comp, int low, int high) {
        if (low >= high)
            return;

        int i = low, j = high;
        K pivot = list.get(low).getKey();
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
