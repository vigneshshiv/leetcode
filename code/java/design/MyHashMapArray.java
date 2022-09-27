package code.java.design;

/**
 * https://leetcode.com/problems/design-hashmap/
 */
public class MyHashMapArray {

    private int[] arr;

    public MyHashMapArray() {
        this.arr = new int[10_00_001];
    }

    /* Value will always be non-negative */
    public void put(int key, int value) {
        this.arr[key] = value + 1;
    }

    /* Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        return this.arr[key] - 1;
    }

    /* Remove the mapping of the specified value of key */
    public void remove(int key) {
        this.arr[key] = 0;
    }

}
