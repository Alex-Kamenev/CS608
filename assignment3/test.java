package assignment3;

import java.util.Arrays;
class test {
    //selects last element as pivot, pi using which array is partitioned. 
    int partition(int intArray[], int low, int high) {
        int pi = intArray[high];
        int i = (low-1); // smaller element index   
        for (int j=low; j<high; j++) {
            // check if current element is less than or equal to pi 
            if (intArray[j] <= pi) {
                i++;
                // swap intArray[i] and intArray[j] 
                int temp = intArray[i];
                intArray[i] = intArray[j];
                intArray[j] = temp;
            }
        }

        // swap intArray[i+1] and intArray[high] (or pi) 
        int temp = intArray[i+1];
        intArray[i+1] = intArray[high];
        intArray[high] = temp;

        return i+1;
    }


    //routine to sort the array partitions recursively
    void quick_sort(int intArray[], int low, int high) {
        if (low < high) {
            //partition the array around pi=>partitioning index and return pi
            int pi = partition(intArray, low, high);

            // sort each partition recursively 
            quick_sort(intArray, low, pi-1);
            quick_sort(intArray, pi+1, high);
        }
    }
}

class Main{
    public static void main(String args[]) {
        //initialize a numeric array, intArray
        int[] intArray = new int[100000];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = i;
        }
        int n = intArray.length;
        //print the original array
        System.out.println("Original Array: " + Arrays.toString(intArray));
        //call quick_sort routine using QuickSort object
        test obj = new test();
        obj.quick_sort(intArray, 0, n-1);
        //print the sorted array
        System.out.println("\nSorted Array: " + Arrays.toString(intArray));
    }
}