/*************************************************************************
 *
 *  Pace University
 *  Spring 2021
 *  Algorithms and Computing Theory
 *
 *  Course: CS 608
 *  Team members: Aleksandar Kamenev
 *  Other collaborators: NONE
 *  References: GeeksForGeeks - Quick Sort, meadian of 3 #, Thomas-H.-Cormen-Charles-E.-Leiserson-Ronald-L.z-lib.org_1
 *
 *  Assignment: 3
 *  Problem: Implement Insertion and Quick Sorts, analise and make conjectures
 *  Description: The purpose of this homework is to evaluate experimentally
 *  the performance of Quick Sort for di  erent methods of choosing
 *  the pivot, and compare with Insertion Sort, which is simpler but quadratic.
 *
 *  Input: int arrays
 *  Output: sorted int array
 *
 *  Visible data fields:
 *  COPY DECLARATION OF VISIBLE DATA FIELDS HERE
 *
 *  Visible methods:
 *  public void sort (int array[])
 *  public static void printArray(int arr[])
 *  public static void main(String args[])
 *
 *
 *   Remarks
 *   -------
 *   For some reason I am yet to discover my implementation of QuickSort give a StackOverflow error for any
 *   ASCENDING input of more than about 8000. It works with much larger descending or random inputs. I am trying to
 *   resolve this but have not had luck so far.
 *
 *
 *
 *
 *
 *
 *************************************************************************/

package assignment3;

import java.util.Random;

public class InsertionSort {

    public void sort (int array[])
    {
        int n = array.length;
        int newValue;


        for(int current = 1; current < n; current++) {
            newValue = array[current];
            int pointerLeft = current - 1;
            while(pointerLeft >= 0 && array[pointerLeft] > newValue) {
                array[pointerLeft+1] = array[pointerLeft];
                pointerLeft--;
            }
            array[pointerLeft+1] = newValue;
        }
    }

    public static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");

        System.out.println();
    }

    //For test purposes
    public static void main(String args[])
    {
        Random rd = new Random();
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * ((1000 - 0) + 1)) + 0;
        }
        System.out.println("Sorted array: ");
        InsertionSort insert = new InsertionSort();
        insert.sort(arr);

        printArray(arr);
    }
}
