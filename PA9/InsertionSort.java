package PA9;

public class InsertionSort {
    int comparisons = 0;
    int datamoves = 0;

    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            comparisons += 1;   //at least one comparison
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j -= 1;
                comparisons += 1;
                datamoves += 1;
            }
            arr[j + 1] = key;
        }
    }
}
