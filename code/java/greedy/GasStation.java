package code.java.greedy;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;

/**
 * https://leetcode.com/problems/gas-station/
 *
 * There are N gas stations along a circular route.
 *
 * Each has A[i] amount of gas.
 * To travel from station i -> i + 1, there is B[i] cost.
 *
 * Find the earliest station from where you can travel around the entire circuit.
 *
 * Return -1 if not possible.
 */
public class GasStation {

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static int canCompleteCircuit(int[] gas_store, int[] fuel_cost) {
        if (Objects.isNull(gas_store) || Objects.isNull(fuel_cost)
                || gas_store.length == 0 || fuel_cost.length == 0 || gas_store.length != fuel_cost.length) {
            return -1;
        }
        int curr = 0, start = 0, idx = 0;
        boolean limitReached = false;
        while (idx < gas_store.length) {
            if (idx == start && limitReached) {
                return start;
            }
            curr += gas_store[idx] - fuel_cost[idx];
            if (curr < 0) {
                if (limitReached) {
                    return -1;
                }
                curr = 0;
                start = idx + 1;
            }
            idx++;
            if (idx == gas_store.length) {
                if (!limitReached) {
                    limitReached = true;
                    idx = 0;
                } else {
                    return -1;
                }
            }
        }
        return -1;
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static int canCompleteCircuitOptimal(int[] gas_store, int[] fuel_cost) {
        if (Objects.isNull(gas_store) || Objects.isNull(fuel_cost)
                || gas_store.length == 0 || fuel_cost.length == 0 || gas_store.length != fuel_cost.length) {
            return -1;
        }
        int start = gas_store.length - 1, end = 0;
        int sum = gas_store[start] - fuel_cost[start];
        while (start > end) {
            if (sum >= 0) {
                sum += gas_store[end] - fuel_cost[end];
                end++;
            } else {
                start--;
                sum += gas_store[start] - fuel_cost[start];
            }
        }
        return sum >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        BiFunction<int[], int[], String> logger = (gas_store, fuel_cost) ->
                String.format("Gas store - %s, Fuel cost - %s", Arrays.toString(gas_store), Arrays.toString(fuel_cost));
        // Why not to consider 2 and 3?
        // Car passes only when: curr >= 0, keeps on collecting more and more fuel.
        int[] gas_store = {3, 5, 2, 1, 7};
        int[] fuel_cost = {4, 2, 1, 9, 1};
        System.out.println(logger.apply(gas_store, fuel_cost) + ", Result - " + canCompleteCircuit(gas_store, fuel_cost));
        System.out.println("Optimal: " + logger.apply(gas_store, fuel_cost) + ", Result - " + canCompleteCircuitOptimal(gas_store, fuel_cost));
        //
        gas_store = new int[] {1, 2, 3, 4, 5};
        fuel_cost = new int[] {3, 4, 5, 1, 2};
        System.out.println(logger.apply(gas_store, fuel_cost) + ", Result - " + canCompleteCircuit(gas_store, fuel_cost));
        System.out.println("Optimal: " + logger.apply(gas_store, fuel_cost) + ", Result - " + canCompleteCircuitOptimal(gas_store, fuel_cost));
        //
        gas_store = new int[] {2, 3, 4};
        fuel_cost = new int[] {3, 4, 3};
        System.out.println(logger.apply(gas_store, fuel_cost) + ", Result - " + canCompleteCircuit(gas_store, fuel_cost));
        System.out.println("Optimal: " + logger.apply(gas_store, fuel_cost) + ", Result - " + canCompleteCircuitOptimal(gas_store, fuel_cost));
    }

}
