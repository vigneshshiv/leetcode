package code.java.graphs;

/**
 * https://leetcode.com/problems/find-center-of-star-graph/
 */
public class StarGraphCenter {

    private static int findCenter(int[][] edges) {
        int[] edge1 = edges[0], edge2 = edges[1];
        return edge1[0] == edge2[0] ? edge1[0] : (edge1[1] == edge2[0]) ? edge1[1] : edge2[1];
    }

    public static void main(String[] args) {
        int[][] edges = {
                {1, 2}, {2, 3}, {4, 2}
        };
        int center = findCenter(edges);
        System.out.println("Center - " + center);
        //
        edges = new int[][] {
                {1, 2}, {5, 1}, {1, 3}, {1, 4}
        };
        center = findCenter(edges);
        System.out.println("Center - " + center);
    }

}
