package code.java.numbers;

/**
 * https://leetcode.com/problems/count-of-matches-in-tournament/
 */
public class TournamentMatchCount {

    private static int numberOfMatches(int n) {
        return n - 1;
    }

    public static void main(String[] args) {
        System.out.println("Teams - " + 7 + ", Total Matches - " + numberOfMatches(7));
        System.out.println("Teams - " + 14 + ", Total Matches - " + numberOfMatches(14));
        System.out.println("Teams - " + 5 + ", Total Matches - " + numberOfMatches(5));
    }

}
