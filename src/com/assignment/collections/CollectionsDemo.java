package com.assignment.collections;

import java.util.*;

/** Standalone demo of the main Java Collections Framework classes (no database needed). */
public class CollectionsDemo {

    public static void run() {
        System.out.println("\n===== 1. ArrayList =====");
        List<String> arrayList = new ArrayList<>(List.of("Java", "Python", "C", "Java"));
        arrayList.add("Go");
        arrayList.remove("C");
        System.out.println(arrayList + " (allows duplicates, index based)");

        System.out.println("\n===== 2. LinkedList (List + Queue) =====");
        LinkedList<Integer> linkedList = new LinkedList<>(List.of(10, 20, 30));
        linkedList.addFirst(5);
        linkedList.addLast(40);
        System.out.println(linkedList + " first=" + linkedList.getFirst() + " last=" + linkedList.getLast());

        System.out.println("\n===== 3. HashSet / LinkedHashSet / TreeSet =====");
        int[] data = {5, 3, 9, 3, 1, 5};
        Set<Integer> hs = new HashSet<>();
        Set<Integer> lhs = new LinkedHashSet<>();
        Set<Integer> ts = new TreeSet<>();
        for (int n : data) { hs.add(n); lhs.add(n); ts.add(n); }
        System.out.println("HashSet       : " + hs + " (no order)");
        System.out.println("LinkedHashSet : " + lhs + " (insertion order)");
        System.out.println("TreeSet       : " + ts + " (sorted)");

        System.out.println("\n===== 4. HashMap / TreeMap =====");
        Map<String, Integer> marks = new HashMap<>();
        marks.put("Aman", 80);
        marks.put("Simran", 92);
        marks.put("Rohit", 75);
        marks.putIfAbsent("Aman", 99);   // ignored, key already exists
        marks.merge("Rohit", 5, Integer::sum);
        for (Map.Entry<String, Integer> e : marks.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
        System.out.println("TreeMap (sorted by key): " + new TreeMap<>(marks));

        System.out.println("\n===== 5. Queue, PriorityQueue, Deque =====");
        Queue<String> queue = new LinkedList<>();
        queue.offer("A"); queue.offer("B"); queue.offer("C");
        System.out.println("Queue poll (FIFO): " + queue.poll());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.addAll(List.of(40, 90, 10, 70));
        System.out.println("PriorityQueue (max first): " + pq.poll() + ", " + pq.poll());

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1); stack.push(2); stack.push(3);
        System.out.println("Stack (LIFO) pop: " + stack.pop());

        System.out.println("\n===== 6. Iterator =====");
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Iterator<Integer> it = nums.iterator();
        while (it.hasNext()) {
            if (it.next() % 2 == 0) it.remove();   // safe removal
        }
        System.out.println("After removing evens: " + nums);

        System.out.println("\n===== 7. Collections utility class =====");
        List<Integer> list = new ArrayList<>(List.of(4, 1, 8, 3));
        Collections.sort(list);
        System.out.println("sorted   : " + list);
        Collections.reverse(list);
        System.out.println("reversed : " + list);
        System.out.println("max=" + Collections.max(list) + " min=" + Collections.min(list));
        System.out.println("frequency of 8: " + Collections.frequency(list, 8));
    }
}
