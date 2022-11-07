package PA9;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HeapSort {

    public int comparisons = 0;
    public int dataMoves = 0;
    public <K,V> void sort(List<PA9.Entry<K,V>> list, Comparator<K> comp){
        int n = list.size();
        for(int i=n/2-1;i>=0;i--)
            heapify(list,n,i,comp);

        for(int i=n-1;i>0;i--){
            Collections.swap(list,0,i);
            dataMoves += 1;
            heapify(list,i,0,comp);
        }
    }

    public <K,V> void heapify(List<PA9.Entry<K,V>> list, int n, int i, Comparator<K> comp){
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i +2;

        if(l < n && comp.compare(list.get(l).getKey(),list.get(largest).getKey()) > 0)
            largest = l;

        if(r < n && comp.compare(list.get(r).getKey(),list.get(largest).getKey()) > 0)
            largest = r;

        comparisons += 2;
        if(largest != i){
            Collections.swap(list,i,largest);
            dataMoves += 1;
            heapify(list,n,largest,comp);
        }
    }
}
