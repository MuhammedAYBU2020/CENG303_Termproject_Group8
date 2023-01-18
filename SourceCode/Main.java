import java.io.File;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {// tries to load the input file. it should exist in the project folder
            Scanner scanFile = new Scanner(new File("road-usroads.mtx"));
            System.out.println("Graph input file found...");
            String line = "";
            while (scanFile.hasNextLine()) {//skips the lines in the beginning which contains data-set information
                line = scanFile.nextLine();
                //System.out.println(line);
                if (!line.startsWith("%"))
                    break;
            }
            // reads the Vertex and Edge numbers
            String[] inputs = line.split(" ");
            int V = Integer.parseInt(inputs[1]);
            int E = Integer.parseInt(inputs[2]);
            System.out.println("Number of vertices: " + V);
            System.out.println("Number of edges: " + E);
            System.out.println("Graph generation started...");

            Dijkstra dijkstra = new Dijkstra();
            BFS bfs = new BFS();
            // Adjacency list for storing which vertices are connected
            for (int i = 0; i < V + 1; i++) {
                dijkstra.addVertex();
                bfs.addVertex();
            }
            // reads all edges in the file between the vertices/nodes and add them to the graph
            for (int i = 0; i < E; i++) {
                String[] edge = scanFile.nextLine().split(" ");
                int u = Integer.parseInt(edge[0]);
                int v = Integer.parseInt(edge[1]);

                dijkstra.addEdge(u, v);
                bfs.addEdge(u, v);
            }
            System.out.println("Graph generation finished...");
            scanFile.close();
            scanFile = new Scanner(System.in);
            int source, dest;
            while (true) {// reads source and destination vertex/node id's from the user
                System.out.println("\nEnter -1 to Exit!");
                System.out.println("Enter source node (between 1-" + V + "):");
                source = scanFile.nextInt();
                if (source == -1) //-1 to exit
                    break;
                System.out.println("Enter destination node (between 1-" + V + "):");
                dest = scanFile.nextInt();
                if (dest != -1) {
                    LocalTime initialTime = LocalTime.now();
                    dijkstra.getShortestPath(V, source, dest);
                    LocalTime finishedTime = LocalTime.now();
                    long elapsedTime = ChronoUnit.NANOS.between(initialTime, finishedTime);
                    double elapsedTimeInMilliseconds = (double) elapsedTime / 1000000;
                    System.out.println("Time taken to complete Dijkstra's = "+elapsedTimeInMilliseconds);

                    initialTime = LocalTime.now();
                    bfs.getShortestPath(V, source, dest);
                    finishedTime = LocalTime.now();
                    elapsedTime = ChronoUnit.NANOS.between(initialTime, finishedTime);
                    elapsedTimeInMilliseconds = (double) elapsedTime / 1000000;
                    System.out.println("Time taken to complete BFS = "+elapsedTimeInMilliseconds);
                } else
                    break;
            }

            System.out.println("End of the program!");
        } catch (Exception ex) {
            System.out.println("Something is wrong with the input file!!!");
            ex.printStackTrace();
        }

    }
}