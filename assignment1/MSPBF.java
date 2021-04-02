/*************************************************************************
 *
 *  Pace University
 *  Spring 2021
 *  Algorithms and Computing Theory
 *
 *  Course: CS 608
 *  Team members: Aleksandar Kamenev
 *  Other collaborators: NONE
 *  References: NONE
 *
 *  Assignment: 1
 *  Problem: Max sub-array problem
 *  Description: The maximum sub-array problem is the task of finding the contiguous sub-array,
 *  within a array of numbers. that has the largest sum. - Brute Force Algorithm
 *
 *  Input: int vector
 *  Output: long sum
 *
 *  Visible data fields:
 *  COPY DECLARATION OF VISIBLE DATA FIELDS HERE
 *
 *  Visible methods:
 *  public long bruteForce(Vector<Integer> inputV)
 *
 *
 *   Remarks
 *   -------
 *
 *   PUT ALL NON-CODING ANSWERS HERE
 *
 *
 *************************************************************************/

package assignment1;

import java.util.Vector;

public class MSPBF {
    public long bruteForce(Vector<Integer> inputV){
        int n = inputV.size();
        long sumMax = Long.MIN_VALUE;

        for (int i = 0; i<n ; i++)
        {
            long sum = 0;
            for (int j = i; j<n ; j++)
            {
                sum = sum + inputV.get(j);
                sumMax = Math.max(sumMax, sum);
            }
        }

        return sumMax;
    }
}
