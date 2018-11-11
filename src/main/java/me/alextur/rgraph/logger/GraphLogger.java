package me.alextur.rgraph.logger;

import java.util.Date;

/**
 * @author Alex Turchynovich
 */
public interface GraphLogger {

    void log(Date timestamp, int nodeId, int receiverId, int wideRequestId, byte[] payload) throws GraphLoggerException;

}
