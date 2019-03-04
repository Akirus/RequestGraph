package me.alextur.rgraph.logger;

import java.io.Closeable;
import java.util.Date;

/**
 * @author Alex Turchynovich
 */
public interface GraphLogger extends Closeable {

    void initialize() throws GraphLoggerException;

    void log(Date timestamp, int nodeId, int receiverId, int wideRequestId, byte[] payload) throws GraphLoggerException;

}
