package code.java.stacks;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/asteroid-collision/
 */
public class AsteroidCollision {

    private static int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> q = new ArrayDeque<>();
        for (int a : asteroids) {
            while (!q.isEmpty() && a < 0 && q.peekLast() > 0) {
                int diff = a + q.peekLast();
                if (diff < 0) {
                    q.pollLast();
                } else if (diff > 0) {
                    a = 0;
                } else {
                    a = 0;
                    q.pollLast();
                }
            }
            if (a != 0) q.offer(a);
        }
        return q.stream().mapToInt(x -> x).toArray();
    }

    private static int[] asteroidCollisionOptimal(int[] asteroids) {
        Deque<Integer> q = new ArrayDeque<>();
        for (int a : asteroids) {
            while (!q.isEmpty() && q.peekLast() > 0 && q.peekLast() < -a) {
                q.pollLast();
            }
            if (q.isEmpty() || a > 0 || q.peekLast() < 0) {
                q.offer(a);
            } else if (a < 0 && q.peekLast() == -a) {
                q.pollLast();
            }
        }
        return q.stream().mapToInt(x -> x).toArray();
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (input, result) -> {
            System.out.println("Asteroids - " + Arrays.toString(input) + ", Result - " + Arrays.toString(result));
        };
        //
        int[] asteroids = {8, -8};
        int[] result = asteroidCollision(asteroids);
        logger.accept(asteroids, result);
        result = asteroidCollisionOptimal(asteroids);
        logger.accept(asteroids, result);
        //
        asteroids = new int[] {10, 2, -5};
        result = asteroidCollision(asteroids);
        logger.accept(asteroids, result);
        result = asteroidCollisionOptimal(asteroids);
        logger.accept(asteroids, result);
        //
        asteroids = new int[] {-1, 3, 2, -3};
        result = asteroidCollision(asteroids);
        logger.accept(asteroids, result);
        result = asteroidCollisionOptimal(asteroids);
        logger.accept(asteroids, result);
    }

}
