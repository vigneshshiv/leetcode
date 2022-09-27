package code.java.design;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * https://leetcode.com/problems/design-hashmap/
 */
public class MyHashMap {

    private static final int INITIAL_CAPACITY = 1 << 4;
    private static final int MAXIMUM_CAPACITY = 1 << 16;
    private static final float LOAD_FACTOR = 0.75f;

    private ListNode[] arr;

    private int capacity = INITIAL_CAPACITY;
    private int threshold = (int) (INITIAL_CAPACITY * LOAD_FACTOR); // Default
    private int shrink_threshold = 0;
    private int size = 0;

    public MyHashMap() {
        arr = new ListNode[INITIAL_CAPACITY];
    }

    /* Value will always be non-negative */
    public void put(int key, int value) {
        int _key = hash(key);
        ListNode node = arr[_key];
        if (node != null) {
            node.add(key, value);
        } else {
            node = new ListNode(key, value);
            arr[_key] = node;
        }
        // Threshold Range for Table doubling
        if (++size > threshold) {
            grow();
        }
    }

    /* Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int _key = hash(key);
        ListNode node = arr[_key];
        Node item;
        if (node == null || (item = node.find(key)) == null) {
            return -1;
        }
        return item.val;
    }

    /* Remove the mapping of the specified value of key */
    public void remove(int key) {
        int _key = hash(key);
        ListNode node = arr[_key];
        if (node == null) return;
        node.remove(key);
        // If head is null, then remove that item from array
        if (node.head == null) {
            arr[_key] = null;
        }
        // Shrink Threshold Range for Table half reducing
        if (--size < shrink_threshold) {
            shrink();
        }
    }

    /* Get size */
    public int size() {
        return size;
    }

    /* Hash key function */
    private int hash(int key) {
        return key % capacity;
    }

    private void grow() {
        if ((capacity <<= 1) >= MAXIMUM_CAPACITY) {
            capacity = MAXIMUM_CAPACITY;
        }
        ListNode[] oldArr = arr;
        ListNode[] newArr = new ListNode[capacity];
        int count = 0;
        for (int i = 0; i < oldArr.length; i++) {
            ListNode node = oldArr[i];
            if (node != null) {
                Node current = node.head;
                while (current != null) {
                    int _key = hash(current.key);
                    node = newArr[_key]; // New Key check
                    if (node != null) {
                        node.add(current.key, current.val);
                    } else {
                        node = new ListNode(current.key, current.val);
                        newArr[_key] = node;
                    }
                    current = current.next;
                }
                count += 1;
            }
        }
        // Update next threshold
        threshold = (int) (capacity * LOAD_FACTOR);
        arr = newArr;
        size = count; // Actual size
    }

    private void shrink() {
        if ((capacity >>= 1) <= INITIAL_CAPACITY) {
            capacity = INITIAL_CAPACITY;
        }
        ListNode[] oldArr = arr;
        ListNode[] newArr = new ListNode[capacity];
        int count = 0;
        for (int i = 0; i < oldArr.length; i++) {
            ListNode node = oldArr[i];
            if (node != null) {
                Node current = node.head;
                while (current != null) {
                    int _key = hash(current.key);
                    node = newArr[_key]; // New Key check
                    if (node != null) {
                        node.add(current.key, current.val);
                    } else {
                        node = new ListNode(current.key, current.val);
                        newArr[_key] = node;
                    }
                    current = current.next;
                }
                count += 1;
            }
        }
        // Update next shrink threshold
        if (capacity == INITIAL_CAPACITY) {
            shrink_threshold = 0;
        } else {
            shrink_threshold = (int) ((capacity >> 1) * LOAD_FACTOR);
        }
        arr = newArr;
        size = count;
    }

    private class Node {
        int key, val;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public Node(int key, int val, Node next) {
            this(key, val);
            this.next = next;
        }
    }

    private class ListNode {
        private Node head;
        private Node tail;

        private int size = 0;

        private ListNode(int key, int val) {
            head = new Node(key, val);
            tail = head;
            size += 1;
        }

