package com.read.test.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by yfwangrui on 2015/9/4.
 * <p>
 * quick sort
 */
public class QuickSort {

    @Test
    public void test() {
        int[] a = {3,1,5,7,2,4,9,6,10,8};
        quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private int partition(int[] arr, int low, int high) {
        int middle = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= middle)
                high--;
            swap(arr, low, high);
            while (low < high && arr[low] <= middle)
                low++;
            swap(arr, low, high);
        }
        return low;
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int middle = partition(arr, low, high);
            quickSort(arr, low, middle - 1);
            quickSort(arr, middle + 1, high);
        }
    }
}
