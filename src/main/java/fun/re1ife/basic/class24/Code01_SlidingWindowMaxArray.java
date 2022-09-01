package fun.re1ife.basic.class24;

import java.util.LinkedList;

/**
 * Don't worry, be happy
 *
 * https://leetcode-cn.com/problems/sliding-window-maximum
 *
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 *
 * 窗口内存index
 * 更新机制
 *
 * @author re1ife
 * @date 2022/09/01 15:44
 **/
public class Code01_SlidingWindowMaxArray {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int l = 0;
        int r = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        int[] ans = new int[nums.length - k + 1];
        int index = 0;
        while (r < nums.length) {
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[r]) {
                queue.removeLast();
            }
            queue.add(r ++);
            if (r - l == k) {
                ans[index ++] = nums[queue.peekFirst()];
                if (r - queue.peekFirst() == k) {
                    queue.removeFirst();
                }
                l ++;
            }
        }
        return ans;
    }

}
