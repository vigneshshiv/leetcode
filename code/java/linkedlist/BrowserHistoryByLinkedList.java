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
        last += 1; // Last accessed page index
        size = last;
    }

    public String back(int steps) {
        if (steps > last) {
            current = head;
            last = 0; // Reset to zero
            return current.url;
        }
        boolean useHead = steps > last / 2;
        current = useHead ? getPageFromHead(last - steps, true) : getPageFromTail(steps, false);
        last -= steps; // Update last accessed page index
        return current.url;
    }

    public String forward(int steps) {
        if (tail == current) {
            return current.url;
        }
        current = (last + steps > size/2) ? getPageFromTail(size - (last + steps), true) : getPageFromHead(steps, false);
        // last += steps; // Update last accessed page index
        if (steps < last) {
            last += steps;
        }
        return current.url;
    }

    private Page getPageFromHead(int step, boolean useHead) {
        Page page = useHead ? head : current;
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
        // test1();
        // test2();
        // test3();
        test4();
    }

    private static void test1() {
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
        System.out.println();
    }

    private static void test2() {
        BrowserHistoryByLinkedList browserHistory = new BrowserHistoryByLinkedList("hdqqhm.com");
        browserHistory.visit("yltqtsj.com");
        System.out.println("1 step forward - " + browserHistory.forward(1));
        System.out.println("1 step back - " + browserHistory.back(1));
        browserHistory.visit("cyv.com");
        browserHistory.visit("vbpvni.com");
        browserHistory.visit("opy.com");
        browserHistory.visit("kbyzp.com");
        System.out.println("7 steps back - " + browserHistory.back(7));
        browserHistory.visit("fchhxaz.com");
        System.out.println("6 steps back - " + browserHistory.back(6));
        System.out.println("9 steps forward - " + browserHistory.forward(9));
        browserHistory.visit("rg.com");
        browserHistory.visit("oemqn.com");
        browserHistory.visit("hyqsb.com");
        System.out.println();
    }

    private static void test3() {
        BrowserHistoryByLinkedList browserHistory = new BrowserHistoryByLinkedList("jrbilt.com");
        browserHistory.visit("uiza.com");
        System.out.println("3 steps forward - " + browserHistory.forward(3));
        System.out.println("3 steps forward - " + browserHistory.forward(3));
        browserHistory.visit("fveyl.com");
        browserHistory.visit("hyhqfqf.com");
        System.out.println("3 steps back - " + browserHistory.back(3));
        browserHistory.visit("cccs.com");
        browserHistory.visit("bivz.com");
        System.out.println("6 steps forward - " + browserHistory.forward(6));
        System.out.println("1 step back - " + browserHistory.back(1));
        browserHistory.visit("cmbw.com");
        browserHistory.visit("iywwwfn.com");
        browserHistory.visit("sktbhdx.com");
        System.out.println("8 steps forward - " + browserHistory.forward(8));
        System.out.println("10 steps forward - " + browserHistory.forward(10));
        browserHistory.visit("bskj.com");
        browserHistory.visit("thw.com");
        System.out.println("6 steps back - " + browserHistory.back(6));
        browserHistory.visit("hgesj.com");
        System.out.println("6 steps forward - " + browserHistory.forward(6));
        browserHistory.visit("ctb.com");
        browserHistory.visit("fllnc.com");
        browserHistory.visit("fs.com");
        System.out.println("7 steps back - " + browserHistory.back(7));
    }

    private static void test4() {
        BrowserHistoryByLinkedList browserHistory = new BrowserHistoryByLinkedList("momn.com");
        browserHistory.visit("bx.com");
        browserHistory.visit("bjyfmln.com");
        System.out.println("3 steps back - " + browserHistory.back(3));
        browserHistory.visit("ijtrqk.com");
        browserHistory.visit("dft.com");
        System.out.println("10 steps back - " + browserHistory.back(10));
        System.out.println("10 steps forward - " + browserHistory.forward(10));
        browserHistory.visit("yc.com");
        browserHistory.visit("yhl.com");
        browserHistory.visit("xynxvix.com");
        browserHistory.visit("izfscdv.com");
        browserHistory.visit("cdenhm.com");
        browserHistory.visit("ocgcjz.com");
        System.out.println("5 steps forward - " + browserHistory.forward(5));
        System.out.println("5 steps forward - " + browserHistory.forward(5));
        browserHistory.visit("gtd.com");
        System.out.println("9 steps back - " + browserHistory.back(9));
        browserHistory.visit("hfeour.com");
        browserHistory.visit("ghmh.com");
        browserHistory.visit("nnm.com");
        browserHistory.visit("knm.com");
        System.out.println("4 steps forward - " + browserHistory.forward(4));
        browserHistory.visit("cbtg.com");
        browserHistory.visit("acyvwod.com");
        browserHistory.visit("mydr.com");
    }

}
