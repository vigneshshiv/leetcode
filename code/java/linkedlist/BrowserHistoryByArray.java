package code.java.linkedlist;

/**
 * https://leetcode.com/problems/design-browser-history/
 */
public class BrowserHistoryByArray {

    private String[] history = new String[5000];
    private int cur = 0, end = 0;

    public BrowserHistoryByArray(String homepage) {
        history[cur] = homepage;
    }

    public void visit(String url) {
        history[++cur] = url;
        end = cur;
    }

    public String back(int steps) {
        cur = Math.max(0, cur - steps);
        return history[cur];
    }

    public String forward(int steps) {
        cur = Math.min(end, cur + steps);
        return history[cur];
    }

    public static void main(String[] args) {
        BrowserHistoryByArray browserHistory = new BrowserHistoryByArray("leetcode.com");
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