        private void add(int key, int val) {
            boolean updateNode = update(key, val);
            if (!updateNode) {
                Node node = new Node(key, val);
                if (head == tail) {
                    head.next = node; // 2nd Node
                } else {
                    tail.next = node;
                }
                tail = node;
            }
            size += updateNode ? 0 : 1;
        }

        private boolean update(int key, int val) {
            Node node = find(key);
            if (node != null) {
                node.val = val;
                return true;
            }
            return false;
        }

        private Node find(int key) {
            if (head == null) return null;
            Node current = head;
            while (current != null && current.key != key) {
                current = current.next;
            }
            return current;
        }

        private void remove(int key) {
            if (head == null) return;
            if (head.key == key) {
                if (head == tail) {
                    head = null; tail = null;
                } else {
                    head = head.next;
                }
            } else {
                Node current = head, prev = null;
                while (current != null && current.key != key) {
                    prev = current;
                    current = current.next;
                }
                if (prev.next == null) return;
                prev.next = prev.next.next;
                // Last Node
                if (prev.next == null) {
                    if (tail.key == key) {
                        tail = prev;
                    } else {
                        tail = head;
                    }
                }
            }
            size -= 1;
        }
    }

    public static void main(String[] args) {
        // testEasy();
        // test100();
        // Test All
        test1000();
        // test5000();
    }

    private static void testEasy() {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        System.out.println("Get size - " + hashMap.size());
        //
        System.out.println("Get 1 - " + hashMap.get(1));
        System.out.println("Get 3 - " + hashMap.get(3));
        //
        hashMap.put(2, 1);
        System.out.println("Get 2 - " + hashMap.get(2));
        //
        hashMap.remove(2);
        System.out.println("Get 2 - " + hashMap.get(2));
    }

