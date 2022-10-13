package code.java.arrays;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 */
public class LongestConsecutiveSequence {

    private static int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        int longest = 0;
        Set<Integer> store = new HashSet<>();
        for (int num : nums) store.add(num);
        for (int num : nums) {
            if (!store.contains(num - 1)) {
                int length = 1;
                while (store.contains(num + 1)) {
                    num += 1; length += 1;
                }
                longest = Math.max(longest, length);
            }
        }
        return longest;
    }

    private static int longestConsecutiveOnePass(int[] nums) {
        if (nums.length == 0) return 0;
        int longest = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                int L = map.getOrDefault(num - 1, 0);
                int R = map.getOrDefault(num + 1, 0);
                int length = L + R + 1;
                map.put(num, length);
                // Calculate current max length
                longest = Math.max(longest, length);
                // Left side sequence update
                if (L != 0) map.put(num - L, length);
                // Right side sequence update
                if (R != 0) map.put(num + R, length);
            }
        }
        return longest;
    }

    /**
     * Union Find approach
     */
    private static int longestConsecutiveUnionFind(int[] nums) {
        if (nums.length == 0) return 0;
        int idx = 0;
        UnionFind uf = new UnionFind(nums.length);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            // Don't add duplicate
            if (map.containsKey(num)) continue;
            if (map.containsKey(num - 1)) {
                uf.union(idx, map.get(num - 1));
            }
            if (map.containsKey(num + 1)) {
                uf.union(idx, map.get(num + 1));
            }
            map.put(num, idx++);
        }
        return uf.findLargestConnectedSet();
    }

    private static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        private int find(int x) {
            int num = parent[x];
            if (num == x) return x;
            return parent[x] = find(num);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
                rank[rootY] += rank[rootX];
            }
        }

        public int findLargestConnectedSet() {
            int mx = 0;
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == i && rank[i] > mx) {
                    mx = rank[i];
                }
            }
            return mx;
        }

    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (nums, result) -> {
            System.out.println("Nums - " + Arrays.toString(nums) + ", Result - " + result);
        };
        //
        int[] nums = {100, 4, 200, 1, 3, 2};
        int result = longestConsecutive(nums);
        logger.accept(nums, result);
        result = longestConsecutiveOnePass(nums);
        logger.accept(nums, result);
        result = longestConsecutiveUnionFind(nums);
        logger.accept(nums, result);
        //
        nums = new int[] {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        result = longestConsecutive(nums);
        logger.accept(nums, result);
        result = longestConsecutiveOnePass(nums);
        logger.accept(nums, result);
        result = longestConsecutiveUnionFind(nums);
        logger.accept(nums, result);
    }

}
