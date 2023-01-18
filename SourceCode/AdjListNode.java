public class AdjListNode {
    private final int vertex, weight;

    AdjListNode(int v, int w)
    {
        vertex = v;
        weight = w;
    }
    int getVertex() { return vertex; }
    int getWeight() { return weight; }
}
