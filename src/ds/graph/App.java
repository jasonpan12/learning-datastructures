package ds.graph;

/**
 *
 */
public class App {
    public static void main(String[] args) {
        BetterGraph myGraph = new BetterGraph(5, "directed");
        myGraph.addVertex("State");
        myGraph.addVertex("Avenel");
        myGraph.addVertex("Elm");
        myGraph.addVertex("Pocono");
        myGraph.addVertex("William");

        myGraph.addEdge("State", "Avenel");
        myGraph.addEdge("State", "Elm");
        myGraph.addEdge("Elm", "Avenel");
        myGraph.addEdge("Elm", "William");
        myGraph.addEdge("William", "State");
        myGraph.addEdge("William", "Pocono");
        myGraph.addEdge("Avenel", "Pocono");
        myGraph.addEdge("Pocono", "Elm");

        myGraph.print();
    }
}
