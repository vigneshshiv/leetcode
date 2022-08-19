package code.ctci.linkedlist;

import java.util.LinkedList;

public class LinkedListExample {

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        // Test Data
        linkedList.add("Tech");
        linkedList.add("Travel");
        linkedList.add("TableTennis");
        System.out.println("Lists initial values - " + linkedList);
        // Peek, PeekFirst, Element
        linkedList.addFirst("Family");
        linkedList.addLast("Gym");
        System.out.println("added to First (peek) - " + linkedList.peek());
        System.out.println("get First - " + linkedList.getFirst());
        System.out.println("get element - " + linkedList.element());
        System.out.println("peek vs peekFirst are same? " + (linkedList.peek() == linkedList.peekFirst()));
        // Peek Last, Get Last
        System.out.println("added to Last (peek) - " + linkedList.peekLast());
        System.out.println("get Last - " + linkedList.getLast());
        System.out.println("peek Last vs  getLast are same? " + (linkedList.peekLast() == linkedList.getLast()));
        System.out.println("Lists after 1st changes - " + linkedList);
        // Add, Offer, OfferFirst, Set
        linkedList.add("Investments");
        System.out.println("Lists after 2nd changes - " + linkedList);
        System.out.println("peek - " + linkedList.peek() + ", first - " + linkedList.getFirst() + ", last - " + linkedList.getLast());
        linkedList.add(1, "Startup");
        System.out.println("Lists after 3rd changes - " + linkedList);
        linkedList.offer("Passive Income Stream");
        System.out.println("Lists after 4th changes - " + linkedList);
        linkedList.offerFirst("Being Happy & Healthy");
        linkedList.offerLast("Calculated Risk");
        linkedList.set(2, "Startup Business");
        System.out.println("Lists after 5th changes - " + linkedList);
        // Poll, PollFirst, Poll Last
        System.out.println("Poll (Being Happy & Healthy) - " + linkedList.poll());
        System.out.println("Poll Last (Calculated Risk) - " + linkedList.pollLast());
        System.out.println("Lists after 6th changes - " + linkedList);
        System.out.println("Poll First (Family) - " + linkedList.pollFirst());
        System.out.println("Lists after 7th changes - " + linkedList);
        // Remove, Remove First
        System.out.println("Remove (Startup) - " + linkedList.remove());
        System.out.println("Remove First (Tech) - " + linkedList.removeFirst());
        System.out.println("Remove Last (Passive Income Stream) - " + linkedList.removeLast());
        System.out.println("Lists after 8th changes - " + linkedList);
    }

}
