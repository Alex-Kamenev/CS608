# CS608: algorithms and computing theory

Pace University coursework from spring 2021:

- Assignment 1: brute-force and divide-and-conquer maximum subarray algorithms.
- Assignment 2: binary search tree exercises, retaining the original attribution to Mark Allen Weiss.
- Assignment 3: insertion sort and quicksort pivot experiments.
- Assignment 4: a directed graph generator and depth-first search with discovery/finish times.

Compile and run the focused regression checks from the repository root with a JDK on `PATH` (tested with Microsoft OpenJDK 21):

```powershell
$sources = @(Get-ChildItem assignment1,assignment2,assignment3,assignment4,tests -Filter *.java -Recurse | ForEach-Object FullName)
javac -d out @sources
java -cp out assignment4.AlgorithmRegression
```

The checks include sorted, reverse-sorted and repeated quicksort inputs; negative maximum-subarray values; invalid graph counts; and running DFS twice on the same graph. Quicksort now bounds recursion by recursing into the smaller partition. Endpoint pivots can still require quadratic time. DFS uses recursive visits, so very deep graphs can exceed the Java stack. Graph generation allows self-loops, consistent with its `V²` maximum edge count.

The original benchmark drivers can allocate very large arrays or print extensive output. Read the driver parameters before running them.
