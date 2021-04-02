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
 *  Assignment: 2
 *  Problem: Binary Search Tree Skewed and balanced Running Time Comparison
 *  Description: Using the provided class for BST write a program that create two instances
 *  of a BST. One completely skewed and one balanced(well random but with high probability of being
 *  mostly balanced), measure running times and draw conclusions.
 *
 *  Input: size of BSTs, search params.
 *  Output: running times for searches(nano seconds) and results(found or not).
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
 *
 *   PUT ALL NON-CODING ANSWERS HERE
 *   1.
 *   a) For a skewed BST the running time would be: O(logn) < running time <= O(n)
 *      in the worst case it would be be O(n). This depends on "how" skewed the BST is, but
 *      in general the answer to this question is O(n).
 *      For a balanced BST the running time would be: O(logn)
 *      In general the running time of a BST varies from O(n) in the worst to O(logn) in the best case.
 *      On average in will be O(logn).
 *      The description of balanced and skewed imply that the running time will be closer
 *      or equal to O(logn) or O(n) respectively.
 *      BSTs examples:
 *               Balanced                       Skewed
 *                  O                              O
 *                /   \                           / \
 *               O     O                         O   O
 *              / \   / \                           / \
 *             O  O  O   O                         0   0
 *                                                  \
 *                                                   O
 *                                                    \
 *                                                     O
 *   b)
 *   The provided class has the following visible methods.
 *    void insert( x )       --> Insert x
 *    void remove( x )       --> Remove x
 *    boolean contains( x )  --> Return true if x is present
 *    Comparable findMin( )  --> Return smallest item
 *    Comparable findMax( )  --> Return largest item
 *    boolean isEmpty( )     --> Return true if empty; else false
 *    void makeEmpty( )      --> Remove all items
 *    void printTree( )      --> Print tree in sorted order
 *
 *   Of those when it comes to searching we will be concerned with contains, findMin and FindMax.
 *   Contains is the method that handles searches with input so we will consider this one.
 *   Contains calls an internal method which will look at the root and if there is no root(empty tree)
 *   it will return false a.k.a the element we are looking for is not found. This is the base case and is
 *   O(1). If there is a root (tree is not empty) it will look at the element stored in the node and if the
 *   value we are looking for is bigger it will recursively call the contains method on the nodes right child.
 *   If smaller it will go to the left child. Else the element stored in the node must be the equal top the value we
 *   are looking for so return true.
 *   All this simple means we will have one comparison per level of the tree. There for the running time of
 *   contains method is O(1) * n were n is height of the tree. So for a skewed BST we will have running time
 *   of O(n) and for a balanced we will have running time of O(logn) as it is known that height of a balanced
 *   binary tree is O(logn)
 *
 *   2. See code below
 *   3.
 *      n                                   10^0          10^1         10^2        10^3        10^4         10^5
 *   Skewed BST
 *      -   bigger than max number     |   7700     |     4700     |   18700   |   84200   |   597400    |    *    |
 *      -   smaller than max number    |   1900     |     1400     |   1600    |   10100   |   12000     |    *    |
 *   Balanced BST                      |   3000     |     4600     |   9600    |   20700   |   124500    |        |
 *      * - due to the way the recursion is implemented stack overflow occurs.
 *
 *   4.
 *     The observed results concur with the previously made analysis. As expected when looking for an element smaller than the root that does not
 *     exist the running time is very fast as most of the tree does not bet traversed. Similarly when looking for a bigger than the biggest element
 *     we need to travel the whole tre and so running time is high or O(n).
 *     As for the balanced tree we get a running time in between the one for smallest in skewed and biggest in skewed. This basicly shows that
 *     O(1) < O(logn) < O(n)
 *     Although we don't see full progression going to 10^8 and up it is clear that as n grows the difference in running time between the balanced and the skewed
 *     grow in orders of magnitude. 4400 to 3000 is a 2 to 1 or so proportion where as 597400 to 124500 is a about 6 to 1 proportion.
 *
 *
 *
 *************************************************************************/
package assignment2;

import java.util.Random;
import java.util.Scanner;

public class MyClass {
    public static void main( String [ ] args )
    {
        Random randGen = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.println("As a recursive algorithm is in use here when the tree is fully skewed to one side\n" +
                "the call stack overflows. There for tests with large input size on skewed result in\noferflow.");
        System.out.println("Please enter value for size of trees: ");
        Long x = scanner.nextLong();
        BinarySearchTree<Long> S = new BinarySearchTree<Long>( );
        for( long  i = 0; i < x; i +=1 ){
            S.insert( i );
        }
        BinarySearchTree<Long> R = new BinarySearchTree<Long>( );
        for( long  i = 0; i < x; i +=1 ){
            long randNum = (long) (Math.random() * ((100000 - 0) + 1)) + 0;
            R.insert( randNum );
        }

        long startTimeBS = System.nanoTime();
        boolean inTree = S.contains(x+1);
        long endTimeBS = System.nanoTime() - startTimeBS;
        System.out.println("Time to determine if bigger than max number is not in the skewed tree "+endTimeBS);

        long startTimeMS = System.nanoTime();
        inTree = S.contains((long) -1);
        long endTimeMS = System.nanoTime() - startTimeMS;
        System.out.println("Time to determine if smaller than max number is not in the skewed tree "+endTimeMS);

        long startTimeBT = System.nanoTime();
        long randNum = (long) (Math.random() * ((100000 - 0) + 1)) + 0;
        inTree = S.contains((long) randNum);
        long endTimeBT = System.nanoTime() - startTimeBT;
        System.out.println("Time to determine if random number is in balanced tree "+endTimeBT);
    }
}
