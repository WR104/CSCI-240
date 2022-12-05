package Makeup;

import java.util.Arrays;

public class FindInversions {

    public static long method1 (int[] arr){
        long count = 0;
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = i + 1; j < arr.length; j++){
                if(arr[i] > arr[j])
                    count += 1;
            }
        }
        return count;
    }

    public static long method2(int[] arr) {
        if (arr.length < 2)
            return 0;

        int m = (arr.length + 1) / 2;
        int left[] = Arrays.copyOfRange(arr, 0, m);
        int right[] = Arrays.copyOfRange(arr, m, arr.length);

        return method2(left) + method2(right) + merge(arr, left, right);
    }

    private static long merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0;
        long count = 0;
        while (i < left.length || j < right.length) {
            if (i == left.length) {
                arr[i+j] = right[j];
                j += 1;
            } else if (j == right.length) {
                arr[i+j] = left[i];
                i += 1;
            } else if (left[i] <= right[j]) {
                arr[i+j] = left[i];
                i += 1;
            } else {
                arr[i+j] = right[j];
                count += left.length-i;
                j += 1;
            }
        }
        return count;
    }

}
