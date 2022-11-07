package PA9;

public class QuickSort {
    int comparisons = 0;
    int dataMoves = 0;

    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length-1);
    }

    public void quickSort(int[] arr, int low, int high) {
        if (low >= high)
            return;

        int i = low, j = high, pivot = arr[low];
        while (i < j) {
            while (arr[j] >= pivot && i < j) {
                j--;
                comparisons += 1;
            }
            while (arr[i] <= pivot && i < j) {
                i++;
                comparisons += 1;
            }
            swap(arr, i, j);
            dataMoves += 1;
        }
        swap(arr, low, j);
        dataMoves += 1;
        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, high);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
