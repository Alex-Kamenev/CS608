package assignment4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {

    //variable dec.
    private int numberOfVertices;
    private int NumberOfEdges;

    //instance of random gen for later use
    Random random = new Random();

    //array list to hold all vertices
    // [obj, obj, obj, obj ]
    private List<Vertex> listOfVertices;

    //array list to hold indexes of vertices which are connected to edges at given index of this array
    // [{}, {}, {}, {} ]
    private List<List<Integer>> adjacencyList;

    //constructor
    public Graph(int numVertices, int numEdges) {
        this.numberOfVertices = numVertices;
        this.NumberOfEdges = numEdges;

        if (NumberOfEdges > maxNumofEdges(numberOfVertices)) {
            System.out.println("Number of edges greater than possible number of edges for given number of vertices");
            return;
        }


        // Creating an adjacency list
        // representation for the random graph
        this.adjacencyList = new ArrayList<>(numberOfVertices);
        this.listOfVertices = new ArrayList<>(numberOfVertices);
        for (int i = 0; i < numberOfVertices; i++) {
            listOfVertices.add(new Vertex(i));
            adjacencyList.add(new ArrayList<>());
        }

        for (int i = 0; i < NumberOfEdges; i++) {
            int v = random.nextInt(numberOfVertices);
            int u = random.nextInt(numberOfVertices);
            if (adjacencyList.get(v).contains(u)) {
                i = i - 1;
                continue;
            }
            addEdge(v, u);
        }
    }

    //helper for max number of edges based on vertices
    private int maxNumofEdges(int numberOfVertices) {
        return Math.multiplyExact(numberOfVertices, numberOfVertices);
    }

    // Method to add edges between given vertices
    void addEdge(int v, int u) {
        // Note: it is a directed graph

        // Add w to v's adjacency list
        adjacencyList.get(v).add(u);
    }

    //Method to check number of edges in graph
    public int getNumberOfEdges() {
        return NumberOfEdges;
    }

    //Method tyo check number fo vertices in graph
    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    public List<List<Integer>> getAdjacencyList() {
        return adjacencyList;
    }

    public List<Vertex> getListOfVertices() {
        return listOfVertices;
    }

    public static void printGraph(Graph G) {
        //check if adjacencyList exists
        if (G.adjacencyList == null)
            return;
        // Print the graph
        System.out.println("The generated random graph :");
        for (int i = 0;
             i < G.adjacencyList.size(); i++) {
            System.out.print(i + " -> { ");

            List<Integer> list
                    = G.adjacencyList.get(i);

            if (list.isEmpty())
                System.out.print(" No adjacent vertices ");
            else {
                int size = list.size();
                for (int j = 0; j < size; j++) {

                    System.out.print(list.get(j));
                    if (j < size - 1)
                        System.out.print(" , ");
                }
            }

            System.out.println("}");
        }
    }

    public static void printVert_Disc_Finish(Graph G) {
        for (int i = 0; i < G.getListOfVertices().size(); i++) {
            int index = G.getListOfVertices().get(i).getVertexIndex();
            int start = G.getListOfVertices().get(i).getDiscovery();
            int finish = G.getListOfVertices().get(i).getFinish();
            System.out.println("Vertex: " + index + " Time of discovery: " + start + " Time of finish: " + finish);
        }
    }

}

class Vertex {
    public enum Color {
        White,
        Black,
        Gray
    }

    private int NULL = -1;

    private int vertexIndex;
    private Color color;
    private int predecessor;
    private int discovery;
    private int finish;

    //constructor
    public Vertex(int vertexIndex) {
        this.vertexIndex = vertexIndex;
        this.color = Color.White;
        this.predecessor = NULL;
        this.discovery = NULL;
        this.finish = NULL;
    }

    public int getVertexIndex() {
        return vertexIndex;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setDiscovery(int discovery) {
        this.discovery = discovery;
    }

    public int getDiscovery() {
        return discovery;
    }

    public void setPredecessor(int predecessor) {
        this.predecessor = predecessor;
    }

    public int getPredecessor() {
        return predecessor;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public int getFinish() {
        return finish;
    }

    public int getNULL() {
        return NULL;
    }
}
