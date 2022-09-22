package code.java.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/design-browser-history/
 */
public class BrowserHistoryByList {

    private List<String> history = new ArrayList<>();
    private int cur, end;

    public BrowserHistoryByList(String homepage) {
        history.add(homepage);
    }

    public void visit(String url) {
        if (++cur < history.size()) {
            history.set(cur, url);
        } else {
            history.add(url);
        }
        end = cur;
    }

    public String back(int steps) {
        cur = Math.max(0, cur - steps);
        return history.get(cur);
    }

    public String forward(int steps) {
        cur = Math.min(end, cur + steps);
        return history.get(cur);
    }

    public static void main(String[] args) {
        BrowserHistoryByList browserHistory = new BrowserHistoryByList("leetcode.com");
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
