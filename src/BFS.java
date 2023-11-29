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
        Map<Vertex, PathInfo> paths = bfs_Layer_Pred(g.getVertex("1"));
        for(Vertex v : paths.keySet()) {
            if(paths.get(v).pred == null) {
                System.out.println(v.label() + " " + paths.get(v).layer + " null");
            }
            else {
                System.out.println(v.label() + " " + paths.get(v).layer + " " + paths.get(v).pred.label());
            }
        }
        List<Vertex> path = pathTo(paths, g.getVertex("8"));
        for(Vertex v : path) {
            System.out.println(v.label());
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

    public static Map<Vertex, PathInfo> bfs_Layer_Pred(Vertex start) {
        Map<Vertex, PathInfo> pathInfo = new LinkedHashMap<>();

        Queue<Vertex> frontier = new LinkedList<>();
        Set<Vertex> vis = new HashSet<>();
        frontier.add(start);
        pathInfo.put(start, new PathInfo(0, null));
        while(!frontier.isEmpty()) {
            Vertex v = frontier.remove();
            System.out.println(v.label());
            for(Edge e : v.outgoingEdges()) {
                if(!vis.contains(e.destination())) {
                    frontier.add(e.destination());
                    vis.add(e.destination());
                    pathInfo.put(e.destination(), new PathInfo(pathInfo.get(v).layer+1, v));
                }
            }
        }
        return pathInfo;
    }

    static class PathInfo {
        int layer;
        Vertex pred;

        public PathInfo(int layer, Vertex pred) {
            this.layer = layer;
            this.pred = pred;
        }

        public String toString() {
            return "{layer=" + layer + ", pred=" + pred + "}";
        }
    }

    static List<Vertex> pathTo(Map<Vertex, PathInfo> pathInfo, Vertex end) {
        List<Vertex> path = new ArrayList<>();
        Vertex v = end;
        while(v != null) {
            path.add(0, v);
            v = pathInfo.get(v).pred;
        }
        return path;
    }
}
