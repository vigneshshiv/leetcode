package code.java.design;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/range-sum-query-immutable/
 */
public class RangeSumQuery {

    private int[] _nums;

    public RangeSumQuery(int[] nums) {
        _nums = nums;
        prefixSum();
    }

    public void prefixSum() {
        for (int i = 1; i < _nums.length; i++) {
            _nums[i] = _nums[i] + _nums[i - 1];
        }
    }

    public int sumRange(int left, int right) {
        return _nums[right] - (left > 0 ? _nums[left - 1] : 0);
    }

    public static void main(String[] args) {
        BiConsumer<int[], Integer> logger = (idx, result) -> {
            System.out.println("Left - " + idx[0] + ", Right - " + idx[1] + ", Result - " + result);
        };
        //
        int[] nums = {-2, 0, 3, -5, 2, -1};
        RangeSumQuery rangeSumQuery = new RangeSumQuery(nums);
        System.out.println("Nums - " + Arrays.toString(nums));
        int left = 0, right = 2;
        int result = rangeSumQuery.sumRange(left, right);
        logger.accept(new int[] {left, right}, result);
        //
        left = 2; right = 5;
        result = rangeSumQuery.sumRange(left, right);
        logger.accept(new int[] {left, right}, result);
        //
        left = 0; right = 5;
        result = rangeSumQuery.sumRange(left, right);
        logger.accept(new int[] {left, right}, result);
        //
        left = 1; right = 5;
        result = rangeSumQuery.sumRange(left, right);
        logger.accept(new int[] {left, right}, result);
    }

}