    private static void test100() {
        MyHashMap hashMap = new MyHashMap();
        hashMap.remove(27);
        hashMap.put(65, 65);
        hashMap.remove(19);
        hashMap.remove(0);
        System.out.println("Get Key - 18 " + hashMap.get(18) + ", Correct - " + (hashMap.get(18) == -1));
        hashMap.remove(3);
        hashMap.put(42, 0);
        System.out.println("Get Key - 19 " + hashMap.get(19) + ", Correct - " + (hashMap.get(19) == -1));
        hashMap.remove(42);
        hashMap.put(17, 90);
        System.out.println("1st Block: Size - " + hashMap.size());
        System.out.println("All Ok 1");
        System.out.println();
        //
        hashMap.put(31, 76);
        hashMap.put(48, 71);
        hashMap.put(5, 50);
        hashMap.put(7, 68);
        hashMap.put(73, 74);
        hashMap.put(85, 18);
        hashMap.put(74, 95);
        hashMap.put(84, 82);
        hashMap.put(59, 29);
        hashMap.put(71, 71);
        System.out.println("2nd Block: Size - " + hashMap.size());
        System.out.println("All Ok 2");
        System.out.println();
        //
        hashMap.remove(42); // Already removed in 1st Block
        hashMap.put(51, 40);
        hashMap.put(33, 76);
        System.out.println("Get Key - 17 " + hashMap.get(17) + ", Correct - " + (hashMap.get(17) == 90));
        hashMap.put(89, 95);
        System.out.println("Get Key - 95 " + hashMap.get(95) + ", Correct - " + (hashMap.get(95) == -1));
        hashMap.put(30, 31);
        hashMap.put(37, 99);
        System.out.println("Get Key - 51 " + hashMap.get(51) + ", Correct - " + (hashMap.get(51) == 40));
        hashMap.put(95, 35);
        System.out.println("3rd Block: Size - " + hashMap.size());
        System.out.println("All Ok 3");
        System.out.println();
        //
        hashMap.remove(65); // Added in 1st block, removed here
        hashMap.remove(81); // Not added before
        hashMap.put(61, 46);
        hashMap.put(50, 33);
        System.out.println("Get Key - 59 " + hashMap.get(59) + ", Correct - " + (hashMap.get(59) == 29));
        hashMap.remove(5); // Added in 1st block, removed here
        hashMap.put(75, 89);
        hashMap.put(80, 17);
        hashMap.put(35, 94);
        System.out.println("Get Key - 80 " + hashMap.get(80) + ", Correct - " + (hashMap.get(80) == 17));
        System.out.println("All Ok 4");
        System.out.println();
        //
        hashMap.put(19, 68);
        hashMap.put(13, 17);
        hashMap.remove(70); // Not added before
        hashMap.put(28, 35);
        hashMap.remove(99); // Not added before
        hashMap.remove(37); // Added in 3rd block
        hashMap.remove(13); // Added in this block
        hashMap.put(90, 83);
        hashMap.remove(41); // Not added before
        System.out.println("Get Key - 50 " + hashMap.get(50) + ", Correct - " + (hashMap.get(50) == 33));
        System.out.println("All Ok 5");
        System.out.println();
        //
        hashMap.put(29, 98);
        hashMap.put(54, 72);
        hashMap.put(6, 8);
        System.out.println("Before update Get Key - 51 " + hashMap.get(51) + ", Correct - " + (hashMap.get(51) == 40));
        hashMap.put(51, 88); // Added in 1st block, updated here
        System.out.println("Updated Get Key - 51 " + hashMap.get(51) + ", Correct - " + (hashMap.get(51) == 88));
        hashMap.remove(13); // Already removed
        hashMap.put(8, 22);
        hashMap.get(85);
        System.out.println("Get Key - 85 " + hashMap.get(85) + ", Correct - " + (hashMap.get(85) == 18));
        hashMap.put(85, 1001);
        System.out.println("Updated Get Key - 85 " + hashMap.get(85) + ", Correct - " + (hashMap.get(85) == 1001));
        hashMap.put(31, 22);
        hashMap.put(60, 9);
        System.out.println("Get Key - 96 " + hashMap.get(96) + ", Correct - " + (hashMap.get(96) == -1));
        System.out.println("All Ok 6");
        System.out.println();
        //
        hashMap.put(6, 35);
        hashMap.remove(54); // Added in block 6, removed here
        System.out.println("Get Key - 15 " + hashMap.get(15) + ", Correct - " + (hashMap.get(15) == -1));
        System.out.println("Get Key - 28 " + hashMap.get(28) + ", Correct - " + (hashMap.get(28) == 35));
        hashMap.remove(51); // Updated in 6th block, removed here
        hashMap.put(80, 69);
        hashMap.put(58, 92);
        hashMap.put(13, 12);
        hashMap.put(91, 56);
        hashMap.put(83, 52);
        System.out.println("All Ok 7");
        System.out.println();
        //
        System.out.println("Before update Get Key - 8 " + hashMap.get(8) + ", Correct - " + (hashMap.get(8) == 22));
        hashMap.put(8, 48); // Added in 6th block, updated here
        System.out.println("Updated Get Key - 8 " + hashMap.get(8) + ", Correct - " + (hashMap.get(8) == 48));
        // hashMap.get(62);
        System.out.println("Get Key - 62 " + hashMap.get(62) + ", Correct - " + (hashMap.get(62) == -1));
        // hashMap.get(54); // Removed in 7th block
        System.out.println("Get Key - 54 " + hashMap.get(54) + ", Correct - " + (hashMap.get(54) == -1));
        hashMap.remove(25);
        hashMap.put(36, 4);
        hashMap.put(67, 68);
        hashMap.put(83, 36);
        hashMap.put(47, 58);
        System.out.println("Get Key - 82 " + hashMap.get(82) + ", Correct - " + (hashMap.get(82) == -1));
        hashMap.remove(36); // Added in this block, removed here
        System.out.println("All Ok 8");
        System.out.println();
        //
        hashMap.put(30, 85);
        System.out.println("Before update Get Key - 33 " + hashMap.get(33) + ", Correct - " + (hashMap.get(33) == 76));
        hashMap.put(33, 87); // Updated here
        System.out.println("Updated Get Key - 33 " + hashMap.get(33) + ", Correct - " + (hashMap.get(33) == 87));
        hashMap.put(42, 18);
        hashMap.put(68, 83);
        hashMap.put(50, 53);
        hashMap.put(32, 78);
        hashMap.put(48, 90);
        hashMap.put(97, 95);
        hashMap.put(13, 8);
        hashMap.put(15, 7);
        System.out.println("All Ok 9");
        System.out.println();
        //
        hashMap.remove(5);
        hashMap.remove(42);
        // hashMap.get(20);
        System.out.println("Get Key - 20 " + hashMap.get(20) + ", Correct - " + (hashMap.get(20) == -1));
        hashMap.remove(65);
        hashMap.put(57, 9);
        hashMap.put(2, 41);
        hashMap.remove(6);
        // hashMap.get(33);
        System.out.println("Updated Get Key - 33 " + hashMap.get(33) + ", Correct - " + (hashMap.get(33) == 87));
        hashMap.put(16, 44);
        hashMap.put(95, 30);
        System.out.println("All Ok 10");
    }

