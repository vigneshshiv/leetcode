package code.ctci.dynamic_programming;

import java.util.*;

public class StackBoxes {

    private static Comparator<Box> boxDescOrder = (x, y) -> y.height - x.height;

    /**
     * Time complexity: O(b * s), where b is the number of boxes and s is consecutive stack order for any b
     * Space complexity: O(b^2),
     */
    private static int createStackMemo(List<Box> boxes) {
        // Sort
        Collections.sort(boxes, boxDescOrder);
        boxes.stream().forEach(box -> System.out.println(box.toString()));
        int[] memo = new int[boxes.size()];
        int maxHeight = 0;
        for (int idx = 0; idx < boxes.size(); idx++) {
            int height = createStack(boxes, idx, memo);
            maxHeight = Math.max(maxHeight, height);
        }
        return maxHeight;
    }

    private static int createStack(List<Box> boxes, int bottomIndex, int[] memo) {
        if (bottomIndex < boxes.size() && memo[bottomIndex] > 0) {
            return memo[bottomIndex];
        }
        Box bottom = boxes.get(bottomIndex);
        int maxHeight = 0;
        for (int idx = bottomIndex + 1; idx < boxes.size(); idx++) {
            if (boxes.get(idx).canBeAbove(bottom)) {
                int height = createStack(boxes, idx, memo);
                maxHeight = Math.max(maxHeight, height);
            }
        }
        maxHeight += bottom.height;
        memo[bottomIndex] = maxHeight;
        return maxHeight;
    }

    /**
     * Another Approach
     */
    private static int createStackModeration(List<Box> boxes) {
        // Sort
        Collections.sort(boxes, boxDescOrder);
        boxes.stream().forEach(box -> System.out.println(box.toString()));
        int[] memo = new int[boxes.size()];
        return createStack(boxes, null, 0, memo);
    }

    private static int createStack(List<Box> boxes, Box bottom, int offset, int[] memo) {
        if (offset >= boxes.size()) {
            return 0;
        }
        // Height with this bottom
        Box newBottom = boxes.get(offset);
        int heightWithBottom = 0;
        if (Objects.isNull(bottom) || newBottom.canBeAbove(bottom)) {
            if (memo[offset] == 0) {
                memo[offset] = createStack(boxes, newBottom, offset + 1, memo);
                memo[offset] += newBottom.height;
            }
            heightWithBottom = memo[offset];
        }
        // Height without this bottom
        int heightWithoutBottom = createStack(boxes, bottom, offset + 1, memo);
        return Math.max(heightWithBottom, heightWithoutBottom);
    }

    public static void main(String[] args) {
        Box[] boxesList = {
                new Box(6, 4, 4),
                new Box(8, 6, 2),
                new Box(5, 3, 3),
                new Box(7, 8, 3),
                new Box(4, 2, 2),
                new Box(9, 7, 3)
        };
        List<Box> boxes = new ArrayList<>();
        Arrays.stream(boxesList).forEach(box -> boxes.add(box));
        int height = createStackMemo(boxes);
        System.out.println("Height - " + height);
        //
        height = createStackModeration(boxes);
        System.out.println("Height - " + height);
    }

}

class Box {
    public int width;
    public int height;
    public int depth;

    public Box() {

    }

    public Box(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public boolean canBeUnder(Box other) {
        return width > other.width && height > other.height && depth > other.depth;
    }

    public boolean canBeAbove(Box other) {
        if (Objects.isNull(other)) return true;
        return width < other.width && height < other.height && depth < other.depth;
    }

    @Override
    public String toString() {
        return String.format("Box (Width - %d, Height - %d, Depth - %d)", width, height, depth);
    }

}