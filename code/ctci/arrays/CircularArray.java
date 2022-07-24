package code.ctci.arrays;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CircularArray<T> implements Iterable<T> {

    private class CircularArrayIterator<TI> implements Iterator<T> {
        /**
         * Current reflects the offset from the rotated head, not from the actual start of the raw array
         */
        private int _current = -1;

        private TI[] _items;

        public CircularArrayIterator(CircularArray<TI> array) {
            _items = array.items;
        }

        @Override
        public boolean hasNext() {
            return _current < _items.length -1 ;
        }

        @Override
        public T next() {
            _current++;
            TI item = (TI) _items[convert(_current)];
            return (T) item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation not supported");
        }
    }

    private T[] items;
    private int head = 0;

    public CircularArray(int size) {
        this.items = (T[]) new Object[size];
    }

    /**
     * Iterator
     */
    public Iterator<T> iterator() {
        return new CircularArrayIterator<T>(this);
    }

    /**
     * Change head position as per index
     */
    private int convert(int index) {
        if (index < 0) {
            index += items.length;
        }
        return (head + index) % items.length;
    }

    /**
     * Rotate to specified index
     */
    public void rotate(int index) {
        head = convert(index);
    }

    /**
     * Get T value
     */
    public T get(int index) {
        if (index < 0 || index > items.length) {
            throw new IndexOutOfBoundsException("Index out of range - " + index);
        }
        return (T) items[convert(index)];
    }

    /**
     * Set T value
     */
    public void set(int index, T value) {
        this.items[convert(index)] = value;
    }

    public static void main(String[] args) {
        int size = 7;
        CircularArray<Integer> circularArray = new CircularArray<>(size);
        for (int i = 0; i < size; i++) {
            circularArray.set(i, (i + 1));
        }
        System.out.println("Circular Array initial values are below... ");
        // Function
        Function<Integer, String> prefix = idx -> idx + (idx == 1 ? "st" : idx == 2 ? "nd" : idx == 3 ? "rd" : "th");
        for (int i = 0; i < size; i++) {
            System.out.println(prefix.apply(i) + " index value " + circularArray.get(i));
        }
        //
        circularArray.rotate(3);
        System.out.println("Circular Array modified, Head Index changed to 3 (act as 0th position now) - " + circularArray.get(0));
        circularArray.rotate(-2);
        System.out.println("Circular Array modified, Head Index changed to -2 (act as 1th position now) - " + circularArray.get(0));
        //
        int index = 0;
        for (Integer item : circularArray) {
            System.out.println(prefix.apply(index++) + " Item - " + (item));
        }
    }

}
