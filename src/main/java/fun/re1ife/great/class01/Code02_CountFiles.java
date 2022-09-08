package fun.re1ife.great.class01;

import java.io.File;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Don't worry, be happy
 * <p>
 * 给定一个文件目录的路径，写一个函数统计这个目录下所有的文件数量并返回隐藏文件也算，但是文件夹不算
 *
 * @author re1ife
 * @date 2022/09/07 14:27
 **/
public class Code02_CountFiles {

    public static int countFiles(String filePath) {
        File file = new File(filePath);
        if (!file.isDirectory()) {
            return 1;
        }
        int count = 0;
        LinkedList<File> queue = new LinkedList<>();
        queue.add(file);
        while (!queue.isEmpty()) {
            File cur = queue.removeFirst();
            if (cur.listFiles() != null) {
                for (File f : cur.listFiles()) {
                    if (f.isDirectory()) {
                        queue.add(f);
                    } else if (f.isFile()) {
                        count ++;
                    }
                }
            }
        }
        return count;
    }

    // 注意这个函数也会统计隐藏文件
    public static int getFileNumber(String folderPath) {
        File root = new File(folderPath);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        Stack<File> stack = new Stack<>();
        stack.add(root);
        int files = 0;
        while (!stack.isEmpty()) {
            File folder = stack.pop();
            for (File next : folder.listFiles()) {
                if (next.isFile()) {
                    files++;
                }
                if (next.isDirectory()) {
                    stack.push(next);
                }
            }
        }
        return files;
    }

    public static void main(String[] args) {
        String path = "D:\\BaiduNetdiskDownload";
        System.out.println(getFileNumber(path));
        System.out.println(countFiles(path));
    }

}
