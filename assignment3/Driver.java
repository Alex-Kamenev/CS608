/*************************************************************************
 *
 *  Pace University
 *  Spring 2021
 *  Algorithms and Computing Theory
 *
 *  Course: CS 608
 *  Team members: Aleksandar Kamenev
 *  Other collaborators: NONE
 *  References: GeeksForGeeks - Quick Sort, median of 3 #s, Thomas-H.-Cormen-Charles-E.-Leiserson-Ronald-L.z-lib.org_1
 *
 *  Assignment: 3
 *  Problem: Implement Insertion and Quick Sorts, analise and make conjectures
 *  Description: The purpose of this homework is to evaluate experimentally
 *  the performance of Quick Sort for di  erent methods of choosing
 *  the pivot, and compare with Insertion Sort, which is simpler but quadratic.
 *
 *  Input: arrays of the form random, sorted increasing and sorted decreasing
 *  Output: running times for Insertion ans Quick sort on each of the inputs
 *
 *  Visible data fields:
 *  COPY DECLARATION OF VISIBLE DATA FIELDS HERE
 *
 *  Visible methods:
 *  public static void main (String[] args)
 *
 *
 *   Remarks
 *   -------
 *   For some reason I am yet to discover my implementation of QuickSort give a StackOverflow error for any
 *   ASCENDING input of more than about 8000. It works with much larger descending or random inputs. I am trying to
 *   resolve this but have not had luck so far.
 *
 *   n = 1000000 was not used and instead n = 100000 was used as compilation times were to high.
 *
 *
 *   PUT ALL NON-CODING ANSWERS HERE
 *
 * 3)
 *
 * following tables with inputs arrays' of size n=8000 for of columns
 *
 *   ---------------------------------------------------------------------------------------------------------------------
 * Version          increasing order                    decreasing order                    random
 * --------------------------------------------------------------------------------------------------------------------
 *    1                    339900                            14679200                      7225300
 *   2A                  11533100                            28180700                       418100
 *   2B                   2530700                             1174200                      1364600
 *
 *
 *  ---------------------------------------------------------------------------------------------------------------------
 * Version          increasing order                    decreasing order                    random
 * --------------------------------------------------------------------------------------------------------------------
 *    1                    293200                            15361900                      7517900
 *   2A                  13065900                            31029400                       430800
 *   2B                   3159800                             1314600                      1406500
 *
 * following tables with inputs arrays' of size n=8000 for increasing order column and n=100000 for all other columns
 * ---------------------------------------------------------------------------------------------------------------------
 * Version          increasing order                    decreasing order                    random
 * --------------------------------------------------------------------------------------------------------------------
 *    1                    262700                          1358191700                    679198300
 *   2A                  11540600                            28784000                       417500
 *   2B                   2520400                             1175900                      1385600
 *
 * ---------------------------------------------------------------------------------------------------------------------
 * Version          increasing order                    decreasing order                    random
 * --------------------------------------------------------------------------------------------------------------------
 *    1                    286300                          1354658000                    671857700
 *   2A                  11437200                            27887400                       424800
 *   2B                   2547300                             1208900                      1417800
 *
 * 4)
 * ---------------------------------------------------------------------------------------------------------------------
 * Version          increasing order                    decreasing order                    random
 * --------------------------------------------------------------------------------------------------------------------
 *    1                   O(n)                                O(n^2)                        O(n^2)
 *   2A                   O(n^2)                              O(n^2)                        O(nlogn)
 *   2B                  O(nlogn)                            O(nlogn)                       O(nlogn)
 *
 * 5)   Output is inline with both prediction prior to starting assignment and conjecture based on comparing
 *   results from running algorithms on sample inputs in 3) and the run times in big O notation in 4).
 *   As expected insertion sort has the best time when input is sorted as no swaps are need as only a linear
 *   number of comparisons is executed and the worst when input is reverse sorted as max (n^2) amount of work is done.
 *   This is clear when we look at the first set of tables in 3) for sorted input the time taken is 2
 *   orders of magnitude lower than the time taken for reverse sorted. For random inputs we observe the average case
 *   and while there is a favorable difference compared to worst case seen in reverse sorted input cases in the for of
 *   one order of magnitude the big O run time still is o(n^2).
 *      For quick sort with pivot first or last position we observe the worst case of the algorithm as the array gets
 *   partitioned in completely unbalanced sections. All elements fall on one side of the partition. This gives us
 *   O(n^2) for both sorted and reverse sorted inputs when using first or last element of the array as pivot. We can
 *   observe this in the 3 orders of magnitude deference between the (ascending and defending) sorted inputs in 2A and
 *   the random input in 2A. Here the random input gives us the expected O(nlogn) running time. Although we may get unlucky
 *   and have the largest or smallest element of the array in the first position with a large enough input the chances are
 *   negligible. Still they do exist and so there is the need for a bettere way of selecting pivot.
 *      One such way is selecting median out of 3 random elements in the array. This all but grantees a favorable pivot
 *   and so it warranties a running time of O(nlogn). This can be observed in the lack of deference in the orders of magnitude
 *   of all columns of 2B.
 *
 *
 *
 *
 *
 *
 *************************************************************************/
