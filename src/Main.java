public class Main {
    public static void main(String[] args) {
        var graph1 = new Graph();

        graph1.addVertex("a");
        graph1.addVertex("b");
        graph1.addVertex("c");
        graph1.addVertex("d");

        graph1.addEdge("a", "b");
        graph1.addEdge("a", "c");
        graph1.addEdge("b", "c");
        graph1.addEdge("a", "d");

        System.out.println(graph1);

        var graph2 = new Graph();

        graph2.addVertex("e");
        graph2.addVertex("f");
        graph2.addVertex("g");
        graph2.addVertex("h");

        graph2.addEdge("e", "f");
        graph2.addEdge("e", "g");
        graph2.addEdge("f", "g");
        graph2.addEdge("e", "h");

        System.out.println(graph2);

        System.out.println("Vizinhos: " + graph1.neighbours("a"));
        System.out.println("Vizinhos: " + graph2.neighbours("e"));

        if (graph1.direct("a", "c"))
            System.out.println("A e C estao ligados!!");

        if (!graph1.direct("b", "d"))
            System.out.println("B e D NAO estao ligados!!");

        System.out.println(graph1.caminho(graph1.findVertex("a"), graph1.findVertex("c")));
        System.out.println("oi");
        System.out.println(Graph.uniao(graph1, graph2));
        System.out.println("tchau");
        System.out.println(Graph.interseccao(graph1, graph2));

        System.exit(0);
    }
}