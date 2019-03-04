package me.alextur.rgraph;

import me.alextur.rgraph.logger.FileGraphLogger;
import me.alextur.rgraph.logger.GraphLoggerException;
import me.alextur.rgraph.logger.RotationLogger;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class RotationLoggerTest {


    @BeforeAll
    public static void beforeAll() {
        try {
            Files.createDirectories(Paths.get("testlogs/"));
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testRotationLogger() {
        RotationLogger rotationLogger = new RotationLogger(FileGraphLogger::new, 1000,
                Paths.get("testlogs/", "log").toString());

        for (int i = 0; i < 10000; i++) {
            try {
                rotationLogger.log(new Date(), 1, 2, 1, new byte[0]);
            } catch (GraphLoggerException e) {
                e.printStackTrace();
            }
        }

    }

}
