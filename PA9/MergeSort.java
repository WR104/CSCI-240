package PA9;

import java.util.Arrays;

public class MergeSort {
    int comparisons = 0;
    int dataMoves = 0;

    public void merge(int[] S1, int[] S2, int[] S) {
        int i = 0, j = 0;
        while (i + j < S.length) {
            comparisons += 1;
            dataMoves += 1;
            if (j == S2.length || (i < S1.length && S1[i] < S2[j]))
                S[i + j] = S1[i++];
            else
                S[i + j] = S2[j++];
        }
    }

    public void sort(int[] S){
        int n = S.length;
        if(n<2) return;
        int mid = n/2;

        dataMoves += n;
        int[] S1 = Arrays.copyOfRange(S,0,mid);
        int[] S2 = Arrays.copyOfRange(S,mid,n);

        sort(S1);
        sort(S2);
        merge(S1,S2,S);
    }
}