package assignment3;

import java.util.Random;

public class Driver {
    public static void main( String [ ] args ){
        Random rd = new Random();
        int[] randomArr1 = new int[8000];
        for (int i = 0; i < randomArr1.length; i++) {
            randomArr1[i] = (int)(Math.random() * ((16000 - 0) + 1)) + 0;
        }
        int randomArr2A[] = new int[randomArr1.length];
        for (int i = 0; i < randomArr1.length; i++)
            randomArr2A[i] = randomArr1[i];
        int randomArr2B[] = new int[randomArr1.length];
        for (int i = 0; i < randomArr1.length; i++)
            randomArr2B[i] = randomArr1[i];

        int[] increasingArr1 = new int[8000];
        for (int i = 0; i < increasingArr1.length; i++) {
            increasingArr1[i] = i;
        }
        int increasingArr2A[] = new int[increasingArr1.length];
        for (int i = 0; i < increasingArr1.length; i++)
            increasingArr2A[i] = increasingArr1[i];
        int increasingArr2B[] = new int[increasingArr1.length];
        for (int i = 0; i < increasingArr1.length; i++)
            increasingArr2B[i] = increasingArr1[i];

        int[] decreasingArr1 = new int[8000];
        for (int i = decreasingArr1.length-1; i >= 0; i--) {
            decreasingArr1[decreasingArr1.length-1 - i] = i;
        }
        int decreasingArr2A[] = new int[decreasingArr1.length];
        for (int i = 0; i < decreasingArr1.length; i++)
            decreasingArr2A[i] = decreasingArr1[i];
        int decreasingArr2B[] = new int[decreasingArr1.length];
        for (int i = 0; i < decreasingArr1.length; i++)
            decreasingArr2B[i] = decreasingArr1[i];


        InsertionSort insert = new InsertionSort();
        QuickSort quick = new QuickSort();

        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%1s %25s %35s %25s", "Version", "increasing order", "decreasing order", "random");
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        long startTimeInsInc = System.nanoTime();
        insert.sort(increasingArr1);
        long endTimeInsInc = System.nanoTime() - startTimeInsInc;

        long startTimeInsDec = System.nanoTime();
        insert.sort(decreasingArr1);
        long endTimeInsDec = System.nanoTime() - startTimeInsDec;

        long startInsRand = System.nanoTime();
        insert.sort(randomArr1);
        long endInsRand = System.nanoTime() - startInsRand;

        System.out.printf("%4s %25s %35s %28s", "1", endTimeInsInc, endTimeInsDec, endInsRand);
        System.out.println();

        long startTimeQuickInc = System.nanoTime();
        quick.sort(increasingArr2A,0, increasingArr2A.length-1, "low");
        long endTimeQuickInc = System.nanoTime() - startTimeQuickInc;

        long startTimeQuickDec = System.nanoTime();
        quick.sort(decreasingArr2A,0, increasingArr2A.length-1, "low");
        long endTimeQuickDec = System.nanoTime() - startTimeQuickDec;

        long startQuickRand = System.nanoTime();
        quick.sort(randomArr2A,0, increasingArr2A.length-1, "low");
        long endQuickRand = System.nanoTime() - startQuickRand;

        System.out.printf("%4s %25s %35s %28s", "2A", endTimeQuickInc, endTimeQuickDec, endQuickRand);
        System.out.println();

        long startTimeQuick3Inc = System.nanoTime();
        quick.sort(increasingArr2B,0, increasingArr2B.length-1, "median");
        long endTimeQuick3Inc = System.nanoTime() - startTimeQuick3Inc;

        long startTimeQuick3Dec = System.nanoTime();
        quick.sort(decreasingArr2B,0, increasingArr2B.length-1, "median");
        long endTimeQuick3Dec = System.nanoTime() - startTimeQuick3Dec;

        long startQuick3Rand = System.nanoTime();
        quick.sort(randomArr2B,0, increasingArr2B.length-1, "median");
        long endQuick3Rand = System.nanoTime() - startQuick3Rand;

        System.out.printf("%4s %25s %35s %28s", "2B", endTimeQuick3Inc, endTimeQuick3Dec, endQuick3Rand);
        System.out.println();
    }
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");

        System.out.println();
    }
}
