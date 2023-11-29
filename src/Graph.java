public interface Graph {
    Iterable<Vertex> vertices();
    int vertexCount();
    int edgeCount();
    Vertex getVertex(String label);
    boolean areAdjacent(String srcLabel, String dstLabel);
    void addVertex(String label);
    void connectVertices(String srcLabel, String dstLabel, double weight);
}
