public class Edge {
    private final Vertex destination;
    private final double weight;

    public Edge(Vertex destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex destination() {
        return destination;
    }

    public double weight() {
        return weight;
    }
}
