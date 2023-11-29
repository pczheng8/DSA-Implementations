import java.util.*;

public class DFS {

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
        recursive_dfs(g.getVertex("1"));
    }

    public static Set<Vertex> vis = new HashSet<>();

    public static void recursive_dfs(Vertex start) {
        if(vis.contains(start)) {
            return;
        }
        if(start.outDegree() == 0) {
            vis.add(start);
            System.out.println(start.label());
            return;
        }
        for(Edge e : start.outgoingEdges()) {
            recursive_dfs(e.destination());
        }
        vis.add(start);
        System.out.println(start.label());
    }

}
