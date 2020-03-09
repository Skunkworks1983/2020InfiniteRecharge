package frc.team1983.util;

import org.junit.Test;

public class SkunkLoggerTest
{
    @Test
    public void test()
    {
        String testOBJ = "test string";

        SkunkLogger logger = new SkunkLogger(testOBJ);

        logger.logMessage("hi naba");
    }





}