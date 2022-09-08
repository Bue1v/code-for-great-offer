package fun.re1ife.great.class01;

import java.util.HashMap;

/**
 * Don't worry, be happy
 *
 * @author re1ife
 * @date 2022/09/03 16:41
 **/
public class Code07_TargetSum {

    public int findTargetSumWays(int[] nums, int target) {
        return process1(nums, 0, target);
    }

    public int process1(int[] nums, int index, int rest) {
        if (index == nums.length) {
            return rest == 0 ? 1 : 0;
        }
        return process1(nums, index + 1, rest - nums[index])
                + process1(nums, index + 1, rest + nums[index]);
    }

    public int findTargetSumWays2(int[] nums, int target) {
        HashMap<Integer, HashMap<Integer, Integer>> dp = new HashMap<>(nums.length + 1);
        return process2(nums, 0, target, dp);
    }

    public int process2(int[] nums, int index, int rest, HashMap<Integer, HashMap<Integer, Integer>> dp) {
        if (dp.containsKey(index) && dp.get(index).containsKey(rest)) {
            return dp.get(index).get(rest);
        }
        if (index == nums.length) {
            return rest == 0 ? 1 : 0;
        }
        int ans = process2(nums, index + 1, rest - nums[index], dp)
                + process2(nums, index + 1, rest + nums[index], dp);
        if (!dp.containsKey(index)) {
            dp.put(index, new HashMap<>());
        }
        dp.get(index).put(rest, ans);
        return ans;
    }

    /**
     * 优化点1 ： 数组中所有的负数都可以转成正数， 不影响结果
     * 优化点2 ： 数组累加和为sum， 当target > sum 时， 永远返回0
     * 优化点3 ： 如果sum和target奇偶性不一致，返回0
     * 优化点4 ： 所有加上的数分在集合P ，减去的数分在集合N， 那么有 P - N = target
     * 两边同时加上 P + N -》  2P = target + P + N -----> 2P = target + sum
     * 所以只要求出  取原数组中任意几个数，能得到 (target + sum)/2 的值，就找到满足的一种
     * 转化为背包问题
     */
    public int findTargetSumWays3(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] < 0 ? -nums[i] : nums[i];
            sum += nums[i];
        }
        if (sum < target || ((sum % 2) != (target % 2))) {
            return 0;
        }
        int k = (target + sum) / 2;
        return bag(nums, k);
    }

    /**
     * 经典背包问题
     */
    public int bag(int[] arr, int k) {
        return processBag(arr, 0, k);
    }

    public int processBag(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        return processBag(arr, index + 1, rest) + processBag(arr, index + 1, rest - arr[index]);
    }

    public int bag2(int[] arr, int k) {
        int[][] dp = new int[arr.length + 1][k + 1];
        return processBag2(arr, 0, k, dp);
    }

    public int processBag2(int[] arr, int index, int rest, int[][] dp) {
        if (rest < 0) {
            return 0;
        }
        if (dp[index][rest] != 0) {
            return dp[index][rest];
        }
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        dp[index][rest] = processBag2(arr, index + 1, rest, dp) + processBag2(arr, index + 1, rest - arr[index], dp);
        return dp[index][rest];
    }

    public int bag3(int[] arr, int k) {
        int[] dp = new int[k + 1];
        dp[0] = 1;
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = k; j >= arr[i]; j--) {
                dp[j] += dp[j - arr[i]];
            }
        }

        return dp[k];
    }


}
