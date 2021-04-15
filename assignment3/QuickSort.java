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
 *  public void sort(int arr[], int low, int high, String type)
 *  public void printArray(int arr[])
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

import java.util.Objects;
import java.util.Random;

public class QuickSort {
    private void random(int arr[],int low,int high)
    {
        Random rand= new Random();
        int a = rand.nextInt(high-low)+low;
        int b = rand.nextInt(high-low)+low;
        int c = rand.nextInt(high-low)+low;

        int pivot = middleOfThree(arr, a, b, c);

        int temp1=arr[pivot];
        arr[pivot]=arr[high];
        arr[high]=temp1;


    }

    private int middleOfThree(int arr[], int a, int b, int c)
    {
        if ((arr[a] < arr[b] && arr[b] < arr[c]) || (arr[c] < arr[b] && arr[b] < arr[a]))
            return b;
        else if ((arr[b] < arr[a] && arr[a] < arr[c]) || (arr[c] < arr[a] && arr[a] < arr[b]))
            return a;
        else
            return c;
    }

    private int partition(int arr[], int low, int high, String type)
    {

        if(Objects.equals(type,"median"))
            random(arr,low,high);
        if(Objects.equals(type, "low")){
            int temp1=arr[low];
            arr[low]=arr[high];
            arr[high]=temp1;
        }
        int pivot = arr[high];


        int i = (low-1);
        for (int j = low; j < high; j++)
        {

            if (arr[j] < pivot)
            {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    public void sort(int arr[], int low, int high, String type)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high, type);

            sort(arr, low, pi-1, type);
            sort(arr, pi+1, high, type);
        }
    }

    public void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

    public static void main(String args[])
    {
        Random rd = new Random();
        int[] arr = new int[10000000];
        for (int i = 0; i < arr.length; i++) {
            arr[arr.length-1 -i] = i;
        }
        int n = arr.length;

        QuickSort quick = new QuickSort();
        quick.sort(arr, 0, n-1, "median");

        System.out.println("Sorted array");
        quick.printArray(arr);
    }
}
