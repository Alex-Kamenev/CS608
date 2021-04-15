/*************************************************************************
 *
 *  Pace University
 *  Spring 2021
 *  Algorithms and Computing Theory
 *
 *  Course: CS 608
 *  Team members: Aleksandar Kamenev
 *  Other collaborators: NONE
 *  References: Introduction to Algorithms 3rd edition (Cormen, Leiserson, Rivest and Stein's), https://www.geeksforgeeks.org/, MIT online lectures (more for further exploration
 *  than for completion of assignment)
 *
 *  Assignment: 4
 *  Problem: DFS Implementation
 *  Description: Implement a Graph as an adjacency list and DFS. This class is an implementation of Dept First Search.
 *  The code is based on a version of DFS which uses color to mark vertices based on visit status. It is based on the sudo code from
 *  Cormen's, Leiserson's, Rivest's and Stein's book - Introduction to Algorithms 3rd edition.
 *
 *  Input: For DFS the input is a directed or undirected graph. For the main method part of the program the input is number of vertices and number of edges
 *  Output: Adjacency list representation f the randomly generated graph based on user input params, Print out of vertex, discovery and finish time and a
 *  nano sec measurement of time taken to complete DFS on the input graph
 *
 *  Visible data fields:
 *  COPY DECLARATION OF VISIBLE DATA FIELDS HERE
 *
 *  Visible methods:
 *  public static void DFS(Graph G)
 *  public static void main(String[] args)
 *
 *
 *   Remarks
 *   -------
 *
 *   PUT ALL NON-CODING ANSWERS HERE
 *
 *  2. (25 points) Using the DFS program of part (??), ll in the following
 *  chart with the running times observed for di  erent edge-set sizes.
 *
 *               |  |E| =  |V| - 1  |   |E| =  flor[(|V| - 1)^3/2]   |  |E| =  (|V| - 1)^2   |
 *
 *   |V| = 10    |       26900      |             32400              |          59400        |
 *   |V| = 100   |       94200      |            241000              |        1792500        |
 *   |V| = 1000  |      601700      |           5089600              |       74563300        |
 *
 * ___________________________________________________________________________________________
 *
 *               |  |E| =  |V| - 1  |   |E| =  flor[(|V| - 1)^3/2]   |  |E| =  (|V| - 1)^2   |
 *
 *   |V| = 10    |       28000      |             34800              |          69600        |
 *   |V| = 100   |       90800      |            246700              |        1710500        |
 *   |V| = 1000  |      645300      |           5237300              |       76592900        |
 *
 *  __________________________________________________________________________________________
 *
 *      (10-1)^(3/2) = 27             (100-1)^(3/2) = 985               (1000-1)^(3/2) = 31575
 *       (10 - 1)^2 = 81              (100 - 1)^2 = 9801                (1000 - 1)^2 = 998001
 *
 * ___________________________________________________________________________________________
 *
 *  3. (25 points) Give an approximate formula (with constants, not big-O)
 *  for the asymptotic running time of DFS based on your experiments.
 *  How does this compare with the expected O(jV j + jEj)? If the results
 *  di  er, overview the code of the data structures used for the adjacency
 *  list and explain what might have happened.
 *
 *  To start with let us consider the bigO time complexity - O(n + m)
 *  where n is number of vertices and m is number of edges
 *  From here we can conduct the following analyses:
 *  a*n + b*m for some constants a and b will be equal to the values from the tables above in (3)
 *
 *  1) a*10 + b*9     =  28000
 *  2) a*100 + b*99   =  90800
 *  3) a*1000 + b*999 =  645300
 *
 *  If we are to assume these constants are near equal to each other we get a, b equal to about
 *  1) 147
 *  2) 456
 *  3) 322
 *  Now this gives as an idea for what (a*n)^2 and (b*m)^2 may be
 *  1) (147*10)^2    = 2160900
 *  2) (456*100)^2   = 2079360000
 *  3) (322*1000)^2  = 103684000000
 *
 *  if we extend this logic to the rest of the columns of the table we would see simulate although larger numbers and
 *  more importantly trends of growth in the growth rares.
 *
 *  This numbers do not not come with a great accuracy (or precision for that matter)
 *  but still are a good way to visually see the difference between O(V + E) and O(V^2)
 *
 *  This serves as a way to a) show the difference between quadratic and linear rates of growth
 *  and b) to help convince us in the believability of the measurements seen i nthe tables in (3)
 *
 *  While there is growth in terms of orders of magnitude when going from top left to bottom right in the tables
 *  this is accounted for in the orders of magnitude growth seen in the inputs for those particular cells of the tables.
 *  And is in line with what we should expect from linear growth.
 *
 *************************************************************************/

package assignment4;

import java.util.List;
import java.util.Scanner;

//1. (50 points)Write a program that, given the adjacency list of a directed
//        graph, computes DFS efficiently. Your program should do the following.
//        (a) Prompt the user to input the number of nodes and the number of
//        edges.
//        (b) Create an adjacency list choosing the edges at random (do not
//        forget that it is a directed graph).
//        (c) Compute DFS (including discovery and finishing times) following
//        the pseudocode in the textbook.
//        (d) Measure and display the running time

public class DFS {

    private static int time;

    public static void DFS(Graph G) {
        //Not really needed since the Graph class and Vertex class are build so that a vertex is created with this values in place
        //mark all vertices as not visited and not having a predecessor
//        for (int i = 0; i < G.getListOfVertices().size(); i++) {
//            Vertex u = G.getListOfVertices().get(i);
//            u.setColor(Vertex.Color.White);
//            u.setPredecessor(u.getNULL());
//        }
        //initialize time
        time = 0;
        //go through graph and mark W,G,B
        for (int i = 0; i < G.getListOfVertices().size(); i++) {
            Vertex u = G.getListOfVertices().get(i);
            if (u.getColor() == Vertex.Color.White) {
                DFS_VISIT(G, u);
            }
        }

    }

    private static void DFS_VISIT(Graph G, Vertex u) {
        time++;
        u.setDiscovery(time);
        u.setColor(Vertex.Color.Gray);

        //for this imp needs
        int indexOfU = u.getVertexIndex();
        List<Integer> curAdjListPoss = G.getAdjacencyList().get(indexOfU);
        for (int i = 0; i < curAdjListPoss.size(); i++) {
            Vertex v = G.getListOfVertices().get(curAdjListPoss.get(i));
            if (v.getColor() == Vertex.Color.White) {
                v.setPredecessor(indexOfU);
                DFS_VISIT(G, v);
            }
        }
        u.setColor(Vertex.Color.Black);
        time++;
        u.setFinish(time);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input number of vertices: ");
        int numVert = scanner.nextInt();
        System.out.println("Please input number of edges: ");
        int numEdges = scanner.nextInt();

        // Create a new Graph G
        Graph G = new Graph(numVert, numEdges);
        //Print G
        G.printGraph(G);
        //start nano sec timer
        long startTime = System.nanoTime();
        //run DFS
        DFS.DFS(G);
        //end nano sec timer
        long endTime = System.nanoTime() - startTime;

        //Print vertex, discovery and finish times
        G.printVert_Disc_Finish(G);

        //Print results of time measurement
        System.out.println("Time taken by DFS: " + endTime);

    }

}
