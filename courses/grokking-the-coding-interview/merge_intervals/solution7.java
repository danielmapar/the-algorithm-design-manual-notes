package merge_intervals;

import java.util.*;

class EmployeeFreeTime {

    public static List<Interval> findFreeIntervals(Interval[] intervals) {

        List<Interval> freeIntervals = new ArrayList<Interval>();

        if (intervals.length <= 1) return freeIntervals;

        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1.start, o2.start));

        Interval prevInterval = null;
        for (Interval interval : intervals) {
            if (prevInterval != null && interval.start <= prevInterval.end) 
                prevInterval.end = Math.max(prevInterval.end, interval.end);
            else {
                if (prevInterval != null) freeIntervals.add(new Interval(prevInterval.end, interval.start));
                prevInterval = new Interval(interval);
            }
        }

        return freeIntervals;
    }
    public static void main(String[] args) {
        System.out.println(findFreeIntervals(new Interval[] {
            new Interval(1, 3),
            new Interval(5, 6),
            new Interval(2, 3),
            new Interval(6, 8),
        }));
        System.out.println(findFreeIntervals(new Interval[] {
            new Interval(1, 3),
            new Interval(9, 12),
            new Interval(2, 4),
            new Interval(6, 8),
        }));
        System.out.println(findFreeIntervals(new Interval[] {
            new Interval(1, 3),
            new Interval(2, 4),
            new Interval(3, 5),
            new Interval(7, 9),
        }));
    }
}

class Interval {
    public Integer start;
    public Integer end;

    public Interval(Interval interval) {
        this.start = interval.start.intValue();
        this.end = interval.end.intValue();
    }

    public Interval(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[ " + this.start + ", " + this.end + " ]";
    }
}