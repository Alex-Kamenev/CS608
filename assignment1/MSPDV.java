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
 *  within a array of numbers. that has the largest sum. - Divide and Conquer Algorithm
 *
 *  Input: int vector
 *  Output: long sum
 *
 *  Visible data fields:
 *  COPY DECLARATION OF VISIBLE DATA FIELDS HERE
 *
 *  Visible methods:
 *  public long divQ(Vector<Integer> inputV)
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

public class MSPDV {
    public long divQ(Vector<Integer> inputV) {
        int n = inputV.size();
        return maxSubArray(inputV, 0, (n-1));
    }
    private long maxSubArray(Vector<Integer> inputV, int low,int high) {
        if (high == low) {
            return inputV.get(low);
        }
        else {
            int mid = (int) Math.floor((low + high)/2);
            long leftSum =  maxSubArray(inputV, low, mid);
            long rightSum =  maxSubArray(inputV, mid+1, high);
            long crossSumA =  maxCrossSubArray(inputV, low, mid, high);
            return Math.max(leftSum, Math.max(crossSumA, rightSum));
        }
    }
    private long maxCrossSubArray(Vector<Integer> inputV,int low,int mid,int high){
        long leftSum = Long.MIN_VALUE;
        long sum = 0;
        for(int i = mid; i>=low; i--){
            sum = sum + inputV.get(i);
            if(sum>leftSum){
                leftSum = sum;
            }
        }
        long rightSum = Long.MIN_VALUE;
        sum = 0;
        for(int j = mid+1; j<=high; j++){
            sum = sum + inputV.get(j);
            if(sum>rightSum){
                rightSum = sum;
            }
        }
        return leftSum+rightSum;
    }
}
