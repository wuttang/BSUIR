import java.util.*;

public class Graph {
    private final LinkedList<Integer>[] adjLists;
    private final boolean[] visited;

    Graph(int vertices) {
        adjLists = new LinkedList[vertices];
        visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++)
            adjLists[i] = new LinkedList<>();
    }

    void addEdge(int src, int dest) {
        adjLists[src].add(dest);
    }

    boolean DFS(int vertex, int targetVal) {
        visited[vertex] = true;

        if (vertex == targetVal) {
            return true;
        }

        for (int adj : adjLists[vertex]) {
            if (!visited[adj]) {
                if (DFS(adj, targetVal)) {
                    return true;
                }
            }
        }

        return false;
    }
}