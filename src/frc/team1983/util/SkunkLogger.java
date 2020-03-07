package frc.team1983.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class SkunkLogger
{
    private String className;

    public SkunkLogger (Object object)
    {
        this.className = object.getClass().getSimpleName();
    }

    public synchronized void logMessage (String message)
    {
        String now = DateTimeFormatter.ofPattern("MM-dd HH:mm:ss.SSS").
                withZone(ZoneId.systemDefault()).format(Instant.now());

        System.out.println(now + " " + className + " " + message);
    }
}
