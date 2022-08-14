package merge_intervals;

import java.util.*;

class ConflictingAppointments {

    public static Boolean canAttendAllAppointments(List<Interval> intervals) {

        if (intervals.size() <= 1) return true;

        Collections.sort(intervals);

        for (int i = 1; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            Interval prevInterval = intervals.get(i-1);
            if (prevInterval.end > interval.start) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(canAttendAllAppointments(new ArrayList<Interval>(){{
            add(new Interval(1, 4));
            add(new Interval(2, 5));
            add(new Interval(7,9));
        }}));
        System.out.println(canAttendAllAppointments(new ArrayList<Interval>(){{
            add(new Interval(6, 7));
            add(new Interval(2, 4));
            add(new Interval(8,12));
        }}));
        System.out.println(canAttendAllAppointments(new ArrayList<Interval>(){{
            add(new Interval(4, 5));
            add(new Interval(2, 3));
            add(new Interval(3,6));
        }}));
    }
}

class Interval implements Comparable<Interval> {
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
