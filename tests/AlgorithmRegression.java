package assignment4;

import assignment1.MSPBF;
import assignment1.MSPDV;
import assignment3.QuickSort;
import java.util.Arrays;
import java.util.Vector;

public class AlgorithmRegression {
    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        for (String pivot : new String[]{"low", "high", "median"}) {
            for (int mode = 0; mode < 3; mode++) {
                int[] values = new int[12000];
                for (int i = 0; i < values.length; i++) values[i] = mode == 0 ? i : mode == 1 ? -i : i % 7;
                int[] expected = values.clone();
                Arrays.sort(expected);
                sort.sort(values, 0, values.length - 1, pivot);
                if (!Arrays.equals(expected, values)) throw new AssertionError("Quicksort: " + pivot);
            }
        }
        Vector<Integer> values = new Vector<>(Arrays.asList(-8, -2, -5));
        if (new MSPDV().divQ(values) != -2 || new MSPBF().bruteForce(values) != -2) throw new AssertionError("Negative maximum subarray");
        try { new MSPDV().divQ(new Vector<>()); throw new AssertionError("Empty input accepted"); }
        catch (IllegalArgumentException expected) { }
        try { new Graph(1, 2); throw new AssertionError("Invalid graph accepted"); }
        catch (IllegalArgumentException expected) { }
        Graph graph = new Graph(3, 0);
        graph.addEdge(0, 1);
        DFS.DFS(graph);
        graph.getListOfVertices().get(0).setDiscovery(-1);
        DFS.DFS(graph);
        if (graph.getListOfVertices().get(0).getDiscovery() != 1) throw new AssertionError("DFS did not reset visit state");
        System.out.println("CS608 regressions passed");
    }
}
