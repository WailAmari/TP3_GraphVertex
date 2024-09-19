public class Vertex {
    private String name;
    private double x;
    private double y;

    // Constructor
    public Vertex(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for x-coordinate
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }

    // Getter for y-coordinate
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }

    // toString method to represent the Vertex as a String
    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

