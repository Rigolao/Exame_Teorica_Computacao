import java.util.*;
import java.util.stream.Collectors;

public class Graph {

    private List<Vertex> vertexes = new LinkedList<>();

    public Graph() {  }

    protected Vertex findVertex(String value) {
        try {
            return vertexes
                    .stream()
                    .filter(v -> v.getLabel().equals(value))
                    .toList()
                    .get(0);
        } catch(Exception e){
            return null;
        }
    }

    public void addVertex(String value) {
        var vertex = findVertex(value);
        if (vertex == null)
            vertexes.add(new Vertex(value));
    }

    public void removeVertex(String value) {
        var vertex = findVertex(value);
        if (vertex != null)
            vertexes.remove(vertex);
    }

    public void addEdge(String valA, String valB) {
        var va = findVertex(valA);
        var vb = findVertex(valB);
        va.addEdge(new Edge(va, vb));
        vb.addEdge(new Edge(vb, va));
    }

    public List<Vertex> neighbours(String value) {
        var v = findVertex(value);
        if (v == null)
            return null;
        List<Vertex> vs = new LinkedList<>();
        for(Edge e : v.getEdges())
            vs.add(e.getTo());

        return vs;
    }

    public boolean direct(String vA, String vB) {
        var v = findVertex(vA);
        if (v != null) {
            return v.getEdges()
                    .stream()
                    .filter(e -> e.getTo().getLabel().equals(vB))
                    .count() == 1;
        }
        return false;
    }

    public List<Vertex> caminho(Vertex a, Vertex b) {
        Map<Vertex, Vertex> path = new HashMap<>();
        Queue<Vertex> queue = new LinkedList<>();
        List<Vertex> visited = new ArrayList<>();

        queue.add(a);
        visited.add(a);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            if (current.equals(b)) {
                break;
            }
            for (Vertex neighbor : neighbours(current.getLabel())) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    path.put(neighbor, current);
                }
            }
        }

        if (!path.containsKey(b)) {
            return null;
        }

        List<Vertex> result = new ArrayList<>();
        Vertex current = b;
        while (!current.equals(a)) {
            result.add(0, current);
            current = path.get(current);
        }
        result.add(0, a);

        return result;
    }

    public static Graph uniao(Graph G1, Graph G2) {
        Graph unionGraph = new Graph();
        for (Vertex v1 : G1.getVertexes()) {
            unionGraph.addVertex(v1.getLabel());
            for (Edge e : v1.getEdges()) {
                Vertex to = e.getTo();
                unionGraph.addVertex(to.getLabel());
                unionGraph.addEdge(v1.getLabel(), to.getLabel());
            }
        }
        for (Vertex v2 : G2.getVertexes()) {
            unionGraph.addVertex(v2.getLabel());
            for (Edge e : v2.getEdges()) {
                Vertex to = e.getTo();
                unionGraph.addVertex(to.getLabel());
                unionGraph.addEdge(v2.getLabel(), to.getLabel());
            }
        }
        return unionGraph;
    }

    public static Graph interseccao(Graph g1, Graph g2) {
        Graph result = new Graph();
        for (Vertex v : g1.getVertexes()) {
            if (g2.findVertex(v.getLabel()) != null) {
                result.addVertex(v.getLabel());
            }
        }

        for (Vertex v : result.getVertexes()) {
            Vertex v1 = g1.findVertex(v.getLabel());
            Vertex v2 = g2.findVertex(v.getLabel());
            for (Edge e : v1.getEdges()) {
                if (v2.getEdges().contains(e)) {
                    result.addEdge(e.getFrom().getLabel(), e.getTo().getLabel());
                }
            }
        }
        return result;
    }

    public List<Vertex> bfs(Vertex start, Vertex end) {
        Queue<Vertex> queue = new LinkedList<>();
        Set<Vertex> visited = new HashSet<>();
        Map<Vertex, Vertex> parentMap = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();

            if (current.equals(end)) {
                break; // Chegamos ao vértice de destino, interrompa o loop
            }

            for (Vertex neighbor : neighbours(current.getLabel())) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }

        // Verifique se o vértice de destino foi alcançado
        if (!parentMap.containsKey(end)) {
            return null; // Não há caminho do vértice de início ao vértice de destino
        }

        // Reconstrua o caminho percorrendo os pais do vértice de destino até o vértice de início
        List<Vertex> path = new ArrayList<>();
        Vertex current = end;
        while (current != null) {
            path.add(0, current);
            current = parentMap.get(current);
        }

        return path;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public void setVertexes(List<Vertex> vertexes) {
        this.vertexes = vertexes;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Vertex v : vertexes)
            s.append(v);
        return s.toString();
    }
}