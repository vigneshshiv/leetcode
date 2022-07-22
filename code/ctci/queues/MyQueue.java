package code.ctci.queues;

public class MyQueue<T> {

    private static class QueueNode<T> {
        private T data;
        private QueueNode<T> next;

        public QueueNode(T data) {
            this.data = data;
        }
    }

    private QueueNode<T> first;
    private QueueNode<T> last;

    /**
     * Add an item to the end of the list
     */
    private void add(T item) {
        QueueNode<T> node = new QueueNode<T>(item);
        if (last != null) {
            last.next = node;
        }
        last = node;
        if (isEmpty()) first = last;
    }

    /**
     * Remove the first item in the list
     */
    private T remove() {
        if (isEmpty()) return null;
        T item = first.data;
        first = first.next;
        if (first == null) {
            last = null;
        }
        return item;
    }

    /**
     * Return the top item from the queue's front
     */
    private T peek() {
        if (isEmpty()) return null;
        return first.data;
    }

    /**
     * Return true if and only if the queue is empty
     */
    private boolean isEmpty() {
        return first == null;
    }

}
