package frc.team1983.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class SkunkLogger
{
    private String className;
    private DateTimeFormatter formatter;

    public SkunkLogger (Object object)
    {
        this.className = object.getClass().getSimpleName();
        this.formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm:ss.SSS").withZone(ZoneId.systemDefault());
    }

    public synchronized void logMessage (String message)
    {
        String now = formatter.format(Instant.now());

        System.out.println(now + " " + className + " " + message);



    }
}
