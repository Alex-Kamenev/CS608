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
 *  within a array of numbers. that has the largest sum.
 *
 *  Input: for this program integer values. For the classes holding the algorithms implementations
 *  a vector.
 *  Output: for this program a long value representing the time a given algorithm toke to complete
 *  on a given input size. For the classes holding the algorithms implementations a long value representing
 *  the contiguous sub-array max sum.
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
 *   1) See code
 *   2)
 *   range                  \   10^1      \   10^2      \   10^3        \   10^4         \   10^5           \
 *   Brute Force            \   635300    \   531300    \   10309000    \   476518200    \   46477763800    \
 *   Divide and Conquer     \   559800    \   104300    \   930000      \   1937000      \   17259300       \
 *   3)
 *   For the brute force algorithm we would expect a runtime of order n^2.
 *   For the divide and conquer algorithm we expect a runtime of order nlogn.
 *   While this time complexities are a little harder to observe for smaller values of n.
 *   They are easily observed for large values of n.
 *   As n grow we start to see several orders of magnitude difference this is consistent with the mathematical
 *   analysis of O(n^2) vs O(nlogn).
 *   For n = 10^4 there is a 10^2 difference and for n = 10^5 there is a 10^3 difference. As the difference
 *   between O(n^2) and O(nlogn) is rather dramatic this is easily observed and the data is consistent with
 *   the theory.
 *   ** Orders of difference in magnitude are consistent across different executions.
 *   ** A more interesting comparison would be O(n) vs O(nlogn) as the difference is not as dramatic and
 *   perhaps n would need to be quite large for a meaningful gain to be observed. - more on this later.
 *   4)
 *   Kadane's algo - do before may 1st (ort sooner)
 *
 *
 *
 *************************************************************************/
package assignment1;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Driver {
    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose from these choices");
        System.out.println("-------------------------");
        System.out.println("1 - Run algorithms once (will be prompted to enter Vector size)");
        System.out.println("2 - Run algorithms on range of sizes from 10 to 10^5");
        System.out.println("-------------------------");
        System.out.print("Please enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                singleVector();
                break;
            case 2:
                doAnalyzes();
                break;
            default:
                singleVector();
        }
    }
    private static void singleVector() {
        int size = getSize();
        printHead();
        showRes(size);
    }
    private static void doAnalyzes() {
        printHead();
        for (int i = (int) Math.pow(10, 1); i<= Math.pow(10, 5); i = i*10) {
            int size = i;
            showRes(i);
        }
    }
    private static void showRes(int size) {
        Vector<Integer> v = createNewVec(size);
        long startTimeBF = System.nanoTime();
        long bruteForceAns = doBrute(v);
        long endTimeBF = System.nanoTime() - startTimeBF;

        long startTimeDC = System.nanoTime();
        long divQAns = doDivAndCon(v);
        long endTimeDC = System.nanoTime() - startTimeDC;

        String array;
        if (v.size() < 11){
            array = v.toString();
            System.out.printf("%1s %25s %25s %25s %25s %85s", size, endTimeBF, endTimeDC,bruteForceAns , divQAns, array);}
        else{
            array = "Vector has more than 10 elements";
            System.out.printf("%1s %25s %25s %25s %25s %65s", size, endTimeBF, endTimeDC,bruteForceAns , divQAns, array);}


        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    private static void printHead(){
        // Print the list objects in tabular format.
        System.out.println("**  Values for algorithms speeds are in nano seconds  **");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%1s %25s %25s %25s %25s %45s", "Input Size", "Brute Force  Speed", "Divide&Conquer Speed", "Brute Force  Result", "Divide&Conquer Result", "Vector");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    private static int getSize () {
        // Create a Scanner object
        Scanner myObj = new Scanner(System.in);

        System.out.print("Please enter initial size of Vector (stay with in positive integer range): ");
        // Read user input
        int size = myObj.nextInt();
        return size;
    }
    private static Vector<Integer> createNewVec(int size) {
        //Create new vector to  hold values
        Vector<Integer> v = new Vector<Integer>();
        //create an instance of java random class
        Random rand = new Random();

        for (int i = 0; i<size;i++){
            //Using 2 bit integer for simplicity
            //Generate a random int in said range for every element/log iteration
            int rInt = rand.nextInt(32767-(-32768)) + (-32768);
            //call vector method to add element to vector
            v.add(rInt);
        }
        return v;
    }
    private static long doBrute(Vector<Integer> v) {
        MSPBF mspbf = new MSPBF();
        return mspbf.bruteForce(v);
    }
    private static long doDivAndCon(Vector<Integer> v) {
        MSPDV mspdv = new MSPDV();
        return mspdv.divQ(v);
    }
}
