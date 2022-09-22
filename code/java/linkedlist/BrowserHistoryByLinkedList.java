package code.java.linkedlist;

/**
 * https://leetcode.com/problems/design-browser-history/
 */
public class BrowserHistoryByLinkedList {

    private Page head, tail, current;
    private int size = 0, last = 0;

    public BrowserHistoryByLinkedList(String homepage) {
        Page page = new Page(homepage);
        head = page;
        tail = head;
        current = tail;
        size += 1;
        last = size; // Last accessed page index
    }

    public void visit(String url) {
        Page page = new Page(url);
        if (current != tail) {
            current.next = page;
            page.prev = current;
        } else {
            tail.next = page;
            page.prev = tail;
        }
        tail = page;
        current = page;
        size += 1;
        last = size; // Last accessed page index
    }

    public String back(int steps) {
        if (steps > last) {
            current = head;
            return current.url;
        }
        current = (steps > last/2) ? getPageFromHead(last - steps) : getPageFromTail(steps, false);
        last -= steps; // Update last accessed page index
        return current.url;
    }

    public String forward(int steps) {
        if (tail == current) {
            return current.url;
        }
        current = (last + steps > size/2) ? getPageFromTail(size - (last + steps), true) : getPageFromHead(steps);
        last += steps; // Update last accessed page index
        return current.url;
    }

    private Page getPageFromHead(int step) {
        Page page = current;
        for (int i = 0; i < step; i++) {
            page = page.next;
        }
        return page;
    }

    private Page getPageFromTail(int step, boolean useTail) {
        Page page = useTail ? tail : current;
        for (int i = 0; i < step; i++) {
            page = page.prev;
        }
        return page;
    }

    private class Page {
        private String url;
        private Page next;
        private Page prev;

        public Page(String url) {
            this.url = url;
        }

        public Page(String url, Page next, Page prev) {
            this.url = url;
            this.next = next;
            this.prev = prev;
        }
    }

    public static void main(String[] args) {
        BrowserHistoryByLinkedList browserHistory = new BrowserHistoryByLinkedList("leetcode.com");
        browserHistory.visit("google.com");
        browserHistory.visit("facebook.com");
        browserHistory.visit("youtube.com");
        System.out.println("1 step back - " + browserHistory.back(1));
        System.out.println("1 step back - " + browserHistory.back(1));
        System.out.println("1 step forward - " + browserHistory.forward(1));
        browserHistory.visit("linkedin.com");
        System.out.println("2 steps forward - " + browserHistory.forward(2));
        System.out.println("2 steps back - " + browserHistory.back(2));
        System.out.println("7 steps back - " + browserHistory.back(7));
    }

}
