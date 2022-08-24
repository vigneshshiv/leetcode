package code.java.greedy;

import java.util.*;

/**
 * Given a list of intervals: [s, e] for meetings.
 *
 * Find the least number of meeting rooms required.
 */
public class MeetingRooms {

    /**
     * Time complexity: O(n log(n))
     * Space complexity: O(n^2)
     */
    private static int requiredMeetingRooms(int[][] meetings) {
        record Meeting(int timeframe, boolean occupied) {};
        List<Meeting> scheduledMeetings = new LinkedList<>();
        for (int[] meeting : meetings) {
            scheduledMeetings.add(new Meeting(meeting[0], true));
            scheduledMeetings.add(new Meeting(meeting[1], false));
        }
        // Sort by time frame
        Collections.sort(scheduledMeetings, Comparator.comparingInt(Meeting::timeframe));
        int current = 0;
        int required = 0;
        for (Meeting meeting : scheduledMeetings) {
            current += meeting.occupied() ? 1 : -1;
            required = Math.max(required, current);
        }
        return required;
    }

    public static void main(String[] args) {
        int[][] meetings = {
                {5, 10}, {15, 20}, {0, 30}
        }; // 2
        int meetingRoomsRequired = requiredMeetingRooms(meetings);
        System.out.println("Required meeting rooms - " + meetingRoomsRequired);
    }

}
