class Edge {
    private float weight = 1;
    private Vertex from;
    private Vertex to;

    public Edge(Vertex from, Vertex to) {
        setFrom(from);
        setTo(to);
    }

    public Edge(Vertex from, Vertex to, float weight) {
        setFrom(from);
        setTo(to);
        setWeight(weight);
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    private void setFrom(Vertex from) {
        this.from = from;
    }

    public Vertex getFrom() {
        return from;
    }

    private void setTo(Vertex to) {
        this.to = to;
    }

    public Vertex getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[" +
                this.weight + ", " +
                this.from.getLabel() + ", " +
                this.to.getLabel() + "]";
    }
}