    private static void test1000() {
        int size = 1000;
        String[] input_op = readInputOpFile("F:\\Dreamplus\\Github\\leetcode\\code\\java\\design\\input_op_1000.txt", size);
        int[][] input_values = readInputValuesFile("F:\\Dreamplus\\Github\\leetcode\\code\\java\\design\\input_values_1000.txt", size);
        MyHashMap hashMap = new MyHashMap();
        for (int i = 0; i < input_op.length; i++) {
            String op = input_op[i];
            int[] values = input_values[i];
            if (op.equals("get")) {
                if (values[0] == 465) {
                    System.out.println("Got me get");
                    int val = hashMap.get(values[0]);
                    System.out.println("What is the val - " + val);
                }
                hashMap.get(values[0]);
            } else if (op.equals("put")) {
                if (values[0] == 465) {
                    System.out.println("Got me put");
                }
                hashMap.put(values[0], values[1]);
            } else {
                hashMap.remove(values[0]);
            }
        }
    }

    private static void test5000() {
        int size = 5000;
        String[] input_op = readInputOpFile("F:\\Dreamplus\\Github\\leetcode\\code\\java\\design\\input_op_5000.txt", size);
        int[][] input_values = readInputValuesFile("F:\\Dreamplus\\Github\\leetcode\\code\\java\\design\\input_values_5000.txt", size);
        MyHashMap hashMap = new MyHashMap();
        for (int i = 0; i < input_op.length; i++) {
            String op = input_op[i];
            int[] values = input_values[i];
            if (op.equals("get")) {
                if (values[0] == 44255) {
                    System.out.println("Got me get");
                    int val = hashMap.get(values[0]);
                    System.out.println("What is the val - " + val);
                }
                hashMap.get(values[0]);
            } else if (op.equals("put")) {
                if (values[0] == 44255) {
                    System.out.println("Got me put");
                }
                hashMap.put(values[0], values[1]);
            } else {
                if (values[0] == 44255) {
                    System.out.println("Got me remove");
                }
                hashMap.remove(values[0]);
            }
        }
    }

    private static String[] readInputOpFile(String fileName, int size) {
        String[] data = new String[size];
        //
        try {
            Path path = Paths.get(fileName);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            data = lines.get(0).split(",");
        } catch (Exception ioe) {
            System.out.println("Exception occurred");
        }
        return data;
    }

    private static int[][] readInputValuesFile(String fileName, int size) {
        int[][] data = new int[size][2];
        //
        try {
            Path path = Paths.get(fileName);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            String[] values = lines.get(0).split("#");
            int idx = 0;
            for (String value : values) {
                String[] input = value.split(",");
                int num1 = Integer.valueOf(input[0]);
                int num2 = input.length == 2 ? Integer.valueOf(input[1]) : 0;
                data[idx++] = new int[] { num1, num2 };
            }
        } catch (Exception ioe) {
            System.out.println("Exception occurred");
        }
        return data;
    }

}
