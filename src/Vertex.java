import java.util.LinkedList;
import java.util.List;

class Vertex {
    private String label;
    private List<Edge> edges = new LinkedList<>();

    public Vertex(String label) {
        setLabel(label);
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    public void removeEdge(Edge edge) {
        edges.remove(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        StringBuilder ts = new StringBuilder("[Label: " + label);
        for (Edge edge : this.edges)
            ts.append(edge);
        return ts + "] ";
    }
}