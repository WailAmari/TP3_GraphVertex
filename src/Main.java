import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Create vertices A, B, C, D
        Vertex vertexA = new Vertex("A", 100, 100);
        Vertex vertexB = new Vertex("B", 300, 100);
        Vertex vertexC = new Vertex("C", 300, 300);
        Vertex vertexD = new Vertex("D", 500, 100);

        // Create edges based on φ(E)
        Edge edge1 = new Edge("a", vertexA, vertexB);  // (A, B)
        Edge edge2 = new Edge("b", vertexA, vertexB);  // (A, B) again (parallel edge)
        Edge edge3 = new Edge("c", vertexA, vertexC);  // (A, C)
        Edge edge4 = new Edge("d", vertexB, vertexC);  // (B, C)
        Edge edge5 = new Edge("e", vertexB, vertexC);  // (B, C) again (parallel edge)
        Edge edge6 = new Edge("f", vertexB, vertexC);  // (B, C) again (parallel edge)
        Edge edge7 = new Edge("g", vertexB, vertexD);  // (B, D)

        // Create a graph and add vertices
        Graph graph = new Graph();
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addVertex(vertexD);

        // Add edges to the graph
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.addEdge(edge7);

        // Generate the HTML file with SVG visualization
        try {
            graph.toHTML("graph.html");
            System.out.println("HTML file 'graph.html' generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
