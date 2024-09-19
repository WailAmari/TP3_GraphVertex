import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Graph {
    private Set<Vertex> vertices;
    private List<Edge> edges;  // Change Set to List to allow for parallel edges
    private Map<Edge, Set<Vertex>> edgeToVertices;

    public Graph() {
        vertices = new HashSet<>();
        edges = new ArrayList<>();  // Use ArrayList to allow parallel edges
        edgeToVertices = new HashMap<>();
    }

    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    public void addEdge(Edge e) {
        edges.add(e);  // Simply add the edge to the list
        Set<Vertex> connectedVertices = new HashSet<>();
        connectedVertices.add(e.getVertex1());
        connectedVertices.add(e.getVertex2());
        edgeToVertices.put(e, connectedVertices);
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {  // Return List instead of Set
        return edges;
    }

    public Set<Vertex> getVerticesForEdge(Edge e) {
        return edgeToVertices.get(e);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "vertices=" + vertices +
                ", edges=" + edges +
                '}';
    }

    // Method to generate HTML with vertex and edge visualization
    public void toHTML(String filename) throws IOException {
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<!DOCTYPE html>\n<html>\n<head>\n");
        htmlContent.append("<title>Graph Visualization</title>\n");
        htmlContent.append("<style>\n");
        htmlContent.append("svg { border: 1px solid black; }\n");
        htmlContent.append("</style>\n");
        htmlContent.append("</head>\n<body>\n");

        htmlContent.append("<svg width='800' height='600' style='background-image: url(\"math-or-mathematics-background-real-millimeter-graph-paper-background-with-seamless-repeating-pattern-or-texture-ideal-for-a-school-website-template-2FXG0FT.jpg\"); background-size: cover;'>\n");
        Map<String, int[]> vertexPositions = new HashMap<>();
        vertexPositions.put("A", new int[]{150, 250});
        vertexPositions.put("B", new int[]{350, 250});
        vertexPositions.put("C", new int[]{250, 400});
        vertexPositions.put("D", new int[]{500, 100});

        int radius = 20;

        // Draw vertices
        for (Map.Entry<String, int[]> entry : vertexPositions.entrySet()) {
            String vertex = entry.getKey();
            int[] pos = entry.getValue();

            htmlContent.append("<circle cx='").append(pos[0]).append("' cy='").append(pos[1])
                    .append("' r='").append(radius).append("' fill='#1b75be' stroke='#0051a2' stroke-width='2'/>\n");

            htmlContent.append("<text x='").append(pos[0]).append("' y='").append(pos[1])
                    .append("' font-size='12' text-anchor='middle' dominant-baseline='central' fill='black'>")
                    .append(vertex).append("</text>\n");
        }

        // Group edges by their vertices
        Map<String, List<Edge>> edgeGroups = new HashMap<>();
        for (Edge edge : edges) {
            String key = edge.getVertex1().getName() + "-" + edge.getVertex2().getName();
            edgeGroups.computeIfAbsent(key, k -> new ArrayList<>()).add(edge);
        }

        // Draw edges


        for (List<Edge> edgeGroup : edgeGroups.values()) {
            int edgeCount = edgeGroup.size();
            for (int i = 0; i < edgeCount; i++) {
                Edge edge = edgeGroup.get(i);
                int[] pos1 = vertexPositions.get(edge.getVertex1().getName());
                int[] pos2 = vertexPositions.get(edge.getVertex2().getName());

                // Calculate the angle of the line
                double angle = Math.atan2(pos2[1] - pos1[1], pos2[0] - pos1[0]);

                // Calculate intersection points
                double startX = pos1[0] + radius * Math.cos(angle);
                double startY = pos1[1] + radius * Math.sin(angle);
                double endX = pos2[0] - radius * Math.cos(angle);
                double endY = pos2[1] - radius * Math.sin(angle);

                // Calculate control point for the quadratic Bezier curve
                double midX = (startX + endX) / 2;
                double midY = (startY + endY) / 2;
                double normalX = -(endY - startY);
                double normalY = endX - startX;
                double normalLength = Math.sqrt(normalX * normalX + normalY * normalY);
                normalX /= normalLength;
                normalY /= normalLength;

                // Offset parallel edges
                double offset = (i - (edgeCount - 1) / 2.0) * 30; // Increased offset for better visibility
                double controlX = midX + normalX * offset;
                double controlY = midY + normalY * offset;

                // Draw the curved edge
                htmlContent.append("<path d='M").append(startX).append(",").append(startY)
                        .append(" Q").append(controlX).append(",").append(controlY)
                        .append(" ").append(endX).append(",").append(endY)
                        .append("' fill='none' stroke='#0051a2' stroke-width='2'/>\n");

                // Add label for the edge
                double labelOffset = offset;
                if (Math.abs(offset) < 1) { // If the line is nearly straight
                    labelOffset = 7; // Add a fixed offset for straight lines
                }
                double labelX = midX + normalX * labelOffset * 1.2;
                double labelY = midY + normalY * labelOffset * 1.2;
                htmlContent.append("<text x='").append(labelX).append("' y='").append(labelY)
                        .append("' font-size='12' text-anchor='middle' dominant-baseline='central' fill='black'>")
                        .append(edge.getId()).append("</text>\n");
            }
        }
        htmlContent.append("</svg>\n");
        htmlContent.append("</body>\n</html>");

        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(htmlContent.toString());
        }
    }

}
