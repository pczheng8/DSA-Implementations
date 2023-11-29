public interface Vertex {
    String label();
    Iterable<Edge> outgoingEdges();
    int outDegree();
}
