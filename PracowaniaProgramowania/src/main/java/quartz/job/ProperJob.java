package quartz.job;

import org.quartz.*;
import collections.MultiMapProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ProperJob implements Job {

    void n() throws FileNotFoundException {
        File Fileright = new File("J:/IdeaProjects/PRA2018-2019/PracowaniaProgramowania/src/main/java/quartz/job/odp.txt");

        PrintWriter pw = new PrintWriter(Fileright);
        pw.write(String.valueOf(MultiMapProject.PeopleList));
        pw.close();
    }

    public ProperJob()
    {
    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException
    {
        System.out.println("Jestem");

        try {
            new ProperJob().n();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

