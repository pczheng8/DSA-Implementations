import java.util.*;

public class BFS {

    public static void main(String[] args) {
        OopGraph g = new OopGraph();
        g.addVertex("1");
        g.addVertex("2");
        g.addVertex("3");
        g.addVertex("4");
        g.addVertex("5");
        g.addVertex("6");
        g.addVertex("7");
        g.addVertex("8");
        g.connectVertices("1", "2", 1);
        g.connectVertices("1", "3", 1);
        g.connectVertices("1", "4", 1);
        g.connectVertices("2", "5", 1);
        g.connectVertices("2", "6", 1);
        g.connectVertices("3", "6", 1);
        g.connectVertices("3", "7", 1);
        g.connectVertices("4", "7", 1);
        g.connectVertices("4", "8", 1);
        bfs(g, g.getVertex("1"));
    }

    public static void bfs(OopGraph g, Vertex start) {
        Iterable<Vertex> vertices = g.vertices();
        Queue<Vertex> frontier = new LinkedList<>();
        Set<Vertex> vis = new HashSet<>();
        frontier.add(start);
        while(!frontier.isEmpty()) {
            Vertex v = frontier.remove();
            System.out.println(v.label());
            for(Edge e : v.outgoingEdges()) {
                if(!vis.contains(e.destination())) {
                    frontier.add(e.destination());
                    vis.add(e.destination());
                }
            }
        }
    }
}
