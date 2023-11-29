import java.util.*;

public class Dijkstra {

    public static void main(String[] args) {
        OopGraph g = new OopGraph();
        g.addVertex("1");
        g.addVertex("2");
        g.addVertex("3");
        g.addVertex("4");
        g.addVertex("5");
        g.addVertex("6");
        g.connectVertices("1", "2", 3);
        g.connectVertices("1", "3", 1);
        g.connectVertices("2", "3", 2);
        g.connectVertices("2", "4", 3);
        g.connectVertices("2", "5", 6);
        g.connectVertices("3", "5", 2);
        g.connectVertices("5", "6", 1);
        g.connectVertices("6", "4", 1);
        //see the directed graph here: http://andrewd.ces.clemson.edu/courses/cpsc212/f06/labs/lab09.html
        Map<Vertex, PathEnd> paths = dijkstra(g.getVertex("1"));
        for(Vertex v : paths.keySet()) {
            System.out.println(v.label() + " distance: " + (int)paths.get(v).distance + " " + (paths.get(v).bp == null ? "" : "predecessor: " + paths.get(v).bp.src.label()));
        }
    }

    public static Map<Vertex, PathEnd> dijkstra(Vertex start) {
        Map<Vertex, PathEnd> pathInfo = new LinkedHashMap<>();
        PQueue<Vertex> frontier = new PQueue<>();

        frontier.add(start, 0);
        pathInfo.put(start, new PathEnd(0, null));
        while(!frontier.isEmpty()) {
            Vertex v = frontier.remove();
            for(Edge e : v.outgoingEdges()) {
                var dist = pathInfo.get(v).distance() + e.weight();
                if(!pathInfo.containsKey(e.destination())) {
                    frontier.add(e.destination(), dist);
                    pathInfo.put(e.destination(), new PathEnd(dist, new Backpointer(v, e)));
                }
                else if(dist < pathInfo.get(e.destination()).distance) {
                    pathInfo.put(e.destination(), new PathEnd(dist, new Backpointer(v, e)));
                    frontier.updatePriority(e.destination(), dist);
                }
            }
        }
        return pathInfo;
    }

    record Backpointer(Vertex src, Edge e) { }

    record PathEnd(double distance, Backpointer bp) { }
}
