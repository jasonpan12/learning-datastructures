package ds.graph;

import java.util.ArrayList;

/**
 *
 */
public class Graph {
    private int numVert; // number of vertices
    private int numEdge; // numEdges

    private ArrayList[] adjacents; // array of integer lists

    public Graph(int numVert) {
        this.numVert = numVert;
        this.numEdge = 0;
        adjacents = new ArrayList[numVert];

        // create arrays for each vertex
        for (int i = 0; i < numVert; i++) {
            adjacents[i] = new ArrayList<Integer>();
        }
    }

    public int getNumEdge() {
        return numEdge;
    }

    public int getVertexCount() {
        return numVert;
    };

    public void addEdge(int src, int dest) {
        adjacents[src].add(dest);
        numEdge++;
    }

    public Object[] adj(int src) {
        return adjacents[src].toArray();
    }
}
