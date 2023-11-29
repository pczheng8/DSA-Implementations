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
        bfs(g.getVertex("1"));
        Map<Vertex, Integer> layer = bfs_Layer_Pred(g.getVertex("1"));
        for(Vertex v : layer.keySet()) {
            System.out.println(v.label() + " " + layer.get(v));
        }
        for(Vertex v : pred.keySet()) {
            System.out.println(v.label() + " " + pred.get(v));
        }
    }

    public static void bfs(Vertex start) {
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

    public static Map<Vertex, String> pred = new LinkedHashMap<>();
    public static Map<Vertex, Integer> bfs_Layer_Pred(Vertex start) { //what about predecessors
        Map<Vertex, Integer> layer = new LinkedHashMap<>();

        Queue<Vertex> frontier = new LinkedList<>();
        Set<Vertex> vis = new HashSet<>();
        frontier.add(start);
        layer.put(start, 0);
        pred.put(start, null);
        while(!frontier.isEmpty()) {
            Vertex v = frontier.remove();
            System.out.println(v.label());
            for(Edge e : v.outgoingEdges()) {
                if(!vis.contains(e.destination())) {
                    frontier.add(e.destination());
                    vis.add(e.destination());
                    layer.put(e.destination(), layer.get(v)+1);
                    pred.put(e.destination(), v.label());
                }
            }
        }
        return layer;
    }
}
