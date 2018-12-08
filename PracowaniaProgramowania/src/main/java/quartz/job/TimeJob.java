package quartz.job;

import org.quartz.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@PersistJobDataAfterExecution
public class TimeJob implements Job {

    public TimeJob() {
    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        String zajecia1 = "08:15:00";
        String zajecia2 = "10:00:00";
        String zajecia3 = "11:45:00";
        String zajecia4 = "13:45:00";
        String zajecia5 = "15:30:00";
        String zajecia6 = "17:15:00";
        String localTime = String.valueOf(LocalTime.now());
        //localTime = localTime.substring(0, localTime.length() - 4);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        try {
            Date local = format.parse(localTime);
            Date zajecia = format.parse(zajecia1);

            if (local.after(format.parse(zajecia1)) && local.before(format.parse(zajecia2))) {
                zajecia = format.parse(zajecia2);
            }
            if (local.after(format.parse(zajecia2)) && local.before(format.parse(zajecia3))) {
                zajecia = format.parse(zajecia3);
            }
            if (local.after(format.parse(zajecia3)) && local.before(format.parse(zajecia4))) {
                zajecia = format.parse(zajecia4);
            }
            if (local.after(format.parse(zajecia4)) && local.before(format.parse(zajecia5))) {
                zajecia = format.parse(zajecia5);
            }
            if (local.after(format.parse(zajecia5)) && local.before(format.parse(zajecia6))) {
                zajecia = format.parse(zajecia6);
            }
            long diff = zajecia.getTime()-local.getTime();
            System.out.println(zajecia);
            System.out.println((90- (diff / 60000)) + " minut do końca zajęć");
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
