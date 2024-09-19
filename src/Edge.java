public class Edge {
    private Vertex vertex1;
    private Vertex vertex2;
    private String name;  // New field to differentiate parallel edges

    // Constructor
    public Edge(String name, Vertex v1, Vertex v2) {
        this.vertex1 = v1;
        this.vertex2 = v2;
        this.name = name;
    }

    // Getter for vertex1
    public Vertex getVertex1() {
        return vertex1;
    }

    // Getter for vertex2
    public Vertex getVertex2() {
        return vertex2;
    }

    // Getter for edge id
    public String getId() {
        return name;
    }

    // toString method to represent the Edge as a String
    @Override
    public String toString() {
        return "Edge{" +
                "vertex1=" + vertex1.getName() +
                ", vertex2=" + vertex2.getName() +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge edge = (Edge) obj;
        // Compare by both vertices and the id (to allow for parallel edges)
        return vertex1.equals(edge.vertex1) && vertex2.equals(edge.vertex2) && name.equals(edge.name);
    }

}
