package fun.re1ife.basic.class16;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Don't worry, be happy
 *
 * 图的深度优先遍历
 *
 * 使用栈，每个循环中，栈中永远存着的是当前完整的路径
 *
 * @author re1ife
 * @date 2022/09/02 18:21
 **/
public class Code02_DFS {

    public void dfs(Node root) {
        if (root == null) {
            return;
        }
        LinkedList<Node> stack = new LinkedList<>();
        stack.add(root);
        HashSet<Node> set = new HashSet<>();
        set.add(root);
        System.out.println(root.val);
        while (!stack.isEmpty()) {
            Node cur = stack.removeLast();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.add(cur);
                    stack.add(next);
                    set.add(next);
                    System.out.println(next.val);
                    break;
                }
            }
        }


    }

}
