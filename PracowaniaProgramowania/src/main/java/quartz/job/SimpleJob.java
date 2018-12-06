package quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.Time;
import java.util.Date;

//Simplest job is a class that implements Job interface (execute method)
public class SimpleJob implements org.quartz.Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date t = jobExecutionContext.getFireTime();
        System.out.println("Executing like there is no tomorrow");
        System.out.println(t.toString());
    }
}
