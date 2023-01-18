import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra {
    private final ArrayList<ArrayList<AdjListNode>> graph;

    Dijkstra() {
        graph = new ArrayList<>();
    }

    // function to form edge between two vertices
    // source i and destination j
    public void addEdge(int i, int j) {
        graph.get(i).add(new AdjListNode(j, 1));
        graph.get(j).add(new AdjListNode(i, 1));
    }

    public void addVertex() {
        graph.add(new ArrayList<>());
    }

    // function to find the shortest distance of all the vertices from the source vertex src.
    // returns only distance to target
    // If distance is positive, calls the printPath function
    // parameters:::
    // graph: adjacency list of the graph
    // src: source node
    // target: destination node
    // V: number of vertices in the graph

    public void getShortestPath(int V, int src, int target) {
        int[] distance = new int[V];
        for (int i = 0; i < V; i++)
            distance[i] = Integer.MAX_VALUE;
        distance[src] = 0;

        int[] parents = new int[V];
        parents[src] = -1;

        PriorityQueue<AdjListNode> pq = new PriorityQueue<>((v1, v2) -> v1.getWeight() - v2.getWeight());
        pq.add(new AdjListNode(src, 0));

        while (pq.size() > 0) {
            AdjListNode current = pq.poll();

            for (AdjListNode n : graph.get(current.getVertex())) {
                if (distance[current.getVertex()] + n.getWeight() < distance[n.getVertex()]) {
                    distance[n.getVertex()] = n.getWeight() + distance[current.getVertex()];
                    parents[n.getVertex()] = current.getVertex();
                    pq.add(new AdjListNode(n.getVertex(), distance[n.getVertex()]));
                }
            }
        }

        if (distance[target] == Integer.MAX_VALUE) {
            System.out.println("Source and destination are not connected!");
            return;
        }

        System.out.println("Path is: ");
        printPath(target, parents);
        System.out.println("\nShortest path length is " + distance[target]);
    }

    // function to print shortest path from src to dest using parents array
    private void printPath(int dest, int[] parents) {
        // Base case : Source node has been processed
        if (dest == -1)
            return;

        printPath(parents[dest], parents);
        System.out.print(dest + " ");
    }

    public ArrayList<ArrayList<AdjListNode>> getGraph() {
        return graph;
    }
}
