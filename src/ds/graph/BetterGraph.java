package ds.graph;

/**
 *
 */
public class BetterGraph {
    // each vertex will lead to a linked list

    Vertex[] arrayOfLists;
    int indexCounter = 0;
    boolean undirected=true;

    class Node {
        public int vertexIdx;
        public Node next;

        public Node(int vertexIdx, Node node) {
            this.vertexIdx = vertexIdx;
            next = node;
        }
    }

    class Vertex {
        String name;
        Node adjList;

        Vertex(String name, Node aNode) {
            this.name = name;
            this.adjList = aNode;
        }
    }

    public BetterGraph(int vCount, String graphType) {
        if (graphType.equals("directed")) {
            undirected = false;
        }

        arrayOfLists = new Vertex[vCount];
    }
    public void addVertex(String vertexName) {
        arrayOfLists[indexCounter] = new Vertex(vertexName, null);
        indexCounter++;
    }

    public void addEdge(String srcVertexName, String destVertexName) {
        int v1Idx = indexForName(srcVertexName);
        int v2Idx = indexForName(destVertexName);

        // Add to adjacent list on v1 IDx
        // give it a new node with vertex id v2Idx
        // At vertex's index (arrayOFLists[v1Idx]),
        // add to its list of adjacents a new node
        // takes destination index and assigns its next variable to be the list of v1's adjacents
        arrayOfLists[v1Idx].adjList = new Node(v2Idx, arrayOfLists[v1Idx].adjList);
        if (undirected) {
            arrayOfLists[v2Idx].adjList = new Node(v1Idx, arrayOfLists[v2Idx].adjList);
        }
    }

    // this is linear search
    int indexForName(String name) {
        for (int v = 0; v < arrayOfLists.length; v++) {
            if (arrayOfLists[v].name.equals(name)) {
                return v;
            }
        }
        return -1;
    }

    public void print() {
        System.out.println();
        for (int v = 0; v < arrayOfLists.length; v++) {
            // for each vertex
            System.out.println(arrayOfLists[v].name);

            // print its linked list
            for (Node aNode = arrayOfLists[v].adjList; aNode != null; aNode = aNode.next) {
                System.out.println("---->" + arrayOfLists[aNode.vertexIdx].name);
            }
            System.out.println("\n");
        }
    }
}
