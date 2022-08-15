package merge_intervals;

import java.util.*;

class MinimumMeetingRooms {


    public static int minNumberOfRooms(Interval[] intervals) {
        Arrays.sort(intervals);

        Queue<Integer> rooms = new PriorityQueue<Integer>(intervals.length); // make it a max heap by doing Collections.reverseOrder()
        Integer numberOfRooms = 0;

        // [[4,5], [2,3], [2,4], [3,5]]

        // [[2,4], [2,3], [3,5], [4,5]]
        for (Interval interval : intervals) {
            numberOfRooms = Math.max(numberOfRooms, rooms.size());
            if (rooms.size() > 0 && rooms.peek() <= interval.start) rooms.remove();
            rooms.add(interval.end);
        }
        return numberOfRooms;
    }

    public static void main(String[] args) {

        System.out.println(
            minNumberOfRooms(new Interval[]{
                new Interval(1, 4),
                new Interval(2, 5),
                new Interval(7,9)
            }
        ));
        System.out.println(
            minNumberOfRooms(new Interval[]{
                new Interval(6, 7),
                new Interval(2, 4),
                new Interval(8,12)
            }
        ));
        System.out.println(
            minNumberOfRooms(new Interval[]{
                new Interval(4, 5),
                new Interval(2, 3),
                new Interval(2,4),
                new Interval(3,5)
            }
        ));

    }

}

class Interval implements Comparable<Interval>{
    public Integer start;
    public Integer end;

    public Interval(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[ " + this.start + ", " + this.end + " ]"; 
    }

    @Override
    public int compareTo(Interval o) {
        return this.start > o.start ? 1 : -1;
    }

}