package merge_intervals;

import java.util.*;

class MaximumCPULoad {
    
    public static int getMaximumCPULoad(Job[] jobs) {

        if (jobs.length == 0) return 0;
        if (jobs.length == 1) return jobs[0].load;

        Arrays.sort(jobs, (o1, o2) -> Integer.compare(o1.start, o2.start));

        Queue<Job> jobsRunningQueue = new PriorityQueue<Job>(jobs.length, new JobComparator());

        Integer currentJobsLoad = 0;
        Integer maxJobsLoad = 0;
        for (Job job : jobs) {
            while (jobsRunningQueue.size() > 0) {
                if (job.start > jobsRunningQueue.peek().end) {
                    Job removedJob = jobsRunningQueue.remove();
                    currentJobsLoad -= removedJob.load;
                    continue;
                }
                break;
            }
            jobsRunningQueue.add(job);
            currentJobsLoad += job.load;

            maxJobsLoad = Math.max(maxJobsLoad, currentJobsLoad);
        }

        return maxJobsLoad;
    }

    public static void main(String[] args) {
        System.out.println(
            getMaximumCPULoad(new Job[] {
                new Job(1,4,3),
                new Job(2,5,4),
                new Job(7,9,6)
            })
        );
        System.out.println(
            getMaximumCPULoad(new Job[] {
                new Job(6,7,10),
                new Job(2,4,11),
                new Job(8,12,15)
            })
        );
        System.out.println(
            getMaximumCPULoad(new Job[] {
                new Job(1,4,2),
                new Job(2,4,1),
                new Job(3,6,5)
            })
        );
        
    }
}

class JobComparator implements Comparator<Job> {
    @Override
    public int compare(Job job1, Job job2) {
        return job1.end < job2.end ? -1 : 1;
    }
}

class Job {
    public Integer start;
    public Integer end;
    public Integer load;

    public Job(Integer start, Integer end, Integer load) {
        this.start = start;
        this.end = end;
        this.load = load;
    }

    @Override
    public String toString() {
        return "[ " + this.start + ", " + this.end + " -> " + this.load + " ]";
    }

}