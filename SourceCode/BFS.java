import java.util.ArrayList;
import java.util.LinkedList;

public class BFS {
    private final ArrayList<ArrayList<Integer>> graph;

    BFS() {
        graph = new ArrayList<>();
    }

    public void addEdge(int i, int j) {
        graph.get(i).add(j);
        graph.get(j).add(i);
    }

    public void addVertex() {
        graph.add(new ArrayList<>());
    }

    // function to print the shortest distance and path between source vertex and destination vertex
    // parameters:::
    // adj: adjacency list of the graph
    // s: source node
    // dest: destination node
    // v: number of vertices in the graph
    public void getShortestPath(int v, int s, int dest) {
        // predecessor[i] array stores predecessor of
        // i and distance array stores distance of i
        // from s
        int[] pred = new int[v];
        int[] dist = new int[v];

        if (!bfs(s, dest, v, pred, dist))
            return;

        // LinkedList to store path
        LinkedList<Integer> path = new LinkedList<>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }

        System.out.println("Path is:");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
        System.out.println("\nShortest path length is " + dist[dest]);

    }

    // a modified version of BFS that stores predecessor of each vertex in array pred
    // and its distance from source in array dist
    // parameters:::
    // adj: adjacency list of the graph
    // src: source node
    // dest: destination node
    // v: number of vertices in the graph
    // pred: array that stores predecessor of each vertex
    // dist: array of distances from source
    public boolean bfs(int src, int dest, int v, int[] pred, int[] dist) {
        // a queue to maintain queue of vertices whose adjacency list is to be scanned as per normal
        // BFS algorithm using LinkedList of Integer type
        LinkedList<Integer> queue = new LinkedList<>();

        // boolean array visited[] which stores the information whether ith vertex is reached
        // at least once in the Breadth first search
        boolean[] visited = new boolean[v];

        // initially all vertices are unvisited so v[i] for all i is false
        // and as no path is yet constructed dist[i] for all i set to infinity
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

        // now source is first to be visited and
        // distance from source to itself should be 0
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);

        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < graph.get(u).size(); i++) {
                if (!visited[graph.get(u).get(i)]) {
                    visited[graph.get(u).get(i)] = true;
                    dist[graph.get(u).get(i)] = dist[u] + 1;
                    pred[graph.get(u).get(i)] = u;
                    queue.add(graph.get(u).get(i));

                    // stopping condition (when we find our destination)
                    if (graph.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }

    public ArrayList<ArrayList<Integer>> getGraph() {
        return graph;
    }
}
