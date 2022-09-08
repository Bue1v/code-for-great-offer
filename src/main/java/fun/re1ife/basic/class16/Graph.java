package fun.re1ife.basic.class16;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Don't worry, be happy
 *
 * @author re1ife
 * @date 2022/09/02 18:20
 **/
public class Graph {

    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();

    }

}
