package Makeup;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        int[] arr1 = {1, 9, 6, 4, 5};
        int[] arr2 = {1, 4, 5, 6, 9};

        System.out.println(FindInversions.method1(arr2));
    }
}
