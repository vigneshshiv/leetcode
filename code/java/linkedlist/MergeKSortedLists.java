package code.java.linkedlist;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 */
public class MergeKSortedLists {

    /**
     * Priority Queue
     */
    private static LinkedListNode mergeKLists(LinkedListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        // Min heap ordering
        PriorityQueue<LinkedListNode> pq = new PriorityQueue<>((x, y) -> x.data - y.data);
        LinkedListNode head = new LinkedListNode(-1);
        LinkedListNode current = head;
        // Add it to queue, 0th index elements sorted order
        for (LinkedListNode node : lists) {
            if (node != null) pq.add(node);
        }
        while (!pq.isEmpty()) {
            current.next = pq.poll();
            current = current.next;
            // If node next is available, add it queue and maintain min heap order
            if (current.next != null) {
                pq.add(current.next);
            }
        }
        return head.next;
    }

    private static LinkedListNode mergeKListsRecursion(LinkedListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeLists(lists, 0, lists.length - 1);
    }

    private static LinkedListNode mergeLists(LinkedListNode[] lists, int low, int high) {
        if (high < low) return null;
        if (low == high) return lists[low];
        int mid = low + (high - low) / 2;
        LinkedListNode L = mergeLists(lists, low, mid);
        LinkedListNode R = mergeLists(lists, mid + 1, high);
        return merge(L, R);
    }

    private static LinkedListNode merge(LinkedListNode L, LinkedListNode R) {
        LinkedListNode head = new LinkedListNode(-1);
        LinkedListNode current = head;
        while (L != null && R != null) {
            if (L.data < R.data) {
                current.next = L;
                L = L.next;
            } else {
                current.next = R;
                R = R.next;
            }
            current = current.next;
        }
        current.next = (L != null) ? L : R;
        return head.next;
    }

    public static void main(String[] args) {
        int[][] inputData = {
                {1, 4, 5}, {1, 3, 4}, {2, 6}
        };
        LinkedListNode[] lists = new LinkedListNode[inputData.length];
        int i = 0;
        for (int[] input : inputData) {
            lists[i++] = LinkedListNode.createNodes(input);
        }
        Arrays.stream(lists).forEach(l -> LinkedListNode.printData(l));
        LinkedListNode result = mergeKLists(lists);
        LinkedListNode.printData(result);
        //
        lists = new LinkedListNode[inputData.length];
        i = 0;
        for (int[] input : inputData) {
            lists[i++] = LinkedListNode.createNodes(input);
        }
        result = mergeKListsRecursion(lists);
        LinkedListNode.printData(result);
        System.out.println();
        //
        lists = new LinkedListNode[0];
        Arrays.stream(lists).forEach(l -> LinkedListNode.printData(l));
        result = mergeKLists(lists);
        LinkedListNode.printData(result);
        lists = new LinkedListNode[0];
        result = mergeKListsRecursion(lists);
        LinkedListNode.printData(result);
        System.out.println();
        //
        lists = null;
        result = mergeKLists(lists);
        LinkedListNode.printData(result);
        result = mergeKListsRecursion(lists);
        LinkedListNode.printData(result);
    }

}
