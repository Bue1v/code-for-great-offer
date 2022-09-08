package fun.re1ife.basic.class16;

/**
 * Don't worry, be happy
 *
 * @author re1ife
 * @date 2022/09/02 18:18
 **/
public class Edge {

    public Node from;
    public Node to;
    public int weight;

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

}
