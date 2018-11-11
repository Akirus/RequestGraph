package me.alextur.rgraph;

import me.alextur.rgraph.logger.GraphLoggerException;
import me.alextur.rgraph.logger.FileGraphLogger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

/**
 * @author Alex Turchynovich
 */
public class SimpleLoggerTest {

    @Test
    public void testLogging() {
        try (FileGraphLogger fileGraphLogger =  new FileGraphLogger("test.logfile")){
            fileGraphLogger.createFile();
            fileGraphLogger.log(new Date(), 1, 2, 3, new byte[]{});
        } catch (GraphLoggerException | IOException e) {
            Assertions.fail(e);
        }
    }

}
