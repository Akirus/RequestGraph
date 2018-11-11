package me.alextur.rgraph.logger;

/**
 * @author Alex Turchynovich
 */
public class GraphLoggerException extends Exception {

    public GraphLoggerException() {
    }

    public GraphLoggerException(String message) {
        super(message);
    }

    public GraphLoggerException(String message, Throwable cause) {
        super(message, cause);
    }

    public GraphLoggerException(Throwable cause) {
        super(cause);
    }

    public GraphLoggerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
