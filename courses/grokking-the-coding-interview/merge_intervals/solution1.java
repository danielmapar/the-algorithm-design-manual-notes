package merge_intervals;

import java.util.*;

class MergeIntervals {

    public static List<Interval> mergeIntervals(List<Interval> intervals) {

        List<Interval> mergedIntervals = new LinkedList<Interval>();

        if (intervals.size() == 0) return mergedIntervals;

        // Sort intervals by the beginning of each interval
        Collections.sort(intervals);

        for (Interval interval : intervals) {
            if (mergedIntervals.size() == 0) {
                mergedIntervals.add(new Interval(interval));
                continue;
            }
            Interval mergedInterval = mergedIntervals.get(mergedIntervals.size()-1);
            if (interval.mem[0] >= mergedInterval.mem[0] && interval.mem[0] <= mergedInterval.mem[1] &&
                interval.mem[1] <= mergedInterval.mem[1]) {
            }
            else if (interval.mem[0] >= mergedInterval.mem[0] && interval.mem[0] <= mergedInterval.mem[1] &&
                interval.mem[1] >= mergedInterval.mem[1]) {
                mergedInterval.mem[1] = interval.mem[1];
            }
            else if (interval.mem[0] <= mergedInterval.mem[0] && interval.mem[1] > interval.mem[0] &&
                        interval.mem[1] <= mergedInterval.mem[1]) {
                mergedInterval.mem[0] = interval.mem[0];
            }
            else if (interval.mem[0] <= mergedInterval.mem[0] && interval.mem[1] >= mergedInterval.mem[1]) {
                mergedInterval.mem[0] = interval.mem[0];
                mergedInterval.mem[1] = interval.mem[1];
            }
            else mergedIntervals.add(new Interval(interval));
        }
        return mergedIntervals;
    }


    public static void main(String[] args) {
        System.out.println(mergeIntervals(
            new ArrayList<Interval>() {{
                add(new Interval(1, 4));
                add(new Interval(2, 5));
                add(new Interval(7, 9));
            }}
        ));
        System.out.println("--------");
        System.out.println(mergeIntervals(
            new ArrayList<Interval>() {{
                add(new Interval(6, 7));
                add(new Interval(2, 4));
                add(new Interval(5, 9));
            }}
        ));
        System.out.println("--------");
        System.out.println(mergeIntervals(
            new ArrayList<Interval>() {{
                add(new Interval(1, 4));
                add(new Interval(2, 6));
                add(new Interval(3, 5));
            }}
        ));
    }
}

class Interval implements Comparable<Interval> {

    @Override
    public int compareTo(Interval o) {
        if (o.mem[0] < this.mem[0])
            return 0;
        else
            return -1;
    }

    public Integer[] mem = new Integer[2];

    public Interval(int a, int b) {
        this.mem[0] = a;
        this.mem[1] = b;
    }

    public Interval(Interval interval) {
        this.mem[0] = interval.mem[0];
        this.mem[1] = interval.mem[1];
    }

    @Override
    public String toString() {
        return "[" + mem[0] + ", " + mem[1] + "]";
    }

}
