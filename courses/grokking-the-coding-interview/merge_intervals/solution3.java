package merge_intervals;

import java.util.*;

class IntervalsIntersection {

    public static List<Interval> findIntersectIntervals(List<Interval> list1, List<Interval> list2) {
        List<Interval> intersect = new ArrayList<Interval>();

        // arr1=[[1, 3], [5, 6], [7, 9]]
        // arr2=[[2, 3], [5, 7]]
        // [2, 3], [5, 6], [7, 7]

        // arr1=[[1, 3], [5, 7], [9, 12]]
        // arr2=[[5, 10]]
        // [5, 7], [9, 10]

        // arr1=[[1, 3], [3, 7], [9, 12]]
        // arr2=[[2, 10]]
        // [5, 7], [9, 10]


        int index1 = 0;
        int index2 = 0;

        while (index1 < list1.size() && index2 < list2.size()) {
            Interval list1Interval = list1.get(index1);
            Interval list2Interval = list2.get(index2);

            if (list1Interval.start <= list2Interval.end && list1Interval.end >= list2Interval.start) {
                Interval interval = new Interval(
                    Math.max(list1Interval.start, list2Interval.start), 
                    Math.min(list1Interval.end, list2Interval.end)
                );
                Interval prevInterval = null;
                if (intersect.size() > 0) prevInterval = intersect.get(intersect.size()-1);

                if (prevInterval != null && prevInterval.end == interval.start) prevInterval.end = interval.end;
                else intersect.add(interval);
            } 
            if (list1Interval.end <= list2Interval.end) index1++;
            else index2++;
        }
        
        return intersect;
    }
 

    public static void main(String[] args) {

        System.out.println(findIntersectIntervals(new ArrayList<Interval>() {{
            add(new Interval(1, 3));
            add(new Interval(5, 6));
            add(new Interval(7, 9));
        }}, new ArrayList<Interval>() {{
            add(new Interval(2, 3));
            add(new Interval(5, 7));
        }}));

        System.out.println(findIntersectIntervals(new ArrayList<Interval>() {{
            add(new Interval(1, 3));
            add(new Interval(5, 7));
            add(new Interval(9, 12));
        }}, new ArrayList<Interval>() {{
            add(new Interval(5, 10));
        }}));

        System.out.println(findIntersectIntervals(new ArrayList<Interval>() {{
            add(new Interval(1, 3));
            add(new Interval(3, 7));
            add(new Interval(9, 12));
        }}, new ArrayList<Interval>() {{
            add(new Interval(2, 10));
        }}));
    }
}

class Interval {
    public int start;
    public int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[ " + this.start + ", " + this.end + " ]";
    }
}