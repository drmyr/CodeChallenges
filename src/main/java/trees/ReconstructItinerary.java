package trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class ReconstructItinerary {

    /**
     * https://leetcode.com/problems/reconstruct-itinerary/
     *
     * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure
     * and the arrival airports of one flight. Reconstruct the itinerary in order and return it.
     *
     * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK".
     * If there are multiple valid itineraries, you should return the itinerary that has the smallest
     * lexical order when read as a single string.
     *
     * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
     *
     * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
     * Example 1:
     *
     * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
     * Output: ["JFK","MUC","LHR","SFO","SJC"]
     *
     * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
     * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
     * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
     * @param itinerary
     * @return
     */
    public static String[] rebuildItinerary(final String[][] itinerary) {
        final Map<String, PriorityQueue<String>> itineraryMap = new HashMap<>();
        for(final String[] srcDestPair : itinerary) {
            itineraryMap.computeIfAbsent(srcDestPair[0], ignore -> new PriorityQueue<>()).offer(srcDestPair[1]);
        }
        final Deque<String> rebuiltItinerary = new ArrayDeque<>();
        final Stack<String> stack = new Stack<>();
        stack.push("JFK");

        while (!stack.empty()) {
            while (itineraryMap.containsKey(stack.peek()) && !itineraryMap.get(stack.peek()).isEmpty()) {
                stack.push(itineraryMap.get(stack.peek()).poll());
            }
            rebuiltItinerary.addFirst(stack.pop());
        }

        return rebuiltItinerary.toArray(String[]::new);
    }
}
