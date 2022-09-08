package fun.re1ife.basic.class16;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Don't worry, be happy
 *
 * 图的宽度优先遍历
 * 利用set记录已经遍历过的节点
 * 其他和树差不多没区别
 *
 * @author re1ife
 * @date 2022/09/02 18:21
 **/
public class Code01_BFS {

    public void bfs(Node root) {
        if (root == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        HashSet<Node> set = new HashSet<>();
        set.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.removeFirst();
            System.out.println(node.val);
            for (Node next : node.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }

}
