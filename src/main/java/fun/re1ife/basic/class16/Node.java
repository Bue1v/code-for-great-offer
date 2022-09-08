package fun.re1ife.basic.class16;

import java.util.ArrayList;
import java.util.List;

/**
 * Don't worry, be happy
 *
 * @author re1ife
 * @date 2022/09/02 18:17
 **/
public class Node {

    public int val;
    public int in;
    public int out;
    public List<Edge> edges;
    public List<Node> nexts;

    public Node(int val) {
        this.val = val;
        in = 0;
        out = 0;
        edges = new ArrayList<>();
        nexts = new ArrayList<>();
    }

}
