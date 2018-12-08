package quartz.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import quartz.job.ProperJob;
import quartz.job.SimpleJob;
import quartz.job.TimeJob;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class SimpleScheduler {

    public static void main(String[] args) throws InterruptedException {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // define the job and tie it to our SimpleJob class
            JobDetail job = newJob(SimpleJob.class)
                    .withIdentity("job1", "group1")
                    .build();
            JobDetail properJob = newJob(ProperJob.class)
                    .build();
            JobDetail timeJob = newJob(TimeJob.class)
                    .build();

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()

                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(1)
                            .repeatForever())
                    .build();
            CronTrigger cronTrigger = newTrigger()
                    .withIdentity("trigger2","group2")
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                    .build();
            CronTrigger cronTrigger1 = newTrigger()
                    .withIdentity("trigger3","group3")
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                    .build();



            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(properJob, cronTrigger);
            //scheduler.scheduleJob(timeJob,cronTrigger1);

            // and start it off
            scheduler.start();

            // Sleep for 6 seconds and then shutdown the scheduler
            Thread.sleep(2000);

            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
