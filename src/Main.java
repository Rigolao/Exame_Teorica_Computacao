import java.util.List;

public class Main {
    public static void main(String[] args) {
        var graph1 = new Graph();

        graph1.addVertex("a");
        graph1.addVertex("b");
        graph1.addVertex("c");
        graph1.addVertex("d");
        graph1.addVertex("e");

        graph1.addEdge("a", "b");
        graph1.addEdge("a", "c");
        graph1.addEdge("b", "c");
        graph1.addEdge("a", "d");
        graph1.addEdge("a", "e");

        System.out.println("Grafo 1: " + graph1);

        var graph2 = new Graph();

        graph2.addVertex("e");
        graph2.addVertex("f");
        graph2.addVertex("g");
        graph2.addVertex("h");

        graph2.addEdge("e", "f");
        graph2.addEdge("e", "g");
        graph2.addEdge("f", "g");
        graph2.addEdge("e", "h");

        System.out.println("Grafo 2: " + graph2);

        System.out.println("Vizinhos: " + graph1.neighbours("a"));
        System.out.println("Vizinhos: " + graph2.neighbours("e"));

        if (graph1.direct("a", "c"))
            System.out.println("A e C estao ligados!!");

        if (!graph1.direct("b", "d"))
            System.out.println("B e D NAO estao ligados!!");

        Vertex startVertex = graph1.findVertex("a");
        Vertex endVertex = graph1.findVertex("c");
        List<Vertex> shortestPath = graph1.bfs(startVertex, endVertex);

        if (shortestPath != null) {
            System.out.println("Caminho mais curto: " + shortestPath);
        } else {
            System.out.println("Não há caminho do vértice de início ao vértice de destino.");
        }

        System.out.println("Caminho: " + graph1.caminho(graph1.findVertex("a"), graph1.findVertex("c")));
        System.out.println("União: " + Graph.uniao(graph1, graph2));
        System.out.println("Intersecção: " + Graph.interseccao(graph1, graph2));

        System.exit(0);
    }
}