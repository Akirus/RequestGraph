package me.alextur.rgraph.logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.function.Supplier;

public class RotationLogger implements GraphLogger{
    private GraphLoggerFactory graphLoggerFactory;
    private int rotationSize;
    private int currentLogEntry;
    private GraphLogger graphLogger;

    private String filePrefix;

    public RotationLogger(final GraphLoggerFactory graphLoggerFactory, final int rotationSize, final String filePrefix) {
        this.graphLoggerFactory = graphLoggerFactory;
        this.rotationSize = rotationSize;
        this.currentLogEntry = 0;
        this.filePrefix = filePrefix;
    }

    protected String getNewFileName() {
        return filePrefix + System.nanoTime() + ".logfile";
    }

    @Override
    public void initialize() throws GraphLoggerException {
    }


    private void refreshLogger() throws GraphLoggerException {
        graphLogger = graphLoggerFactory.createLogger(getNewFileName());
        graphLogger.initialize();
        this.currentLogEntry = 0;
    }

    @Override
    public void log(Date timestamp, int nodeId, int receiverId, int wideRequestId, byte[] payload) throws GraphLoggerException {
        if (graphLogger == null) {
            refreshLogger();
        }

        graphLogger.log(timestamp, nodeId, receiverId, wideRequestId, payload);
        currentLogEntry++;
        if (currentLogEntry >= rotationSize) {
            try {
                this.graphLogger.close();
            } catch (IOException e) {
                throw new GraphLoggerException(e);
            }
            refreshLogger();
        }
    }

    @Override
    public void close() throws IOException {
        this.graphLogger.close();
        this.graphLogger = null;
    }
}
